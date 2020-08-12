package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 *  二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 *  说明: 叶子节点是指没有子节点的节点。
 *
 *  示例：
 *  给定二叉树 [3,9,20,null,null,15,7]，
 *
 *  3
 *  / \
 *  9  20
 *  /  \
 *  15   7
 *  返回它的最大深度 3 。
 *
 *  todo: 类似  111
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {

    }

    /**
     * 递归算法
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        //todo:递归终止条件
        if (root == null)
            return 0;
        //todo:递归体
        int leftDeep = maxDepth(root.left);
        int rightDeep = maxDepth(root.right);
        return Math.max(leftDeep,rightDeep)+1;
    }
}
