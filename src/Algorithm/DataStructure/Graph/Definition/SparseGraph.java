package Algorithm.DataStructure.Graph.Definition;

import java.util.Vector;

/**
 * todo: 邻接表实现稀疏图
 */
public class SparseGraph implements Graph {
    private int n; //节点数
    protected int e; //边数
    private boolean directed;
    private Vector<Integer>[] g; //图的边；一般用链表实现 邻接表；删除效率更高（这里没有涉及删除就用Vector代替了）

    public SparseGraph(int n, boolean directed){
        assert n>0;
        this.n = n;
        this.e = 0;
        this.directed = directed;
        // n个元素的Vector 数组
        g = new Vector[n];
        for (int i=0; i<n; i++)
            g[i] = new Vector<Integer>();
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
        //遍历顶点 v 的 Vector 查找 和 w是否有边
        for (int i=0; i<g[v].size(); i++){
            if (g[v].elementAt(i) == w)
                return true;
        }
        return false;
    }

    //增加一条边
    public void addEdge(int v, int w){
        assert v>=0 && v<n;
        assert w>=0 && w<n;
        g[v].add(w);
        //todo: 不能指向自己（自环边）
        if( v != w && !directed )
            g[w].add(v);
        e++;
    }

    //显示图数据
    public void show(){
        for (int i=0; i<n; i++){
            for (int j=0; j<g[i].size(); j++)
                System.out.println(g[i].elementAt(j));
        }
        System.out.println();
    }

    //返回一个顶点的所有邻边
    public Vector<Integer> adj(int v){
        assert n>=0 && v<n;
        return g[v];
    }
}
