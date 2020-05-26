package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import java.util.*;

/**
 * todo： 插入排序要点：
 *      - 外层 for， [0, i-1] 是有序的
 *      - 里层 for j=i， 把 j 插入到 [0, i] 合适位置
 *      - swap(arr[j-1], arr[j]), 终止条件 j>=1 或 j>0
 *
 * todo: 优化
 *        - 减少交换，采用元素后移，空出一个位置
 *        - 有序部分采用，折半查找
 */
public class InsertionSort{

    // 我们的算法类不允许产生任何实例
    private InsertionSort(){}

    /**
     * O(n^2)
     * todo: 在 [0...i] 范围内找到 i  的位置插入
     * @param arr
     */
    public static void sort2(Comparable[] arr){
        int n = arr.length;
        //todo: [0, i-1] 是有序区间
        for (int i = 0; i < n; i++) {
            //todo:[0, i-1] 是有序的；把 i 插入到 [0, i]的合适位置
            for( int j = i ; j > 0 ; j -- ) //todo:注意 j 到 1为止
                if(arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr,j, j-1);
                }else{
                    break;
                }
        }
    }

    /**
     * todo: 优化，减少交换次数
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            Comparable tmp = arr[i]; //todo:保存 arr[i] 的副本
            int j;
            //todo: 在 [0...i] 位置找 arr[i]的位置
            for(j = i ; j > 0 ; j -- )
                //todo: 如果 tmp < arr[j-1]; 把 arr[j-1] 往后挪； 因为 [0...j-1] 是有序的
                if(tmp.compareTo(arr[j-1]) < 0){
                    arr[j] = arr[j-1];
                }else{
                    break;
                }
            arr[j]=tmp;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试InsertionSort
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 5, 15);
        Dump.array(arr);
        Generate.testSort("Algorithm.DataStructure.Sort.InsertionSort", arr);
        Dump.array(arr);
    }

}

/**
 * todo: train 1
 */
class train_insert_1{
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试InsertionSort
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 5, 15);
        Dump.array(arr);
        train(arr);
        Dump.array(arr);
    }

    private static void train(Comparable[] arr){
        int n = arr.length;
        //todo: [0, i-1] 是有序区间 [0,i)
        for (int i=0; i<n; i++){
            //todo: 把 i 插入到 [0, i] 合适位置
            for (int j=i; j>=1; j--){ //todo:注意 j 到 1为止
                if (arr[j-1].compareTo(arr[j]) > 0){
                    swap(arr, j-1, j);
                }else {
                    break;
                }
            }

        }
    }
}

class train_insert_2{
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //todo: [0, i) 有序
    private static void insertSort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++){
           for (int j = i; j >= 1; j--){ //todo: [0, j) 逆序交换
               if (arr[j].compareTo(arr[j - 1]) < 0){
                   swap(arr, j - 1, j);
               }else {
                   break;
               }
           }
        }
    }

    // 测试InsertionSort
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 5, 15);
        Dump.array(arr);
        insertSort(arr);
        Dump.array(arr);
    }
}