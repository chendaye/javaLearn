package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;
import Utils.Dump;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 类似：111  404
 */
public class PathSum {
    public static void main(String[] args) {
        Dump.dump(Solution(null, 0));
    }

    /**
     * todo: 题中的求和 ---》 可以转化为求差
     * @param root
     * @param sum
     * @return
     */
    public static boolean Solution(TreeNode root, int sum) {
        if (root == null)
            return false;
        //todo:判断是不是叶子节点
        if (root.left == null && root.right == null)
            return sum == 0;
        if (Solution(root.left, sum - root.val))
            return true;
        if (Solution(root.right, sum - root.val))
            return true;
        return false;
    }
}
