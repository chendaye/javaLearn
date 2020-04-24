package Algorithm.DataStructure.Graph.WeightedGraph.SpanTree;

import Algorithm.DataStructure.Graph.WeightedGraph.Definition.Edge;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.SparseWeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.WeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.IndexMinHeap;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.ReadWeightedGraph;

import java.util.Vector;

/**
 * todo: 使用最小索引堆； 对的大小 = 顶点数 V
 *      - 索引堆 key：顶点编号  value 当前正在考虑的顶点到 key的权值 heap[v]=weight
 *      - 每次考虑新的节点 都更新索引堆
 *
 * todo:时间复杂度
 * @param <Weight>
 */
public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph G;              // 图的引用
    private IndexMinHeap<Weight> heap;     // 最小索引堆, 算法辅助数据结构
    private Edge<Weight>[] edgeTo;        // todo: edgeTo[v]=e 到达 节点 w的一条边；e 包含2个端点和权值
    private boolean[] marked;             // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;     // 最小生成树所包含的所有边
    private Number mstWeight;             // 最小生成树的权值

    // 构造函数, 使用Prim算法求图的最小生成树
    public PrimMST(WeightedGraph graph){

        G = graph;
        assert( graph.E() >= 1 );
        heap = new IndexMinHeap<Weight>(graph.V());

        // 算法初始化
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        for( int i = 0 ; i < G.V() ; i ++ ){
//            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new Vector<Edge<Weight>>();

        // Prim
        push(0);
        while( !heap.isEmpty() ){
            // 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是点的索引, 通过点的索引找到相对应的边
            int v = heap.pop();
            assert( edgeTo[v] != null ); // edgeTo[v]=edgeTo[w]=e
            mst.add( edgeTo[v] );
            //todo: v 取出 v 的所有邻接点；遍历 edgeTo[v]=e
            //todo: 如果 一个邻接点 w 没有访问过:
            // todo ：情形1：edgeTo[w]=null ==》  edgeTo[w] = edgeTo[v] ； w 进堆 heap.push(w, e.wt());
            // todo ：情形1：edgeTo[w] !=null ==》 edgeTo[w] = min{ edgeTo[w] , edgeTo[v] }  ; 更新堆  heap.change(w, e.wt());
            push( v );
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    /**
     * 访问节点v， 将它的邻接点 放入索引堆  heap[邻接点x]=当前节点v 到x的权
     * todo: edgeTo[x] = ex = edgeTo[v]= e = edgeTo[w]
     *      - edgeTo[v] 表示的是 v 入边； 从上一个节点到达 v的边
     * @param v
     */
    void push(int v){

        assert !marked[v];
        marked[v] = true;

        //todo： 将和节点v相连接的未访问的另一端点, 和与之相连接的边, 放入最小堆中
        for( Object item : G.adj(v) ){
            Edge<Weight> e = (Edge<Weight>)item;
            int w = e.other(v);
            // 如果边的另一端点未被访问
            if( !marked[w] ){
                //todo: 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
                if( edgeTo[w] == null ){
                    edgeTo[w] = e; //todo: edgeTo[w]=edgeTo[v]=e
                    heap.push(w, e.wt());
                } else if( e.wt().compareTo(edgeTo[w].wt()) < 0 ){
                    //todo: 如果曾经考虑这个端点, 但现在的边比之前考虑的边更短, 则进行替换
                    edgeTo[w] = e; // todo: e 是到达 w的更短的一条边
                    heap.change(w, e.wt());
                }
            }
        }

    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    }


    // 测试 Prim
    public static void main(String[] args) {

        String filename = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\WeightedGraph\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        Vector<Edge<Double>> mst = primMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }
}
