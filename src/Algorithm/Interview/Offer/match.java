package Algorithm.Interview.Offer;

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
        boolean[][] dp = new boolean[str.length][pattern.length];

    }

    public static void main(String[] args) {
        char[] str = {'"','"'};
        char[] pattern = {'"','"'};;


        System.out.println(new match().match2(str, pattern));
    }
}
