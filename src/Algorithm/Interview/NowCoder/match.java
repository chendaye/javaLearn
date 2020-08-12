package Algorithm.Interview.NowCoder;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class match {

    //todo: 递归解法， 每轮递归只考虑前两个 元素
    public boolean match(char[] str, char[] pattern)
    {
        if (str.length == 0) return pattern.length == 0;
        //todo: 第一个个元素是否匹配
        boolean first = (str[0] == pattern[0]) ||(pattern[0] == '.'); // 匹配 '.'

        //todo: 第二个元素是否匹配
        if (pattern.length >= 2 && pattern[1] == '*'){ // 匹配 * 号
            //todo: 第一个匹配, * 号前面的元素相当于有任意多个 所以 pattern 不变 继续匹配后面
            return first && match(Arrays.copyOfRange(str,1, str.length), pattern)
                    ||
                    //todo： 第一个不匹配  x* 不能匹配，就消去
                    match(str, Arrays.copyOfRange(pattern, 2, pattern.length));
        }else {
            return first && match(Arrays.copyOfRange(str,1, str.length), Arrays.copyOfRange(pattern, 1, pattern.length));
        }
    }

    /**
     * todo: 带备忘录的递归
     * @param i
     * @param j
     */
    public boolean dp1(int i, int j, char[] str, char[] pattern, HashMap<String, Boolean> record){
        String key = i+"-"+j;
        if (record.containsKey(key)) return record.get(key);
        boolean res;
        //todo: str 一步一步往前挪，
        if (i == str.length) return j == pattern.length;
        boolean first = (str[i] == pattern[j]) || (pattern[j] == '.');
        if (pattern.length >= j + 2 && pattern[j + 1] == '*'){
            res = first && dp1(i + 1, j, str, pattern, record) || dp1(i, j + 2, str, pattern, record);
        }else {
            res = dp1(i + 1, j + 1, str, pattern, record);
        }
        record.put(key, res);
        return  res;
    }
    public boolean match2(char[] str, char[] pattern){
        HashMap<String, Boolean> record = new HashMap<>();
        dp1(0, 0, str, pattern, record);
        return record.get(0+"-"+0);
    }

    /**
     * todo:动态规划
     *      dp[i][j] str 0~i pattern 0 ~ j 是否匹配
     * @param str
     * @param pattern
     * @return
     */
    public boolean dp(char[] str, char[] pattern){
        int s = str.length, p = pattern.length;
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        //base case
        dp[0][0] = true; // str 和 pattern 都为空
        for (int i = 1; i < s; i++)  // str 非空， pattern为空
            dp[i][0] = false;
        for (int i = 0; i < s; i++){
            for (int j = 1; j < p; j++){
                if (pattern[j - 1] != '*'){
                    if (i >= 1 && (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.'))
                        dp[i][j] = dp[i - 1][j - 1];
                }else {
                    // 重复 0 次
                    if (j >= 2) {
                        dp[i][j] |= dp[i][j-2];
                    }
                    // 重复 1 次或者多次
                    // 这里要用 | 连接， 不然重复 0 次的会直接覆盖
                    if (i >= 1 && j>=2 && (str[i-1] == pattern[j-2] || pattern[j-2] == '.')) {
                        dp[i][j] = dp[i][j] | dp[i-1][j];
                    }
                }
            }
        }
        return dp[s][p];
    }



    //todo: 通过
    public boolean matchStr(char[] str, int i, char[] pattern, int j) {

        // 边界
        if (i == str.length && j == pattern.length) { // 字符串和模式串都为空
            return true;
        } else if (j == pattern.length) { // 模式串为空
            return false;
        }

        boolean next = (j + 1 < pattern.length && pattern[j + 1] == '*'); // 模式串下一个字符是'*'
        if (next) {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) { // 要保证i<str.length，否则越界
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }



    public boolean match3(char[] str, char[] pattern) {
        return matchStr(str, 0, pattern, 0);
    }

    public static void main(String[] args) {
//        char[] str = {'"','"'};
//        char[] pattern = {'"','"'};;
//
//
//        System.out.println(new match().match2(str, pattern));

        System.out.println('6' - '0');
        System.out.println((int)'c');
        System.out.println((int)'0');
        System.out.println(6 - (int)'6');
        System.out.println('a' - 'b');
        System.out.println('a');

        String s = "458";
        int n = 0;
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            n = n * 10 + (cur - '0'); // todo: '6' != 6  '6' - '0' = 6
        }
        System.out.println(n);
    }
}
