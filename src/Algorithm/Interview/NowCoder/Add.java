package Algorithm.Interview.NowCoder;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * todo: 异或 ^ : 不进位加法
 * 普通加法	异或
 * 1 + 1 = 0	1 ^ 1 = 0(错误)
 * 1 + 0 = 1	1 ^ 0 = 1(正确)
 * 0 + 1 = 1	0 ^ 1 = 1(正确)
 * 0 + 0 = 0	0 ^ 0 = 0(正确)
 *
 *
 * todo" 与 & ： 加法的进位
 * 1 & 1 = 1(进位)
 * 1 & 0 = 0(不进位)
 * 0 & 1 = 0(不进位)
 * 0 & 0 = 0(不进位)
 *
 * todo: 拥有了两个基本表达式：
 *          - 执行加法 x ^ y
 *          - 进位操作 ( x & y ) << 1
 *
 *
 * 二位加法
 * 例子：
 *
 * *正确的加法计算：11+01 = 100 *
 * 使用位运算实现二位加法：
 * 按位加法： res1 = 11 ^ 01 = 10
 * 与运算进位： res2 = (11 & 01) << 1 = ( 01 ) << 1 = 010
 * res1 ^ res2 = 10 ^ 010 = 00
 * (10 & 10) << 1 = 100
 */
public class Add {
    public int Add(int num1,int num2) {
        int carry = 0; // 进位
        int sum = 0; // 无进位 加和
        do{
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            // 准备下一次相加操作
            num1 = sum;
            num2 = carry;
        }while (carry != 0); //todo: 进位不为0则继续执行加法处理进位(包括负数)
        return sum;
    }
}
