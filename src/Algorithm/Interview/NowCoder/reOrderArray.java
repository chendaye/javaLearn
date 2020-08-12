package Algorithm.Interview.NowCoder;

import Utils.Dump;
import Utils.Generate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class reOrderArray {

    public void reOrderArray0(int [] array) {
        int n = array.length;
        int l = 0;
        int r = n - 1; // 6 2 1 3 1

        while (true){
            while ((array[l] & 1) == 1)
                l++;
            while ((array[r] & 1) == 0)
                r--;
            if (l >= r) break;
            Integer tmp = array[l];
            array[l++] = array[r];
            array[r--] = tmp;
        }
    }
    //todo: [0, l) (r, n-1]
    public void reOrderArray(Integer[] array) {
        int n = array.length;
        int l = 0;
        int r = n - 1; // 6 2 1 3 1

        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();
        int i = 0;
        while (i < n){
            if ((array[i] & 1) == 1){
                l1.add(array[i]);
            }else {
                l2.add(array[i]);
            }
            i++;
        }

        l1.addAll(l2);
        Iterator<Integer> iterator = l1.iterator();
        int j = 0;
        while (iterator.hasNext()){
            array[j++] = iterator.next();
        }
    }

    //todo: 稳定的排序算法：选择 冒泡 插入 归并 可以采用排序的思想来解决
    public void reOrderArrayBubble(Integer[] array){
        int n = array.length;
        // 冒泡排序的思想
        do {
            for (int i = 1; i < n; i++){
                if ((array[i-1] & 1) == 0 && (array[i] & 1) == 1){
                    Integer tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                }
            }
            n--;
        }while (n >= 0);
    }



    public static void main(String[] args) {
        reOrderArray reOrderArray = new reOrderArray();
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        reOrderArray.reOrderArrayBubble(arr);
        Dump.array(arr);
    }
}
