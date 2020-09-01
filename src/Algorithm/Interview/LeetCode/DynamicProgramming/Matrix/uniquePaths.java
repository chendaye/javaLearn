package Algorithm.Interview.LeetCode.DynamicProgramming.Matrix;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * todo: m 行 n 列  下 （1,0） 右 （0，1）
 *  -----> y（n）
 *  |
 *  |x（m）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class uniquePaths {
    /**
     * todo:
     *       - 状态 dp[i][j] : dp[0][0]  到 dp[i][j] 的路线总数
     *       - 选择 dp[i][j] = dp[i-1][j] + dp[i][j-1] { i-1 >= 0 ,  j-1 >= 0}
     *       - base case 沿着x y轴
     * @param m
     * @param n
     * @return
     *
     * todo: 独立完成
     */
    public int dp(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        // m = 0, n = 0~n
        for(int i = 1; i < n; i++)
            dp[0][i] = dp[0][i - 1];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0];
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (i - 1 >= 0)
                    dp[i][j] += dp[i - 1][j];
                if (j - 1 >= 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int m = 3, n = 2;
//        int m = 7, n = 3;
//        int m = 1, n = 1;
        int dp = new uniquePaths().dp(m, n);
        System.out.println(dp);
    }
}
