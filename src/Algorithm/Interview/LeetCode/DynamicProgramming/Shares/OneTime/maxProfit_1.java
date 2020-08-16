package Algorithm.Interview.LeetCode.DynamicProgramming.Shares.OneTime;

import java.util.Stack;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 类似 单调栈
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * https://leetcode-cn.com/problems/maximal-rectangle/
 *
 *
 * todo: 动态规划
 */
public class maxProfit_1 {
    public static void main(String[] args) {
//        int[] arr = {7,6,4,3,1};
        int[] arr = {7,1,5,3,6,4};

        System.out.println(dp2(arr));
    }
    /**
     * todo: 动态规划: 交易一次
     *      - 首先我遍历价格数组，将每天的价格减去前一天的价格，构建一个利润数组profit[]记录每一天的利润 遍历利润数组，
     *      - 利用动态规划的思想，求利润数组的最大子数组的和，即为最大利润
     * @param prices
     * @return
     *
     * todo: 交易次数 k=1
     *  - base case：
     *  - dp[-1][k][0] = dp[i][0][0] = 0
     *  - dp[-1][k][1] = dp[i][0][1] = -infinity
     *  - 状态转移方程：
     *  -  dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *  -  dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     *  todo: dp[i][j][k]
     *      - i : 第 i天
     *      - j: 0~i 一共最多交易了 j 次
     *      - k： 0：没有持有 1： 有持有
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            // todo: 当前没有持有股票： ① 前一天也没有持有股票、现在 rest ② 前一天持有股票、现在sell
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i - 1]);
            // todo: 当前持有股票： ① 前一天持有、现在 rest ② 前一天不持有、现在 buy
            //  （注意：因为只能买一次，所以之前不可能买；所以当前是唯一一次买入 成本 -prices[i - 1]）
            dp[i][1] = Math.max(dp[i-1][1],  -prices[i - 1]);
        }
        return dp[n][0];
    }

    public static int dp2(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][2];
        // 第 0 天交易情况
        for (int i = 0; i < 2; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = Integer.MIN_VALUE;// 第 0 天不可能持有股票 用负无穷表示
        }
        //todo： 买入的时候 记为交易一次
        for (int i = 1; i <= n; i++) {
            //todo: 0~i 天 、最多可以交易一次、不持有股票：①前一天就不持有股票，且现在 rest ②前一天持有股票，现在 sell（sell 不计为交易）
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i -1]);
            // todo: ①前一天就持有股票，且现在rest ② 前一天不持有，现在 buy（记一次交易）
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
        }
        //todo: 0~n天，最多可以好意 1 次，最终不持有股票
        return dp[n][1][0];
    }
}

