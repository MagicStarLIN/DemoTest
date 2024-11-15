package com.lcl.test;

import com.lcl.entity.TrackData;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class Test12 {

    public static void main(String[] args) {
        try {

            Thread.sleep(3000);
            System.out.println("start");
            System.out.println(VM.current());
            System.out.println(VM.current().details());
            TrackData trackData = new TrackData(1100006001L, 1, 1, 20231113, null, "test");
            ClassLayout layout = ClassLayout.parseInstance(trackData);
            System.out.println(layout.instanceSize());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
