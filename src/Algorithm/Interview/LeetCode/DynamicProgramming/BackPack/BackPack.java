package Algorithm.Interview.LeetCode.DynamicProgramming.BackPack;

/**
 * 背包问题
 */
public class BackPack {
    public static void main(String[] args) {

    }

    /**
     * todo: 状态方程 : dp[i,c]  [0, i] 范围内的物品，在容量为 c的情况下 ，能装的最大值
     *       base case: i = 0
     *       状态转移：dp[i,c] = max{dp[i-1,c], v(i)+dp[i-1, c-w(i)]}
     *      时间复杂度： O(n*c)
     *      空间复杂度： O(n*c)
     * @param v 物品体积
     * @param w 物品重量
     * @param c 背包容量
     * @return
     */
    public int dp(int[] v, int[] w, int c){
        int n = v.length;
        if (n==0) return 0;
        //todo: 状态数组
        int[][] dp = new int[n][c+1];
        //todo: 在背包容量=0~c 时， 把为物品 0 放入背包. 获得的最大值
        //todo: 状态数组是一维 初始化一个元素； 状态数组是二维，初始化一行
        for (int i=0; i<c+1; i++){
            if (w[0] <= c) dp[0][i] = v[0];  //初始化第一行
        }

        //todo: 状态转移； 从 i=1 的产品开始
        for (int i=1; i<n; i++){
            for (int j=0; j<c+1; j++){
                //todo: 情形1  不把 物品 i 放入背包
                int s1 = dp[i-1][j];
                //todo: 情形2  把 物品 i 放入背包
                int s2 = w[i] <= j ? v[i] + dp[i-1][j-w[i]]: s1;
                dp[i][j] = Math.max(s1,s2);
//                dp[i][j] = dp[i-1][j];
//                if (w[i] <= j)
//                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i-1][j-w[i]]);
            }
        }
        //todo: 考虑 [0, n-1] 范围内的物品， 容量 c 的状态
        return dp[n-1][c];
    }


    /**
     *
     * @param v 0~n-1 个物品各自的体积
     * @param w 0~n-1 个物品各自的价值
     * @param c 背包的容量
     * @return
     */
    public int Solution(int[] v, int[] w, int c){
        int n = v.length;
        //todo: 记录数组有2个约束条件 index 和 c。 也就是 一个 index和c 对应一种状态
        //todo: 递归函数就是处理 index、 和 c 这一个状态
        int[][] record = new int[n][c];
        return bestVal(v, w, n-1, c, record);
    }


    /**
     * todo:递归函数 处理状态 完成状态转移
     * @param v
     * @param w
     * @param index
     * @param c
     * @return
     */
    public int bestVal(int[] v, int[] w, int index, int c, int[][] record){
        if (index < 0 || c < 0) return 0;
        if (record[index][c] > 0) return record[index][c];
        //todo: 第 index 个 物品不放进背包
        int res = bestVal(v, w, index-1,c, record);
        //todo: 第 index 个 物品放进背包
        if(c - w[index] >= 0){
            res = Math.max(res, v[index] + bestVal(v, w, index-1, c-w[index], record));
        }
        record[index][c] = res;
        return res;
    }


    /**
     * todo: 状态方程  F(i,c) = max{F(i-1,c), v(i)+F(i-1, c-w(i))}
     *      时间复杂度： O(n*c)
     *      空间复杂度： O(2*c)  实际上，状态转移过程中只需要保存上一个状态，也就是2行就可以
     **/
    public int dp2(int[] v, int[] w, int c){
        int n = v.length;
        if (n==0) return 0;
        //todo: 状态数组
        //todo: 奇数位置的 产品状态 始终在第一行； 偶数位置的产品状态 始终在第二行 row%2 即可
        int[][] dp = new int[2][c+1];
        //todo: 在背包容量=0~c 时， 把为物品 0 放入背包
        //todo: 状态数组是一维 初始化一个元素； 状态数组是二维，初始化一行
        for (int i=0; i<c+1; i++){
            if (w[0] <= c) dp[0][i] = v[0];  //初始化第一行
        }

        //todo: 状态转移； 从 i=1 的产品开始
        for (int i=1; i<n; i++){
            for (int j=0; j<c+1; j++){
                dp[i%2][j] = dp[(i-1)%2][j];
                if (w[i] <= j)
                    dp[i%2][j] = Math.max(dp[i%2][j], v[i] + dp[(i-1)%2][j-w[i]]);
            }
        }
        return dp[(n-1)%2][c];
    }


    /**
     * todo: 状态方程  F(i,c) = max{F(i-1,c), v(i)+F(i-1, c-w(i))}
     *      时间复杂度： O(n*c)
     *      空间复杂度： O(1*c)  只用一行
     **/
    public int dp3(int[] v, int[] w, int c){
        int n = v.length;
        if (n==0) return 0;
        //todo: 状态数组
        //todo: 使用一行
        int[] dp = new int[c+1];
        //todo: 在背包容量=0~c 时， 把为物品 0 放入背包
        //todo: 状态数组是一维 初始化一个元素； 状态数组是二维，初始化一行
        for (int i=0; i<c+1; i++){
            if (w[0] <= c) dp[i] = v[0];  //初始化第一行
        }

        //todo: 状态转移； 从 i=1 的产品开始
        for (int i=1; i<n; i++){
            //todo: 只用考虑  j>=w[i] 的位置的变化；
            // todo：因为 j<w[i] 的位置，放不下 物品i，也就是值和上一个状态保持一致
            for (int j=c; j>=w[i]; j--){
                dp[j] = Math.max(dp[j], v[i] + dp[j-w[i]]);
            }
        }
        return dp[c];
    }
}
