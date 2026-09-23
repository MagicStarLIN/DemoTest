package com.lcl.interview;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: OfflineMsgPull
 * @date 2026/9/17 10:57
 */
public class OfflineMsgPull {

    static class Msg{
        long msgId;
        long seq;

        public Msg(long msgId, long seq) {
            this.msgId = msgId;
            this.seq = seq;
        }
    }

    static class Page {
        List<Msg> msgs;
        long nextCursor;
        boolean hasMore;

        public Page(List<Msg> msgs, long nextCursor, boolean hasMore) {
            this.msgs = msgs;
            this.nextCursor = nextCursor;
            this.hasMore = hasMore;
        }
    }

    public static Page pull(List<List<Msg>> sources, long cursorSeq, int limit) {
        if (sources == null || sources.isEmpty()) {
            throw new IllegalArgumentException("cant be null");
        }

        if (limit < 0) {
            throw new IllegalArgumentException("cant be 0");
        }
        List<Msg> allMsg = new ArrayList<>();
        for (List<Msg> source : sources) {
            if (source == null || source.isEmpty()) {
                continue;
            }

            for (Msg msg : source) {
                if (msg == null) {
                    continue;
                }
                if (msg.seq > cursorSeq) {
                    allMsg.add(msg);
                }
            }
        }

        allMsg.sort(Comparator.comparing(msg -> msg.seq));

        Map<Long, Msg> uniqueMap = new LinkedHashMap<>();
        for (Msg msg : allMsg) {
            uniqueMap.put(msg.msgId, msg);
        }

        List<Msg> uniqueMsgs = new ArrayList<>(uniqueMap.values());

        int finalLimit = Math.min(limit, uniqueMsgs.size());

        List<Msg> result = new ArrayList<>(uniqueMsgs.subList(0, finalLimit));

        boolean hasMore = uniqueMsgs.size() > finalLimit;

        long nextCursor = cursorSeq;

        if (!result.isEmpty()) {
            nextCursor = result.getLast().seq;
        }

        return new Page(result, nextCursor, hasMore);
    }


}
