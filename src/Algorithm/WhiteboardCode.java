package Algorithm;


import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;


public class WhiteboardCode {
    public static void main(String[] args) throws FileNotFoundException {
        //todo: 输入输出
        Scanner scanner = new Scanner(System.in);

        //todo: Int
        Integer i = 1;
        String s = Integer.toString(222);
        int i1 = Integer.parseInt("222");
        int minValue = Integer.MIN_VALUE;
        int minValue1 = Integer.MIN_VALUE;

        //todo: String
        Character c = 'a';
        String str = "abcdef";
        char[] chars = str.toCharArray();
        char c1 = str.charAt(1);
        int length = str.length();
        String abc = str.replace("abc", "");
        String substring = str.substring(0, 3); // [l, r)
        boolean yyy = str.equals("yyy");
        int i2 = str.indexOf("bc");
        int i3 = str.indexOf('c');
        //todo: 正则
        str.startsWith("a");
        str.endsWith("a");
        // ^ 【开始】 $ 【结束】  . 【任意】  + 【>=1】  ? 【>=0】
        // {n} 【=n】 {n,} 【>=n】 {n,m} 【>= b  <= m】
        // 此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串
        // x|y [xy|c] [a-z] [^a-z]
        // \d 等效 [0-9] \D 等效 【^0-9】  \w 等效 [A-Za-z0-9] \W 等效 [A-Za-z0-9]
        // \s \S 	 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
        boolean matches = Pattern.matches("^aa$", str);
        boolean matches1 = str.matches("^aaa$");
        System.out.println(i2);

        //todo: 数组
        int[] arr = {1,2,3,4,5,6,7,8,9};
        Arrays.sort(arr);
        Arrays.sort(arr, 0, 3);
        int[] ints = Arrays.copyOf(arr, arr.length);
        Arrays.copyOfRange(arr, 0, 4);



    }
}
