package Algorithm.Interview.LeetCode.DynamicProgramming.Matrix;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 独立完成
 * todo: 不同的 dp 空间，base case 求法不同，一定要注意
 *      - dp  空间有冗余； base case求法 被一般化
 *      - dp  空间没有冗余； base case 求法特殊化
 */
public class minPathSum {
    /**
     * todo: 状态 dp[i][j] : (0,0) 到 (i, j) 的最短路径和
     *       选择 dp[i][j] = min{dp[i-1][j], dp[i][j-1]} + grid[i - 1][j - 1]
     *       base case :
     * @param grid
     * @return
     */
    public int dp(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {

//        int[][] arr = {{1,3,1}, {1,5,1}, {4,2,1}};
        int[][] arr = {{}};
        System.out.println(arr.length);
        int dp = new minPathSum().dp(arr);
        System.out.println(dp);
    }
}
