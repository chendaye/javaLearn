package Algorithm.Interview.NowCoder;

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


    public boolean isSymmetrical(TreeNode pRoot) {
        return pRoot==null || jude(pRoot.left, pRoot.right);
    }

    /**
     * todo: 判断左右 两个子树是不是对称
     * @param node1
     * @param node2
     * @return
     */
    public boolean jude(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;

        if (node1 == null || node2 == null || node1.val != node2.val) return false;

        return jude(node1.left, node2.right) && jude(node1.right, node2.left);
    }
}
