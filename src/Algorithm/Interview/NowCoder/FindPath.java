package Algorithm.Interview.NowCoder;

import java.util.ArrayList;


/**
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的 所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * todo: 树无非
 *          - 层序遍历
 *          - 先序遍历
 *          - 中序遍历
 *          - 后序遍历
 */
public class FindPath {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<>();

//        dfs(root, target, result, list, 0);
        dfs2(root, target, result, list);
        return result;
    }

    //todo: 递归，先序遍历所有节点， 采用复制当前状态
    private void dfs(TreeNode root,int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int sum){
        if (root == null) return;
        sum += root.val;
        ArrayList<Integer> cur = new ArrayList<>(list); //todo: 用一个日期复制当前状态
        cur.add(root.val);
        if (sum == target && root.left == null && root.right == null)
            result.add(cur);
        if (root.left != null)
            dfs(root.left, target, result, cur, sum);
        if (root.right != null)
            dfs(root.right, target, result, cur, sum);
    }


    private void dfs2(TreeNode root,int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list){
        if (root == null) return;
        target -= root.val; //todo: 相加和=target  ==> target - * == 0
        list.add(root.val);
        if (target == 0 && root.left == null && root.right == null)
            result.add(new ArrayList<Integer>(list)); //todo:
        if (root.left != null)
            dfs2(root.left, target, result, list);
        if (root.right != null)
            dfs2(root.right, target, result, list);
        //todo: 回溯
        list.remove(list.size() - 1);
    }




    public static void main(String[] args) {
        System.out.println("chendaye");
    }
}
