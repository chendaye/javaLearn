package Algorithm.Interview.NowCoder;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 比如n=3时，2*3的矩形块有3种覆盖方法：
 *
 * todo: 大矩形 由 n 个 高=2 的小矩形组成
 *         - 若 n=1 的位置 竖着放 则 [2, n] 位置 的摆放总数 = 这种情况的摆放种数
 *         - 若 n=1 n=2 位置横着放  则 [2, n] 位置的摆放总数 = 这种情况的摆放种数
 *         - 动态规划： dp[i] = dp[i - 1] + dp[i - 2]  斐波拉契 数列
 */
public class RectCover {
    public int RectCover(int target) {
        if(target  <= 1){
            return 1;
        }
        if(target*2 == 2){
            return 1;
        }else if(target*2 == 4){
            return 2;
        }else{
            return RectCover((target-1))+RectCover(target-2);
        }
    }

    //todo:更一般的结论，如果用1*m的方块覆盖m*n区域，递推关系式为f(n) = f(n-1) + f(n-m)，(n > m)
    public int dp(int target) {
        if (target <= 2) return target;
        int[] dp = new int[target + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= target; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }

    public static void main(String[] args) {
        RectCover q5 = new RectCover();

        System.out.println(q5.RectCover(4));
        System.out.println(q5.dp(4));
    }
}
