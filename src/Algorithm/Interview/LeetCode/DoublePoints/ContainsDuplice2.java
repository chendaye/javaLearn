package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 
 * nums [i] 和 nums [j] 的差的绝对值最大为 t，
 * 并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsDuplice2 {
    public static void main(String[] args) {
        int[] array = {1,5,9,1,5,9};
        Dump.array(array,true);
        Dump.dump(Solution(array, 3,2));
    }

    public static boolean Solution(int[] array, int t,int k){
        int l=0,r=0; // [l...r] 区间长度 = r-l+1
        HashMap<Integer, Integer> record = new HashMap<>();
        while (r<array.length && l<=r){
            if (r-l <= k){
                //todo:判断窗口有没有满足条件的元素
                if (abs(record, array[r], t)){
                    return true;
                }else {
                    // todo:一定要注意是否越界，判断循环里条件分支是否一定被执行到
                    record.put(array[r], record.containsKey(array[r]) ?record.get(array[r])+1 : 1);
                    r++;
                }
            }else {
                record.put(array[l],record.get(array[l])-1);  //todo 移出滑动窗口的要-1
                l++;
            }
        }
        return false;
    }


    /**
     * todo:判断区间内 有没有 跟 r的差的绝对值 <=t的元素
     * @param record
     * @param target
     * @param t
     * @return
     */
    public static boolean abs(HashMap<Integer, Integer> record,int target, int t){

        Iterator<Map.Entry<Integer, Integer>> iterator = record.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() > 0 && Math.abs(next.getKey()-target)<=t)
                return true;
        }
        return false;
    }
}
