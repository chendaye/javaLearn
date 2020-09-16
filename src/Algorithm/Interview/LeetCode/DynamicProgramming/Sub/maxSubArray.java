package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxSubArray {
    /**
     * todo: 典型的 在 dp table 上找到最大值， 而不是取 dp[n-1]
     *      - 状态：dp[i]  以 nums[i] 结尾的 最大子数组
     *      - 选择：dp[i]  = Max{dp[i - 1] + nums[i],   nums[i]}
     *      - base case  dp[0] = nums[0]
     *      - dp的过程 一定是一个状态地推的过程； 当前状态由前一个状态得到；这就要求当前状态与 i密切相关
     *      - 但是题目要求的解 不一定是最终的地推结果； 因为题目要求的解经过转换之后 变成dp问题
     * @param nums
     * @return
     *
     * todo：独立完成
     */
    public int dp(int[] nums) {
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

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4,100};
        int dp = new maxSubArray().dp(arr);
        System.out.println(dp);
    }
}
