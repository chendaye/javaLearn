package Algorithm.Interview.NowCoder;

import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 *
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class GetMedian {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    public void Insert(Integer num) {
        queue.add(num);
    }

    public Double GetMedian() {
        int size = queue.size();
        int[] num = new int[size];
        int i = 0;
        PriorityQueue<Integer> temp = new PriorityQueue<>();
        temp.addAll(queue);
        while (!temp.isEmpty())
            num[i++] = temp.poll();
        if (size % 2 == 0){
            // 0 1 2 3 4 5 6
            return (double)(num[size / 2] + num[size / 2 - 1]) / 2;
        }else{
            // 5 2
            return (double) num[size / 2];
        }
    }

    public static void main(String[] args) {
        System.out.println((double) 7/2);
        System.out.println(7/2);
    }
}
