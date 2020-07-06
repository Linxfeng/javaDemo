package com.practice.demo;

import java.math.BigDecimal;
import java.util.*;

/**
 * 简单算法联系
 *
 * @author lintao
 * @date 2020/7/3
 */
public class ArithTest {

    public static void main(String[] args) {
        //test1();
        //System.out.println(getCubeRoot(12));//输出2.3
        //System.out.println(reverStr("a bcd"));//输出dcb a
        //averageInt(new String[]{"-11", "-1", "-4"});//输出2和1.0
        //subStr();
        //getMaxSteps(new int[]{4, 0, 3, 7, 2, 8}, 6);//输出4
        //countStr(new char[]{'c', 'b', 'a', 'b', 'a'});//abc
        //countStr("8v26ktzk069lm400061m0".toCharArray());//06km12489ltvz
        //sortIntByFlag(new int[]{1, 6, 4, 12, 9}, 0);//1 4 6 9 12
        //arith(3);//15
        //calAutomorphicNumbers(2000);//8
        //showNumbers("Jkdi234klowe90a3");//Jkdi*234*klowe*90*a*3*
        //System.out.println(sliptIntArray(new int[]{2, -3, 5, 5, -4, -4, 5}));//false
        //findMaxNum("abcd12345ed125ss123058789");//123058789,9
        //findMaxNum("8749r6k4nugdm04p5b1yhegi8hiq3937");//87493937,4
        //System.out.println(checkerboard(6-1, 3-1));


    }

    /**
     * 棋盘走法：
     * 给定m * n的棋盘，从左上角走到右下角，一共多少种走法
     * 示例：2,2 -> 2; 3,3 -> 6, 4,3 -> 10, 6,3 -> 21
     * 递归解法
     */
    public static int checkerboard(int m, int n) {
        //从(m, n)走到(0,0)，每一步都有两种选择，总共走的步数是m+n
        if (m == 0 && n == 0) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        return checkerboard(m - 1, n) + checkerboard(m, n - 1);
    }

    /**
     * 找出连续最长数字串
     * 如果有相同长度的串，则要一块儿输出，但是长度还是一串的长度
     */
    public static void findMaxNum(String str) {
        int max = 0;//记录最大长度
        //记录最长的数字串，采用list的有序性，先发现的先打印
        List<String> maxList = new ArrayList<>();
        String[] ss = str.split("[^0-9]");
        for (String s : ss) {
            if (s.length() >= max) {
                max = s.length();
                maxList.add(s);
            }
        }
        StringBuilder longStr = new StringBuilder();
        for (String s : maxList) {
            if (s.length() == max) {
                longStr.append(s);
            }
        }
        System.out.println(longStr.toString() + "," + max);
    }

    /**
     * 传入一个int型数组，返回该数组能否分成两组，使得两组中各元素加起来的和相等，
     * 并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数）
     * 能满足以上条件，返回true；不满足时返回false。
     */
    public static boolean sliptIntArray(int[] nums) {
        int n = nums.length;
        int[] a = new int[n];//存放5的倍数
        int ai = 0;
        int[] b = new int[n];//存放3的倍数
        int bi = 0;
        int[] c = new int[n];//存放剩余的数
        int ci = 0;
        for (int num : nums) {
            if (num % 5 == 0 && num != 0) {
                a[ai] = num;
                ++ai;
            } else if (num % 3 == 0 && num != 0) {
                b[bi] = num;
                ++bi;
            } else if (num != 0) {
                c[ci] = num;
                ++ci;
            }
        }
        int left = Math.abs(countIntArray(a) - countIntArray(b));
        //使用递归，求解剩余的数组分配给两个数组的情况
        return deal(0, ci, c, 0, left);
    }

    /**
     * @param i     计数
     * @param count other数组的个数
     * @param other 剩余的那个数组
     * @param j     分配之后的剩余值
     * @param sum   两个数组的差值
     * @return
     */
    private static boolean deal(int i, int count, int[] other, int j, int sum) {
        if (i == count) {
            return Math.abs(j) == sum;
        } else {
            return (deal(i + 1, count, other, j + other[i], sum) || deal(i + 1, count, other, j - other[i], sum));
        }
    }

    /**
     * 求一个int数组的元素和
     */
    public static int countIntArray(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        return count;
    }


    /**
     * 表示数字、字符串
     * 字符中所有出现的数字前后加上符号“*”，其他字符保持不变
     */
    public static void showNumbers(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        //判断字符是否是数字
        if (Character.isDigit(chars[0])) {
            sb.append("*").append(chars[0]);
        } else {
            sb.append(chars[0]);
        }
        for (int i = 1; i < chars.length; i++) {
            //当前非数字，上一个是数字，则当前的前面+*
            if (!Character.isDigit(chars[i]) && Character.isDigit(chars[i - 1])) {
                sb.append("*").append(chars[i]);
            }
            //当前是数字，上一个非数字，则当前的前面+*
            else if (Character.isDigit(chars[i]) && !Character.isDigit(chars[i - 1])) {
                sb.append("*").append(chars[i]);
            } else {
                sb.append(chars[i]);
            }
        }
        if (Character.isDigit(chars[chars.length - 1])) {
            sb.append("*");
        }
        System.out.println(sb.toString());
    }

