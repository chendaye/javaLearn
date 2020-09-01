package Algorithm.Interview.LeetCode.DynamicProgramming.HuiWen;

/**
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 *
 * 请你返回让 s 成为回文串的 最少操作次数 。
 *
 * 「回文串」是正读和反读都相同的字符串。
 *
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 * 示例 2：
 *
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 * 示例 3：
 *
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 * 示例 4：
 *
 * 输入：s = "g"
 * 输出：0
 * 示例 5：
 *
 * 输入：s = "no"
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome
 * https://mp.weixin.qq.com/s/C14WNUpPeBMVSMqh28JdfA 参考
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class minInsertions {
    public int minInsertions(String s) {
        return 1;
    }

    /**
     * todo: 思路很简单
     *      - dp[i][j]: 表示 把字符串 s[i ~ j] 变成回文的最小代价
     *      - base case: dp[i][j] = 0 , i = j 单个字符就是回文
     *      - 状态转移：
     *          - s[i] = s[j]: dp[i][j] = dp[i+1][j-1],  dp[i+1][j-1] 是把 dp[i+1][j-1] 变成回文的最小代价
     *          - s[i] != s[j]: dp[i][j] = Min(dp[i+1][j], dp[i][j-1]) + 1
     *
     * todo: 对于任意一个回文无论奇数偶数， 插入一个字符可以保证 还是回文
     *
     * todo: 遍历方法： 因为 base case 分布在对角线上
     *              - 转移方程中 变量   dp[i+1][j-1] dp[i+1][j] dp[i][j-1]
     *              - 从下往上，从左往右遍历 dp矩阵
     *
     * todo: 对矩阵的遍历统一 把原点固定在 左上角
     *       -----> j
     *       |              ===> dp[i][j] 是 四方块的 右上方块
     *       | i
     * @param s
     * @return
     */
    public int dp(String s){
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];

        //todo: i 为纵坐标-从下往上
        for(int i = len - 2; i >= 0; i--){
            //todo: j 为横坐标- 从对角线往左, 注意 j = i + 1 (当 i = n-1 时, j = i + 1 = n 不遍历)
            for (int j = i + 1; j < len; j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1];
                }else{
                    //todo：s[i+1][j] 是回文： ①奇数个字符 把一个字符插入中间 变成偶数个字符回文
                    //todo: ② 偶数个字符 把任意字符插到 正中间 变成奇数个字符回文
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][len - 1];
    }
}
