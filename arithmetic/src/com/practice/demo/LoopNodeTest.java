package com.practice.demo;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * @author lintao
 * @date 2020/7/5
 */
public class LoopNodeTest {

    /**
     * 给一个链表，例如：1-2-3-4-5-3...，那么环的入口节点就是3
     */
    public static void main(String[] args) {

        ListNode node = createListNode(new int[]{1, 2, 3, 4, 5});

        ListNode node1 = entryNodeOfLoop(node);

        System.out.println(node1 == null ? "null" : node1.val);//3
    }

    /**
     * 创建一个环状链表
     */
    public static ListNode createListNode(int[] a) {
        ListNode root = new ListNode(a[0]);
        ListNode root1 = new ListNode(a[1]);
        ListNode root2 = new ListNode(a[2]);
        //将链表头放在外面，里面是个环
        root.next = root1;
        root1.next = root2;
        convert(root2, root2, a, 3);
        return root;
    }

    /**
     * 创建一个完全的单向环状链表
     */
    public static void convert(ListNode root, ListNode node, int[] a, int i) {
        if (node.next == null && i < a.length) {
            node.next = new ListNode(a[i]);
            convert(root, node.next, a, i + 1);
        } else {
            node.next = root;
        }
    }

    private static ListNode entryNodeOfLoop(ListNode node) {
        if (node != null && node.next != null && node.next.next != null) {
            //设一个快指针，每次移动两个节点
            ListNode fast = node.next.next;
            //设一个慢指针，每次移动一个结点
            ListNode slow = node.next;
            //让两个指针一起跑，如果有环的话，两个指针必然在环里相遇
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            if (fast.next == null) {
                return null;
            }
            //两个指针相遇后，再来一个新的指针，指向起点，然后速度和慢指针一样，
            ListNode new_slow = node;
            //新指针和慢指针一起跑，当这两个指针相遇的时候，返回当前相遇的结点就得到了环的入口结点
            while (new_slow != slow) {
                new_slow = new_slow.next;
                slow = slow.next;
            }
            return slow;
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
