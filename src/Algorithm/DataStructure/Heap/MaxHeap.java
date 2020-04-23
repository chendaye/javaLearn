package Algorithm.DataStructure.Heap;

import Utils.Dump;
import Utils.Generate;

public class MaxHeap<T extends Comparable> {
    protected T[] data;
    protected int count;
    protected int capacity;
    //todo:构造函数
    //todo: 将 n个元素插入空堆中 时间复杂度 O(nlogn)
    public MaxHeap(int capacity){
        data = (T[])new Comparable[capacity+1];
        count=0;
        this.capacity = capacity;
    }

    //todo: 使用 Heapify 把传入的数组 转换成一个堆; 时间复杂度 O(n)
    public MaxHeap(T[] arr){
        int n = arr.length;
        data = (T[])new Comparable[n+1];
        capacity = n;
        //todo:数组的值 赋值给 data
        for (int i=0; i<n; i++){
            data[i+1] = arr[i];
        }
        count = n;
        //todo: 从最后一个非叶子节点开始，每一个非叶子节点向下调整成堆
        //todo:Heapify
        for (int i=count/2; i>1; i--){
            shiftDown(i);
        }
    }

    /**
     * todo: 原地堆排序， 索引从 0 开始
     *      - parent(i) = (i-1)/2
     *      - left-child(i) = 2i+1
     *      - right-child(i) = 2i+2
     * @param arr
     */
    public void InPlaceHeapSort(T[] arr){
        int count = arr.length;
        if (count==0) return;
        int index = count-1;
        //todo: shiftDown 是删除堆的最大元素， 把把剩下的调整为堆
        //todo: heapify  是从最后一个 非叶子节点开始  向上 使用 shiftDown 把整个数组调整为 堆
        for (int i=(index-1)/2; i>0; i--)
            shiftDown(arr, i, index);
        while (index >= 0){
            shiftDown(arr, 0, index); //todo:[0,index] 调整为堆
            swap(arr, 0, index); //todo: 堆的首位元素交换
            index--;
        }
    }
    private void swap(T[] arr, int i, int j){
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * todo: O(logn)
     *
     * todo: 把数组 arr [0, index] 的部分调整为堆
     *      - shiftDown 一个非叶子节点处理完 跳到 下一个 非叶子节点  i= 2i (2i+1)
     *      - heapify  是从 最后一个非叶子节点 逐个 向上处理， 把每一个子树都搞成堆
     * @param start
     * @param end
     */
    private void shiftDown(T[] arr,int start ,int end){
        if (end-start==0) return;
        while (2*start+1 <= end){
            int i = 2*start+1;
            if (i+1<=end && arr[i].compareTo(arr[i+1]) < 0)
                i++;
            //todo: 如果根已经是最大，就不用调整了
            if (arr[start].compareTo(arr[i]) > 0) return;
            swap(arr, start, i);
            //todo:下一个非叶子节点
            start=i;
        }
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

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j){
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 在堆中插入元素
     * @param item
     */
    public void push(T item){
        count++;
        assert count <= capacity;
        data[count] = item;
        //todo:自底向上调整堆
        shiftUp(count);
    }



    /**
     * todo: O(logn)
     *
     * 自底向上调整堆
     * todo: 不断和父节点比较，如果比父节点大 就交换
     * @param k
     */
    private void shiftUp(int k){
        while (k>1 && data[k/2].compareTo(data[k]) < 0){
            swap(k/2, k);
            k = k/2;
        }
    }

    /**
     * 取堆顶的元素
     * @return
     */
    public T pop(){
        assert !isEmpty();
        //todo:堆顶 和 最后一个元素交换位置
        swap(1, count);
        //todo:取出最后一个元素(原来的堆顶)
        T top = data[count];
        //todo: 元素个数 --
        count--;
        //todo: 自顶向下调整堆
        shiftDown(1);
        return top;
    }

    /**
     * todo: O(logn)
     *
     * 自顶向下调整堆
     * @param k
     */
    private void shiftDown(int k){
        while (2*k <= count){
            int i = 2*k;
            if (i+1<=count && data[i].compareTo(data[i+1]) < 0)
                i = i+1;
            //todo: 已经大于最大的子节点 就不用调整了
            if (data[k].compareTo(data[i]) > 0) break;
            swap(k, i);
            k = i;

        }
    }

    /**
     * todo: 指定堆中 元素个数； 不断插入元素构造堆
     *      - 再取出堆顶元素 就是有序的
     * @param capacity
     */
    public static void heapSort1(int capacity){
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(capacity);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.push( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.pop();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];
    }

    /**
     * todo: 堆传入的数组 先改造成堆；再取堆顶元素进行排序
     * @param arr
     */
    public static void heapSort2(Integer[] arr) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(arr);

        for (int i=arr.length -1; i>=0; i--){
            arr[i] = maxHeap.pop();
        }
    }

    // 测试 MaxHeap
    public static void main(String[] args) {
//       heapSort1(100);
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
//        heapSort2(arr);
        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);
        maxHeap.InPlaceHeapSort(arr);
        Dump.array(arr);
    }
}
