package com.lcl.interview;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ReadSyncCount
 * @date 2026/9/15 15:30
 */
public class ReadSyncCount {

    static class Message {
        private String appId;
        private String convId;
        private String msgKey;
        private long version;
        private long seq;
        private long senderId;
        private boolean deleted;

        public Message(String appId, String convId, String msgKey, long version, long seq, long senderId, boolean deleted) {
            this.appId = appId;
            this.convId = convId;
            this.msgKey = msgKey;
            this.version = version;
            this.seq = seq;
            this.senderId = senderId;
            this.deleted = deleted;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getConvId() {
            return convId;
        }

        public void setConvId(String convId) {
            this.convId = convId;
        }

        public String getMsgKey() {
            return msgKey;
        }

        public void setMsgKey(String msgKey) {
            this.msgKey = msgKey;
        }

        public long getVersion() {
            return version;
        }

        public void setVersion(long version) {
            this.version = version;
        }

        public long getSeq() {
            return seq;
        }

        public void setSeq(long seq) {
            this.seq = seq;
        }

        public long getSenderId() {
            return senderId;
        }

        public void setSenderId(long senderId) {
            this.senderId = senderId;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }

    static class Read {
        private String appId;
        private String convId;
        private long readSeq;

        public Read(String appId, String convId, long readSeq) {
            this.appId = appId;
            this.convId = convId;
            this.readSeq = readSeq;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getConvId() {
            return convId;
        }

        public void setConvId(String convId) {
            this.convId = convId;
        }

        public long getReadSeq() {
            return readSeq;
        }

        public void setReadSeq(long readSeq) {
            this.readSeq = readSeq;
        }
    }

    static class Summary {
        private String appId;
        private String convId;
        private long lastReadSeq;
        private long latestSeq;
        private long unreadCount;

        public Summary(String appId, String convId, long lastReadSeq, long latestSeq, long unreadCount) {
            this.appId = appId;
            this.convId = convId;
            this.lastReadSeq = lastReadSeq;
            this.latestSeq = latestSeq;
            this.unreadCount = unreadCount;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getConvId() {
            return convId;
        }

        public void setConvId(String convId) {
            this.convId = convId;
        }

        public long getLastReadSeq() {
            return lastReadSeq;
        }

        public void setLastReadSeq(long lastReadSeq) {
            this.lastReadSeq = lastReadSeq;
        }

        public long getLatestSeq() {
            return latestSeq;
        }

        public void setLatestSeq(long latestSeq) {
            this.latestSeq = latestSeq;
        }

        public long getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(long unreadCount) {
            this.unreadCount = unreadCount;
        }
    }

    public static List<Summary> solve(long targetUser, List<Message> messages, List<Read> reads) {

        Map<String, Message> latestMsg = new HashMap<>();
        Set<String> convsations = new HashSet<>();
        // 1.最新版本
        for (Message message : messages) {
            convsations.add(conv(message.getAppId(), message.getConvId()));
            String msgKey = messageKey(message.getAppId(), message.getConvId(), message.getMsgKey());
            Message latest = latestMsg.get(msgKey);
            if (latest == null || latest.getVersion() < message.getVersion()) {
                latestMsg.put(msgKey, message);
            }
        }

        // 2. read seq max
        Map<String, Long> readSeq = new HashMap<>();
        for (Read read : reads) {
            String convKey = conv(read.getAppId(), read.getConvId());
            convsations.add(convKey);
            long latestReadSeq = readSeq.getOrDefault(convKey, 0L);
            if ((latestReadSeq == 0L && read.getReadSeq() != 0L) || read.getReadSeq() > latestReadSeq) {

                readSeq.put(convKey, read.getReadSeq());
            }
        }

        // 3. msg seq max
        Map<String, Long> latestSeqMap = new HashMap<>();
        for (Message message : messages) {
            if (message.isDeleted()) {
                continue;
            }
            String convId = conv(message.getAppId(), message.getConvId());
            convsations.add(convId);

            long lastSeq = latestSeqMap.getOrDefault(convId, 0L);
            if ((lastSeq == 0L && message.getSeq() != 0L) || message.getSeq() > lastSeq) {
                latestSeqMap.put(convId, message.getSeq());
            }

        }


        // 4.unreadCount
        Map<String, Long> unreadCountMap = new HashMap<>();
        for (Message value : latestMsg.values()) {
            if (value.isDeleted() || targetUser == value.getSenderId()) {
                continue;
            }

            String convKey = conv(value.getAppId(), value.getConvId());

            long lastReadSeq = readSeq.getOrDefault(convKey, 0L);

            long unreadCount = unreadCountMap.getOrDefault(convKey, 0L);
            if (value.seq > lastReadSeq) {
                unreadCountMap.put(convKey, unreadCount + 1);
            }

        }

        List<Summary> result = new ArrayList<>();
        for (String convsation : convsations) {
            String[] appIdAndConvId = convsation.split("_");
            String appId = appIdAndConvId[0];
            String convId = appIdAndConvId[1];

            result.add(new Summary(appId, convId,
                    readSeq.getOrDefault(convId, 0L),
                    latestSeqMap.getOrDefault(convId, 0L),
                    unreadCountMap.getOrDefault(convId, 0L)
            ));

        }


        return result;

    }


    private static String conv(String appId, String convId) {
        return appId + "_" + convId;
    }

    private static String messageKey(String appId, String convId, String msgKey) {
        return appId + "_" + convId + "_" + msgKey;
    }



}
