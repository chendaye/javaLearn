package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

import Utils.Dump;

/**
 *给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * todo:类似 322 377 474 139 494
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
//        int[] nums = {1, 5, 11, 5};
        int[] nums = {1, 2, 3, 5};
        Dump.dump(canPartition(nums));
    }

    /**
     *
     * todo:关键点
     *      - 状态定义 F(i, c) = dp[i][c] 是什么？ 是前提
     *      - 它是原问题 的 子问题的解
     *      - 用 [0...i] 这么多数字中，能否找到一个子集； 去刚好填充 容量 c
     *
     * todo: 题目特征：
     *          - 从集合选择子集（组合）
     *          - 动态规划
     *
     * todo: 题目转化： 集合分成2部分 和相等 ====> 取出一部分元素的和= 所有元素的和/2
     *          - 如此转化为： 从集合中选择若干元素，放入 容量为  所有元素的和/2 的背包中
     *
     *  todo:  F(i, c) 状态定义 ：[0....i] 范围内 的数字 能否,找到一个子集， 填充 c/2 这么大的空间
     *      - 注意  F(i, c) 是一个递归子问题，解决的问题与题干描述一致，只是范围有别
     *      - 状态转移方程(元素 i 是否放进去)： F(i, c) = F(i-1, c) || F(i-1, c-w(i))
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length; // todo:元素个数
        if (n == 0) return true;
        int sum = 0;
        for (int i=0; i<n; i++)
            sum += nums[i];
        if (sum%2 != 0) return false;
        int c = sum/2; //todo:容量
        boolean[][] dp = new boolean[n][c+1];
        //todo:F(i, c) = F(i-1, c) || F(i-1, c-w(i))
        for (int i=1; i<=c; i++)
            dp[0][i] = nums[0] == i;

        for (int i=1; i<n; i++){
            for (int j=1; j<=c; j++){
                dp[i][j] = dp[i-1][j] || (j-nums[i] >= 0 ? dp[i-1][j-nums[i]] : false);
            }
        }
        return dp[n-1][c];
    }
}
