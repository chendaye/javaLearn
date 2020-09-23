package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

import java.util.Arrays;

/**
 * todo: 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class findNumberOfLIS {
    /**
     * todo: 区间dp
     *      dp状态： dp[i] : [0, i] 范围内最长递归子序列
     *        base case ： dp[0] = 1;
     *        状态转移： dp[i] = max{dp[j] + 1,  num[i] > num[j], 0 <= j < i}
     * @param nums
     * @return
     */
    public static int dp(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int[] dp = new int[n]; //dp[i] = 以 nums[i] 结尾的最长递增子序列的长度
        int[] counts = new int[n]; //count[i] = 以nums[i] 结尾的最长递增子序列的个数
        Arrays.fill(counts, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }

        int longest = 0, ans = 0;
        for (int length: dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < n; ++i) {
            if (dp[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] arr = {1,3,5,4,7};
        int[] arr = {2,2,2,2,2};
        System.out.println(dp(arr));
    }
}
