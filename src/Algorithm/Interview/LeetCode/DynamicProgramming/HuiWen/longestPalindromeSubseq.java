package Algorithm.Interview.LeetCode.DynamicProgramming.HuiWen;

/**
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 *  
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        return 0;
    }

    /**
     * todo: dp[i][j]: s[i ~ j] 范围内的最长回文
     *       base case: dp[i][j],  dp[i][j] = 1, i == j
     *       状态方程： s[i] = s[j] : dp[i][j] = dp[i + 1][j - 1] + 2
     *                 s[i] != s[j] : dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); 不相等的时候 左边缩一位 或者 右边缩一位
     * @param s
     * @return
     */
    public static int dp(String s){
        int n = s.length();
        if (s == null || n == 0) return 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;
        for (int i = n - 2; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(dp(s));
    }
}
