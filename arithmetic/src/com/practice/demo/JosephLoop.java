package com.practice.demo;

/**
 * 算法之约瑟夫环问题
 *
 * @author lintao
 * @date 2020/7/5
 */
public class JosephLoop {
    /**
     * 1-100个人围成一个圈，每个人有自己的序号，输入一个数字m，1<m<100，
     * 从1开始报数，报到m的人出圈，下一个继续从1开始，输出最后留下的人的序号
     */
    public static void main(String[] args) {
        //这里用数组实现，遍历完从头开始接上，模拟环状链表
        int[] a = creatIntArray(100);
        josephLoopPrint(a, 3);//58 91
    }

    /**
     * 处理数组a，喊到m的人出列，剩下继续，打印剩下的人的序号
     */
    private static void josephLoopPrint(int[] a, int m) {
        int n = a.length;
        //记录喊到哪了，1<j<=m
        int j = 0;
        //记录圈内还剩多少个人
        int left = 100;
        //循环喊，直到剩下的人不够m了，结束
        while (left >= m) {
            //遍历数组中的人，喊到m的标记为0，剩下的从1开始
            for (int i = 0; i < n; i++) {
                //已经出列的人跳过
                if (a[i] == 0) {
                    continue;
                }
                j++;
                //喊到m了，标记出列的人为0，报数清0，剩余人数-1
                if (j == m) {
                    a[i] = 0;
                    j = 0;
                    left--;
                }
            }
        }
        //打印剩下的人的序号
        for (int value : a) {
            if (value != 0) {
                System.out.println(value);
            }
        }
    }

    /**
     * 创建一个从1开始，大小为n的整形数组
     */
    public static int[] creatIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        return a;
    }
}
