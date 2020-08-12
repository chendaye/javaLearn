package Algorithm.Interview.LeetCode.TreeGraph;

import java.util.ArrayList;
import java.util.List;


public class CloneGraph  {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     *
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     */
    public Node cloneGraph(Node node) {
        Node start_node = new Node(node.val);
        //广度优先遍历
        return new Node();
    }

    public static void main(String[] args) {

    }
}
