package Algorithm.Interview.LeetCode.DynamicProgramming.Matrix;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class uniquePathsWithObstacles {
    /**
     * todo:  状态：dp[i][j] 从起始点到达 obstacleGrid[i][j] 的路径数
     * todo:  选择：
     *          - obstacleGrid[i][j] = 0
     *              dp[i][j] = 0
     *          - obstacleGrid[i][j] != 0
     *              dp[i][j] = dp[i - 1][j] + dp[i][j - 1]  {obstacleGrid[i - 1][j] != 1 , obstacleGrid[i][j - 1] != 1}
     * todo:  base case:  dp[0][0] = 1
     * @param obstacleGrid
     * @return
     */
    public int dp(int[][] obstacleGrid) {
        int m = obstacleGrid.length; //行
        int n = obstacleGrid[0].length; //列
        if (m == 0 || n == 0 || obstacleGrid[0][0] == 1) return 0;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        // m = 0, n = 0~n
        for(int i = 1; i < n; i++)
            dp[0][i] = obstacleGrid[0][i - 1] == 0 && obstacleGrid[0][i] == 0 ? dp[0][i - 1] : 0;
        for (int i = 1; i < m; i++)
            dp[i][0] = obstacleGrid[i - 1][0] == 0 && obstacleGrid[i][0] == 0 ? dp[i - 1][0] : 0;
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (i - 1 >= 0 && obstacleGrid[i - 1][j] == 0 && obstacleGrid[i][j] == 0)
                    dp[i][j] += dp[i - 1][j];
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0  && obstacleGrid[i][j] == 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] arr = {{0,0,0}, {0,1,0}, {0,0,0}};
//        int[][] arr = {{0,0,0}, {0,0,0}, {0,0,0}, {0,1,0}, {0,0,0}, {0,0,0}, {0,0,0}};
//        int[][] arr = {{0,1}};
        int dp = new uniquePathsWithObstacles().dp(arr);
        System.out.println(dp);
    }
}
