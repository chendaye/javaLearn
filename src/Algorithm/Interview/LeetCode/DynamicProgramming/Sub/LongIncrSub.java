package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

import Utils.Dump;

/**
 *
 * todo: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 *
 *  输入: [10,9,2,5,3,7,101,18]
 *  输出: 4
 *  解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *  说明:
 *
 *  可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *  你算法的时间复杂度应该为 O(n2) 。
 *
 * todo:类似 376
 */
public class LongIncrSub {
    public static void main(String[] args) {
        int[] test = {1,3,2};
//        int[] test = {1,3,6,7,9,4,10,5,6};
//        Dump.dump(lengthOfLIS(test));
        Dump.dump(dp(test));
    }

    /**
     *
     * todo: 区间dp: dp[i] 由 dp[0 ~ i-1] 推出， 不连续
     *
     * todo: 状态 ： dp[i]： [0, i] 范围内最长递增子序列
     *       base case： dp[0] = 1
     *       转移： dp[i] = Max{dp[x], 0<=x<i nums[x] < nums[i]}
     * @param nums
     * @return
     */
    public static int dp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            maxans = Math.max(dp[i], dp[i]);
        }
        return maxans;
    }

    /**
     * todo:  递归/动态规划 特点
     *      - 从一个集合中选择最优解（组合）
     *      - 递归可以改写成 记忆搜索
     *      - 记忆搜索可以改成 动态规划
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int len = 0;
        int[] record = new int[nums.length];
        //todo: 在主函数中尝试 每一个 起点
        //todo: 在递归中，解决这个起点 对应的子问题
        for (int i=0;i<nums.length;i++){
            len = Math.max(len, find(nums, i, record));
        }
        return len;
    }

    /**
     * todo: [index......nums.length-1] 范围的最大递增子序列
     * @param nums
     * @param index
     * @return
     */
    public static int find(int[] nums, int index, int[] record){
        if (index > nums.length-1) return 0;
        if (index == nums.length-1) return 1;

        if (record[index] > 0) return record[index]; // 记忆数组中有值，直接返回
        int len = 0;
        for (int i=index+1; i<nums.length; i++){
            // 找到一个 比 index 大的元素 i， 看从 i开始 最长递增子序列
            // 从 index 之后的 所有递增子序列中找一个最大的
            if (nums[i]>nums[index]) len = Math.max(len, find(nums, i, record));
        }
        record[index] = len+1;  // 设置 record[index]
        return len+1;
    }



}
