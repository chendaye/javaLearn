package Algorithm.Interview.LeetCode.TreeGraph;

import java.util.LinkedList;


/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class sumNumbers {
     static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
     }

    /**
     * todo: 采用层序遍历， 入队的时候记录父节点的值
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers(root));
    }


    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        int total = 0;
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<String> queue2 = new LinkedList<>();
        queue1.add(root);
        queue2.add(Integer.toString(root.val));
        while (!queue1.isEmpty()){
            TreeNode node = queue1.pop();

            String str = queue2.pop();
            if (node.left != null){
                queue1.add(node.left);
                queue2.add(str + Integer.toString(node.left.val));
            }
            if(node.right != null){
                queue1.add(node.right);
                queue2.add(str + Integer.toString(node.right.val));
            }
            if(node.left == null && node.right == null){
                total += Integer.parseInt(str);
            }
        }
        return total;
    }

    public static int sumNumbers2(TreeNode root) {
        if (root == null) return 0;
        int total = 0;
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();
        queue1.add(root);
        queue2.add(root.val);
        while (!queue1.isEmpty()){
            TreeNode node = queue1.pop();

            int sum = queue2.pop();
            if (node.left != null){
                queue1.add(node.left);
                queue2.add(sum + node.left.val);
            }
            if(node.right != null){
                queue1.add(node.right);
                queue2.add(sum + node.right.val);
            }
            if(node.left == null && node.right == null){
                total += sum;
            }
        }
        return total;
    }

    //todo: 深度优先搜索
    public int sumNumbers3(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum); // 递归整棵树
        }
    }


}
