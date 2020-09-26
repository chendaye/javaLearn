package Algorithm.Interview.Company.KDXF.Q3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/KDXF/Q3/test.txt"));
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String s = str.replaceAll("_{1,}", "_");
        StringBuilder sb = new StringBuilder(s);
        if (sb.charAt(0) == '_') sb.deleteCharAt(0);
        if (sb.charAt(sb.length()-1) == '_') sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());

//        int left = 0, right = 0;
//
//
//
//        StringBuilder builder = new StringBuilder();
//        while (right < str.length()){
//            while (right < str.length() && str.charAt(right) == '_'){
//                left++;
//                right++;
//            }
//
//            while (right < str.length() && str.charAt(right) >= 'a' && str.charAt(right) <= 'z'){
//                right++;
//            }
//            if (left != right){
//                builder.append(str.substring(left, right)+"_");
//            }
//
//            left = right;
//
//        }
//        builder.deleteCharAt(builder.length() - 1);
//        System.out.println(builder.toString());
    }



}
