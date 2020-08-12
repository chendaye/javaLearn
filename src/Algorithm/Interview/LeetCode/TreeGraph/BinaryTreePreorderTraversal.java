package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 类似： 94 145 341
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {

    }

    /**
     * todo: 递归函数要压入栈中的命令
     */
    class Command{
        String cmd;  // 命令
        TreeNode node; // 命令要操作的节点
        Command(String cmd, TreeNode node){
            this.cmd = cmd;
            this.node = node;
        }
    }


    /**
     * todo：模拟递归算法的执行流程。实现先序遍历的非递归算法
     *
     * todo: 二叉树的递归遍历函数
     *      - 每一个节点执行一次递归函数。递归函数可以分为3条命令
     *      - 打印当前节点的值：print
     *      - 访问左节点：goL
     *      - 访问右节点:goR
     *      - 所以调用一次递归函数就相当于 把当前递归函数 的3条命令 压入栈中[goR_1 goL_1 print_1）
     *      - 然后：print_1 出栈执行，[goR_1 goL_1）
     *      - goL_1出栈 执行 [goR_1），又调用一次递归函数,又进栈3条命令 [goR_1 goR_2 goL_2 print_2)
     *      - 然后 print_2 出栈执行  [goR_1 goR_2 goL_2 )
     *      - goL_2 出栈执行 [goR_1 goR_2 )， 又调用一次递归函数，3条命令进栈  [goR_1 goR_2 goR_3 goL_3 print_3)
     *      - 如此循环直到 栈空
     *
     * todo：关键
     *      - 递归函数 可以拆分成那几条 命令 （此题可分解为2个命令 go print）
     *      - 栈操作顺序
     *
     * todo: 这种非递归算法 特点
     *      - 模拟计算机执行递归 操作栈的过程
     *      - 相比课本中不需要额外的类，直接把节点数据入栈，能更清晰的体现递归的过程
     *      - 很容易将这个算法改造成后序 中序遍历
     *      - 按照这种思路，很容易将其他递归算法改造成非递归算法
     * @param root
     * @return
     */
    public  List<Integer> Solution(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        //命令栈
        Stack<Command> stack = new Stack<>();
        //初始化栈
        stack.push(new Command("go", root));

        while (!stack.empty()){
            // 出栈一条命令
            Command cmd = stack.pop();
            if (cmd.cmd == "print"){
                list.add(cmd.node.val);  // 打印
            }

            if (cmd.cmd == "go"){
                //todo: 先序--先打印，再分左右--所以 left最先入栈 print 最后入栈
                // todo: print 命令操作的对象是 当前节点； go 命令操作对象是左右节点
                if (cmd.node.right != null)
                    stack.push(new Command("go", cmd.node.right));
                if (cmd.node.left != null)
                    stack.push(new Command("go", cmd.node.left));
                stack.push(new Command("print", cmd.node));
            }
        }
        return list;
    }


}



