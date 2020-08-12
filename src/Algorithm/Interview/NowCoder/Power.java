package Algorithm.Interview.NowCoder;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponentonent。求base的exponentonent次方。
 *
 * 保证base和exponentonent不同时为0
 */
public class Power {





    //todo: 快速幂 递归
    public static double Power2(double base, int exp) {
        boolean flag = exp < 0;
        if (flag) {
            exp = -exp;
        }
        double result = getPower(base, exp);
        return flag ? 1 / result : result;
    }

    public static double getPower(double base, int exp) {
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            return base;
        }
        double ans = getPower(base, exp >> 1);
        ans *= ans;
        if ((exp & 1) == 1) {
            ans *= base;
        }
        return ans;
    }

    //todo: 快速幂 非递归
    public double Power(double base, int exponent) {
        boolean flag = false;
        if (exponent < 0) {
            flag = true;
            exponent = -exponent;
        }
        double ans = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) { //todo: 如果是奇数,所有的奇数，无论正负，最低位必然是1，偶数，无论正负，包括0，最低位必然是0
                ans = ans * base;
            }
            base *= base;
            exponent >>= 1;
        }
        return flag ? 1 / ans : ans; //todo: 处理负数幂
    }

    //不是开根号
    public double train(double base, int exp){
        boolean flag = false;
        if (exp < 0){
            flag = true;
            exp = -exp;
        }
        double res = 1;
        while (exp > 0){ // 4 / 2 = 2  2 / 2 = 1    最后一定变成奇数
            if ((exp & 1) == 1) res *= base;
            base *= base;
            exp >>=  1;
        }
        return flag ? 1 / res : res;
    }
    public static void main(String[] args) {
        Power q7 = new Power();

        System.out.println(q7.train(2,  3));
    }
}
