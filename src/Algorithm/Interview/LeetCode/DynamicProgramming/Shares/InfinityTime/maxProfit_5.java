package Algorithm.Interview.LeetCode.DynamicProgramming.Shares.InfinityTime;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxProfit_5 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,0,2};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;
        int[][] dp = new int[n + 1][2];
        //todo: 第 0 天交易情况; 可以交易N多次， 交易次数没有影响
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;// 第 0 天不可能持有股票 用负无穷表示
        dp[1][0] = 0;
        dp[1][1] = -prices[0]; // 第一天买入
        //todo： 买入的时候 记为交易一次
        for (int i = 2; i <= n; i++) {
            //todo: 0~i 天 、不持有股票：①前一天就不持有股票，且现在 rest ②前一天持有股票，现在 sell
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i -1]);
            // todo: ①前一天就持有股票，且现在rest ② 前二天（两次买之间要间隔一天）不持有，现在 buy
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i - 1]); //todo: 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
        }
        //todo: 0~n天，最多可以好意 1 次，最终不持有股票
        return dp[n][0];
    }
}
