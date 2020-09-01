package Algorithm.Interview.LeetCode.DynamicProgramming.Match;

public class isMatch {
    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     *
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     * 示例 3:
     *
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     * 示例 4:
     *
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     * 示例 5:
     *
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/wildcard-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return boolean
     *
     * todo: 终于tmd 做出来了 一题 dp
     *      - 状态
     *      - 选择
     *      - base case
     *      - 关注当前： 当前dp 只与 上下左右的状态有关
     *
     * todo: 独立完成
     */
    public boolean dp(String s, String p) {
        if (s == null || p == null)return false;
        if(p.length() == 0) return s.length() == 0;
        boolean[][] dp = new boolean[s.length() + 1][p.length() +1];
        // s 为 空； p 非空 dp[0][i];     相反: dp[i][0] = false  dp[i][0] 默认值就是0 不需要初始化
        dp[0][0] = true;
        dp[0][1] = p.charAt(0) == '*';
        for (int i = 2; i <= p.length(); i++)
            dp[0][i] = p.charAt(i - 1) == '*' && dp[0][i - 1];

        for (int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= p.length(); j++){
                //todo: p[j] = a~z
                if (p.charAt(j - 1) == s.charAt(i - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //todo: p[j] = '?'
                if(p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //todo: p[j] = '*'
                if(p.charAt(j - 1) == '*'){
                    //todo： * 匹配0个 匹配1个 匹配n个
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j]; //todo: 三个方向可以得到
                    /**
                     * dp[i][j - 1] : s[0 ~ i] = p[0 ~ j -1] 的情况下； 如果 p[j] = '*' dp[i][j] = true
                     * dp[i - 1][j - 1] : s[0 ~ i - 1] = p[0 ~ j -1] 的情况下； 如果 p[j] = '*' dp[i][j] = true
                     * dp[i - 1][j] : s[0 ~ i - 1] = p[0 ~ j] 的情况下； 如果 p[j] = '*' dp[i][j] = true
                     */
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        String s = "acdcb";
        String p = "a*c?b";
        boolean isMatch = new isMatch().dp(s, p);
        System.out.println(isMatch);
    }
}
