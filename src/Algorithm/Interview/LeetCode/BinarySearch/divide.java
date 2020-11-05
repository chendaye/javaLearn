package Algorithm.Interview.LeetCode.BinarySearch;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *  
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 *
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class divide {

    public static void main(String[] args){
        System.out.println(method(-2147483648, -1));
    }

    /**
     * 举例：算 63 / 8
     * 过程为：63 / 8 = (63-32) / 8 + 4 = (63-32-16) / 8 + 2 + 4 = (63-32-16-8) / 8 + 1+ 2 + 4 = 7
     * 其中(63-32-16-8) / 8 = 7 / 8 = 0
     * @param dividend
     * @param divisor
     * @return
     */
    public static int method(int dividend, int divisor){
        int min = -2147483648, max = 2147483647;
        boolean flag = true;
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) flag = false;
        //todo: 因为正数存在溢出问题，所以转化为负数
        dividend = dividend > 0 ? 0 - dividend : dividend;
        divisor = divisor > 0 ? 0 - divisor : divisor;
        if (dividend > divisor) return 0;
        int mut = 0;
        while (dividend <= divisor){
            int cur = divisor;
            int multiple = -1;
            while (cur + cur >= dividend){
                cur += cur;
                multiple -= multiple;
            }
            dividend -= cur;
            mut -= multiple;
        }
        return flag ? (mut == min ? max : 0 - mut) : mut;

    }


}
