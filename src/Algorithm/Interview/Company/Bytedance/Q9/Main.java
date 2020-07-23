package Algorithm.Interview.Company.Bytedance.Q9;

import LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
 *
 * 区间中的最小数 * 区间所有数的和
 * 最后程序输出经过计算后的最大值即可，不需要输出具体的区间。
 * 如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
 *
 *
 *
 * [6] = 6 * 6 = 36;
 *
 * [2] = 2 * 2 = 4;
 *
 * [1] = 1 * 1 = 1;
 *
 * [6,2] = 2 * 8 = 16;
 *
 * [2,1] = 1 * 3 = 3;
 *
 * [6, 2, 1] = 1 * 9 = 9;
 *
 *
 *
 * 从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
 *
 * 区间内的所有数字都在[0, 100]的范围内;
 *
 *
 * 输入描述:
 * 第一行输入数组序列长度n，第二行输入数组序列。
 * 对于 50%的数据,  1 <= n <= 10000;
 * 对于 100%的数据, 1 <= n <= 500000;
 *
 * 输出描述:
 * 输出数组经过计算后的最大值。
 *
 * 输入例子1:
 * 3
 * 6 2 1
 *
 * 输出例子1:
 * 36
 */
public class Main {
    //区间最小数*区间所有数的和
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/Bytedance/Q9/test.txt"));
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] record = new int[length];
        for (int i = 0; i < length; i++){
            record[i] = sc.nextInt();
        }
        int max = Integer.MIN_VALUE; // 当前得到的最大值
        int l = 0, r = 0; //todo: 区间 [l, r)
        ArrayList<Integer> window = new ArrayList<>();
        while (r < length){
            window.add(record[r++]); // 进入窗口
            //计算当前窗口和
            Iterator<Integer> iterator = window.iterator();
            int cur_sum = 0; // 当前窗口和
            int cur_min = Integer.MIN_VALUE; // 当前窗口最小值
            while (iterator.hasNext()){
                Integer next = iterator.next();
                cur_min = Math.min(cur_min, next);
                cur_sum += next;
            }
            // 更新 最大和
            int cur = cur_min * cur_sum;
            int tmp = max;
            max = Math.max(max, cur);
            if (tmp == max){
                while (l < r){

                }
            }
        }
    }

    //判断 当前区间和 是否更大
    public static boolean judge(int sum, ArrayList<Integer> list){
        Iterator<Integer> iterator = list.iterator();
        int min = Integer.MAX_VALUE;
        int total = 0;
        while (iterator.hasNext()){
            Integer next = iterator.next();
            min = Math.min(min, next);
            total += next;
        }
        return min * total > sum;
    }
}
