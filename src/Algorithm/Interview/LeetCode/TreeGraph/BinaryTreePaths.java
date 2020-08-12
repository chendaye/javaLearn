package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;
import Utils.Dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *类似： 113 129
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        List<String> solution = Solution(root);
        Dump.iterator(solution);
    }

    /**
     * 函数定义：返回到自己所有子节点的 路径字符串 组成的list
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        //递归终止条件
        if (root == null)
            return list;
        if (root.left == null && root.right == null){
            Integer val = new Integer(root.val);
            list.add(val.toString());
            return list;
        }

        //todo:递归体
        List<String> lefts = binaryTreePaths(root.left);
        for (String left:lefts){
            list.add(root.val+"->"+left);
        }

        List<String> rights = binaryTreePaths(root.right);
        for (String right:rights){
            list.add(root.val+"->"+right);
        }

        return list;

    }

    /**
     * todo：非递归算法
     * @param root
     * @return
     */
    public static   List<String> Solution(TreeNode root){
        ArrayList<String> list = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> record = new HashMap<>();
        if (root == null)
            return list;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("join", root, 0));
        stack.push(new Command("go", root,0));
        while (!stack.empty()){
            //todo:出栈一条命令
            Command top = stack.pop();
            if (top.cmd.equals("go")){
                //todo: 防止兄弟的 孩子序号比自己大
                if (top.node.left != null){
                    stack.push(new Command("join", top.node.left, top.current+2));
                    stack.push(new Command("go", top.node.left, top.current+2));
                }
                if (top.node.right != null){
                    stack.push(new Command("join", top.node.right, top.current+1));
                    stack.push(new Command("go", top.node.right, top.current+1));
                }

            }

            if (top.cmd.equals("join")){
                Dump.dump(top.node.val +"-------"+ top.current);
                if (top.node.left == null && top.node.right == null){
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(top.node.val+"");
                    record.put(top.current, tmp);
                }else{
                    ArrayList<String> res = new ArrayList<>();
                    if (record.containsKey(top.current + 1)){
                        ArrayList<String> tmp = record.get(top.current + 1);
                        for (String s:tmp){
                            res.add(top.node.val+"->"+s);
                        }
                        record.remove(top.current+1);
                    }

                    if (record.containsKey(top.current + 2)){
                        ArrayList<String> tmp = record.get(top.current + 2);
                        for (String s:tmp){
                            res.add(top.node.val+"->"+s);
                        }
                        record.remove(top.current+2);
                    }
                    record.put(top.current, res);
                }
            }
        }
        ArrayList<String> strings = record.get(0);
        list = strings;
        return list;
    }

    /**
     * 递归命令
     * join  go
     */
    static class Command{
        String cmd;
        TreeNode node;
        Integer current;
        Command(String cmd,TreeNode node,Integer current){
            this.cmd = cmd;
            this.node = node;
            this.current = current;
        }
    }
}
