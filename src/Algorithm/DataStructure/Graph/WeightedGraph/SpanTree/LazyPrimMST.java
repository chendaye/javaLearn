package Algorithm.DataStructure.Graph.WeightedGraph.SpanTree;

import Algorithm.DataStructure.Graph.WeightedGraph.Definition.Edge;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.SparseWeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Definition.WeightedGraph;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.MinHeap;
import Algorithm.DataStructure.Graph.WeightedGraph.Utils.ReadWeightedGraph;

import java.util.Vector;

/**
 * 思路： 先选取一个节点，比如0； 将0余其他部分切片； 0 的所有边
 * 就是 “横切边”； 将这些“横切边”放入小根堆中，
 * 取出最小的一条边。根据切分定理，这一条边一定是最小生成树种的一条边。 如此就确定了一条边。
 * 然后，处理这条边 除了0之外的另一个节点 也就是7（要没有被访问过）； 将 7 相关的 "横切边"放入最小堆中
 * 再从最小堆中取出堆顶 ，也就是权值最小的边。 循环往复
 * @param <Weight>
 */
public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> G;
    private MinHeap<Edge<Weight>> heap; //最小堆
    private boolean[] marked; // 标记节点 i 是否被访问过
    private Vector<Edge<Weight>> mst; // 最小生成树中的边
    private Number mstWeight=0; // 最小生成树的权值

    /**
     * 时间复杂度：E条边 while 循环 E次；
     *      - 每次循环 堆 pop push 时间复杂度 logE
     *      - 总的时间复杂度 O(ElogE)
     * @param G
     */
    public LazyPrimMST(WeightedGraph<Weight> G){
        // 初始化
        this.G = G;
        heap = new MinHeap<Edge<Weight>>(G.E()); // 开辟一个和图中边一样多的堆
        marked = new boolean[G.E()]; // 访问数组
        mst = new Vector<Edge<Weight>>(G.E()); // 保存最小生成树中的边

        //todo: Lazy Prim(把已经不可能是生成树中的边 的横切边 也放入堆中； 没有做判断)
        //关键是：出堆的最小边必须是单向延伸的，也就是只有一端被访问
        push(0); //从交节点 0 开始
        while (!heap.isEmpty()){
            Edge<Weight> min = heap.pop(); // 堆中的最小边
            //todo: 进入到 heap 中的边，至少有 一个顶点 w或 v 被访问了
            //todo: 如果边的两边都被访问了，说明 这条边的 有一个顶点，已经选出选出了最小横切边
            //todo:那么这条 就不可能是这个顶点的 最小横切边，也就不可能是 mst的一员
            if (marked[min.v()] == marked[min.w()])
                continue;
            //todo: 只有一端被访问，并且这条边的权值是最小的；这条边应该加到 mst中
            mst.add(min);

            //todo:访问这条边 还没有被访问的节点
            if (!marked[min.w()]){
                push(min.w());
            }else {
                push(min.v());
            }

            //todo: 计算最小生成树的权值
            for (int i=0; i<mst.size(); i++)
                mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue() ;
        }
    }

    /**
     * 和 v相连的 所有 未被访问过的边 放入堆中
     * @param v
     */
    private void push(int v){
        assert !marked[v]; // 保证v没有被访问过
        marked[v] = true;
        // 将和节点相连的所有 未访问的边放入堆中
        for (Edge<Weight> e: G.adj(v) ){
            int other = e.other(v); // 当前边的另一个顶点
            //todo: 如果 other 被访问过，说明在 v 之前已经访问过 other；
            //todo: 已经对 other 执行过 push(other) 操作了；也就是 e 已经放到堆中了
            if (!marked[other])
                heap.push(e);
        }
    }

    //返回最小生成树的所有边
    public Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    }

    // 测试 Lazy Prim
    public static void main(String[] args) {

        String filename = "E:\\learnJava\\src\\Algorithm\\DataStructure\\Graph\\WeightedGraph\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
    }
}
