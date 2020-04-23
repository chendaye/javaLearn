package Algorithm.DataStructure.Graph.Definition;

import java.util.Vector;

/**
 *todo: 稠密图： 邻接矩阵
 */
public class DenseGraph implements Graph {
    private int n; //节点数
    private int e; //边数
    private boolean[][] g; //连接状态
    private boolean directed; //是否为有向图

    //初始化图
    public DenseGraph(int n, boolean directed){
        this.n = n;
        this.directed = directed;
        this.e = 0;
        // boolean 的默认值是false； 初始状态都是false
        g = new boolean[n][n];
    }

    //返回顶点数
    public int V(){
        return this.n;
    }

    //返回边数
    public int E(){
        return this.e;
    }

    //判断两个顶点是否有边
    public boolean hasEdge(int v, int w){
        assert v>=0 && v<n;
        assert w>=0 && w<n;
        return g[v][w];
    }

    //在图中加一条边
    public void addEdge(int v, int w){
        assert v>=0 && v<n;
        assert w>=0 && w<n;
        //如果已经连接直接返回
        if (hasEdge(v, w)) return;
        //无向图的话 两边都有设置为 true
        g[v][w] = true;
        if (!directed)
            g[w][v] = true;
        e++;
    }

    // 显示图的信息
    public void show(){

        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                System.out.print(g[i][j]+"\t");
            System.out.println();
        }
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        Vector<Integer> adjV = new Vector<Integer>();
        for(int i = 0 ; i < n ; i ++ )
            if( g[v][i] )
                adjV.add(i);
        return adjV;
    }

}
