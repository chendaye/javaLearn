package Algorithm.Interview.Offer;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 */
public class IsBalanced {
    public class TreeNode {
        int val = 0;
        TreeDepth.TreeNode left = null;
        TreeDepth.TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    public boolean IsBalanced_Solution(TreeNode root) {
        return true;
    }

    //todo: 计算树的高度
    public int level(TreeNode root){
        return 0;
    }
}
