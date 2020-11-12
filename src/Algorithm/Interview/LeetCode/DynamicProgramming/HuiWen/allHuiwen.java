package Algorithm.Interview.LeetCode.DynamicProgramming.HuiWen;

import java.util.Scanner;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串（回文串是一个正读和反读都一样的字符串）。
 *
 * 具有不同开始位置或结束位置的回文串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 输入仅包含一个字符串，长度不会超过 1000。
 *
 * 输出仅包含一个非负整数， 代表输入字符串有多少个回文子串。
 *
 * abc 3
 * aaa 6
 */
public class allHuiwen {

    /**
     *todo: dp[i] : [0, i] 范围内的 回文字符串总数
     *      base case： dp[0] = 1
     *      状态转移： dp[i] = {[0, i] 内以i结尾的所有回文数} + dp[i-1]
     * @param str
     * @return
     */
    public static int dp1(String str){
        if (str == null || str.length() == 0) return 0;
        int[] dp = new int[str.length()];
        dp[0] = 1;
        for (int i = 1; i < str.length(); i++){
            dp[i] += dp[i - 1];
            for (int j = 0; j <= i; j++){
                dp[i] += judge(str, j, i); //以dp结尾的 所有回文数
            }
        }
       return dp[str.length() - 1];
    }


    //todo: 判断是否是回文
    public static int judge(String str, int start, int end){
        while (start <= end){
            if (str.charAt(start) != str.charAt(end)) return 0;
             start++;
             end--;
        }
        return 1;
    }

    /**
     * todo: 二维dp
     *          状态： dp[i][j] : [i, j] 是否是回文子串
     *          base case
     *          状态转移： dp[i][j] = dp[i+1][j-1] && s[i] == s[j] ||
     *                                 s[i] == s[j] && i=j || s[i] == s[j] && i + 1 = j
     *
     * todo: 子序列： 不连续  子串：连续
     * @param str
     * @return
     */
    public static int dp2(String str){
        int cnt = 0;
        boolean[][] dp = new boolean[str.length()][str.length()];
        //todo: 斜着遍历 内层循环 j=i. 想成 二维数组遍历就复杂了
        //todo: 实际上可以想成 [0, j] 所有 以 j 结尾的子串 是否回文
        for(int j = 0; j < str.length(); j++){
            for(int i = j;  i >=0; i--){
                if(str.charAt(i) == str.charAt(j) && (dp[i+1][j-1] || j - i < 2)){
                    dp[i][j] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(dp1(str));
    }

}
