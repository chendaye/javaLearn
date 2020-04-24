package Algorithm.DataStructure.Graph.WeightedGraph.SpanTree;

import Algorithm.DataStructure.Graph.WeightedGraph.Definition.Edge;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.SparseWeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.WeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.MinHeap;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.ReadWeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.UnionFind;

import java.util.Vector;

/**
 * todo： 将所有边放到最小堆中
 *      - 每次取出最小边；判断它和已经选出的边是否构成环；不构成环就加入到 mst中
 *      - 用UnionFind 判断是否构成环
 *
 * todo：O(ElogE) 循环每一条边，每一条边处理都要重新整理堆 E*logE
 * @param <Weight>
 */
public class KruskalMST<Weight extends Number & Comparable> {

    private Vector<Edge<Weight>> mst;   // 最小生成树所包含的所有边
    private Number mstWeight;           // 最小生成树的权值

    // 构造函数, 使用Kruskal算法计算graph的最小生成树
    public KruskalMST(WeightedGraph graph){

        mst = new Vector<Edge<Weight>>();

        // 将图中的所有边存放到一个最小堆中
        MinHeap<Edge<Weight>> pq = new MinHeap<Edge<Weight>>( graph.E() );
        for( int i = 0 ; i < graph.V() ; i ++ )
            for( Object item : graph.adj(i) ){
                Edge<Weight> e = (Edge<Weight>)item;
                if( e.v() <= e.w() )
                    pq.push(e);
            }

        //todo： 创建一个并查集, 来查看已经访问的节点的联通情况
        UnionFind uf = new UnionFind(graph.V());
        while( !pq.isEmpty() && mst.size() < graph.V() - 1 ){

            // 从最小堆中依次从小到大取出所有的边
            Edge<Weight> e = pq.pop();
            // 如果该边的两个端点是联通的, 说明加入这条边将产生环, 扔掉这条边
            if( uf.isConnected( e.v() , e.w() ) )
                continue;

            // 否则, 将这条边添加进最小生成树, 同时标记边的两个端点联通
            mst.add( e );
            //todo: e.v() , e.w() 是一条边的两个端点；将w v 所属的集合合并，表示这两个集合的元素可以相互到达
            uf.unionElements( e.v() , e.w() );
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    }


    // 测试 Kruskal
    public static void main(String[] args) {

        String filename = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\WeightedGraph\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}
