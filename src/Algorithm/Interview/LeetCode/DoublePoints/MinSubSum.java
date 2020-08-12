package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;
import Utils.Generate;

/**
 *
 * todo:滑动窗口
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSubSum {
    public static void main(String[] args) {
        int[] array = new int[20];
        array = Generate.get(10,10,false,false);
        Dump.array(array,true);

        int[] test = {2,3,1,2,4,3};
        Dump.dump(Solution(test, 7));
    }

    /**
     * 用两个指针做一个滑动窗口
     * @param array
     * @param s
     * @return
     */
    public static int Solution(int[] array, int s){
        int l=0,r=0; // [l....r]
        int len = array.length;
        if (len == 0)
            return 0;
        int min=len+1; // 最小长度
        int sum = array[0];
        while (l<=r && r<len){
            if (array[l] == s || array[r] == s)
                return 1;
            if (sum < s){
                r++; // 可能会有越界的问题
                if (r<len)
                    sum += array[r];
                else
                    break;
            }else{
                if (min > r-l+1)
                    min = r-l+1;
                sum -= array[l++];
            }
        }
        return min==len ? 0 : min;
    }

}
