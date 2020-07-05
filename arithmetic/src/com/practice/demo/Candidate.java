package com.practice.demo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 投票统计
 *
 * @author lintao
 * @date 2020/7/4
 */
public class Candidate {

    //记录候选人姓名和对应的票数
    private static Map<String, Integer> candiMap = new LinkedHashMap<>();
    //记录无效的票数
    private static int invalid = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            //设置候选人姓名
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = sc.next();
            }
            addCandidate(names);
            //投票
            int a = sc.nextInt();
            //设置候选人姓名
            String[] vos = new String[a];
            for (int i = 0; i < a; i++) {
                vos[i] = sc.next();
            }
            vote(vos);
            //打印结果
            getVoteResult();
            //情空
            clear();
        }
        sc.close();
    }

    /**
     * 设置候选人姓名
     */
    public static void addCandidate(String[] names) {
        for (String name : names) {
            candiMap.put(name, 0);
        }
    }

    /**
     * 投票
     */
    public static void vote(String[] names) {
        for (String name : names) {
            if (candiMap.containsKey(name)) {
                candiMap.put(name, candiMap.get(name) + 1);
            } else {
                ++invalid;
            }
        }
    }

    /**
     * 获取候选人和对应的票数
     */
    public static void getVoteResult() {
        for (Map.Entry<String, Integer> entry : candiMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("Invalid : " + invalid);
    }

    /**
     * 清空
     */
    public static void clear() {
        candiMap = new LinkedHashMap<>();
        invalid = 0;
    }
}
