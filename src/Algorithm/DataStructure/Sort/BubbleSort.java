package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import java.util.Arrays;

public class BubbleSort {

    // 我们的算法类不允许产生任何实例
    private BubbleSort(){}

    /**
     *优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
     *所以下一次排序, 最后的元素可以不再考虑
     * @param arr
     */
    public static void sort1(Comparable[] arr){
        int n = arr.length;
        boolean swapped = false;
        do{
            swapped = false;
            for( int i = 1 ; i < n ; i ++ )
                if( arr[i-1].compareTo(arr[i]) > 0 ){
                    swap( arr , i-1 , i );
                    swapped = true;
                }
            n --;
        }while(swapped);
    }


    /**
     * todo: do{}while()  + for
     *记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
     * @param arr
     */
    public static void sort2(Comparable[] arr){
        int n = arr.length;
        int newn; // 使用newn进行优化
        do{
            newn = 0;
            //todo: 从 [0...n) 开始冒泡， 把最大的放在后面
            for (int i=1; i<n; i++){
                if (arr[i-1].compareTo(arr[i]) > 0){
                    swap(arr, i-1,i);
                    newn=i;
                }
            }
            n = newn;
        }while (newn > 0);
    }


    /**
     * 手写
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int len = arr.length;
        int loc = 0;
        do{
            //todo: 从 [0...len) 开始冒泡， 把最大的放在后面
            for (int i=1; i<len; i++){
                if (arr[i-1].compareTo(arr[i]) > 0){
                    swap(arr,i-1,i);
                }
            }
        loc--;
        }while (loc >= 0);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] integers = Generate.generateRandomArray(10, 1, 20);
        Dump.array(integers);
        Generate.testSort("Algorithm.DataStructure.Sort.BubbleSort", integers);
        Dump.array(integers);
    }
}
