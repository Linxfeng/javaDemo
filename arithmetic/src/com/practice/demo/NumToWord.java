package com.practice.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 人民币转换：将数字转换为大写汉字输出
 *
 * @author lintao
 * @date 2020/7/4
 */
public class NumToWord {

    public static void main(String[] args) {
        //wordFinal("150021.05");//输出：人民币拾伍万零贰拾壹元零角伍分
        wordFinal("0");//输出：人民币零元整
    }


    /**
     * 判断n有多少位
     */
    public static int hwei(double n) {
        int wei = 1;
        for (int i = 2; n >= 10; i++) {
            wei = i;
            n /= 10;
        }
        return wei;
    }

    /**
     * 将n中的数字按位保存在一个数组中
     */
    public static int[] cutNum(double n) {
        int wei = hwei(n);// 获取n的位数
        int[] num = new int[wei];
        for (int i = 0; i < wei; i++) {
            num[i] = (int) (n % 10);
            n /= 10;
        }
        return num;
    }

    /**
     * 将int类型数组对应转换为大写汉字数组输出
     */
    public static String[] word(int[] num, int wei) {
        String[] word_bz = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] word = new String[wei];

        for (int i = 0; i < wei; i++) {
            switch (num[i]) {
                case 0:
                    word[i] = word_bz[0];
                    break;
                case 1:
                    word[i] = word_bz[1];
                    break;
                case 2:
                    word[i] = word_bz[2];
                    break;
                case 3:
                    word[i] = word_bz[3];
                    break;
                case 4:
                    word[i] = word_bz[4];
                    break;
                case 5:
                    word[i] = word_bz[5];
                    break;
                case 6:
                    word[i] = word_bz[6];
                    break;
                case 7:
                    word[i] = word_bz[7];
                    break;
                case 8:
                    word[i] = word_bz[8];
                    break;
                case 9:
                    word[i] = word_bz[9];
                    break;
            }
        }
        return word;
    }

    /**
     * 将汉字数组加上量级单位数组输出
     */
    public static String[] wordTato(String[] word, int wei) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < wei; i++) {
            switch (i) {
                case 0:
                    list.add(word[i]);
                    break;
                case 1:
                    list.add("拾");
                    list.add(word[i]);
                    break;
                case 2:
                    list.add("佰");
                    list.add(word[i]);
                    break;
                case 3:
                    list.add("仟");
                    list.add(word[i]);
                    break;
                case 4:
                    //list.add("萬");
                    list.add("万");
                    list.add(word[i]);
                    break;
                case 5:
                    list.add("拾");
                    list.add(word[i]);
                    break;
                case 6:
                    list.add("佰");
                    list.add(word[i]);
                    break;
                case 7:
                    list.add("仟");
                    list.add(word[i]);
                    break;
                case 8:
                    list.add("亿");
                    list.add(word[i]);
                    break;
                case 9:
                    list.add("拾");
                    list.add(word[i]);
                    break;
                case 10:
                    list.add("佰");
                    list.add(word[i]);
                    break;
                case 11:
                    list.add("仟");
                    list.add(word[i]);
                    break;
                default:
                    break;
            }
        }
        String[] words = list.toArray(new String[0]);
        return words;
    }

    /**
     * 让数组wordTato中的内容连续传递给resultw[]
     */
    public static String[] resultw(String[] wordTato) {
        String[] resultw = new String[wordTato.length + 1];
        int n123 = 0;
        for (int i = wordTato.length - 1; i >= 0; i--) {
            if (wordTato[i] != null) {
                resultw[n123] = wordTato[i];
                n123++;
            }
        }
        return resultw;
    }

    /**
     * 单独处理小数
     */
    public static String dealDecimal(double n) {
        int a = (int) n;
        //取到两位小数部分
        String dec = new BigDecimal(n - a).setScale(2, BigDecimal.ROUND_HALF_UP).toString().substring(2);
        if ("00".equals(dec)) {
            return "元整";//不含小数
        }
        int[] num = new int[2];
        num[0] = Integer.parseInt(dec.substring(0, 1));
        num[1] = Integer.parseInt(dec.substring(1));
        //将int类型数组对应转换为大写汉字数组
        String[] word = word(num, 2);
        String str = "元";
        if (!"零".equals(word[0])) {
            str = str + word[0] + "角";
        }
        if (!"零".equals(word[1])) {
            str = str + word[1] + "分";
        }
        return str;
    }

    /**
     * 获得最终汉字字符串输出
     */
    public static void wordFinal(String s) {
        double n = new Double(s);

        // 获取n的位数
        int wei = hwei(n);

        //将n中的数字逐位保存在一个数组中
        int[] cutNum = cutNum(n);

        //将int类型数组对应转换为大写汉字数组
        String[] word = word(cutNum, wei);

        //将汉字数组加上量级单位
        String[] wordTato = wordTato(word, wei);

        //去掉零后面的单位
        for (int i = wordTato.length - 1; i >= 0; i--) {
            if ("零".equals(wordTato[i]) && i > 0) {
                if ("萬".equals(wordTato[i - 1]) || "亿".equals(wordTato[i - 1])) {
                    wordTato[i] = null;
                } else if (i != 0) {
                    wordTato[i - 1] = null;
                }
            }
        }

        //让连在一起的零只显示一个
        String[] resultw = resultw(wordTato);

        for (int i = 0; i < resultw.length; i++) {
            if ("零".equals(resultw[i])) {
                if ("零".equals(resultw[i + 1])) {
                    resultw[i] = null;
                }
            }
        }

        //去掉萬、亿前面的零
        for (int i = resultw.length - 1; i >= 0; i--) {
            if ("萬".equals(resultw[i]) || "亿".equals(resultw[i])) {
                if ("零".equals(resultw[i - 1])) {
                    resultw[i - 1] = null;
                }
            }
        }

        //如果萬前面是亿，就去掉萬
        String[] result2 = resultw(resultw);
        for (int i = 0; i < result2.length; i++) {
            if ("萬".equals(result2[i])) {
                if ("亿".equals(result2[i + 1])) {
                    result2[i] = null;
                }
            }
        }

        //顺序理正
        String str = "";
        for (int i = result2.length - 1; i >= 0; i--) {
            if (result2[i] != null) {    //去掉数组中的null
                str += result2[i];
            }
        }

        //如果开头是壹拾，后面接的不是零，则需要去掉壹
        if (str.startsWith("壹拾") && !str.startsWith("壹拾零")) {
            str = str.substring(1);
        }
        //如果只有一位0，保留
        if (str.length() == 0) {
            str = "零";
        }

        //处理小数
        String decimal = dealDecimal(n);

        //特殊处理只有小数的情况
        if ("零".equals(str) && decimal.startsWith("元")) {
            System.out.println("人民币" + decimal.substring(1));
        } else {
            //获得最终汉字字符串输出
            System.out.println("人民币" + str + decimal);
        }
    }
}
