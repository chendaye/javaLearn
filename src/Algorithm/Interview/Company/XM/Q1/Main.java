package Algorithm.Interview.Company.XM.Q1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/XM/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] strs = line.split(" ");


        for (String s : strs){
            System.out.println(helper(s));
        }




    }

    /**
     * 数字 字符 大写 小写
     * 符合 0
     * 长度不符 1
     * 类型不符 2
     * @param str
     * @return
     */
    public static int helper(String str){
        int len = str.length();
        if(len < 8 || len > 120) return 1;

        String reg1 = ".*[0-9]+.*";
        boolean matches1 = Pattern.matches(reg1, str);
        if (!matches1) return 2;

        String reg2 = ".*[a-z]+.*";
        boolean matches2 = Pattern.matches(reg2, str);
        if (!matches2) return 2;

        String reg3 = ".*[A-Z]+.*";
        boolean matches3 = Pattern.matches(reg3, str);
        if (!matches3) return 2;

        String reg4 = ".*[^A-Za-z0-9]+.*";
        boolean matches4 = Pattern.matches(reg4, str);
        if (!matches4) return 2;

        return 0;
    }
}
