package com.lcl.codeReview.shopManager;

/**
 * @author liuchanglin
 * @author 75196
 * @version 1.0
 * @ClassName: User
 * @date 2021/1/3 8:03 下午
 * User类
 */
public class User {
    private int ID;
    private String password;
    private String TrueName;

    /**
     * 重载构造方法  对内部进行声明
     *
     * @param ID
     * @param password
     */
    public User(int ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }
}
