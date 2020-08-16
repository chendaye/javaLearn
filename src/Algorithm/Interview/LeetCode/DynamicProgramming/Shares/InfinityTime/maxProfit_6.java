package Algorithm.Interview.LeetCode.DynamicProgramming.Shares.InfinityTime;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxProfit_6 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(arr, 2));
    }

    /**
     * todo: 交易次数不限 = 交易次数没有影响
     *      - dp[i][j]: 0~i 天 是否持有 的利润
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        //todo: 第 0 天交易情况; 可以交易N多次， 交易次数没有影响
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;// 第 0 天不可能持有股票 用负无穷表示
        //todo： 买入的时候 记为交易一次
        for (int i = 1; i <= n; i++) {
            //todo: 0~i 天 、不持有股票：①前一天就不持有股票，且现在 rest ②前一天持有股票，现在 sell
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i -1]);
            // todo: ①前一天就持有股票，且现在rest ② 前一天不持有，现在 buy
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1] - fee); //todo 每笔交易都要手续费
        }
        //todo: 0~n天，最多可以好意 1 次，最终不持有股票
        return dp[n][0];
    }
}
