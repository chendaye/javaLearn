package Algorithm.Interview.LeetCode.DynamicProgramming.BackPack;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * todo: 类似 213 337 309
 */
public class HouseRobber {
    public static void main(String[] args) {

    }

    /**
     * todo: 动态规划======》 递归函数/状态 要清晰
     *      - 状态的定义 就是 递归函数的定义
     *      - 要搞清楚，递归函数是在干嘛。 它可以是一个中间状态
     *  todo: 这里递归函数的定义  也就是状态：  考虑 [x......len) 范围内可以偷到的最大值
     *          状态也可以改成  考虑 [0..............x) 范围内可以获取的最大值
     *
     *  todo: 动态规划
     *      - 状态： dp[i] : [i, n-1] 范围内偷到的最大值
     *      - base case ： dp[n - 1] = nums[n-1]
     *      - 状态转移： dp[i] = max{num[x] + dp[x+2] | i <= x <= n-1}
     * @param nums
     * @return
     */
    public static int dp(int[] nums){
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        //todo: 考虑最后一个房子，也就是区间 [len-1.......len-1]
        dp[len-1] = nums[len-1];
        for (int i=len-2; i>-1; i--){
            for (int j=i; j<len; j++)
                dp[i] = Math.max(dp[i], nums[j]+ (j+2 < len ? dp[j+2] : 0));
        }
        return dp[0];
    }

    public static int rob(int[] nums) {
        //todo: 记忆数组, 考虑某个房子时的最大收益
        int[] record = new int[nums.length];
        Arrays.fill(nums, -1);
        return tryRob(nums,0, record);
    }

    /**
     * todo: 考虑 [index....nums.length) 范围内的房子
     *      所谓状态 就是递归函数的定义
     * @param nums
     * @param index
     * @return
     */
    public static int tryRob(int [] nums, int index,  int[] record){
        if (record[index] >= 0) return record[index];
        if (index >= nums.length)
            return 0;
        int money = 0;
        for (int i=index; i< nums.length; i++){
            money = Math.max(money,  nums[i] + tryRob(nums, i+2, record));
        }
        record[index] = money;
        return money;
    }


}
