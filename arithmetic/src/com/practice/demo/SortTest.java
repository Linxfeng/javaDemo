package com.practice.demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 排序算法
 *
 * @author lintao
 * @date 2020/7/6
 */
public class SortTest {

    /**
     * 1、插入排序：直接插入排序；希尔排序
     * 2、选择排序：简单选择排序；堆排序
     * 3、交换排序：快速排序；冒泡排序
     * 4、归并排序
     * 5、基数排序
     */
    public static void main(String[] args) {
        insertSort(new int[]{5, 1, 4, 2, 3});//直接插入排序
        sheelSort(new int[]{5, 1, 4, 2, 3});//希尔排序
        selectSort(new int[]{5, 1, 4, 2, 3});//简单选择排序
        heapSort(new int[]{5, 1, 4, 2, 3});//堆排序
        quickSort(new int[]{5, 1, 4, 2, 3});//快速排序
        bubbleSort(new int[]{5, 1, 4, 2, 3});//冒泡排序
        System.out.println(Arrays.toString(mergeSort(new int[]{5, 1, 4, 2, 3})));//归并排序
        radixSort(new int[]{5, 1, 4, 2, 3});//基数排序

    }

    /**
     * 插入排序
     * 适用于把新的数据插入到已经排好的数据列中
     */
    public static void insertSort(int[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int insertNum = a[i];//要插入的数
            int j = i - 1;//要插入的位置
            //将要插入的数和前面的逐个比较
            while (j >= 0 && a[j] > insertNum) {
                //如果小于前面的数，则前面的数全部右移一位
                a[j + 1] = a[j];
                j--;
            }
            //插入到指定的位置
            a[j + 1] = insertNum;
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 希尔排序
     * 对于直接插入排序问题，数据量巨大时，可以先分组再排序
     */
    public static void sheelSort(int[] a) {
        int d = a.length;
        //先分组，对组中元素进行插入排序，直到d=0
        while (d != 0) {
            d = d / 2;
            //分的组数
            for (int x = 0; x < d; x++) {
                //从第二组开始，插入排序
                for (int i = x + d; i < a.length; i += d) {
                    int insertNum = a[i];//要插入的数
                    int j = i - d;//要插入的位置
                    //将要插入的数和前面的逐个比较
                    while (j >= 0 && a[j] > insertNum) {
                        //如果小于前面的数，则前面的数全部右移一位
                        a[j + d] = a[j];
                        j -= d;
                    }
                    //插入到指定的位置
                    a[j + d] = insertNum;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 简单选择排序
     * 常用于取序列中最大最小的几个数时
     * 如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序
     */
    public static void selectSort(int[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = a[i];//最小值
            int minIndex = i;//最小值的下标
            for (int j = i + 1; j < length; j++) {
                //除了前面排列好的i个数，后面的数里面找出最小值
                if (a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }
            a[minIndex] = a[i];
            a[i] = min;
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 堆排序
     * 对简单选择排序的优化
     */
    public static void heapSort(int[] a) {
        int len = a.length;
        //构建一个最大堆
        buildMaxHeap(a);
        //循环将堆首位（最大值a[0]）与末位a[len-1]交换，然后在重新调整最大堆
        while (len > 0) {
            int temp = a[len - 1];
            a[len - 1] = a[0];
            a[0] = temp;
            len--;
            adjustMaxHeap(a, len, 0);
        }
        System.out.println(Arrays.toString(a));
    }

    //构建一个最大堆
    private static void buildMaxHeap(int[] a) {
        int len = a.length;
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustMaxHeap(a, len, i);
        }
    }

    //调整成为最大堆
    private static void adjustMaxHeap(int[] a, int len, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (len > i * 2 && a[i * 2] > a[maxIndex]) {
            maxIndex = i * 2;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (len > i * 2 + 1 && a[i * 2 + 1] > a[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            int temp = a[maxIndex];
            a[maxIndex] = a[i];
            a[i] = temp;
            adjustMaxHeap(a, len, maxIndex);
        }
    }

    /**
     * 冒泡排序
     * 两两比较，直到比完，一般不用
     */
    public static void bubbleSort(int[] a) {
        int length = a.length;
        int temp;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 快速排序
     * 要求时间最快
     * 选择一个基准数，小于放左，大于放右，递归如此操作
     */
    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] a, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start;//左边索引
        int j = end;//右边索引
        int temp = a[start];//基准
        while (i < j) {
            while (i < j && temp <= a[j]) {
                j--;
            }
            while (i < j && a[i] <= temp) {
                i++;
            }
            if (i < j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //此时i==j，将基准与start交换位置
        a[start] = a[i];
        a[i] = temp;
        quickSort(a, start, j - 1);
        quickSort(a, j + 1, end);
    }

    /**
     * 归并排序
     * 速度仅次于快速排序，内存少的时候使用，可以进行并行计算的时候使用
     */
    public static int[] mergeSort(int[] a) {
        int len = a.length;
        if (len < 2) {
            return a;
        } else {
            int mid = len / 2;
            //分为左右两个子序列
            int[] left = Arrays.copyOfRange(a, 0, mid);
            int[] right = Arrays.copyOfRange(a, mid, len);
            return merge(mergeSort(left), mergeSort(right));
        }
    }

    //将两段排序好的数组结合成一个排序数组
    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aa = 0;
        int bb = 0;
        for (int i = 0; i < result.length; i++) {
            if (aa >= a.length) {
                result[i] = b[bb++];//先取值，再+1
            } else if (bb >= b.length) {
                result[i] = a[aa++];
            } else if (a[aa] > b[bb]) {
                result[i] = b[bb++];
            } else {
                result[i] = a[aa++];
            }
        }
        return result;
    }

    /**
     * 基数排序
     * 用于大量数，很长的数进行排序时
     */
    public static void radixSort(int[] a) {
        int len = a.length;
        //取出数组中的最大的数
        int max = a[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(a[i], max);
        }
        int maxDigit = 0;//最大数有几位
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        //初始化存放位的数字的桶
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        //按位数遍历塞值
        int mod = 10;
        for (int i = 0; i < maxDigit; i++) {
            for (int value : a) {
                int num = (value % mod) / (mod / 10);
                bucketList.get(num).add(value);
            }
            mod *= 10;
            int index = 0;//将当前位的数字排好序塞回数组a
            for (int j = 0; j < 10; j++) {
                //排序
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    a[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        System.out.println(Arrays.toString(a));
    }

}
