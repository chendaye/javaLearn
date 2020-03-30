package Algorithm.Tree;


import java.util.Arrays;

/**
 * 根据先序遍历 和中序遍历重建二叉树
 */
public class Binarytree {
    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};

        reConstructBinaryTree(pre, in);

    }

    // 重建方法
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0){
            return null;  // 树为空
        }else if (pre.length == 1){
            return new TreeNode(pre[0]); // 树只有一个节点
        }else{
            TreeNode root = new TreeNode(pre[0]); // 先序遍历的第一个就是根节点
//            int location = Arrays.binarySearch(in, pre[0]); // 获取根节点在中序中的位置
//            //todo：左子树的节点（中序）  p:1  2   i:2  1 i:1 2
//            int[] nl_in = Arrays.copyOfRange(in, 0, location); // [from, to)
//            //todo: 左子树的节点（先序）
//            int[] tmp1 = {pre[1]};
//            int[] nl_pre = (nl_in.length+1 >= pre.length) ? tmp1 : Arrays.copyOfRange(pre,1, nl_in.length+1);
//
//            //todo: 右子树的节点（中序）
//            int[] tmp2 = {in[1]};
//            int[] nr_in = (location + 1 >= in.length) ? tmp2 :  Arrays.copyOfRange(in, location+1, in.length);
//            //todo: 右子树的节点（先序）
//            int[] nr_pre = Arrays.copyOfRange(pre,nl_in.length+1, pre.length);
//            //todo:构建左子树
//            root.left = reConstructBinaryTree(nl_pre, nl_in);
//            //todo:构建右子树
//            root.right = reConstructBinaryTree(nr_pre, nr_in);
            return root;

        }

    }
}
