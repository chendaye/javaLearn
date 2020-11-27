package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
 *
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点
 * （译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ）空就是没有下一个结点了。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * 示例 2：
 *
 *
 *
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * 示例 3：
 *
 * 输入：graph = [[1],[]]
 * 输出：[[0,1]]
 * 示例 4：
 *
 * 输入：graph = [[1,2,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,2,3],[0,3]]
 * 示例 5：
 *
 * 输入：graph = [[1,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,3]]
 *  
 *
 * 提示：
 *
 * 结点的数量会在范围 [2, 15] 内。
 * 你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class allPathsSourceTarget {
    public static void main(String[] args) {
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        List<List<Integer>> res =  allPathsSourceTarget(graph);
        for (List<Integer> list :  res){
            Dump.iterator(list);
        }
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        int[] visited = new int[graph.length];
        back(res, path,graph, graph.length, 0, visited);
        return res;
    }

    public static void back( List<List<Integer>> res, ArrayList<Integer> path, int[][] graph, int n, int start, int[] visited){
        if (graph[start].length == 0 && n - 1 != start) return;
        if (n-1 == start){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            list.addAll(path);
            res.add(list);
            return;
        }


        for (int node :  graph[start]){
            if (visited[start] == 1) continue;
            //todo: 每一个节点都是一个新的开始， 状态回溯紧跟着递归调用
            // 回溯一次是一路到底
            path.add(node);
            visited[start] = 1; //todo: 选择走start这条路径
            back(res, path, graph, n, node, visited); // start = i 就是可以重复选取 start=i+1 就是不能重复选取
            path.remove(path.size() - 1); // todo: start 这条路径走完了，恢复状态
            visited[start] = 0;
        }

    }
}
