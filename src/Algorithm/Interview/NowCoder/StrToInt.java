package Algorithm.Interview.NowCoder;

import java.util.regex.Pattern;

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * 示例1
 * 输入
 * 复制
 * +2147483647
 * 1a33
 * 输出
 * 复制
 * 2147483647
 * 0
 */
public class StrToInt {
    public int StrToInt(String str) {
        if (str.length() == 0) return 0;
        String pattern = "^[+,-]?[0-9]{1,}$";
        boolean matches = Pattern.matches(pattern, str);
        if (!matches) return 0;
        boolean negative = false;
        int sum = 0;
        int m = 1;
        int left = 0, right = str.length() - 1;
        if (str.startsWith("+")) left = 1;
        if (str.startsWith("-")) {
            left = 1;
            negative = true;
        };
        while (right >= left){
            sum += Character.getNumericValue(str.charAt(right)) * m;
            m *= 10;
            right--;
        }
        return negative ? -1 * sum : sum;
    }

    public static void main(String[] args) {
        String str = "-0010jh0";
        System.out.println(new StrToInt().StrToInt(str));
    }
}
