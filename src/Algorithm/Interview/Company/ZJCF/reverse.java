package Algorithm.Interview.Company.ZJCF;

import java.util.Collections;
import java.util.Vector;

public class reverse {
    public static void main(String[] args) {
        System.out.println(solve("1234"));
    }

    public static String solve(String str){
        if (str.length() == 0) return "";
        char[] chars = str.toCharArray();
        int l = 0, r = str.length() - 1;
        while (l <= r){
            char tmp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = tmp;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : chars) builder.append(c);
        return builder.toString();
    }
}
