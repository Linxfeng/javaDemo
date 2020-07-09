package com.practice.demo;

/**
 * 转换函数，将阿拉伯数字转换为中文字符
 *
 * @author lintao
 * @date 2020/7/9
 */
public class ChangeNum2Word {

    public static void main(String[] args) {
        System.out.println(transformNum("5000010012"));//五十亿零一万零一十二
    }

    /**
     * 转换函数，将阿拉伯数字转换为中文字符
     */
    public static String transformNum(String num) {
        //先将数字都转化为汉字
        String word = toWord(num);
        //给汉字加上单位
        return addUnit(word);
    }

    /**
     * 数字转汉字
     */
    public static String toWord(String num) {
        StringBuilder result = new StringBuilder();
        int length = num.length();
        String[] words = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        for (int i = 0; i < length; i++) {
            int index = num.charAt(i) - '0';
            result.append(words[index]);
        }
        return result.toString();
    }

    /**
     * 给汉字加上单位
     */
    public static String addUnit(String word) {
        StringBuilder result = new StringBuilder();
        String[] units = new String[]{"", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿"};
        char[] words = word.toCharArray();
        int length = words.length;
        int j = length - 1;//units单位的下标
        for (int i = 0; i < length; i++) {
            //处理'零'
            if ('零' == words[i]) {
                --j;
                if (i < length - 1 && '零' == words[i + 1]) {
                    continue;
                }
                result.append(words[i]);
            } else {
                result.append(words[i]);
                if (i < length - 1) {
                    result.append(units[j]);
                }
                --j;
            }
        }
        return result.toString();
    }


}
