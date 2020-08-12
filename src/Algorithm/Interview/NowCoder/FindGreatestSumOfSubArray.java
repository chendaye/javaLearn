package Algorithm.Interview.NowCoder;

/**
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算 连续子向量 的最大和,
 * 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
 * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray {

    //todo: 暴力解法
    public int force(int[] array){
        int max = Integer.MIN_VALUE; //todo: 在有负数的情况下 0 不是最小值
        //从每一个位置出发遍历
        for (int i = 0; i < array.length; i++){
            int current_sum = 0;
            int current_max = Integer.MIN_VALUE;
            for (int j = i; j < array.length; j++){
                current_sum += array[j];
                current_max = Math.max(current_max, current_sum);
            }
            max = Math.max(max, current_max);
        }
        return max;
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484?answerType=1&f=discussion
     * 来源：牛客网
     *
     * 典型的动态规划。
     * todo: dp[n]代表以当前元素为截止点的连续子序列的最大和，
     *  todo: 如果dp[n-1]>0，dp[n]=dp[n]+array[i]， 因为当前数字加上一个正数一定会变大；
     * todo: 如果dp[n-1]<0，array[i]不变，因为当前数字加上一个负数一定会变小。
     * 使用一个变量max记录最大的dp值返回即可。
     *
     * todo: dp 的关键在于， dp的定义
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        int max = array[0];
        dp[0] = array[0];
        for(int i=1; i < array.length; i++){
            //todo: 到 i - 1 的最大连续序列和， 如果 < 0 那么 以 i 截止的 最大连续序列 一定= array[i] 不包含 i 之后的
            dp[i] = dp[i - 1] > 0 ? dp[i-1] + array[i] : array[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }


    public static void main(String[] args) {
        FindGreatestSumOfSubArray sum = new FindGreatestSumOfSubArray();
        int[] arr = {6,-3,-2,7,-15,1,2,2};
//        int[] arr = {1,-2,3,10,-4,7,2,-5};
//        int[] arr = {-2,-8,-1,-5,-9};
        System.out.println(sum.FindGreatestSumOfSubArray(arr));
    }
}
