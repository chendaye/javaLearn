package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

import Utils.Dump;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestCommonSub {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        text1 = "hofubmnylkra";
        text2 = "pqhgxgdofcvmr";
//        text1 = "a";
//        text2 = "abcwax";
//        Dump.dump(longestCommonSubsequence(text1, text2));
        Dump.dump(dp(text1, text2));
    }

    /**
     * todo: 主函数 遍历所有 状态
     *      - 换句话说就是， 遍历 状态数组 dp 的每一个位置；
     *      - 并且调用 状态转移函数（递归函数） 求每个位置的值
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int s1 = text1.length();
        int s2 = text2.length();
        int[][] dp = new int[s1][s2];
        if (s1 == 0 || s2 == 0) return 0;
        int res = 0;
        //todo:想象成 遍历 dp[][] 数组， 一行一行赋值
        for (int m=0; m<s1; m++){
            for (int n=0; n<s2; n++){
                res = Math.max(res, common(text1, text2, m, n, dp));
            }
        }
        return res;
    }

    /**
     * todo: [0, m) [0, n) 区间内最长公共子串
     * todo: 状态方程: F(m, n) =
     *          - s1[m] == s2[n]  : 1 + F(m-1, n-1)
     *          - s1[m] != s2[n] : max{F(m, n-1), F(m-1, n)}
     *
     * @param s1
     * @param s2
     * @param m
     * @param n
     * @return
     */
    public static int common(String s1, String s2, int m, int n, int[][] dp){
        if (m<0 || n<0) return 0;
        if (dp[m][n] > 0) return dp[m][n];
        int res = 0;
        if (s1.charAt(m) == s2.charAt(n)){
            res = 1 + common(s1, s2, m-1,n-1, dp);
        }else{
            res = Math.max(common(s1, s2, m, n-1, dp), common(s1, s2, m-1, n, dp));
        }
        dp[m][n] = res;
        return res;
    }

    /**
     * todo:二维动态规划
     *      - 初始化第一行 也要遵循规则
     *      - 可以同时初始化第一行第一列
     *
     * todo: dp[i][j] : [0, i] [0, j] 范围内的公共子序列
     *       dp[i][j] = s[i] == s[j] ?
     *                  1 + (j-1 < 0 ? 0 : +dp[i-1][j-1]) :
     *                  Math.max(dp[i-1][j], j-1 < 0 ? 0 : dp[i][j-1])
     * @param text1
     * @param text2
     * @return
     */
    public static int dp(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        if (m==0 || n==0) return 0;
        int[][] dp = new int[m][n];
        for (int i=0; i<n; i++)
            dp[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : (i-1<0 ? 0 :dp[0][i-1]);
//        for (int i=0; i<m; i++)
//            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : i-1<0 ? 0 :dp[i-1][0];

        for (int i=1; i<m; i++){
            for (int j=0; j<n; j++){
                dp[i][j] = text1.charAt(i) == text2.charAt(j) ?
                        1+ (j-1<0 ? 0 : dp[i-1][j-1]) :
                        Math.max(dp[i-1][j], (j-1<0?0:dp[i][j-1]));
            }
        }
        return dp[m-1][n-1];
    }
}
