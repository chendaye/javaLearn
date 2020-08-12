package Algorithm.Interview.LeetCode.TreeGraph;


import Algorithm.DataStructure.TreeNode;

import java.util.Arrays;


/**
 * 根据先序遍历 和中序遍历重建二叉树
 */
public class Binarytree {
    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
//        int[] pre = {1,2};
//        int[] in = {2,1};
//        int[] pre = {1,2};
//        int[] in = {1,2};

        reConstructBinaryTree_bak(pre, in);
//        reConstructBinaryTree(pre, in);

    }

    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = reConstruct(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    private static TreeNode reConstruct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue); //todo: 根节点
        int location = Arrays.binarySearch(in, rootValue);

        for(int i = inStart; i <= inEnd; i ++) {  // todo: 根节点在中序中的下标
            if(in[i] == rootValue) {
                root.left = reConstruct(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
                root.right = reConstruct(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
            }
        }
        return root;
    }


    // 重建方法
    public static TreeNode reConstructBinaryTree_bak(int [] pre,int [] in) {
        if(pre.length == 0){
            return null;  // 树为空
        }else if (pre.length == 1){
            return new TreeNode(pre[0]); // 树只有一个节点
        }else{
            TreeNode root = new TreeNode(pre[0]); // 先序遍历的第一个就是根节点
            //todo: 获取根节点在中序中的位置
            int location = 0;
            for (int i = 0; i < in.length; i++) {
                if (in[i] == pre[0]) {
                    location = i;                  //字符串时，换为 equals
                }
            }

            //todo：左子树的节点（中序）
            int[] nl_in = Arrays.copyOfRange(in, 0, location); // [from, to)
            //todo: 右子树的节点（中序）
            int[] nr_in = Arrays.copyOfRange(in, location+1, in.length);

            //todo: 左子树的节点（先序）
            int[] nl_pre = Arrays.copyOfRange(pre,1, nl_in.length+1);
            //todo: 右子树的节点（先序）
            int[] nr_pre = Arrays.copyOfRange(pre,nl_in.length+1, pre.length);

            //todo:构建左子树
            root.left = reConstructBinaryTree(nl_pre, nl_in);
            //todo:构建右子树
            root.right = reConstructBinaryTree(nr_pre, nr_in);
            return root;

        }

    }
}
