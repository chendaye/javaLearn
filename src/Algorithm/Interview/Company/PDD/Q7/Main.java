package Algorithm.Interview.Company.PDD.Q7;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static int max = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q7/test.txt"));
        Scanner sc = new Scanner(System.in);
        int  N = sc.nextInt(); // 商品总数
        int  M = sc.nextInt(); // 背包大小

        int[][] goods = new int[N][2];
        for (int i = 0; i < N; i++){
            goods[i][0] = sc.nextInt();
            goods[i][1] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++){
            ArrayList<int[]> path = new ArrayList<int[]>();
            back(goods, M, path, i, 0);
        }
        System.out.println(max);

    }


    public static void back(int[][] goods, int w, ArrayList<int[]> path, int k, int start){
        if (path.size() == k || start == goods.length){
            if (path.size() < k) return;
            int v = 0;
            for(int[] arr : path){
                w -= arr[0];
                if (w >= 0){
                    v += arr[1];
                }else {
                    return;
                }
            }
            max = Math.max(max, v);
            return;
        }
        for (int i=start;i< goods.length;i++){
            if (goods[i][0] > 0 && goods[i][1] < 0) continue;
            path.add(goods[i]);  // 组合新增一个值
            back(goods, w, path, k, i+1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * todo: dp[i][j]: 0~i 个商品  容量：j 的最大 收益
     * @param goods
     * @param w
     * @return
     */
    public static int dp(int[][] goods, int w){
        int n = goods.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][w+1];
        // 在背包容量=0~c 时， 把为物品 0 放入背包. 获得的最大值
        for (int i = 0; i <= w; i++){
            if(goods[0][0] <= i) dp[0][i] = Math.max(goods[0][1], 0);
        }
        for (int i=1; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i-1][j]; // 不取当前产品
                // 放的下当前的产品
                if (goods[i][0] <= j){
                    if (goods[i][0] == 0){
                        dp[i][j] = Math.max(dp[i][j], goods[i][1] + dp[i-1][j]);
                    }else if(goods[i][0] < 0){
                        dp[i][j] = Math.max(dp[i][j], goods[i][1] + dp[i-1][j]);
                    }else{
                        dp[i][j] = Math.max(dp[i][j], goods[i][1] + dp[i-1][j-goods[i][0]]);
                    }
                }
            }
        }
        return dp[n-1][w];
    }
}
