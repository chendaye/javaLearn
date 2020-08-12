package Algorithm.Interview.NowCoder;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
    int inx = 0;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if (pRoot == null || k == 0) return null;
        //todo: 左边有没有 第 k 小的
        TreeNode left =  KthNode(pRoot.left, k);
        if (left != null) return left;
        //todo: 当前是不是 第 k 小的
        if (++inx == k) return pRoot;
        //todo: 右边有没有 第 k 小的
        TreeNode right =  KthNode(pRoot.right, k);
        if (right != null) return right;
        //todo: 左中右都没有
        return null;
    }

}
