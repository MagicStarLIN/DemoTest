package com.lcl.arithmetic;

import java.util.Arrays;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: BubbleSort
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-15 16:16
 */
public class SortAlg {
    public static void main(String[] args) {
        int[] nums = {23,31,54,36,23,324,123,41,234,1,5,6543};
        int[] tmpArray = new int[nums.length];
//        BubbleSort(nums);
//        SelectSort(nums);
//        InsertSort(nums);
        MergeSort(nums,0,nums.length-1,tmpArray);
        System.out.println(Arrays.toString(nums));
    }
    private static void Swap(int A[], int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private static void BubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0 ; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    Swap(nums,j,j+1);
                }
            }
        }
    }

    private static void SelectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i ; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    Swap(nums,i,j);
                }
            }
        }
    }

    private static void InsertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0 && nums[j] > nums[j+1]; j--) {
                Swap(nums,j,j+1);
            }

        }
    }

    /*---------------------归并排序-------------------- */
    private static void MergeSort(int[] array, int start, int end, int[] tmpArray) {
        if (start < end) {
            int mid = (start + end) / 2;
            MergeSort(array, start, mid, tmpArray);
            MergeSort(array, mid + 1, end, tmpArray);

            MergeArray(array,start,mid,end,tmpArray);
        }
    }

    private static void MergeArray(int[] array, int start, int mid, int end, int[] tmpArray) {
        int i = start;
        int j = mid + 1;

        int index = start;

        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tmpArray[index++] = array[i];
                i++;
            }
            if (array[i] >= array[j]) {
                tmpArray[index++] = array[j];
                j++;
            }
        }
        if (i <= mid) {
            while (i <= mid) {
                tmpArray[index] = array[i];
                i++;
            }
        }
        if (j <= end) {
            while (j <= end) {
                tmpArray[index++] = array[j];
                j++;
            }
        }
        System.out.println(array.length);
        System.out.println(end);
        for (int k = 0; k < end; k++) {
            array[k] = tmpArray[k];
        }
    }
}
