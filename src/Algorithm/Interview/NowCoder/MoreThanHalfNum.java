package Algorithm.Interview.NowCoder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
 * 超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNum {

    public int MoreThanHalfNum_Solution(int [] array) {
        int ret = 0;
        if (array.length == 0) return ret;
        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i:array){
            if (record.containsKey(i)){
                record.put(i, record.get(i) + 1);
            }else{
                record.put(i, 1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = record.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() > array.length / 2){
                ret = next.getKey();
            }
        }
        return ret;
    }

    /**
     *
     * 加入数组中存在众数，那么众数一定大于数组的长度的一半。
     * 思想就是：如果两个数不相等，就消去这两个数，最坏情况下，每次消去一个众数和一个非众数，那么如果存在众数，最后留下的数肯定是众数。
     *
     * 具体做法：
     *
     * 初始化：候选人cond = -1， 候选人的投票次数cnt = 0
     * 遍历数组，如果cnt=0， 表示没有候选人，则选取当前数为候选人，++cnt
     * 否则，如果cnt > 0, 表示有候选人，如果当前数=cond，则++cnt，否则--cnt
     * 直到数组遍历完毕，最后检查cond是否为众数
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution2(int [] array) {
        if (array.length == 0) return 0;
        int cnt = 0;
        int n = array[0];
        for (int i = 0; i < array.length; i++){
            if (cnt == 0){
                n = array[i];
                cnt++;
            }else {
                if (array[i] == n)
                    cnt++;
                else
                    cnt--;
            }
        }
        cnt = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] == n)
                cnt++;
        return cnt > array.length / 2 ? n : 0;
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,2,2,2,5,4,2};

        MoreThanHalfNum moreThanHalfNum = new MoreThanHalfNum();

        System.out.println(moreThanHalfNum.MoreThanHalfNum_Solution2(num));
    }
}
