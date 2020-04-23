package Algorithm.DataStructure.Graph.DFS;

import Algorithm.DataStructure.Graph.Definition.Graph;
import Algorithm.DataStructure.Graph.Definition.SparseGraph;
import Algorithm.DataStructure.Graph.Utils.ReadGraph;

/**
 * 求无权图的连通分量
 * todo：把对图的操作封装成一个类
 */
public class Component {
    private Graph G; // 图的引用
    private boolean[] visited;
    private int count; // 连通分量的个数
    private int[]  componentId; //todo: 每一个节点所属的连通分量的编号；属于同一个连通分量的节点相连
    
    public Component(Graph G){
        this.G = G;
        int v = this.G.V(); // 顶点数
        visited = new boolean[v]; //默认值是 false
        componentId = new int[v];
        for (int i=0; i<v; i++)
            componentId[i] = -1;

        //todo:深度优先 DFS 求连通分量的个数
        for (int i=0; i<v; i++){
            if (!visited[i]){
                dfs(i); //从节点 i 出发进行DFS，最终一定得到一个 连通分量
                count++;
            }
        }
    }

    /**
     * 从顶点 v 进行深度优先遍历
     * @param v
     */
    private void dfs(int v){
        visited[v]=true;
        componentId[v] = count; // 连通分量的编号，从0开始
        //todo: 对V的所有邻接点 进行深度优先遍历
        for (Integer next: G.adj(v))
            if (!visited[next])
                dfs(next);
    }

    /**
     * 返回所有连通分量的个数
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * 看两个 顶点是否连通，就是看它们是否在同一个连通中
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w){
        assert v>=0 && v<G.V();
        assert w>=0 && w<G.V();
        return componentId[w] == componentId[v];
    }

    //todo:测试
    public static void main(String[] args) {
        // TestG1.txt
        String filename1 = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        Component component1 = new Component(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        Component component2 = new Component(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }

}
