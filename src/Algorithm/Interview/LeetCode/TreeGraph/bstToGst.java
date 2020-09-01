package Algorithm.Interview.LeetCode.TreeGraph;

/**
 * 给出二叉 搜索 树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class bstToGst {
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
     }

    /**
     * todo: 因为 二叉搜索树的 中序遍历是 递增的 所以
     *          - 用中序遍历来求累加
     * @param root
     * @return
     */
    int sum = 0;
     public TreeNode solution(TreeNode root){
         if (root == null) {
             solution(root.right);
             sum += root.val;
             root.val = sum;
             solution(root.left);
         }
         return root;
     }


     //todo: 测试没通过
    public  TreeNode bstToGst(TreeNode root) {
         if (root == null) return null;
         if (root.right != null) root.val += treeSum(root.right);
         bstToGst(root.left);
         bstToGst(root.right);
        return root;
    }

    //todo: 求一棵树的和
    public int treeSum(TreeNode root){
         // base case
         if (root == null) return 0;
         int sum = root.val;
         if (root.left != null)
             sum += treeSum(root.left);
         if (root.right != null)
             sum += treeSum(root.right);
         return sum;
    }

    public static void main(String[] args) {

    }
}
