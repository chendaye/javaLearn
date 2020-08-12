package Algorithm.Interview.NowCoder;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class JumpFloor {
    public static void main(String[] args) {
        JumpFloor q3 = new JumpFloor();
        System.out.println(q3.JumpFloor(1));
    }

    //todo: dp[i]  第 i 阶 有几种上法
    public int JumpFloor(int target) {
        int[] dp = new int[target + 1];
        dp[1] = 1;
        if (target >= 2)
            dp[2] = 2;
        for (int i = 3; i <= target; i++){
            dp[i] = dp[i - 1] + dp[i - 2]; //todo:可以从 i-1 上来 也可以从 i - 2 上来
        }
        return dp[target];
    }
}
