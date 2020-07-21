package Algorithm.DataStructure.Sort;

import Algorithm.DataStructure.Heap.MaxHeap;
import Utils.Dump;
import Utils.Generate;

public class HeapSort {

    private static void swap(Comparable[] arr, int i, int j){
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * todo: O(logn)
     *
     * todo: 把数组 arr [0, index] 的部分调整为堆
     *      - shiftDown 一个非叶子节点处理完 跳到 下一个 非叶子节点
     *              - 索引从 1 开始 ：parent = i/2  current = i left = 2i  right = 2i+1
     *              - 索引从 0 开始 ：parent = (i-1)/2 current = i  left = 2i+1  right = 2i+2
     *      - heapify  是从 最后一个非叶子节点 逐个 向上处理， 把每一个子树都搞成堆
     * @param start
     * @param end
     */
    private static void shiftDown(Comparable[] arr,int start ,int end){
        if (end == start) return;
        while (2*start+1 <= end){
            int i = 2*start+1;
            //todo: 叶子节点取其大
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
     * todo: 原地堆排序， 索引从 0 开始
     *      - parent(i) = (i-1)/2
     *      - left-child(i) = 2i+1
     *      - right-child(i) = 2i+2
     * @param arr
     */
    public static void InPlaceHeapSort(Comparable[] arr){
        int count = arr.length;
        if (count==0) return;
        int index = count-1;
        //todo: shiftDown 是删除堆的最大元素， 把把剩下的调整为堆
        for (int i=(index-1)/2; i>0; i--)
            shiftDown(arr, i, index); //todo: heapify  是从最后一个 非叶子节点开始  向上 使用 shiftDown 把整个数组调整为 堆
        //todo: (index, N-1] 有序
        while (index >= 0){
            shiftDown(arr, 0, index); //todo:[0,index] 调整为堆
            swap(arr, 0, index); //todo: 堆的首位元素交换. 堆顶放到最后
            index--;
        }
    }

    // 测试 MaxHeap
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        InPlaceHeapSort(arr);
        Dump.array(arr);
    }
}

class train_heap_1{
    private static void swap(Comparable[] arr, int i, int j){
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //todo: [start, end] 范围内向下调整为堆.
    // shiftDown 如果是从最后一个父节点开始向上调整 => 初始化堆
    // shiftDown 如果是从某一个父节点开始调整 => 调整一个堆的半成品
    private static void shiftDown(Comparable[] arr, int start, int end){
        if (start == end) return;
        while (2 * start + 1 <= end){
            int i = 2 * start + 1; // 默认左节点最大
            if (i + 1 <= end && arr[i].compareTo(arr[i + 1]) < 0)
                i++;
            if (arr[start].compareTo(arr[i]) >= 0) return; //如果根最大，不需要再调整
            swap(arr, start, i);

//            if (arr[start].compareTo(arr[i]) < 0)
//                swap(arr, start, i);
            //todo:下一个节点
            start = i;
        }
    }

    private static void heapSort(Comparable[] arr){
        int end = arr.length - 1;
        //从最后一个父节点向上调整
        for (int i = (end - 1) / 2; i >= 0; i--)
            shiftDown(arr, i, end);
        //取堆顶与最后元素交换，再调整 [0, index]
        int index = end;
        while (index >= 0){
            swap(arr, 0, index--);
            shiftDown(arr, 0, index);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        heapSort(arr);
        Dump.array(arr);
    }
}

class train_heap_2{
    private static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }

    private static void shiftDown(Comparable[] arr, int start, int end){
        if (start == end) return;
        while ((2 * start + 1) <= end){
            int i = 2 * start + 1;
            if (i + 1 <= end && arr[i].compareTo(arr[i + 1]) < 0)
                i++;
            if (arr[start].compareTo(arr[i]) >= 0) return;
            swap(arr, start, i);
            start = i;
        }
    }


    private static void heapSort(Comparable[] arr){
        int n = arr.length - 1;
        for (int i = (n - 1) / 2; i >= 0; i--)
            shiftDown(arr, i, n);

        while (n >= 0){
            swap(arr, 0, n--);
            shiftDown(arr, 0, n);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        heapSort(arr);
        Dump.array(arr);
    }
}

class train_heap_3{
    private static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }


    private static void shiftDown(Comparable[] arr, int start, int end){
        if (start == end) return;
        while ((2 * start + 1) <= end){
            int i = 2 * start + 1;
            if (i + 1 <= end && arr[i].compareTo(arr[i + 1]) < 0)
                i++;
            if (arr[start].compareTo(arr[i]) >= 0) return;
            swap(arr, start, i);
            start = i;
        }
    }


    private static void heapSort(Comparable[] arr){
        int n = arr.length - 1;
        for (int i = (n - 1) / 2; i >= 0; i--)
            shiftDown(arr, i, n);
        while (n >= 0){
            swap(arr, 0, n--);
            shiftDown(arr, 0, n);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        heapSort(arr);
        Dump.array(arr);
    }
}



class train_heap_4{
    private static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }


    private static void shiftDown(Comparable[] arr, int start, int end){
        if (start >= end) return;
        while ((2 * start) + 1 <= end){
            int i = (2 * start) + 1;
            if (i + 1 <= end && arr[i].compareTo(arr[i + 1]) < 0){
                i++;
            }
            if (arr[start].compareTo(arr[i]) >= 0) return;
            swap(arr, start, i);
            start = i;
        }
    }

    private static void heapSort(Comparable[] arr){
        int n = arr.length - 1;
        // 自底向上调整堆
        for (int i = n; i >= 0; i--)
            shiftDown(arr, i, n);
        while (n >= 0){
            swap(arr, 0, n--);
            shiftDown(arr, 0, n);
        }
    }



    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        heapSort(arr);
        Dump.array(arr);
    }
}

class train_heap_5 {
    private static void swap(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }
    private static void shiftDown(Comparable[] arr, int start, int end){
        if (start >= end) return;
        while (2*start + 1 <= end){
            int i = 2*start + 1;
            if (i+1 <= end && arr[i].compareTo(arr[i+1]) < 0){
                i++;
            }
            if (arr[start].compareTo(arr[i]) >= 0) return;
            swap(arr, start, i);
            start = i;
        }
    }
    private static void heapSort(Comparable[] arr){
        int n = arr.length - 1;
        // 自底向上调整堆
        for (int i = n; i >= 0; i--)
            shiftDown(arr, i, n);
        while (n >= 0){
            swap(arr, 0, n--);
            shiftDown(arr, 0, n);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        heapSort(arr);
        Dump.array(arr);
    }
}