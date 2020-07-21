package Algorithm.Interview.Company.Bytedance.Q6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
 * 现在小Y使用1024元的纸币购买了一件价值为的商品，请问最少他会收到多少硬币？
 *
 * 输入描述:
 * 一行，包含一个数N。
 *
 * 输出描述:
 * 一行，包含一个数，表示最少收到的硬币数。
 *
 * 输入例子1:
 * 200
 *
 * 输出例子1:
 * 17
 *
 * 例子说明1:
 * 花200，需要找零824块，找12个64元硬币，3个16元硬币，2个4元硬币即可。
 *
 * todo: 1 4 16 64
 */
public class Main {
    private int min = Integer.MAX_VALUE; //todo：最少硬币数
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm.Interview\\Bytedance\\Q6\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = 1024 - sc.nextInt(); // todo:余额
        int[] coins = {1,4,16,64};
        Main main = new Main();

//        main.min = main.coinChange(coins, N);
        main.min = main.DP(coins, N);
        System.out.println(main.min);
    }

    /**
     * todo: 金额N 最少需要几个硬币
     * @param money
     * @param N
     * @return
     */
    private int coinChange(int[] money, int N){
        int[] record = new int[N+1];
        return helper(money, N, record);
    }

    private int helper(int[] money, int N, int[] record){
        if (N==0) return 0;
        if (record[N] != 0) return record[N];
        int num = Integer.MAX_VALUE;
        for (int m : money){
            if (N>=m)
                num = Math.min(helper(money, N-m, record)+1, num);
        }
        record[N] = num==Integer.MAX_VALUE ? 0:num;
        return num;
    }


    private int DP(int[] money, int N){
        int[] dp = new int[N+1];
        Arrays.fill(dp, N+1);
        dp[0] = 0;

        for (int i=1; i<=N; i++){
            for (int m:money){
                if (i>=m){
                    dp[i] = Math.min(dp[i], dp[i-m]+1);
                }
            }
        }
        return dp[N];
    }

}
