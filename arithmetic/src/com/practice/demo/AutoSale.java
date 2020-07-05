package com.practice.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 自动售货系统
 *
 * @author lintao
 * @date 2020/7/4
 */
public class AutoSale {

    //商品名称和单价
    private static Map<String, Integer> goods = new HashMap<>(6);
    //商品A1-A6的数量，整形，取值[0, 10]
    //纸币张数，1元 2元 5元 10元，整形，取值[0, 10]
    private static Map<String, Integer> amount = new HashMap<>(10);
    //投币余额，每次投币将累加
    private static int BALANCE = 0;

    static {
        goods.put("A1", 2);
        goods.put("A2", 3);
        goods.put("A3", 4);
        goods.put("A4", 5);
        goods.put("A5", 8);
        goods.put("A6", 6);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] str = line.split(";");
            for (String s : str) {
                if (s.startsWith("r")) {
                    rOrder(s.substring(2));
                } else if (s.startsWith("p")) {
                    pOrder(s.substring(2));
                } else if (s.startsWith("b")) {
                    bOrder(s.substring(2));
                } else if (s.startsWith("c")) {
                    cOrder();
                } else if (s.startsWith("q")) {
                    qOrder(s.substring(2));
                }
            }
        }
        sc.close();
    }

    /**
     * 初始化命令 r
     */
    public static void rOrder(String str) {
        String[] orders = str.split(" ");
        String[] A = orders[0].split("-");
        amount.put("A1", Integer.parseInt(A[0]));
        amount.put("A2", Integer.parseInt(A[1]));
        amount.put("A3", Integer.parseInt(A[2]));
        amount.put("A4", Integer.parseInt(A[3]));
        amount.put("A5", Integer.parseInt(A[4]));
        amount.put("A6", Integer.parseInt(A[5]));
        String[] R = orders[1].split("-");
        amount.put("1元", Integer.parseInt(R[0]));
        amount.put("2元", Integer.parseInt(R[1]));
        amount.put("5元", Integer.parseInt(R[2]));
        amount.put("10元", Integer.parseInt(R[3]));
        System.out.println("S001:Initialization is successful");
    }

    /**
     * 投币命令 p
     */
    public static void pOrder(String str) {
        int p = Integer.parseInt(str);
        if (p != 1 && p != 2 && p != 5 && p != 10) {
            System.out.println("E002:Denomination error");
            return;
        }
        if (p != 1 && p != 2) {
            if ((amount.get("2元") * 2 + amount.get("1元")) < p) {
                System.out.println("E003:Change is not enough, pay fail");
                return;
            }
        }
//        if ((p + BALANCE) > 10) {
//            System.out.println("E004:Pay the balance is beyond the scope biggest");
//            return;
//        }
        if (amount.get("A1") == 0 && amount.get("A2") == 0 && amount.get("A3") == 0
                && amount.get("A4") == 0 && amount.get("A5") == 0 && amount.get("A6") == 0) {
            System.out.println("E005:All the goods sold out");
            return;
        }
        BALANCE += p;
        String ps = p + "元";
        amount.put(ps, amount.get(ps) + 1);
        System.out.println("S002:Pay success,balance=" + BALANCE);
    }

    /**
     * 购买商品 b
     */
    private static void bOrder(String str) {
        Integer A = amount.get(str);
        if (A == null) {
            System.out.println("E006:Goods does not exist");
            return;
        } else if (A == 0) {
            System.out.println("E007:The goods sold out");
            return;
        }
        if (BALANCE < goods.get(str)) {
            System.out.println("E008:Lack of balance");
            return;
        }
        amount.put(str, A - 1);
        BALANCE -= goods.get(str);
        System.out.println("S003:Buy success,balance=" + BALANCE);
    }

    /**
     * 退币命令 c
     */
    public static void cOrder() {
        if (BALANCE == 0) {
            System.out.println("E009:Work failure");
            return;
        }
        //退币张数
        int R1 = 0;
        int R2 = 0;
        int R5 = 0;
        int R10 = 0;
        while (BALANCE >= 10 && amount.get("10元") > 0) {
            BALANCE -= 10;
            ++R10;
        }
        while (BALANCE >= 5 && amount.get("5元") > 0) {
            BALANCE -= 5;
            ++R5;
        }
        while (BALANCE >= 2 && amount.get("2元") > 0) {
            BALANCE -= 2;
            ++R2;
        }
        while (BALANCE >= 1 && amount.get("1元") > 0) {
            BALANCE -= 1;
            ++R1;
        }
        BALANCE = 0;
        System.out.println("1 yuan coin number=" + R1);
        System.out.println("2 yuan coin number=" + R2);
        System.out.println("5 yuan coin number=" + R5);
        System.out.println("10 yuan coin number=" + R10);
    }

    /**
     * 查询命令 q
     */
    public static void qOrder(String str) {
        if ("0".equals(str)) {
            System.out.println("A1 " + goods.get("A1") + " " + amount.get("A1"));
            System.out.println("A2 " + goods.get("A2") + " " + amount.get("A2"));
            System.out.println("A3 " + goods.get("A3") + " " + amount.get("A3"));
            System.out.println("A4 " + goods.get("A4") + " " + amount.get("A4"));
            System.out.println("A5 " + goods.get("A5") + " " + amount.get("A5"));
            System.out.println("A6 " + goods.get("A6") + " " + amount.get("A6"));
        } else if ("1".equals(str)) {
            System.out.println("1 yuan coin number=" + amount.get("1元"));
            System.out.println("2 yuan coin number=" + amount.get("2元"));
            System.out.println("5 yuan coin number=" + amount.get("5元"));
            System.out.println("10 yuan coin number=" + amount.get("10元"));
        } else {
            System.out.print("E010:Parameter error");
        }
    }

}
