package Algorithm.Interview.LeetCode.DynamicProgramming.Matrix;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 以 matrix[i][j] 为右下角的最大矩形面积
 *      - 状态 dp[i][j] :  （一行中）以 matrix[i][j] 结束的 最大宽度（连续的1）
 *      - 选择:   dp[i][j] = j==0 ? 1 : dp[i][j - 1] + 1;  matrix[i][j] == '1'
 *      - base case:
 */
public class maximalRectangle {
    /**
     * 将输入拆分成一系列的柱状图，每个柱状图代表一列的子结构。为了计算长方形的最大面积，
     * 我们仅仅需要计算每个柱状图中的最大面积并找到全局最大值（注意后面的解法对每一行而非列建立了柱状图，两者思想一致）。
     *
     * @param matrix
     * @return
     */
    public int dp(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){ //todo: matrix[i][j]=1 才是矩形才有意义

                    //todo: 每一行 以 matrix[i][j] 结尾的最大宽度
                    dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;
                    int width = dp[i][j]; //todo: 第 i 行， 到第 j 列为止的最大宽度

                    //todo: 计算以 [i, j] 为 右下角的最大矩形面积
                    //todo: [0, 0] ~ [i, j] 这个矩形范围内，形成一个 以宽为高的柱状图
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]); //todo: 求最矮的柱子
                        maxarea = Math.max(maxarea, width * (i - k + 1)); //todo: 宽 * 高 = width * (i - k + 1)
                    }
                }
//                else{
//                    dp[i][j] = 0;
//                    maxarea = Math.max(maxarea, 0);
//                }
            }
        } return maxarea;
    }
}
