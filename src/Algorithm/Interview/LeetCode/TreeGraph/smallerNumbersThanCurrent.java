package Algorithm.Interview.LeetCode.TreeGraph;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 *
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 *
 * 以数组形式返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 *
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 *
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class smallerNumbersThanCurrent {
    public static void main(String[] args){
        int[] nums = {8,1,2,2,3};
        Dump.array(method2(nums));
    }
    //todo: 暴力求解
    public static int[] method1(int[] nums){
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            int cnt = 0;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] < nums[i]) cnt++;
            }
            ans[i] = cnt;
        }
        return ans;
    }

    /**
     * todo: 计数法
     *      - 思想：0 <= nums[i] <= 100， 构造一个 大小为101的数组
     *              统计每个位置出现的次数
     * @param nums
     * @return
     */
    public static int[] method2(int[] nums){
        int[] record = new int[101];
        //todo: 计数
        for (int i = 0; i < nums.length; i++) record[nums[i]]++;

        //todo: 累加计数数组
        for (int i = 1; i < record.length; i++){
            record[i] = record[i - 1] + record[i];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            if (nums[i] >= 1) ans[i] = record[nums[i] - 1];
        }
        return ans;
    }
}
