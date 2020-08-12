package Algorithm.Interview.NowCoder;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
 * n<=39
 *
 * F(0)=1，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 */
public class Fibonacci {

    public int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int [] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Fibonacci q2 = new Fibonacci();
        System.out.println(q2.Fibonacci(32));
    }
}
