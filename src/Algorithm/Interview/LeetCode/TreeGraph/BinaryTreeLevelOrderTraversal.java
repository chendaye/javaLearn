package Algorithm.Interview.LeetCode.TreeGraph;

import Algorithm.DataStructure.TreeNode;

import java.util.*;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 类似： 107 103 199
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    /**
     * todo: 从root开始；每次进入循环，队列中只包含一层的元素，队列的size就是本层的元素个数
     *          出队size 次，出队元素放入新建的list中，同时将出队的元素的左右节点追加到队尾
     *          下次进入循环时，队列中只含有当前层的元素
     * @param root
     * @return
     */
    public List<List<Integer>> Solution(TreeNode root) {
        //todo: List<Integer> 内层 list的索引就是 层数
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;
        //todo: Queue 也是Collection的一员
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
            // start the current level， 每一层用一个list存
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size(); //todo:每一层的个数就是当前队列的长度
            //todo:每一次进循环，队列中只包含一层的元素；前提是循环内，要把当前层的元素出队完毕
            for(int i = 0; i < level_length; ++i) { // 相当于图的邻居节点
                TreeNode node = queue.remove(); // todo:循环结束，当前层也出队完成

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }

    /**
     * todo： 使用单键值对类型
     *
     * todo： 思路 每一个节点都带一个 level，通过level 去除对应的 list放入其中（包含list的创建）
     * @param root
     * @return
     */
    public List<List<Integer>> Solution2(TreeNode root) {
        //todo: List<Integer> 内层 list的索引就是 层数
        ArrayList<List<Integer>> levels = new ArrayList<>(); // 保存每一层的节点
        if (root == null)
            return levels;
        //todo: 层序遍历的队列
        Queue<AbstractMap.SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();
        //todo:根节点入队
        queue.add(new AbstractMap.SimpleEntry<TreeNode,Integer>(root,0));
        while (!queue.isEmpty()){
            //出队
            AbstractMap.SimpleEntry<TreeNode, Integer> top = queue.remove();
            TreeNode node = top.getKey(); // 出队的节点
            Integer level = top.getValue(); // 出队的节点所在的层。从0开始
            //todo: size从1开始  level从0开始， 两者相等的时候size个数 比level少1
            if (levels.size() == level)
                levels.add(new ArrayList<Integer>());
            //todo: 当前层的元素放入当前层list中
            levels.get(level).add(node.val);
            //todo:左右子树入队
            if (node.left != null)
                queue.add(new AbstractMap.SimpleEntry<TreeNode,Integer>(node.left, level+1));
            if (node.right != null)
                queue.add(new AbstractMap.SimpleEntry<TreeNode,Integer>(node.right, level+1));
        }
        return  levels;
    }
}
