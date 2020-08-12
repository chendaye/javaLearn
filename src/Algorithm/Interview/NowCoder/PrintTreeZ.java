package Algorithm.Interview.NowCoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class PrintTreeZ {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean dict = true;
        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size(); //todo: 本层的节点数量
            for (int i = 0; i < size; i++){ //todo: 本层的节点全部出队
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            dict = !dict;
            if (dict){
                int len = list.size() - 1;
                int l = 0, r = len;
                while (l < r){
                    Integer tmp = list.get(l);
                    list.set(l, list.get(r));
                    list.set(r, tmp);
                    l++;r--;
                }
            }

            res.add(list);
        }
        return res;
    }
}
