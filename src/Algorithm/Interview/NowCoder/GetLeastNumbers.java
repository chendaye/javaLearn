package Algorithm.Interview.NowCoder;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbers {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input.length == 0 || input.length < k) return list;
        heapSort(input);
        for (int i = 0; i < k; i++)
            list.add(input[i]);
        return list;
    }

    //todo: 堆排序
    private void shiftDown(int[] arr, int l, int r){
        if (l == r) return;
        while((2 * l + 1) <= r){ //todo: 从第一个父节点开始
            int i = 2 * l + 1;
            if (i + 1 <= r && arr[i] < arr[i + 1])
                i++;
            if (arr[l] > arr[i]) return;  //todo: 如果根已经是最大循环结束
            int tmp = arr[l];
            arr[l] = arr[i];
            arr[i] = tmp;
            l = i;
        }
    }

    private void heapSort(int[] arr){
        int n = arr.length - 1;
        //todo: 从最后一个父节点开始 shiftDown
        for (int i  = (n - 1) / 2; i >= 0; i--)
            shiftDown(arr, i, n);
        while (n >= 0){
            int tmp = arr[0];
            arr[0] = arr[n];
            arr[n] = tmp;
            n--;
            shiftDown(arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,5,1,6,2,7,3,8};
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
//        getLeastNumbers.heapSort(arr);
//        Dump.array(arr);

        getLeastNumbers.GetLeastNumbers_Solution(arr, 4);
    }
}
