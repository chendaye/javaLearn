package Algorithm.Interview.LeetCode.DynamicProgramming.Number;

/**
 * 凑零钱问题：
 *
 * 题目：给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，再给一个总金额 n，
 * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，则回答 -1 。
 *
 * 暴力解法 首先是最困难的一步，写出状态转移方程
 *
 * 这个方程就用到了「最优子结构」性质：原问题的解由子问题的最优解构成。即 f(11) 由 f(10), f(9), f(6) 的最优解转移而来
 * 要符合「最优子结构」，子问题间必须互相独立
 */
public class Money {
    public static void main(String[] args) {
        System.out.println(Math.round(-1.5));
    }

    /**
     * 递归方法：暴力求解
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 金额不可达
            if (amount - coin < 0) continue;
            // 返回需要用 subProb 个硬币凑成 amount - coin
            int subProb = coinChange(coins, amount - coin);
            // 子问题无解
            if (subProb == -1) continue;
            // 这种方案可以凑 需要的硬币数 ans
            ans = Math.min(ans, subProb + 1);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 带备忘录的递归算法
     * - 一个主要方法，设置备忘录
     * - 一个递归方法，使用方法
     */
    public static int coinChange2(int[] coins, int amount) {
        // 备忘录初始化为 -2
        int[] memo = new int[amount+1]; // 0~amount,每个键值对应，凑成key 要几个硬币
        for (int i = 0; i<= amount; i++){
            memo[i] = -2;
        }
        //todo:获取amount 要几个硬币凑成
        return helper(coins, amount, memo);
    }

    public static int helper(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != -2) return memo[amount]; // 看备忘录中有没有，有直接返回
        int ans = Integer.MAX_VALUE;

        for (int coin : coins) {
            // 金额不可达
            if (amount - coin < 0) continue;
            // 子问题的解
            int subProb = helper(coins, amount - coin, memo);
            // 子问题无解
            if (subProb == -1) continue;
            ans = Math.min(ans, subProb + 1);
        }
        // 记录本轮答案
        memo[amount] = (ans == Integer.MAX_VALUE) ? -1 : ans;
        return memo[amount];
    }

    /**
     * 动态规划
     *
     * 计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举，穷举所有可能性。
     * 算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。
     * 列出动态转移方程，就是在解决“如何穷举”的问题。之所以说它难，一是因为很多穷举需要递归实现，
     * 二是因为有的问题本身的解空间复杂，不那么容易穷举完整。
     * 备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门，
     * 除此之外，试问，还能玩出啥花活？

     */
    public static int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount+1]; // 0~amount,每个键值对应，凑成key 要几个硬币
        for (int i = 0; i<= amount; i++){
            //todo: dp[i] 表示 凑成金额 i 需要的 硬币数量
            dp[i] = amount + 1;  // 先初始化为大于 硬币数量的值
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins)
                if (coin <= i)
                    //自底向上的更新 dp[i]
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * todo:
     *      算数运算：
     *      - Math.addExact(int x, int y) 计算两参数之和，参数类型：int 或 long
     *      - Math.subtractExact(int x, int y) 计算两参数之差（第一个参数 - 第二个参数），参数类型：int 或 long
     *      - Math.multiplyExact(int x, int y) 计算两参数的乘积，参数类型：int 或 long
     *      - Math.floorMod(int x, int y) 计算两参数相除的余数（第一个参数 / 第二个参数），参数类型：int 或 long
     *      - Math.floorDiv(int x, int y) 计算小于或等于商的最大整数值，参数类型：int 或 long
     *      - Math.pow(double a, double b)  计算 a 的 b 次幂
     *      - Math.exp(double a)  计算自然常数 e 的 a 次幂
     *      - Math.sqrt(double a) 计算开平方根
     *      - Math.hypot(double x, double y) 计算两个参数平方和的平方根
     *      - Math.log(double x) 计算以 e 为底的对数 (自然对数)
     *      - Math.log10(double x) 计算以 10 为底的对数
     *      取整运算：
     *      - Math.ceil(double x) 向上取整，返回大于该值的最近 double 值
     *      - Math.floor(double x) 向下取整，返回小于该值的最近 double 值
     *      - Math.round(double x) 四舍五入取整，参数类型：double、float
     *      随机运算：
     *      - Math.random() 内部调用了 Random.nextDouble() 方法，生成一个伪均匀分布在 0.0 到 1.0 之间的 double 值 (int)(Math.random() * 10 + 1)
     *      符号运算：
     *      - Math.abs(int x) 计算绝对值
     *      - Math.negateExact(int x) 计算相反值  相当于乘以-1
     *      大小运算：
     *      - Math.max(int x, int y)
     *      - Math.min(int x, int y)
     *
     */

    /**
     * todo:字符串操作
     *      > 比较操作
     *          - str1.compareTo(str2) 字符串比较
     *          - str1.compareToIgnoreCase(str2) 忽略大小写比较
     *      > 查找、搜索字符串
     *          - str1.contains("world") 是否包含子串
     *          - str1.startsWith("he") str1.endsWith("world")
     *          - str1.startsWith("ll", 2)
     *          - str2.indexOf('t') 从左向右查找字符'o'第一次出现的位置,找到返回索引，没找到返回-1
     *          - str2.indexOf('o',5)
     *          - str2.lastIndexOf('o') 从右向左查找字符'o'第一次出现的位置,找到返回索引，没找到返回-1
     *       > 替换字符串
     *          - str1.replace('o', '8')
     *          - str5.replaceAll("\\d+", "")
     *          - str5.replaceFirst("\\d+", "")
     *          - str4.matches("\\d+") 正则表达式专门用于验证字符串是否符合特定的格式
     *       > 拆分字符串
     *          - String[] arr = str1.split("-")
     *          - String[] arr3 = str3.split("\\d+")
     *       > 求子串、大小写转换
     *          - str1.substring(0, 3)
     *          - str1.substring(3)
     *          - str3.toUpperCase()
     *          - str3.toLowerCase()
     *       > 其他方法
     *          - str1.trim() 去掉前导空格和后导空格
     *          - char[] arr = str1.toCharArray() 获取字符串的字符数组
     *          - String str2 = String.valueOf(10) 把其他数据类型转化为字符串
     *
     *
     *       todo:StringBuffer
     *        > StringBuffer sb = new StringBuffer(); sb.append("a");
     *
     */



}
