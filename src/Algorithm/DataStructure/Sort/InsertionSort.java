package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import java.util.*;

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
        for (int i = 0; i < n; i++) {
            for( int j = i ; j > 0 ; j -- )
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