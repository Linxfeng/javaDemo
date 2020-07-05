package com.practice.demo;

import java.util.Scanner;

/**
 * io输入输出示例
 *
 * @author lintao
 * @date 2020/7/5
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int[] nums = new int[a];
            for (int i = 0; i < a; i++) {
                nums[i] = sc.nextInt();
            }
            String str = sc.nextLine();
        }
        sc.close();
    }

    /**
     * io输入：
     * 输入整形
     */
    public static void test2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int[] nums = new int[a];
            for (int i = 0; i < a; i++) {
                nums[i] = sc.nextInt();
            }
            int n = sc.nextInt();
            //sortIntByFlag(nums, n);
        }
    }

    /**
     * 输入两个正整数A和B。输出A和B的最小公倍数。
     */
    public static void test1() {
        Scanner sc = new Scanner(System.in);
        //输入有空格，要用nextLine()方法获取一行（含空格）
        String a1 = sc.next();
        String b1 = sc.next();
        int a = Integer.parseInt(a1);
        int b = Integer.parseInt(b1);
        sc.close();
    }

}
