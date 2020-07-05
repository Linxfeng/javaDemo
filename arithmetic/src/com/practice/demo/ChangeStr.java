package com.practice.demo;

/**
 * 大小写转换
 *
 * @author lintao
 * @date 2020/7/1
 */
public class ChangeStr {

    public static void main(String[] args) {
        String s = ChangeStr.changeStr("127AY8cn32me9$&^hg");
        System.out.println(s);
    }

    /**
     * 将字母大小写翻转，其他字符不处理
     *
     * @param str
     * @return
     */
    private static String changeStr(String str) {
        //return str.toLowerCase();//全部转小写字母
        //return str.toUpperCase();//全部转大写

        //使用ASCII码转换
        char[] chars = str.toCharArray();
        //获取大小写字母之间的差值，小写字母ASCII码较大
        int a = 'a' - 'A';
        for (int i = 0; i < chars.length; i++) {
            //大转小
            if ('A' <= chars[i] && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] + a);
            }
            //小转大
            else if ('a' <= chars[i] && chars[i] <= 'z') {
                chars[i] = (char) (chars[i] - a);
            }
        }
        return new String(chars);
    }
}
