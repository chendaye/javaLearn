package Algorithm.DataStructure.Graph.WeightedGraph.Definition;

/**
 * 带权图的接口
 * @param <Weight>
 */
public interface WeightedGraph<Weight extends Number & Comparable> {
    public int V();
    public int E();
    public void addEdge(Edge<Weight> e);
    boolean hasEdge( int v , int w );
    void show();
    public Iterable<Edge<Weight>> adj(int v);
}
