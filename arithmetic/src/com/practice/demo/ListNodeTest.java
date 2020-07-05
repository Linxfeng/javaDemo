package com.practice.demo;

/**
 * 输入两个链表，找出它们的第一个公共结点
 *
 * @author lintao
 * @date 2020/7/2
 */
public class ListNodeTest {

    /**
     * 两个链表，例如：1-2-5-6-7
     * ----------------4-5-6-7
     * 那么他们的公共节点就是5-6-7，第一个就是5
     */
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 5, 6, 7};
        int[] b = new int[]{4, 5, 6, 7};

        ListNodeTest test = new ListNodeTest();

        ListNode headA = test.createListNode(a);
        ListNode headB = test.createListNode(b);

        ListNode node = test.getIntersectionNode(headA, headB);

        System.out.println(node.val);

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //两个节点相同,即为公共节点
        //如果两个链表长度不相同,
        int sizeA = getSize(headA);
        int sizeB = getSize(headB);
        int steps = 0;
        ListNode pos1 = headA;
        ListNode pos2 = headB;
        //判断两个链表长度
        //让长的链表先走 steps = sizeA - sizeB 步
        if (sizeA > sizeB) {
            steps = sizeA - sizeB;
            for (int i = 0; i < steps; i++) {
                pos1 = pos1.next;
            }
        } else {
            steps = sizeB - sizeA;
            for (int i = 0; i < steps; i++) {
                pos2 = pos2.next;
            }
        }
        //此时 pos1 和 pos2 所指的位置是相同的,进行判断即可
        while (pos1 != null) {
            if (pos1.val == pos2.val) {
                return pos1;
            }
            pos1 = pos1.next;
            pos2 = pos2.next;
        }
        return null;
    }

    private int getSize(ListNode head) {
        int size = 0;
        ListNode pos = head;
        while (pos != null) {
            size++;
            pos = pos.next;
        }
        return size;
    }

    //创建链表
    private ListNode createListNode(int[] a) {
        ListNode root = new ListNode(a[0]);
        convert(root, a, 1);
        return root;
    }

    private void convert(ListNode node, int[] a, int i) {
        if (node.next == null && i < a.length) {
            node.next = new ListNode(a[i]);
            convert(node.next, a, i + 1);
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
