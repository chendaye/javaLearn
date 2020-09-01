package Algorithm.Interview.LeetCode.DynamicProgramming.Match;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class isMatch2 {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 || p.length() == 0) return p.length() == s.length(); // 空字符串情况
        boolean match = (p.charAt(0) == s.charAt(0)) || (p.charAt(0) == '.'); //todo: 第一个字符字符是否匹配

        if (p.length() >= 2 && p.charAt(1) == '*'){
            //如果有 *
            return isMatch(s, p.substring(2)) || isMatch(s.substring(1), p.substring(2)) ||(match && isMatch(s.substring(1), p));
        }else{
            return match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 如果 p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]；
     * 如果 p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]；
     * 如果 p.charAt(j) == '*'：
     * 如果 p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
     * 如果 p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.'：
     * dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a
     * or dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
     * or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
     *
     * 作者：kao-la-7
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching/solution/dong-tai-gui-hua-zen-yao-cong-0kai-shi-si-kao-da-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     */
    public boolean dp(String s, String p){
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int i = 1; i <= p.length(); i++)
            dp[0][i] = p.charAt(i-1) == '*' && dp[0][i - 2]; // s=0
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        // dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                        // dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                        // dp[i][j] = dp[i][j-2] // 没有匹配的情况
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * "mississippi"
     * "mis*is*p*."
     * @param args
     */
    public static void main(String[] args) {
        //String s = "aa";
        //String p = "a*";
        String s = "mississippi";
        String p = "mis*is*p*.";
        boolean match = new isMatch2().isMatch(s, p);
        System.out.println(match);
    }
}
