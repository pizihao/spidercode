package com.deep.sort;

public class InsertionSort {


    public static void insertionSort(int[] arr) {
        // 从第二个元素开始
        //(索引 0 处的元素已经排序)
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i;

            // 在排序子集 `arr[0…i-1]` 中找到索引 `j`
            // 元素 `arr[i]` 所属的位置
            while (j > 0 && arr[j - 1] > value) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 注意亚阵列 `arr[j…i-1]` 被转移到
            // 右移一位，即 `arr[j+1…i]`

            arr[j] = value;
        }
    }

}
