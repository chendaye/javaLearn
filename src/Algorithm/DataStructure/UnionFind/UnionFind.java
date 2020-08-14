package Algorithm.DataStructure.UnionFind;

/**
 * todo: 并查集的时间复杂度 近乎是 O(1)
 * 路径压缩 path-compress
 */
public class UnionFind {

    // rank[i]表示以i为根的集合所表示的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
    // 关于这个问题，可以参考问答区：http://coding.imooc.com/learn/questiondetail/7287.html
    private int[] rank;
    private int[] parent; // parent[i]表示第i个元素所指向的父节点
    private int count;    // 数据个数

    // 构造函数
    public UnionFind(int count){
        rank = new int[count];
        parent = new int[count];
        this.count = count;
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < count ; i ++ ){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        assert( p >= 0 && p < count );

        // path compression 1
        while( p != parent[p] ){
            /**
             * todo: 大名鼎鼎的路径压缩：
             *      - 如果当前的节点不是根， 就把 它 指向它 父亲的父亲
             *      - 这样最后找到了 根节点，同时把路径的深度减少了
             */
            parent[p] = parent[parent[p]]; //todo: p的爸爸 指向 p的爷爷
            p = parent[p];
        }
        return p;

        //todo: 递归压缩，最终把一个集合中所有元素都直接连在根上。理论上更优化
        //todo: 实际上由于递归的性能消耗，性能不如上面的 压缩方法

        // path compression 2, 递归算法
//            if( p != parent[p] )
//                parent[p] = find( parent[p] ); // find是直接找到根，这一句就是直接连到根上
//            return parent[p];
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    public void unionElements(int p, int q){

        int pRoot = find(p);
        int qRoot = find(q);

        if( pRoot == qRoot )
            return;

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if( rank[pRoot] < rank[qRoot] ){
            parent[pRoot] = qRoot;
        }
        else if( rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 此时, 我维护rank的值,不是严格意义的层数
        }
    }
}