package Algorithm.Interview.Offer;

import java.util.Arrays;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class match {
    public boolean match(char[] str, char[] pattern)
    {
        if (str.length == 0) return pattern.length == 0;
        boolean first = str[0] == pattern[0] || pattern[0] == '.'; // 匹配 '.'
        if (pattern.length >= 2 && pattern[1] == '*'){ // 匹配 * 号
            //todo: 第一个匹配
            return first && match(Arrays.copyOfRange(str,1, str.length), pattern)
                    ||
                    //todo： 第一个不匹配
                    match(Arrays.copyOfRange(str,0, str.length), Arrays.copyOfRange(pattern, 2, pattern.length));
        }else {
            return first && match(Arrays.copyOfRange(str,1, str.length), Arrays.copyOfRange(pattern, 1, pattern.length));
        }
    }

    public static void main(String[] args) {

    }
}
