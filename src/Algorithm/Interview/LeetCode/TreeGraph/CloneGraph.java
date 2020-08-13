package Algorithm.Interview.LeetCode.TreeGraph;

import java.util.*;

/**
 *
 作者：LeetCode-Solution
 链接：https://leetcode-cn.com/problems/clone-graph/solution/ke-long-tu-by-leetcode-solution/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
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
     *
     * 下面是Java中Queue的一些常用方法：
     * add         增加一个元索                      如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     * offer       添加一个元素并返回true        如果队列已满，则返回false
     *
     * poll         移除并返问队列头部的元素     如果队列为空，则返回null
     * remove   移除并返回队列头部的元素     如果队列为空，则抛出一个NoSuchElementException异常
     *
     * element  返回队列头部的元素              如果队列为空，则抛出一个NoSuchElementException异常
     * peek       返回队列头部的元素              如果队列为空，则返回null
     *
     * put         添加一个元素                       如果队列满，则阻塞
     * take        移除并返回队列头部的元素
     */
    public Node cloneGraph(Node node) {
        Node start_node = new Node(node.val);
        HashMap<Node, Boolean> visited = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> queue_new = new LinkedList<>();
        // 入队
        queue.add(node);
        queue_new.add(start_node);
        visited.put(start_node, true);
        while (!queue.isEmpty()){
            Node elem = queue.poll();
            Node cur = queue_new.poll();
            cur.val = elem.val;
            Iterator<Node> iterator = elem.neighbors.iterator();
            while (iterator.hasNext()){
                Node item = iterator.next();
                Node tmp = new Node(item.val);
                if (!visited.containsKey(item)){
                    queue.add(item);
                    queue_new.add(tmp);
                    visited.put(item, true);
                }
                cur.neighbors.add(tmp);
            }
        }

        //广度优先遍历
        return start_node;
    }

    //todo: HashMap key => value: key = 原图中的节点  value = 克隆的节点
    private  HashMap <Node, Node> visited = new HashMap <> ();
    /**
     * todo: 深度优先遍历
     *         - DFS(node) : 返回 node 节点的克隆节点
     * @param node
     * @return
     */
    public Node DFS(Node node){
        //todo: base case
        if (node == null) return node;

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) return visited.get(node);

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(DFS(neighbor)); // 克隆 node的每一个节点
        }
        return cloneNode;
    }

    //todo: 广度优先搜索；在遍历过程中，克隆节点之间已经建立了关联
    public Node BFS(Node node) {
        if (node == null) return node;

        HashMap<Node, Node> visited = new HashMap();

        // 将题目给定的节点添加到队列
        LinkedList<Node> queue = new LinkedList<Node> ();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node n = queue.remove();
            // 遍历该节点的邻居
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }

    public static void main(String[] args) {

    }
}
