package Algorithm.Interview.LeetCode.DynamicProgramming.Shares.KTime;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 *
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxProfit_4 {
    public static void main(String[] args) {
        int[] arr = {3,2,6,5,0,3};
        System.out.println(maxProfit(2, arr));
    }
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        //todo: 在 n 天内顶多 交易 (n + 1) / 2 次
        if(k > (n + 1) / 2){
            return maxProfit(prices);
        }
        int[][][] dp = new int[n + 1][k + 1][2]; //todo: [0, n] [0, k]
        //todo： 在第 0 天交易
        for (int i = 0; i <= k; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = Integer.MIN_VALUE;
        }

        //TODO： 最多交易 0次
        for (int i = 0; i <= n; i++){
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= k; j++){
                //todo: ①前一天没有持有、现在rest ②前一天持有、现在 sell
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
                //todo: ①前一天持有、现在 rest ② 前一天没有持有、现在 buy （buy 记为一次交易）
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp[n][k][0];
    }

    //todo: 交易次数=无限次的情况
    public static int maxProfit(int[] prices) {
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
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }
        //todo: 0~n天，最多可以好意 1 次，最终不持有股票
        return dp[n][0];
    }
}
