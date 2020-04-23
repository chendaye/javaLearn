package Algorithm.DataStructure.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树
 * 由于Key需要能够进行比较，所以需要extends Comparable<Key>
 *
 * todo: 二分搜索树可以做的事情
 *      - 找打最大最小值
 *      - 找到前驱 后继
 *      - 找到节点的 floor ceil
 *      - 某节点排名（每个节点多存一个属性：保存以它为根的子树 有几个节点）
 *
 * todo:局限性 二分搜索树可能会退化成一个链表  变成 O(n) 时间复杂度
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

    //todo: 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node  right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            left = node.left;
            right = node.right;
        }
    }

    private Node root;  // 根节点
    private int count;  // 树种的节点个数

    // 构造函数, 默认构造一棵空二分搜索树
    public BST() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 向搜索树种插入节点
     * @param key
     * @param value
     */
    public void insert(Key key, Value value){
        root = insert(root, key, value);
    }

    /**
     * 向根为 root 的节点 插入
     * @param root
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node root, Key key, Value value){
        if (root == null){
            count++;
            return new Node(key, value);
        }
        if (key.compareTo(root.key) == 0){
            root.value = value;
        }else if(key.compareTo(root.key) > 0) {
            insert(root.right, key, value);
        }else {
            insert(root.left, key, value);
        }
        return root;
    }

    /**
     * 在二叉树中查找
     * @param key
     * @return
     */
    public boolean contain(Key key){
        return contain(root, key);
    }

    /**
     * 在root 为根的二叉树中查找
     * @param root
     * @param key
     * @return
     */
    private boolean contain(Node root, Key key){
        if (root == null) return false;

        if (root.key.compareTo(key) == 0){
            return true;
        }else if (root.key.compareTo(key) < 0){
            return contain(root.left, key);
        }else {
            return contain(root.right, key);
        }
    }

    /**
     * 查找值
     * @param key
     * @return
     */
    public Value search(Key key){
        return search(root, key);
    }

    private Value search(Node root, Key key){
        if (root == null) return null;

        if (root.key.compareTo(key) == 0){
            return root.value;
        }else if (root.key.compareTo(key) < 0){
            return search(root.left, key);
        }else {
            return search(root.right, key);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    //前序
    public void preOrder(Node root){
        if (root != null){
            System.out.println(root.value);
            preOrder(root.right);
            preOrder(root.left);
        }
    }

    // 中序
    public void inOrder(Node root){
        if (root != null){
            preOrder(root.right);
            System.out.println(root.value);
            preOrder(root.left);
        }
    }

    //后序
    public void postOrder(Node root){
        if (root != null){
            preOrder(root.right);
            preOrder(root.left);
            System.out.println(root.value);
        }
    }

    //层序
    public void levelOrder(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /**
     * 最小节点
     * @param root
     * @return
     */
    private Node min(Node root){
        if (root == null) return null;
        if (root.left == null)
            return root;
        return min(root.left);
    }

    /**
     * 删除最小值
     * @param root
     * @return
     */
    private Node removeMin(Node root){
        if (root == null) return null;
        //左子树为空 说明当前节点就是最小的
        if (root.left == null){
            Node rightNode = root.right;  // right 存不存在都一样 右子树顶上去
            root.right = null;
            count--;
            return rightNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if (root == null) return null;
        if( node.right == null ){

            Node leftNode = node.left;
            node.left = null;
            count --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * O(logn)
     * 删除任意节点
     * @param root
     * @param key
     * @return
     */
    private Node remove(Node root, Key key){
        if (root == null) return null;
        if(key.compareTo(root.key) < 0){
            root.left = remove(root.left, key);
            return root;
        }else if(key.compareTo(root.key) > 0){
            root.right = remove(root.right, key);
            return root;
        }else {
            if (root.left == null){
                Node rightNode = root.right;
                root.right = null;
                count--;
                return rightNode;
            }

            if (root.right == null){
                Node leftNode = root.left;
                root.left = null;
                count--;
                return leftNode;
            }
            //todo: root 的左右子树都存在； 找到右子树中的最小值 node， 新建一个节点
            Node node = new Node(min(root.right));
            count++;
            //todo: 已经取了 root.right 中的最小值，现在删除它
            node.right = removeMin(root.right); //这一步操作也由 count--
            node.left = root.left;
            root.left = root.right = null;
            count--;
            return node;
        }
    }

    public static void main(String[] args) {

    }
}