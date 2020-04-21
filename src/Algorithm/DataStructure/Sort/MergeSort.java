package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        Generate.testSort("Algorithm.DataStructure.Sort.MergeSort", arr);
        Dump.array(arr);

        Dump.dump("+++++++++++++++++++++++++++++++");
        Integer[] test = Generate.generateRandomArray(10, 1, 30);
        Dump.array(test);
        sortInsert(test, 5, 9);
        Dump.array(test);
    }


    /**
     * todo: 插入排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sortInsert(Comparable[] arr, int l, int r){
        //todo: 插入排序，前面部分有序； 第一个元素默认是有序；所以从 l+1 开始
        for( int i = l + 1 ; i <= r ; i ++ ){
            Comparable e = arr[i];
            int j;
            //todo: [l, i) 找插入的位置
            for(j=i ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    /**
     * todo: O(nlogn)
     * @param arr
     */
    public static void sort(Comparable[] arr){
//        mergeSort(arr, 0, arr.length - 1);  //todo:自顶向下
        mergeSortUp(arr); //todo:自底向上
    }

    /**
     * todo: 自顶向下的 归并排序
     * todo:递归使用 归并排序， 对 [r.....l] 范围元素进行排序
     * @param arr
     * @param r
     * @param l
     */
    private static void mergeSort(Comparable[] arr, int l, int r){

        //todo: 这种是归并到底
        if (l >= r) return; // 归并结束

        //todo: 当剩下的无序的元素很少时，可以用插入排序
//        if (r-l < 15){
//            sortInsert(arr, l, r);
//            return;
//        }


        int mid = (l+r)/2; // 求mid
        mergeSort(arr, l, mid); // 递归对每一段进行归并排序
        mergeSort(arr, mid+1, r);
        //todo: 优化 只有当 前一段大于后面一段时 才需要归并
        //todo: 对于基本有序的数据，加上这一个判断 可以提升性能
        if (arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r); // 归并两段
    }

    /**
     * todo: 自底向上的归并排序
     * @param arr
     */
    private static void mergeSortUp(Comparable[] arr){
        int n = arr.length;
        //todo: 每一轮归并段的小=sz, 没经过一轮 sz 翻倍
        for (int sz=1; sz<= n; sz=sz*2){
            //todo: 按 sz 大小 给数组分段， 没 2个sz进行归并
            for (int i=0; i+sz-1<n; i += sz*2)
                merge(arr, i, i+sz-1, Math.min( i+2*sz-1 , n-1)); // todo:注意越界问题
        }
    }


    /**
     * todo: 归并逻辑
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Comparable[] arr, int l, int mid, int r){
        //todo: 复制原数组 [l.....r] 位置的元素， 用来排序
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r+1);
        int i=l,j=mid+1;
        for (int k=l; k<=r; k++){
            if (i>mid){
                /**
                 *       0 1 2 3 4 5 6 7
                 * index 0 1 2 3 4 5 6 7
                 *       3 4 5 6 7
                 * index 0 1 2 3 4
                 *
                 * 临时数组的偏移量是 l
                 */
                arr[k] = tmp[j-l]; //todo: j-l 是 j 相对 位移
                j++;
            }else if (j>r){
                arr[k] = tmp[i-l];
                i++;
            }else{
                if (tmp[j-l].compareTo(tmp[i-l]) < 0){
                    arr[k] = tmp[j-l];
                    j++;
                }else{
                    arr[k] = tmp[i-l];
                    i++;
                }
            }
        }
    }
}
