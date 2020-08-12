package Algorithm.Interview.NowCoder;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
 * 序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
 */
public class SerializeTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
    //todo: 序列化
    String Serialize(TreeNode root) {
        StringBuilder tree = new StringBuilder();
        order(root, tree);
        return tree.toString();
    }

    //todo: 先序
    public void order(TreeNode root, StringBuilder tree){
        if (root == null) {
            tree.append("#!");
            return;
        }
        tree.append(root.val + "!");
        order(root.left, tree);
        order(root.right, tree);
    }

    //todo: 反序列化 先序
    int index = -1;
    TreeNode Deserialize(String str) {
        String[] nodes = str.split("!"); //todo: 按 ！ 分割节点
        index++;//todo:索引每次加一, 每次反序列化一个值
        if (index == nodes.length || nodes[index].equals("#")) return null;

        /**
         * todo: 递归
         *      - 当前函数处理的是当前值，
         *      - 调用的递归函数 处理的是一个子问题
         */
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index])); //todo: 不是反序列化整棵树，是反序列化当前值
        root.left = Deserialize(str);
        root.right = Deserialize(str);
        return root;
    }

}
