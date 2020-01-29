package com.lcl.testdemos;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MultiTread
 * @date 2020/1/23 9:28 下午
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.err.println("[" + threadInfo.getThreadId() + "] " + threadInfo);
        }

    }
}
