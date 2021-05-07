package com.lcl.test;

/**
 * @ClassName CacheLineEffect
 * @Description
 * @Date 2021/5/7 2:47 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class CacheLineEffect {
    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static long[][] arr;

//    public static void main(String[] args) {
//        arr = new long[1024 * 1024][];
//        for (int i = 0; i < 1024 * 1024; i++) {
//            arr[i] = new long[8];
//            for (int j = 0; j < 8; j++) {
//                arr[i][j] = 0L;
//            }
//        }
//        long sum = 0L;
//        long marked = System.currentTimeMillis();
//        for (int i = 0; i < 1024 * 1024; i++) {
//            for(int j =0; j< 8;j++){
//                sum = arr[i][j];
//            }
//        }
//        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
//
//        marked = System.currentTimeMillis();
//        for (int i = 0; i < 8; i+=1) {
//            for(int j =0; j< 1024 * 1024;j++){
//                sum = arr[j][i];
//            }
//        }
//        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
//    }
    /*
     0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0
     ...
     0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 (1024 * 1024 line)
     */

    public static void main(String[] args) {
        int sum = 0;
        arr = new long[1024 * 1024][8];
        // 横向遍历
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i++) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        marked = System.currentTimeMillis();
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}
