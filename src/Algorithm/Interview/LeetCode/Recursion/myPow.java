package Algorithm.Interview.LeetCode.Recursion;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class myPow {
    public static void main(String[] args) {
        System.out.println(myPow(0.00001, 2147483647));
    }

    public static double myPow(double x, int n) {
        double ans = solve(x, Math.abs(n));
        return n >= 0 ? ans : 1 / ans;
    }

    public static double solve(double x, int n){
        if (n == 0) return 1;

        return  x * solve(x, n - 1);

    }


    /**
     * todo: 快速幂(二分法)
     *  https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
     *   向下整除 n // 2 等价于 右移一位 n >> 1 ；
     *   取余数 n%2 等价于 判断二进制最右一位值 n&1 ；
     *
     *   算法流程：
     * 当 x = 0x=0 时：直接返回 00 （避免后续 x = 1 / xx=1/x 操作报错）。
     * 初始化 res = 1res=1 ；
     * 当 n < 0n<0 时：把问题转化至 n \geq 0n≥0 的范围内，即执行 x = 1/xx=1/x ，n = - nn=−n ；
     * 循环计算：当 n = 0n=0 时跳出；
     * 当 n \& 1 = 1n&1=1 时：将当前 xx 乘入 resres （即 res *= xres∗=x ）；
     * 执行 x = x^2x=x
     * 2
     *   （即 x *= xx∗=x ）；
     * 执行 nn 右移一位（即 n >>= 1n>>=1）。
     * 返回 resres 。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }



}
