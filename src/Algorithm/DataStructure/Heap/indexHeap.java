package Algorithm.DataStructure.Heap;

import Utils.Dump;
import Utils.Generate;

/**
 * 索引堆： 不改变原数组中元素的位置
 * 给每个元素 加一个索引指明 它在堆中的位置
 * 构建堆变成了 索引的交换-
 *
 * todo: 取值 先通过 indexes[i] 拿到 原数组重的索引 data[indexes[i]]
 *      - 整理堆的时候 交换的是 indexes 中的值， 也就是原数组中的索引
 */
public class indexHeap<T extends Comparable> {
    protected T[] data;
    protected int[] indexes; //todo: 存放堆的索引 本质上是一个堆

    //todo: indexes: key->value   reverse: value->key
    //todo: 是为了 在 O(1) 时间复杂度内， 通过 indexes 的 value 快速找到 对应的 key
    protected int[] reverse;
    protected int count;
    protected int capacity;
    //todo:构造函数
    //todo: 将 n个元素插入空堆中 时间复杂度 O(nlogn)
    public indexHeap(int capacity){
        data = (T[])new Comparable[capacity+1];
        indexes = new int[capacity+1];
        reverse = new int[capacity+1];
        count=0;
        this.capacity = capacity;
    }


    /**
     * 返回元素个数
     * @return
     */
    public int size(){
        return count;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return count==0;
    }

    public T getMax(){
        assert !isEmpty();
        return data[1];
    }

    // 交换索引 中 i和j的两个元素
    private void swap(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

    /**
     * 在堆中插入元素
     * @param item
     */
    public void push(T item, int i){
        assert i+1 <= capacity && i+1 >= 1; // 对于用户来说 i是从0 开始的； 内部要处理这个差异
        i+=1; // 内部使用的索引
        count++;
        assert count <= capacity;
        //todo: 数组还是数组
        data[i] = item;
        indexes[count] = i; // count 是堆中的位置 i是原数组中的位置
        reverse[i] = count; // reverse 与indexes 是对应的
        //todo:indexes 自底向上调整堆
        shiftUp(count);
    }



    /**
     * 自底向上调整堆
     * todo: 不断和父节点比较，如果比父节点大 就交换
     * @param k
     */
    private void shiftUp(int k){
        //todo: 要通过 indexes 拿到 原数组中的值 进行比较
        while (k>1 && data[indexes[k/2]].compareTo(data[indexes[k]]) < 0){
            swap(k/2, k);
            //维护反向数组
            reverse[indexes[k/2]] = k/2;
            reverse[indexes[k]] = k;
            k = k/2;
        }
    }

    /**
     * 取堆顶的元素
     * @return
     */
    public T pop(){
        assert !isEmpty();
        //todo:交换索引数组中的元素
        swap(1, count);
        // 维护reverse
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 因为删除了 count，赋值 0
        //todo:取出最后一个元素(原来的堆顶)
        T top = data[indexes[count]];
        //todo: 元素个数 --
        count--;
        //todo: 自顶向下调整堆
        shiftDown(1);
        return top;
    }

    /**
     * 获取堆中最大元素的索引
     * @return
     */
    public int maxIndex(){
        assert !isEmpty();
        //todo:交换索引数组中的元素
        swap(1, count);
        // 更新reverse
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 因为删除了 count，赋值 0
        //todo:取出最后一个元素(原来的堆顶)
        int top = indexes[count];
        //todo: 元素个数 --
        count--;
        //todo: 自顶向下调整堆
        shiftDown(1);
        return top;
    }

    /**
     * 外部用户获取一个元素
     * @param i
     * @return
     */
    public T getItem(int i){
        return data[i+1];
    }

    /**
     * 修改数组中 索引位置 i 值
     * @param i
     * @param newItem
     */
    public void change(int i, T newItem){
        i+=1; // 内部索引是从1开始的
        data[i] = newItem; //修改数组中的元素

        for (int n=1; n<=count; n++){
            //todo:找到堆索引中 i的位置
            if (indexes[n] == i){
                //todo: 因为不知道 是向上调整堆，还是向下调整堆 所以 shiftDown shiftUp 都执行一下
                shiftDown(n);
                shiftUp(n);
                return;
            }
        }
    }

    //todo: O(1)
    public void change2(int i, T newItem){
        assert contain(i);
        i+=1; // 内部索引是从1开始的
        data[i] = newItem; //修改数组中的元素
        int n = reverse[i];
        shiftDown(n);
        shiftUp(n);
    }

    //todo: 判断 i 是否在堆中
    private boolean contain(int i){
        assert i+1 >= 1 && i+1<=capacity;
        return reverse[i+1] != 0;
    }

    /**
     * 自顶向下调整堆
     * @param k
     */
    private void shiftDown(int k){
        while (2*k <= count){
            int i = 2*k;
            //todo: 取数组中的值要通过 indexes
            if (i+1<=count && data[indexes[i]].compareTo(data[indexes[i+1]]) < 0)
                i = i+1;
            //todo: 已经大于最大的子节点 就不用调整了
            if (data[indexes[k]].compareTo(data[indexes[i]]) > 0) break;
            swap(k, i);
            //todo: 更新reverse
            reverse[indexes[k]] = k;
            reverse[indexes[i]] = i;
            k = i;

        }
    }



    // 测试 MaxHeap
    public static void main(String[] args) {

    }
}

