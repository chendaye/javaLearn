package Algorithm.Interview.LeetCode.DynamicProgramming.Other;

import java.util.Vector;

/**
 * 动态规划问题
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fib2(10));
    }


    /**
     * 递归方式求解： 但凡遇到需要递归的问题，最好都画出递归树，这对你分析算法的复杂度，寻找算法低效的原因都有巨大帮助。
     * 递归算法的时间复杂度怎么计算？子问题个数乘以解决一个子问题需要的时间。
     * 子问题个数，即递归树中节点的总数。显然二叉树节点总数为指数级别，所以子问题个数为
     * @param n
     * @return
     */
    public static int Recursive(int n){
        if (n == 1 || n == 2){
            return n;
        }

        return Recursive(n-1) + Recursive(n-2);
    }


    /**
     * 即然耗时的原因是重复计算，那么我们可以造一个「备忘录」，每次算出某个子问题的答案后别急着返回，先记到「备忘录」里再返回；每次遇到一个子问题先去「备忘录」里查一查，如果发现之前已经解决过这个问题了，直接把答案拿出来用，不要再耗时去计算了。
     * 一般使用一个数组充当这个「备忘录」，当然你也可以使用哈希表（字典），思想都是一样的。
     *
     * 带备忘录的递归解法的效率已经和动态规划一样了。实际上，这种解法和动态规划的思想已经差不多了，
     * 只不过这种方法叫做「自顶向下」，动态规划叫做「自底向上」
     * @param N
     * @return
     */
    public static int fib(int N) {
        if (N < 1) return 0;
        // 备忘录全初始化为 0
        Vector<Integer> memo = new Vector<Integer>();
        for (int i = 0; i<10; i++){
            memo.add(0);
        }
        return helper(memo, N-1);
    }
    public static int helper(Vector<Integer> memo, int n) {
        if (n < 0 || n == memo.size()) return 0;
        if (n == 1 || n == 2) return 1;
        if (memo.get(n) != 0) return memo.get(n);
        // 未被计算过
        memo.set(n, helper(memo, n - 1) + helper(memo, n - 2));

        return memo.get(n);
    }


    /**
     * 有了上一步「备忘录」的启发，我们可以把这个「备忘录」独立出来成为一张表，
     * 就叫做 DP table 吧，在这张表上完成「自底向上」的推算岂不美哉！
     *
     * 为啥叫「状态转移方程」？为了听起来高端。你把 f(n) 想做一个状态 n，
     * 这个状态 n 是由状态 n - 1 和状态 n - 2 相加转移而来，这就叫状态转移，仅此而已。
     * 你会发现，上面的几种解法中的所有操作，例如 return f(n - 1) + f(n - 2)，
     * dp[i] = dp[i - 1] + dp[i - 2]，以及对备忘录或 DP table 的初始化操作，都是围绕这个方程式的不同表现形式。可见列出「状态转移方程」的重要性，它是解决问题的核心。很容易发现，其实状态转移方程直接代表着暴力解法。
     *
     * 千万不要看不起暴力解，动态规划问题最困难的就是写出状态转移方程，即这个暴力解。
     * 优化方法无非是用备忘录或者 DP table，再无奥妙可言。
     */
    public static int fib2(int N) {
        int[] dp = new int[N];

        dp[0] = dp[1] = 1;

        //todo:自从底向上求解
        for (int i = 2; i < N; i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[N - 1];
    }



}





