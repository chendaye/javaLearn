package Algorithm.Interview.NowCoder;

import Utils.Dump;

import java.util.ArrayList;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种 连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你
 * 能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class FindContinuousSequence {
    //todo: O(n^2)
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 1; i < sum; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            int plus = 0;
            for (int j = i; j < sum; j++){
                if (plus < sum){
                    plus += j;
                    temp.add(j);
                }else
                    break;
            }
            if (plus == sum)
                result.add(temp);
        }
        return result;
    }

    //todo: 滑动窗口
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        int p = 1,q = 2; //todo: 窗口 [p, q]
        int plus = 3;
        while (p < q && q < sum){
            if (plus < sum){
                plus += ++q;
            }else if(plus > sum){
                plus -= p++;
            }else {
                //todo: 注意引用变量带来的问题
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = p; i <= q; i++) list.add(i);
                result.add(list);
                plus -= p++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindContinuousSequence findContinuousSequence = new FindContinuousSequence();
        ArrayList<ArrayList<Integer>> lists = findContinuousSequence.FindContinuousSequence2(9);
        for (ArrayList<Integer> list: lists) {
            Dump.array(list.toArray());
        }
    }
}
