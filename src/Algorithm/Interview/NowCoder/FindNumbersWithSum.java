package Algorithm.Interview.NowCoder;

import Utils.Dump;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array.length < 2) return list;
        int mult = Integer.MAX_VALUE;
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] >= sum) break;
            int left = sum - array[i];
            for (int j = i + 1; j < array.length; j++){
                if (array[j] == left && array[j] * array[i] < mult){
                    mult = array[j] * array[i];
                    list.clear();
                    list.add(array[i]);
                    list.add(array[j]);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = {1,2,4,6,11,16};
        int sum = 11;

        Dump.iterator(new FindNumbersWithSum().FindNumbersWithSum(array, sum));
    }
}
