# 动态规划问题的特征

- 首要是状态设计；然后是状态转移
- 首先可以切分为一个个子问题，每一个子问题都是一个最优解
- 可以用递归来解决
- 递归函数是一个 状态=子问题=子问题的解决方案
- 递归可以用记忆化搜索来优化


[动态规划与状态机](https://recomm.cnblogs.com/blogpost/11277064)


[股票问题](https://www.cnblogs.com/hanyuhuang/p/11083384.html)

# 动态规划分类

[动态规划 分类](http://cppblog.com/menjitianya/archive/2015/10/23/212084.html)

- 线性模型 opt[i] = min{opt[i-1] + a[1] + a[i] , opt[i-2] + a[1] + a[i] + 2*a[2] }
- 区间模型 d[i][j] = min{ d[i+1][j], d[i][j-1] } + 1
- 背包模型  f[i][v] = max{ f[i-1][v], f[i-1][v - Ci] +Wi } 
- 状态压缩模型
- 树状模型


# 栗子

## 小朋友过桥

- n个人
- 每次最多过2个
- 只有一个手电筒，每次过桥的两个人需要把手电筒带回来
- i号小朋友过桥的时间为T[i]
- 求最短过桥时间

# 动态规划总结

## 三要素

- 状态
- 选择（转移）
- base case

## 思路

> 动态规划一定是一个状态递推的过程

> 也就是当前的状态一定由，之前的状态地推而来。 也就是说 dp[i] 一定与i密切相关

> 所谓选择就是之前状态，到达当前状态的路径

> 题干所求不一定是，直接的动态规划结果； 可以考虑把 题干要求转化 为与 i相关 （与 i 相关 == 重叠子问题）
>然后 在 dp 空间里面求解


# 子序列

## 一维dp

> 在子数组array[0..i]中，以array[i]结尾的目标子序列（最长递增子序列）的长度是dp[i]。

```bash
int n = array.length;
int[] dp = new int[n];

for (int i = 1; i < n; i++) {
    for (int j = 0; j < i; j++) {
        dp[i] = 最值(dp[i], dp[j] + ...)
    }
}
```

## 二维dp

```bash
int n = arr.length;
int[][] dp = new dp[n][n];

for (int i = 0; i < n; i++) {
    for (int j = 1; j < n; j++) {
        if (arr[i] == arr[j]) 
            dp[i][j] = dp[i][j] + ...
        else
            dp[i][j] = 最值(...)
    }
}
```

> 涉及两个字符串/数组时: 在子数组arr1[0..i]和子数组arr2[0..j]中，我们要求的子序列（最长公共子序列）长度为dp[i][j]。

>  只涉及一个字符串/数组时: 在子数组array[i..j]中，我们要求的子序列（最长回文子序列）的长度为dp[i][j]。


# 字符串动态规划总结

## 一维

> 情形一： 连续递推：dp[i] = F{dp[i-1]} 的值只取决于 dp[i-1], 逐次递推。 最终结果在 dp[n-1]

> 情形二：dp[i] = F{dp[i]....dp[n-1]} dp[i] 的值不能直接有 dp[i-1] 推出，而是在范围上找最值。
> 递推不是连续的，需要范围查找，确定 dp[i]

dp[i] : 以 i 结尾的某最值
最终结果： dp[n-1]

```bash
  /**
     * todo: 状态 ： dp[i]： [0, i] 范围内最长递增子序列
     *       base case： dp[0] = 1
     *       转移： dp[i] = Max{dp[x], 0<=x<i nums[x] < nums[i]}
     * @param nums
     * @return
     */
    public static int dp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    maxval = Math.max(maxval, dp[j]);
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
```

> 情形二： 定左侧

dp[i] : [0, i] 范围内最**
最终结果： 在 dp table 上找最值

```通常要两重循环。 内层循环 0~i ```

```bash
 /**
     * todo: 典型的 在 dp table 上找到最大值， 而不是取 dp[n-1]
     *      - 状态：dp[i]  以 nums[i] 结尾的 最大子数组 
     *      - 选择：dp[i]  = Max{dp[i - 1] + nums[i],   nums[i]}
     *      - base case  dp[0] = nums[0]
     *      - dp的过程 一定是一个状态地推的过程； 当前状态由前一个状态得到；这就要求当前状态与 i密切相关
     *      - 但是题目要求的解 不一定是最终的地推结果； 因为题目要求的解经过转换之后 变成dp问题
     * @param nums
     * @return
     *
     * todo：独立完成
     */
    public int dp(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
```

```bash
public static int dp(int[] nums){
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        //todo: 考虑最后一个房子，也就是区间 [len-1.......len-1]
        dp[len-1] = nums[len-1];
        for (int i=len-2; i>-1; i--){
            for (int j=i; j<len; j++)
                dp[i] = Math.max(dp[i], nums[j]+ (j+2 < len ? dp[j+2] : 0));
        }
        return dp[0];
    }
```

## 二维

> 情形一： 单字符串（斜着遍历）

```bash
/**
     * 若 A 和 B 的最后一个字母相同：
     *
     * todo: 设置 dp[len + 1] 是为了将所有状态一般化; 很重要
     *      - 所以 设置长度 就设置为 len + 1
     *
     * todo: dp[i] 是当前所处的状态； 它完全有之前的状态通过选择转换而来
     *      - 状态 d[i][j] : word1[0 ~ i] 与 word2[0 ~ j] 的最短编辑距离
     *      - 选择
     *          if: word1[i] != word2[j]
     *              - dp[i - 1][j]  word1[0 ~ i-1] 插入一个 非word2[j]的字符； 即可 转变成 word1[i] != word2[j] 的状态：dp[i][j] = 1 + dp[i - 1][j] （最小操作次数）
     *              - dp[i][j - 1] word2[0 ~ j-1] 插入一个 非word1[i]的字符； 即可 转变成 word1[i] != word2[j] 的状态：dp[i][j] = 1 + dp[i][j - 1]  （最小操作次数）
     *              - dp[i - 1][j - 1]  把 word1[i] 、 word2[j] 中任意一个修改的和另一个相同即可， 最小操作次数 dp[i][j] = 1 + dp[i - 1][j - 1]
     *          if: word1[i] == word2[j] (同上)
     *              - dp[i][j] = 1 + dp[i - 1][j]
     *              - dp[i][j] = 1 + dp[i][j - 1]
     *              - dp[i][j] = dp[i - 1][j - 1]
     *
     *
     */
    //todo: 设置长度为 len + 1； 目的在于； 将原始字符串所有位置 一般化（包括第一个位置）
    public int dp2(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j<= word2.length(); j++)
            dp[0][j] = j;
        for (int i  = 1; i <= word1.length(); i++){
            for (int j = 1; j <= word2.length(); j++){
                if (word1.charAt(i-1) != word2.charAt(j-1)){ //todo: i,j 和真正的索引相差 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1] - 1, Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
```

> 情形二： 双字符串

```bash
/**
     * todo:二维动态规划
     *      - 初始化第一行 也要遵循规则
     *      - 可以同时初始化第一行第一列
     * 
     * todo: dp[i][j] : [0, i] [0, j] 范围内的公共子序列
     *       dp[i][j] = s[i] == s[j] ?
     *                  1 + (j-1 < 0 ? 0 : +dp[i-1][j-1]) :
     *                  Math.max(dp[i-1][j], j-1 < 0 ? 0 : dp[i][j-1])
     * @param text1
     * @param text2
     * @return
     */
    public static int dp(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        if (m==0 || n==0) return 0;
        int[][] dp = new int[m][n];
        for (int i=0; i<n; i++)
            dp[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : (i-1<0 ? 0 :dp[0][i-1]);
//        for (int i=0; i<m; i++)
//            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : i-1<0 ? 0 :dp[i-1][0];

        for (int i=1; i<m; i++){
            for (int j=0; j<n; j++){
                dp[i][j] = text1.charAt(i) == text2.charAt(j) ?
                        1+ (j-1<0 ? 0 : dp[i-1][j-1]) :
                        Math.max(dp[i-1][j], (j-1<0?0:dp[i][j-1]));
            }
        }
        return dp[m-1][n-1];
    }
```