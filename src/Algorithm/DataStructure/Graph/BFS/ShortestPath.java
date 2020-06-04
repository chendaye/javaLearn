package Algorithm.DataStructure.Graph.BFS;

import Algorithm.DataStructure.Graph.DFS.Path;
import Algorithm.DataStructure.Graph.Definition.Graph;
import Algorithm.DataStructure.Graph.Definition.SparseGraph;
import Algorithm.DataStructure.Graph.Utils.ReadGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 广度优先遍历 求最短路径
 */
public class ShortestPath {
    Graph G;
    private int s; //起始点
    private boolean[] visited; // 访问数组，从 s 出发访问到的节点
    private int[] from; // 当前遍历节点的上一个节点 from[i] = 节点 i 的上一个节点
    private int[] ord; //todo:  记录节点在路径中的次序；ord[i] = 节点 i 在路径中的次序. 也就是路径长度

    public ShortestPath(Graph G, int s){
        assert s>= 0&& s<G.V();
        this.s = s;
        this.G = G;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i=0; i<G.V(); i++){
            from[i] = -1;
            ord[i] = -1;
        }

        //todo: 广度优先遍历
        bfs(s);
    }

    /**
     * 广度优先遍历
     * @param s
     */
    public void bfs(int s){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(s);
        visited[s] = true;
        ord[s] = 0; //todo: 到自己的距离是 0
        while (!queue.isEmpty()){
            //上一个节点
            Integer current = queue.remove();
            // 上一个节点下面的所有节点
            for (Integer next: G.adj(current)){
                if (!visited[next]){
                    queue.push(next);
                    visited[next] = true; //todo: 入队时，标记访问
                    from[next] = current;
                    ord[next] = ord[current]+1;
                }
            }
        }
    }

    // 查询从s点到w点是否有路径
    public boolean hasPath(int w){
        assert w >= 0 && w < G.V();
        return visited[w];
    }

    /**
     * todo: 返回 s 到 w的路径
     *      - BFS一层一层访问的方式，找到的就是最短路径（无权图）
     * @param w
     * @return
     */
    public ArrayList<Integer> path(int w){
        assert hasPath(w); // 先判断是否连通

        //因为要逆向输出，所以使用栈
        Stack<Integer> stack = new Stack<>();
        int loc = w;
        // from[s] = -1
        while (loc != -1){
            stack.add(loc);
            loc = from[loc];
        }

        ArrayList<Integer> path = new ArrayList<>();
        while (!stack.isEmpty())
            path.add(stack.pop());

        return path;
    }

    // 打印出从s点到w点的路径
    void showPath(int w){

        assert hasPath(w) ;

        ArrayList<Integer> vec = path(w);
        for( int i = 0 ; i < vec.size() ; i ++ ){
            System.out.print(vec.get(i));
            if( i == vec.size() - 1 )
                System.out.println();
            else
                System.out.print(" -> ");
        }
    }

    //todo: 查看从s点到w点的最短路径长度
    //todo: 若从s到w不可达，返回-1
    public int length(int w){
        assert w >= 0 && w < G.V();
        return ord[w];
    }

    // 测试无权图最短路径算法
    public static void main(String[] args) {

        String filename = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs = new Path(g,0);
        System.out.print("DFS : ");
        dfs.showPath(6);

        ShortestPath bfs = new ShortestPath(g,0);
        System.out.print("BFS : ");
        bfs.showPath(6);
    }

}
