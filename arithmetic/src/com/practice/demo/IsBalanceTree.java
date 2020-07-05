package com.practice.demo;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 判断一个树是否是平衡二叉树
 *
 * @author lintao
 * @date 2020/7/2
 */
public class IsBalanceTree {

    /**
     * 假如有一个二叉树，需要确定它是否是平衡二叉树。
     * 最直接的做法，就是遍历每个结点，借助一个获取树深度的递归函数，
     * 根据该结点的左右子树高度差判断是否平衡，然后递归地对左右子树进行判断。
     */
    HashMap<TreeNode, Integer> map = new HashMap<>();

    /**
     *       1
     *    1      1
     * 1   #   #   1
     * #  1 # # # # #  1
     */
    private String[] treeValue = {
            "1",
            "1", "1",
            "1", "#", "#", "1",
            "#", "1", "#", "#", "#", "#", "#", "1"
    };

    public static void main(String[] args) {
        IsBalanceTree test = new IsBalanceTree();
        TreeNode head = test.buildTree();
        System.out.println(test.isBalanceTree(head));

    }

    private int etDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = etDepth(root.left);
        if (left == -1)
            return -1;
        int right = etDepth(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    public boolean isBalanceTree(TreeNode root) {
        return etDepth(root) != -1;
    }

    // （减枝优化） 判断上层节点的时候，可能会多次判断下层的节点。
    // 用一个map来记录已经判断过的节点，当要重复访问某个子节点时，直接返回当前节点的最大深度。
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            Integer max = map.get(node);
            if (max == null) {
                max = Math.max(height(node.left) + 1, height(node.right) + 1);
                map.put(node, max);
            }
            return max;
        }
    }

    /**
     * 创建一棵树
     */
    public TreeNode buildTree() {
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(treeValue[0]));
        queue.offer(root);
        for (int i = 1; i <= treeValue.length; i = i + 2) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            if (i < treeValue.length && !"#".equals(treeValue[i])) {
                node.left = new TreeNode(Integer.parseInt(treeValue[i]));
                queue.offer(node.left);
            } else {
                queue.offer(null);
            }
            if (i + 1 < treeValue.length && !"#".equals(treeValue[i + 1])) {
                node.right = new TreeNode(Integer.parseInt(treeValue[i + 1]));
                queue.offer(node.right);
            } else {
                queue.offer(null);
            }

        }
        return root;
    }

    //创建一个树
    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
