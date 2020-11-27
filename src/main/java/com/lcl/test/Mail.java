package mail;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Mail {

    public static void main(String[] args) throws IOException {
        Mail mail = new Mail();
        mail.setSmtpServer("smtp.qq.com");
        mail.setFromMail("1344364****@qq.com");
        mail.addToMail("105648****@qq.com");
        mail.addToMail("long*****@sina.com");
        mail.setUserName("134364****");
        mail.setPassword("*************");
        mail.setSubject("测试邮件");
        mail.setContent("<h1>你好</h1><br/><img src=\"https://www.baidu.com/img/baidu_jgylogo3.gif?v=39549282.gif\" />");
        mail.setShowLog(true);
        mail.send();
        System.out.println("程序结束");
    }

    /** 邮件主题 **/
    private String subject;
    /** 从此地址发出 **/
    private String fromMail;
    /** 用户名 **/
    private String userName;
    /** 登录密码 **/
    private String password;
    /** SMTP 服务器地址 **/
    private String smtpServer;
    /** SMTP 服务器端口（默认：25） **/
    private int smtpPort = 25;
    /** 发送到 toMail 中的所有地址 **/
    private List<String> toMail;
    /** 邮件内容 **/
    private String content;
    /** 是否显示日志 **/
    private boolean showLog;

    public void addToMail(String mail) {
        if (toMail == null)
            toMail = new ArrayList<String>();
        toMail.add(mail);
    }

    public void send() {
        if (smtpServer == null) {
            throw new RuntimeException("smtpServer 不能为空");
        }
        if (userName == null) {
            throw new RuntimeException("userName 不能为空");
        }
        if (password == null) {
            throw new RuntimeException("password 不能为空");
        }
        if (fromMail == null) {
            throw new RuntimeException("fromMail 不能为空");
        }
        if (toMail == null || toMail.isEmpty()) {
            throw new RuntimeException("toMail 不能为空");
        }
        if (content == null || toMail.isEmpty()) {
            throw new RuntimeException("content 不能为空");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            socket = new Socket(smtpServer, smtpPort);
            socket.setSoTimeout(3000);
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException("连接到 " + smtpServer + ":" + smtpPort + " 失败", e);
        }

        BufferedReaderProxy reader = new BufferedReaderProxy(new InputStreamReader(in), showLog);
        PrintWriterProxy writer = new PrintWriterProxy(out, showLog);

        reader.showResponse();
        writer.println("HELO " + smtpServer);
        reader.showResponse();
        writer.println("AUTH LOGIN");
        reader.showResponse();
        writer.println(new String(Base64.encodeBase64(userName.getBytes())));
        reader.showResponse();
        writer.println(new String(Base64.encodeBase64(password.getBytes())));
        reader.showResponse();
        writer.println("MAIL FROM:" + fromMail);
        reader.showResponse();
        for (String mail : toMail) {
            writer.println("RCPT TO:" + mail);
            reader.showResponse();
        }
        writer.println("DATA");
        writer.println("Content-Type:text/html");
        if (subject != null) {
            writer.println("Subject:" + subject);
        }
        writer.println("From:" + fromMail);
        writer.print("To:");
        for (String mail : toMail) {
            writer.print(mail + "; ");
        }
        writer.println();
        writer.println();
        writer.println(content);
        writer.println(".");
        reader.showResponse();
        writer.println("QUIT");
        reader.showResponse();
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("发送邮件完成，关闭 Socket 出错：" + e.getMessage());
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getToMail() {
        return toMail;
    }

    public void setToMail(List<String> toMail) {
        this.toMail = toMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getShowLog() {
        return showLog;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

    static class PrintWriterProxy extends PrintWriter {
        private boolean showRequest;

        public PrintWriterProxy(OutputStream out, boolean showRequest) {
            super(out, true);
            this.showRequest = showRequest;
        }

        @Override
        public void println() {
            if (showRequest)
                System.out.println();
            super.println();
        }

        public void print(String s) {
            if (showRequest)
                System.out.print(s);
            super.print(s);
        }
    }

    static class BufferedReaderProxy extends BufferedReader {
        private boolean showResponse = true;

        public BufferedReaderProxy(Reader in, boolean showResponse) {
            super(in);
            this.showResponse = showResponse;
        }

        public void showResponse() {
            try {
                String line = readLine();
                String number = line.substring(0, 3);
                int num = -1;
                try {
                    num = Integer.parseInt(number);
                } catch (Exception e) {
                }
                if (num == -1) {
                    throw new RuntimeException("响应信息错误 : " + line);
                } else if (num >= 400) {
                    throw new RuntimeException("发送邮件失败 : " + line);
                }
                if (showResponse) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("获取响应失败");
            }
        }

    }
}