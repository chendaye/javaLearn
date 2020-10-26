package Algorithm.Interview.LeetCode.TreeGraph;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class longestUnivaluePath {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    static  int  length = 0;
    public static void main(String args[]){

    }

    /**
     * todo: 返回 root 向左 和向右两个方向中 最长地的符合条件的路径，不包含root
     * @param root
     * @return
     */
    public static int path(TreeNode root){
        if (root == null) return 0;
        int left = path(root.left); // 左边最长路径(边数)
        int right = path(root.right); // 右边最长路径(边数)

        int l = 0; int r = 0;
        if (root.left != null && root.val == root.left.val)
            l  = left + 1;
        if (root.right != null && root.val == root.right.val)
            r = right + 1;

        length = Math.max(length, l + r);
        return Math.max(l, r);
    }
}
