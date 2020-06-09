package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

/**
 * todo: 选择排序要点：
 *      - [0, i-1] 是有序区
 *      - [i, n-1] 选择一个最小的与 i 交换
 *      - index 记录 [j, n-1] 最小值的位置； 比较 j 和 index 元素大小
 */
public class SelectionSort {

    /**
     * O(n^2)
     * todo: 在 [i...n) 范围内找一个最小值与 i 交换
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int len = arr.length;
        for (int i=0; i<len; i++){
            int index=i;
            for (int j=i; j<len; j++)
                if (arr[j].compareTo(arr[index]) < 0) // todo: 比较的是 j 和 index
                    index = j;
            swap(arr, i,index);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {


        Integer[] arr = Generate.generateRandomArray(20, 1, 100);
//        SelectionSort.sort(arr);

        Dump.array(arr);

        Generate.testSort("Algorithm.DataStructure.Sort.SelectionSort",arr);
        Dump.array(arr);
    }

}

/**
 * todo: train 1
 */
class train_select_1{
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 100);
        Dump.array(arr);
        train(arr);
        Dump.array(arr);
    }
    private static void train(Comparable[] arr){
        //todo: [0, i) [i, n-1] 选一个最小值 与 i交换
        for (int i=0; i<arr.length; i++){
            int inx = i;
            for (int j=i; j<arr.length; j++){
                if (arr[j].compareTo(arr[inx]) < 0)
                    inx = j;
            }
            swap(arr, i, inx);
        }
    }

}

class train_select_2{
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //todo: 有序区 [0, i)
    private static void selectSort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++){
            int ind = i;
            for (int j = i; j < arr.length; j++){
                if (arr[ind].compareTo(arr[j]) > 0)
                    ind = j;
            }
            swap(arr, i, ind);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 100);
        Dump.array(arr);
        selectSort(arr);
        Dump.array(arr);
    }
}