package Algorithm.DataStructure.Graph.DFS;

import Algorithm.DataStructure.Graph.Definition.Graph;
import Algorithm.DataStructure.Graph.Definition.SparseGraph;
import Algorithm.DataStructure.Graph.Utils.ReadGraph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * todo: 查找途中 s 到 w的路径； 核心就是，在从 s DFS的过程中，记录当前遍历节点的上一个节点
 *          - 一个逆向输出的技巧是 使用 栈
 *
 * todo: 邻接矩阵 DFS O(n^2)  邻接表 DFS O(V+E)
 */
public class Path {
    private Graph G;   // 图的引用
    private int s;     //todo: 起始点;从s出发 开始dfs
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int[] from;         // 记录路径, from[i]表示查找的路径上i的上一个节点

    public Path(Graph G, int s){
        this.G = G;
        assert s>=0 && s<G.V();
        this.s = s;
        from = new int[G.V()];
        visited = new boolean[G.V()];
        for (int i=0; i<G.V(); i++){
            from[i] = -1;
        }

        //todo: 在深度优先遍历中 记录当前遍历到的节点的上一个节点 from[current] = last
        //todo: 因为只是找 s出发的路径，没有必要堆途中的每一个点，进行DFS
        dfs(s);
    }

    // 图的深度优先遍历
    private void dfs( int v ){
        visited[v] = true; //根先访问
        for( int i : G.adj(v) )
            if( !visited[i] ){
                from[i] = v; //todo:表示DFS路径中 i的上一个节点是 v
                dfs(i);
            }
    }

    /**
     * todo:要看 s 到 v 有没有路径，只需要看 从 s的DFS过程中
     *      - 有没有访问 v； 也就是看 visited[v] = true
     * @param v
     * @return
     */
    public boolean hasPath(int v){
        assert v>=0 && v<G.V();
        return visited[v];
    }

    /**
     * todo: 返回 s 到 w的路径
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
    public void showPath(int w){

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

    // 测试寻路算法
    public static void main(String[] args) {

        String filename = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
