package Algorithm.Interview.LeetCode.TopologicalSort;

import Grammar.LanguageElement.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class canFinish {

    /**
     * todo: 深度优先
     *      - 思想： 对每一个节点 DFS 看有没有环； 有环就不满足拓扑排序，否则就满足
     *      - 一般 DFS 思想， 设置一个 visited 数组， 被访问过 置 1 ；某节点n visited[n] = 1; DFS所有邻接点后 回溯到 n visited[n] = 0
     *      - 然后 对下一个节点 进行同样的操作
     * todo：优化：为了性能， 在回溯到 n 时 visited[n] = 2; 代表 这个点已经被回溯过了 = 这个点没有在任和环上
     *
     * todo： 回溯：
     *          - 暴力解法
     *          - 对候选集合中的每一个元素 都进行考虑；考虑过程中要标记，避免重复
     *          - 考虑玩某个元素之后，把它恢复原状
     *
     *  todo:时间复杂度: O(n+m)O(n+m)，其中 nn 为课程数，mm 为先修课程的要求数。这其实就是对图进行深度优先搜索的时间复杂度。
     */
    public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
        // 邻接表
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for (int[] nodes : prerequisites){
            adj.get(nodes[1]).add(nodes[0]); // 想要完成 课程 1 先要完成课程 0
        }
        // 访问数组
        int[] visited = new int[numCourses];

        // todo: 对所有节点 DFS+回溯; 判断每一个节点是否在环上
        for (int i = 0; i < numCourses; i++)
            if (!dfs(i, adj, visited)) return false;
        return true;
    }
    // todo: 回溯同时也是是递归
    public boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited){
        // 本轮回溯中已经访问过一次
        if (visited[node] == 1) return false;

        // 其他节点的回溯中已经判断过 没有环
        if (visited[node] == 2) return true;

        // 访问标记
        visited[node] = 1;
        // 判断所有 选择列表
        for (int i : adj.get(node))
            if (!dfs(i, adj, visited)) return false;
        // 回溯到原点
        visited[node] = 2;
        return true;
    }

    /**
     * todo: 广度优先遍历
     *          - 使用一个队列来进行广度优先搜索。初始时，所有入度为 0 的节点都被放入队列中
     *          - 队列元素一次出栈， 此元素到达的所有节点 入度 -1； 如果入度=0 加入队列
     *          - 如果进入过队列的元素个数 < N 则有环
     */
    public boolean canFinish_BFS(int numCourses, int[][] prerequisites) {
        // 邻接表
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<Integer>());
        for (int[] nodes : prerequisites)
            adj.get(nodes[0]).add(nodes[1]); // 学习课程 0 ，你需要先完成课程 1; 1 -> 0  用逆向邻接表 方便求入度

        // 入度 队列
        int cnt = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (adj.get(i).size() == 0) {
                queue.add(i); // 入度为0 进队列
                cnt++; // 入队元素+1
            }
        }

        // 队列非空
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            // 每个点的入度中去掉 pool
            for (int i = 0; i < numCourses; i++){
                ArrayList<Integer> edge = adj.get(i);
                if (edge.contains(poll)){
                    edge.remove(poll);
                    // 入度 -1 后判断入度是否=0
                    if (edge.size() == 0) {
                        queue.add(i);
                        cnt++;
                    }
                }
            }
        }
        return cnt == numCourses;
    }



    public static void main(String[] args) {

    }
}
