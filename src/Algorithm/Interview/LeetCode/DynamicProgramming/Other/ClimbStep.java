package Algorithm.Interview.LeetCode.DynamicProgramming.Other;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数
 *
 * todo: 动态规划是子问题相加
 *
 * todo: 类似 120 64
 */
public class ClimbStep {
    public static void main(String[] args) {
        int n = 12;
        int tmp = method(n);
        System.out.println(tmp);

        int tmp2 = climbStairs(n);
        System.out.println(tmp2);

        int tmp3 = dpMethod(n);
        System.out.println(tmp3);
    }

    /**
     * 递归法
     */
    public static int method(int n){
        if (n == 1) return 1;
        if (n == 2) return 2;
        //todo: 从 n-2 到达 n ； 和 从 n-1 到达 n的路径不重复
        return  method(n-2)  + method(n-1);
    }

    public static int dpMethod(int n){
        int[] dp = new int[n+1];

        dp[1] = 1;
        dp[2] = 2;
        //todo: 从下到上
        for (int step = 3; step <= n; step++ ){
            dp[step] = dp[step -1]+dp[step-2];
        }

        return dp[n];
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public static  int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];  // 多开一位，考虑起始位置

        dp[0] = 0; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
