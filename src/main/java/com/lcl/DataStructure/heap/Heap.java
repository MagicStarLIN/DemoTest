package com.lcl.DataStructure.heap;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Heap
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-16 13:41
 */
public class Heap {
    private int[] a;//数组，从下标为1开始存储数据
    private int n;//堆的大小
    private int count;//堆中现在已经存储的数据个数

    public Heap(int capacity) {
       a = new int[capacity+1];
       n = capacity;
       count = 0;
    }

    public void insert(int data) {
        if (count >= n) {
            return;//堆已满
        }
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
        }
    }
    private void swap(int[] a, int i, int i1) {
        int temp = i1;
        a[i1] = a[i];
        a[i] = a[temp];
    }

    //移除堆顶元素
    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];//将最后一个元素 置于堆顶
        --count;
        heapify(a,count,1);
    }

    private void heapify(int[] a, int n, int i) {
        int maxPos = i;
        while (true) {
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    public void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heapify(a, n, i);
        }
    }

    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, k, 1);
            k--;
            heapify(a,k,1);
        }
    }
}