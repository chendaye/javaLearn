package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;

/**
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *todo：类似 100 101 222   110
 */
public class InvertBinaryTree {
    public static void main(String[] args) {

    }

    /**
     * todo:递归算法
     * @param root
     * @return
     */
    public static TreeNode Solution(TreeNode root) {
        //todo:终止条件
        if (root == null)
            return null;
        //todo:递归体
        TreeNode left = Solution(root.left);
        TreeNode right = Solution(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
