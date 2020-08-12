package Algorithm.Interview.NowCoder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
 * 最长路径的长度为树的深度。
 */
public class TreeDepth {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    // todo: 二叉树简化版本 BFS
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cnt = 1; // todo: 记录每一层的节点个数
        int deep = 0;
        while (!queue.isEmpty()){
            //出队
            TreeNode node = queue.remove();
            cnt--;
            //左右节点入队
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
            // 一层已经出队完毕；此时队列中，是下一层的所有节点
            if (cnt == 0) {
                cnt = queue.size(); // 下一层的节点个数
                deep++;
            }
        }
        return deep;
    }


    //todo: 递归解法
    public int TreeDepth2(TreeNode root) {
        if(root==null) return 0;
        int left = TreeDepth(root.left); // 左子树深度
        int right = TreeDepth(root.right); // 友子树深度
        return Math.max(left,right)+1; // 取深度大的一个
    }

    //todo: 一般的 BFS解法。 如果有子节点到父节点的指针要设置 visit 数组
    public int TreeDepth3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int deep = 0;
        while (!queue.isEmpty()){
            // 当前层的 节点个数
            int size = queue.size();
            // 当前层全部出队
            for (int i = 1; i <= size; i++){
                //出队
                TreeNode node = queue.remove();
                //左右节点入队
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 出队完 层高+1
            deep++;
        }
        return deep;
    }

    public static void main(String[] args) {
        ReverseSentence[] a = new ReverseSentence[10];
    }
}
