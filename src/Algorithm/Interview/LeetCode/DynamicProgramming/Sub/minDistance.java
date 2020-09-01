package Algorithm.Interview.LeetCode.DynamicProgramming.Sub;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class minDistance {
    /**
     * 若 A 和 B 的最后一个字母相同：
     *
     * todo: 设置 dp[len + 1] 是为了将所有状态一般化; 很重要
     *      - 所以 设置长度 就设置为 len + 1
     *
     * todo: dp[i] 是当前所处的状态； 它完全有之前的状态通过选择转换而来
     *      - 状态 d[i][j] : word1[0 ~ i] 与 word2[0 ~ j] 的最短编辑距离
     *      - 选择
     *          if: word1[i] != word2[j]
     *              - dp[i - 1][j]  word1[0 ~ i-1] 插入一个 非word2[j]的字符； 即可 转变成 word1[i] != word2[j] 的状态：dp[i][j] = 1 + dp[i - 1][j] （最小操作次数）
     *              - dp[i][j - 1] word2[0 ~ j-1] 插入一个 非word1[i]的字符； 即可 转变成 word1[i] != word2[j] 的状态：dp[i][j] = 1 + dp[i][j - 1]  （最小操作次数）
     *              - dp[i - 1][j - 1]  把 word1[i] 、 word2[j] 中任意一个修改的和另一个相同即可， 最小操作次数 dp[i][j] = 1 + dp[i - 1][j - 1]
     *          if: word1[i] == word2[j] (同上)
     *              - dp[i][j] = 1 + dp[i - 1][j]
     *              - dp[i][j] = 1 + dp[i][j - 1]
     *              - dp[i][j] = dp[i - 1][j - 1]
     *
     *
     */
    //todo: 设置长度为 len + 1； 目的在于； 将原始字符串所有位置 一般化（包括第一个位置）
    public int dp2(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j<= word2.length(); j++)
            dp[0][j] = j;
        for (int i  = 1; i <= word1.length(); i++){
            for (int j = 1; j <= word2.length(); j++){
                if (word1.charAt(i-1) != word2.charAt(j-1)){ //todo: i,j 和真正的索引相差 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1] - 1, Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }



    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";
        int dp = new minDistance().dp2(word1, word2);
        System.out.println(dp);
    }
}
