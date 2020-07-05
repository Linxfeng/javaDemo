package com.practice.demo;

/**
 * 背包问题--贪心算法
 *
 * @author lintao
 * @date 2020/7/1
 */
public class ChooseMax {

    public static void main(String[] args) {
        //给定两个数组，价值和占背包容量，顺序相互对应，要求容量不超过背包总容量，求价值最大
        int[] p = {6, 3, 5, 4, 6};//价值
        int[] w = {2, 2, 6, 5, 4};//容量
        int m = 10;

        /**
         * 使用贪心算法：
         * 1、根据物品重量和物品价值，求出每个物品的性价比
         * 2、将性价比进行排序，标记好数组下标
         * 3、将排序好的重量和价值分别存到数组中
         * 4、选取性价比最高的物品添加到背包中，直到背包不能再添加，记录放入的物品在原数组中的下标
         * 5、计算出背包中的物品总价值，同时也能得出对应的物品下标
         */
        int c = charMax(m, p.length, w, p);
        System.out.println(c);


        //获取用户输入
//        Scanner sc = new Scanner(System.in);
//        String str1 = sc.next();//6,3,5,4,6
//        String str2 = sc.next();//2,2,6,5,4
//        String str3 = sc.next();//10
//
//        int[] value = transform(str1);//价值数组
//        int[] weight = transform(str2);//容量数组
//        int[] bag = transform(str3);//总容量
//
//        sc.close();
//
//        int c[][] = charMax2(bag[0], value.length, weight, value);
//        System.out.println(c[c.length-1][c[0].length-1]);
    }

    /**
     * @param m 总容量
     * @param n 数组的长度
     * @param w 容量:[2,2,6,5,4]
     * @param p 价值:[6,3,5,4,6]
     * @return 返回最大价值的值
     */
    private static int charMax(int m, int n, int[] w, int[] p) {
        int c = 0;//用来存储最大值
        //1、根据物品重量和物品价值，求出每个物品的性价比
        double[] wp = new double[n]; //性价比数组
        int[] index = new int[n];   //按性价比排序物品的下标
        for (int i = 0; i < n; i++) {
            wp[i] = (double) p[i] / w[i];
            index[i] = i;
        }
        //2、将性价比进行排序，标记好数组下标
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double temp = 0;
                int te = 0;
                if (wp[i] < wp[j]) {
                    temp = wp[i];
                    wp[i] = wp[j];
                    wp[j] = temp;
                    te = index[i];
                    index[i] = index[j];
                    index[j] = te;
                }
            }
        }
        //3、将排序好的重量和价值分别存到数组中
        int[] w1 = new int[n];
        int[] p1 = new int[n];
        for (int i = 0; i < n; i++) {
            w1[i] = w[index[i]];
            p1[i] = p[index[i]];
        }
        //4、选取性价比最高的物品添加到背包中，直到背包不能再添加，记录放入的物品在原数组中的下标
        for (int i = 0; i < n; i++) {
            if (w1[i] <= m) {
                c += p1[i];
                m -= w1[i];
                System.out.println("放入物品：p[" + p1[i] + "],w[" + w1[i] + "]");
            }
        }
        return c;
    }

    /**
     * @param m 总容量
     * @param n 数组的长度
     * @param w 容量
     * @param p 价值
     * @return
     */
    public static int[][] charMax2(int m, int n, int[] w, int[] p) {
        //c[i][v]表示前i件物品恰放入一个重量为m的背包可以获得的最大价值
        int[][] c = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++)
            c[i][0] = 0;
        for (int j = 0; j < m + 1; j++)
            c[0][j] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，c[i][j]为下列两种情况之一：
                //(1)物品i不放入背包中，所以c[i][j]为c[i-1][j]的值
                //(2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以c[i][j]为c[i-1][j-w[i-1]]的值加上当前物品i的价值
                if (w[i - 1] <= j) {
                    if (c[i - 1][j] < (c[i - 1][j - w[i - 1]] + p[i - 1]))
                        c[i][j] = c[i - 1][j - w[i - 1]] + p[i - 1];
                    else
                        c[i][j] = c[i - 1][j];
                } else
                    c[i][j] = c[i - 1][j];
            }
        }
        return c;
    }

}
