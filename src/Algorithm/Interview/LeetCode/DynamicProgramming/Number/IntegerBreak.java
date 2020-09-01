package Algorithm.Interview.LeetCode.DynamicProgramming.Number;

import Utils.Dump;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 279 91 62  63
 */
public class IntegerBreak {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        Dump.dump(integerBreak(20));
        Dump.dump(dp(50));
        long end = System.currentTimeMillis();
        Dump.dump((end-start)+"毫秒");
    }

    /**
     * todo 递归暴力求解
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        int[] record = new int[n+1];
        return  mult(n,record);
    }

    /**
     * todo: 递归改为记忆化搜索，减少重复计算
     * @param n
     * @param record
     * @return
     */
    private static int mult(int n, int[] record){
        if (record[n] > 0) return record[n];
        if (n==1)return 1;
        int ret = 0;
        for (int i=1; i<n; i++){
            int next = n-i;
            int mult = i * integerBreak(next); // 往下分解
            mult = Math.max(mult, i*next); // 就分解成2个
            ret = Math.max(mult, ret);
        }
        record[n] = ret;
        return ret;
    }

    /**
     * todo:动态规划
     *      - 与记忆递归搜索的区别在于
     *      - 记忆递归搜索，保存已经计算过的值， 值是在递归的过程中计算
     *      - 动态规划。 也是保存值，只不过 是自底向上的，一直到根
     *      - 两者具体计算 递归树中的，某一个节点值的逻辑是一样的，不同的是 获取和保存 已经计算过的值的方法不同
     *      - 自顶向下-递归  自底向上-动态规划
     *
     * todo:动态规划思考方式
     *      - 先自顶向下 构造出递归树
     *      - 然后自底向上 动态规划
     *      - 递归树中是否有重复节点
     *      - 每一个节点是不是一个求最优解的问题
     * @param n
     * @return
     */
    public static int dp(int n){
        int[] dp = new int[n+1];
        dp[2] = 1;
        //todo: 第一层循环，给每一个dp数组元素赋值
        for (int i=n-3; i>=0; i--){
            int next = n-i;
            int ret = 0;
            // todo:处理当前dp数组元素
            for (int j=1;j<next; j++){
                int tmp = next-j;
                int mult = j * dp[tmp]; // 往下分解
                mult = Math.max(mult, j*tmp); // 就分解成2个
                ret = Math.max(mult, ret);
            }
            dp[next] = ret;
        }
        return dp[n];
    }

}
