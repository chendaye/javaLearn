package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;


/**
 * todo: 冒泡排序 要点
 *      - [m-1, n) 有序区
 *      - 外层 do{}while() 里层 for
 *      - i=1 比较交换 swap(arr[i-1], arr[i])
 *      - [0,m] 是无序的， for 一趟 m--
 *
 * todo: 优化
 *      - 设置一个标志位 flag=true 表示for 一趟 有发生过交换， flag=false 时 表示没有交换过，排序完成
 */
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
            for( int i = 1 ; i < n ; i ++ ) // todo: 把 i-1 冒泡到正确的位置
                if( arr[i-1].compareTo(arr[i]) > 0 ){
                    swap( arr , i-1 , i );
                    swapped = true; //todo: 没有发生交换说明已经 排序完成了
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


/**
 * todo: train 1
 */
class train_bubble_1{
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void train(Integer[] arr){
        int n = arr.length;
        do{
            for (int i=1; i<n; i++){
                if (arr[i-1] > arr[i]){
                    swap(arr, i-1, i);
                }
            }
            n--;
        }while (n >=0);
    }

    public static void main(String[] args) {
        Integer[] integers = Generate.generateRandomArray(10, 1, 20);
        Dump.array(integers);
        train(integers);
        Dump.array(integers);
    }
}

class train_bubble_2{
    private static void swap(Object[] arr, int l, int r){
        Object tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    private static void bubbleSort(Comparable[] arr){
        int n = arr.length - 1;
        do{
            for (int i = 0; i < n; i++){
                if (arr[i].compareTo(arr[i+1]) > 0)
                    swap(arr, i, i+1);
            }
            n--;
        }while (n >= 0);
    }

    public static void main(String[] args) {
        Integer[] integers = Generate.generateRandomArray(10, 1, 20);
        Dump.array(integers);
        bubbleSort(integers);
        Dump.array(integers);
    }
}