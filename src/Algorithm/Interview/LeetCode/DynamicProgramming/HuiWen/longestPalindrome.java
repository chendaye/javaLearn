package Algorithm.Interview.LeetCode.DynamicProgramming.HuiWen;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class longestPalindrome {
    public String longestPalindrome(String s) {
        return "";
    }


    /**
     * todo: 状态: dp[i][j] : s[i ~ j] 是否是回文
     *       base case: dp[i][j] = 0, i = j
     *       转移方程：
     *              s[i] != s[j]: dp[i][j] = 0
     *              s[i] == s[j]:
     *                      - dp[i][j] = dp[i+1][j-1] && len([i, j]) >= 3
     *                      - dp[i][j] = 1 && len([i, j]) < 3
     *
     * todo: 回文动态规划：
     *      - 沿对角线斜着遍历： 线性 + dp[][] 都斜着遍历
     *      - 按 s[i] == s[j] 分情况讨论
     *
     *  todo: 时间复杂度： n^2
     *         空间复杂度： n^2
     * @param s
     * @return
     */
    public static String dp(String s){
        int n = s.length();
        if (n < 2) return s;
        int begin = 0, maxLen = 1;
        int[][] dp = new int[n][n];
        // 从下往上，从左往右遍历
        for (int i = n - 2; i >=0; i--){
            for (int j = i + 1; j < n; j++){
                if(s.charAt(i) != s.charAt(j)){
                    dp[i][j] = 0;// 可以省略
                }else{
                    // (j - i + 1) - 2 <= 1, i~j 中间的字符个数 <= 1
                    if(j -i <= 2){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] == 1 && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }

        return s.substring(begin, begin + maxLen);
    }

    /**
     * todo: 从中心扩展 找最长
     * @param s
     * @return
     */
    public static String expendCenter(String s){
        if (s == null || s.length() == 0) return "";
        String ms = "";
        for (int i = 0; i < s.length(); i++){
            //todo: 考虑奇偶两种情况
            String s1 = maxLen(s, i, i);
            String s2 = maxLen(s, i, i + 1);
            if (ms.length() < s1.length() || ms.length() < s2.length()){
                ms = s1.length() > s2.length() ? s1 : s2;
            }
        }
        return ms;
    }

    public static String maxLen(String s, int l, int r){
        if (r == s.length()){
            return s.substring(l, r); // [n-1, n]
        }
        while (r < s.length() && l >= 0 && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        l++;r--; //todo: 最后一次循环 不满足回文，要回退一步
        return s.substring(l, r+1);
    }

    public static void main(String[] args) {
        String s = "ba";
        System.out.println(expendCenter(s));
    }
}
