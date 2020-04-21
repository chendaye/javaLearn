package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;
import javafx.scene.SnapshotResult;

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
                if (arr[j].compareTo(arr[index]) < 0)
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


        Integer[] arr = Generate.generateRandomArray(100, 1, 100);
        SelectionSort.sort(arr);

        Dump.array(arr);

        Generate.testSort("Algorithm.DataStructure.Sort.SelectionSort",arr);
    }
}
