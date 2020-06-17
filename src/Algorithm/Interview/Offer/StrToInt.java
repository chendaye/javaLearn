package Algorithm.Interview.Offer;

import java.util.regex.Pattern;

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 *
 * 输入一个字符串,包括数字字母符号,可以为空
 *
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * +2147483647 2147483647
 * 1a33 0
 *
 *
 *
 */
public class StrToInt {
    public int StrToInt(String str) {
        if (str.length() == 0) return 0;
        String pattern = "^[+,-]?[0-9]{1,}$";
        boolean matches = Pattern.matches(str, pattern);
        if (!matches) return 0;
        int res = 0;
        int end = 0;
        boolean negative = false;
        if (str.startsWith("+")) end = 1;
        if (str.startsWith("-")){
            end = 1;
            negative = true;
        }
        int m = 1;
        int start = str.length() - 1;
        while (start >= end){
            res += Character.getNumericValue(str.indexOf(str)) * m;
            m *= 10;
            start--;
        }
        return negative ? res * -1 : res;
    }

    public static void main(String[] args) {

    }
}
