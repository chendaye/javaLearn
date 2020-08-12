package Algorithm.Interview.NowCoder;

import java.util.ArrayList;

/**
 *输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Convert {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    //todo: 最大最小的节点左右指针都为空
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        preOrder(pRootOfTree, treeNodes); // 有序集合
        TreeNode p;
        TreeNode q;
        for (int i = 0; i < treeNodes.size() - 1; i++){
            p = treeNodes.get(i);
            q = treeNodes.get(i + 1);
            p.right = q;
            q.left = p;
        }
        return treeNodes.get(0);
    }

    //todo:先序遍历
    private void preOrder(TreeNode pRootOfTree, ArrayList<TreeNode> treeNodes){
        if (pRootOfTree == null) return;
        preOrder(pRootOfTree.left, treeNodes);
        treeNodes.add(pRootOfTree);
        preOrder(pRootOfTree.right, treeNodes);
    }



    //todo: 线索化二叉树方式
    TreeNode pre=null;  // 全局变量每次将前驱节点记录
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        //todo: 右子树转化为链表
        Convert2(pRootOfTree.right);

        if (pre != null){
            pRootOfTree.right = pre;
            pre.left = pRootOfTree;
        }
        pre = pRootOfTree;

        //todo: 转化左子树
        Convert2(pRootOfTree.left);
        return pre;
    }

}
