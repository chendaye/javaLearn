package Algorithm.Interview.Template;

/**
 * todo: 动态规划模板
 */
public class Dp {
    public static void main(String[] args) {

    }

    /**
     * todo: 连续dp
     * @param nums
     * @return
     */
    public int dp1(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * todo: 区间dp: dp[i] 由 dp[0 ~ i-1] 推出， 不连续
     *          最长递增子序列
     *                  dp状态： dp[i] : [0, i] 范围内最长递归子序列
     *                  base case ： dp[0] = 1;
     *                  状态转移： dp[i] = max{dp[j] + 1,  num[i] > num[j], 0 <= j < i}
     *                  
     * todo: 对于一维dp，基本都是 区间dp
     */
    public static int dp2(int[] nums) {
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



}
