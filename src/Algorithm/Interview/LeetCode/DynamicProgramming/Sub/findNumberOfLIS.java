package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
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
class findnumberOfLIS {
    public static int findnumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int[] dp = new int[n]; //dp[i] = length of longest ending in nums[i]
        int[] counts = new int[n]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                if (nums[i] < nums[j]) {
                    if (dp[i] >= dp[j]) {
                        dp[j] = dp[i] + 1;
                        counts[j] = counts[i];
                    } else if (dp[i] + 1 == dp[j]) {
                        counts[j] += counts[i];
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
        System.out.println(findnumberOfLIS(arr));
    }
}
