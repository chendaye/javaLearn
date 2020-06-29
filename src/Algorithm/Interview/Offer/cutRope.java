package Algorithm.Interview.Offer;

/**
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
 * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 *
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class cutRope {

    /**
     * todo: 动态规划
     *      - 状态、选择、base case
     *      - 需要知道多少信息可以把原问题拆成重叠子问题
     *      - 抽象问题的能力
     *
     * todo: 回溯
     *      - 路径、选择列表
     *      - 路径 sum  ~ [1, target)
     *      - 选择列表 [target - sum, target)
     * @param target
     * @return
     */
    int max = 1;
    public int cutRope(int target) {
        trackBack(0, 1, target);
        return max;
    }

    /**
     *todo: 回溯 超时
     * @param path int 已经切下的绳子
     * @param res int 已经切下的乘积
     * @param target int 绳子总长度
     * @return
     */
    public void trackBack(int path, int res, int target){
        if (path == target){ //绳子已经切完
            max = Math.max(max, res);
            return ;
        }
        //剩余绳子长 target - path，下一步所有可能的切法
        for (int i = 1; i <= target - path; i++){
            path += i;
            res *= i;
            trackBack(path, res, target);
            path -= i;
            res /= i;
        }
    }



    /**
     * todo: 动态规划
     * @param target
     * @return
     */
    public int dp(int target) {
        return target;
    }

    public static void main(String[] args) {
        cutRope cutRope = new cutRope();
        cutRope.cutRope(8);
        System.out.println(cutRope.max);
    }
}