    /**
     * 自守数：
     * 自守数是指一个数的平方的尾数等于该数自身的自然数。例如：25^2 = 625，76^2 = 5776，9376^2 = 87909376。
     * 请求出n以内的自守数的个数。
     * 提示：除0除以外，其他的自守数必定基于1、5、6，结尾的数
     */
    public static int calAutomorphicNumbers(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % 10 == 0 || i % 10 == 1 || i % 10 == 5 || i % 10 == 6) {
                String temp = String.valueOf(i * i);
                if (temp.endsWith(String.valueOf(i))) {
                    ++count;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * 等差数列：
     * 求等差数列前N项和
     * 公式：Sn = n*a1 + n(n-1)d/2
     * <p>
     * 例题：等差数列2，5，8，11，14...输入一个正整数n，输出前n项和
     */
    public static void arith(int n) {
        int d = 3;//公差d
        int s = 2 * n + n * (n - 1) * d / 2;
        System.out.println(s);
    }

    /**
     * 排序：
     * 输入整型数组和排序标识，对其元素按照升序或降序进行排序
     *
     * @param nums 给定整型数组
     * @param flag 排序标志：0表示按升序，1表示按降序
     */
    public static void sortIntByFlag(int[] nums, int flag) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        if (flag == 0) {
            //升序
            Collections.sort(list);
        } else {
            //降序
            list.sort(Collections.reverseOrder());
        }
        System.out.print(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            System.out.print(" ");
            System.out.print(list.get(i));
        }
        System.out.println();
    }

    /**
     * 字符统计：
     * 输入一串字符串，按照统计个数由多到少输出,如果统计的个数相同，则按照ASII码由小到大排序输出
     */
    public static void countStr(char[] str) {
        //数组先排序，保证相同字符相邻
        Arrays.sort(str);
        //用map来存储每个字符和其出现的个数，使用TreeMap的默认降序特性来自动排序key
        Map<Character, Integer> map = new TreeMap<>();
        map.put(str[0], 1);
        //遍历数组到map中，判断与前一个字符是否相同
        for (int i = 1; i < str.length; i++) {
            if (str[i] == str[i - 1]) {
                map.put(str[i], map.get(str[i]) + 1);
            } else {
                map.put(str[i], 1);
            }
        }
        //根据value（出现的次数）排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));//降序
        //list.sort(Map.Entry.comparingByValue());//升序
        for (Map.Entry<Character, Integer> entry : list) {
            System.out.print(entry.getKey());
        }
    }

    /**
     * Redraiment的走法
     * 转化成求最长递增子序列
     *
     * @param arr 数组
     * @param n   数组长度
     * @return 最长递增子序列的size
     */
    public static int getMaxSteps(int[] arr, int n) {
        //用来存放递增子序列的长度
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 找到dp数组中的最大值即可
        int max = 0;
        for (int value : dp) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println(max);
        return max;
    }

    /**
     * 字符串分割
     */
    public static void subStr() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String str = sc.next();
                while (str.length() % 8 != 0) {
                    str += "0";
                }
                for (int a = 0; a < str.length(); a += 8) {
                    System.out.println(str.substring(a, a + 8));
                }
            }
        }
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
        int c = getLcm(a, b);
        System.out.print(c);
    }

    /**
     * 求两个数的最小公倍数
     */
    public static int getLcm(int a, int b) {
        return a * b / getGcd(a, b);
    }

    /**
     * 求两个数的最大公约数
     */
    public static int getGcd(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if (max % min == 0) {
            return min;
        } else {
            return getGcd(min, max % min);
        }
    }

    /**
     * 求一个double数的立方根--牛顿迭代法
     */
    public static double getCubeRoot(double input) {
        if (input == 0) {
            return 0;
        }
        double a = input;
        double b = (2 * a + input / a / a) / 3;
        while (Math.abs(b - a) > 0.000001) {
            a = b;
            b = (2 * a + input / a / a) / 3;
        }
        return new BigDecimal(b).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 输出逆序的字符串
     */
    public static String reverStr(String str) {
        int size = str.length();
        char[] temp = new char[size];
        int j = 0;
        for (int i = size - 1; i >= 0; i--) {
            temp[j] = str.charAt(i);
            ++j;
        }
        return new String(temp);
    }

    /**
     * io输入n，和n个整数
     */
    public static void inputInt() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
        }
    }

    /**
     * 记负均正：
     * 输入任意个整数
     * 输出负数个数以及所有非负数的平均值
     */
    public static void averageInt(String[] str) {
        int a = 0;//负数个数
        int b = 0;//非负数个数
        double average = 0;//非负数的平均值
        for (int i = 0; i < str.length; i++) {
            int c = Integer.parseInt(str[i]);
            if (c < 0) {
                ++a;
            } else {
                average += Math.abs(c);
                ++b;
            }
        }
        System.out.println(a);
        if (b == 0) {
            average = 0.0;
        } else {
            average = new BigDecimal(average / b).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        System.out.println(average);
    }

}
