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
//        MergeSort(nums,0,nums.length-1,tmpArray);
        QuickSort(nums,0,nums.length - 1);
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
    /**
     * 有序数组合并的方法
     */
    private static void MergeArray(int[] array, int start, int mid, int end, int[] tmpArray) {
        int i = start;//i指向左半组的起始元素
        int j = mid + 1;//j指向右半组的起始元素

        int index = start;//使用这个变量控制临时数组中下标的推进

        //这个循环是有序数组合并的主循环
        while (i <= mid && j <= end) {

            //两个半组中的i和j对应位置上的元素，谁小取谁
            if (array[i] < array[j]) {
                tmpArray[index++] = array[i];
                i++;
            }
            if (array[i] >= array[j]) {
                tmpArray[index++] = array[j];
                j++;
            }
        }
        //到此为止，一定是现有一个组已经完成合并，但是另一个组中应该还有剩余元素
        //另一组中剩余的元素一定比合并好的所有元素都大，将剩余的元素全部落在合并好的组的后面即可
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
        //到这一步为止，在tmpArray的start~end区间内，所有元素是有序的
        //所以可以将tmpArray的start~end区间内所有的元素拷贝到array当中去，覆盖原来的元素
        for (int k = 0; k < end; k++) {
            array[k] = tmpArray[k];
        }
    }

    /*---------------------快速排序-------------------- */
    private static void QuickSort(int[] array, int start, int end) {
        if (start < end) {
            //定义两个游标，i指向排序部分的最前端，j指向排序部分的末尾
            int i = start;
            int j = end;
            int tmp = 0;
            //开始执行震荡操作，震荡操作的条件是两个游标不能碰面，游标碰面，元素归位
            while (i != j) {

                //让i向前走，找到一个比j大的元素
                while (i != j && array[i] <= array[j]) {
                    i++;
                }

                //上面的循环退出，说明array[i] >= array[j]
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;

                //让j往回走，找到一个比i小的元素
                while (i != j && array[j] >= array[i]) {
                    j--;
                }
                //上面的循环退出了，说明array[i] <= array[j]
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
            //整个循环都已经结束，说明i和j已经碰面了，说明array[i]==array[j]，这个元素已经归位
            int middle = i;
            //将这个数组按照已经归位的元素为中心，分为两组，分别执行上面的操作
            QuickSort(array, start, middle - 1);
            QuickSort(array, middle + 1, end);
        }
    }



}
