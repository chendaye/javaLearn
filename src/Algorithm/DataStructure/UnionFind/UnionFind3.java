package Algorithm.DataStructure.UnionFind;

/**
 * 第二个版本，有一个问题，就是：合并两个集合时。固定的将一个集合的根 指向另一个集合；这样可能会导致 集合过深，堆查找效率有影响
 *
 * todo： 改进：让集合高度尽可能的平衡： 合并的时候将 元素个数少的集合 只向元素个数多的集合。
 *          - 这就需要保存每一个集合的元素个数
 */
public class UnionFind3 {

    private int[] parent; // parent[i]表示第一个元素所指向的父节点
    private int[] sz;     // sz[i]表示以i为根的集合中元素个数
    private int count;    // 数据个数

    // 构造函数
    public UnionFind3(int count){
        parent = new int[count];
        sz = new int[count];
        this.count = count;
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < count ; i ++ ){
            parent[i] = i;
            sz[i] = 1; //todo: 用来保存集合的元素个数
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        assert( p >= 0 && p < count );
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while( p != parent[p] )
            p = parent[p];
        return p;
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
        if( sz[pRoot] < sz[qRoot] ){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}