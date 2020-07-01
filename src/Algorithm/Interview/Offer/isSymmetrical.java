package Algorithm.Interview.Offer;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。
 *
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class isSymmetrical {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
    boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null) return true;

        return true;
    }
}
