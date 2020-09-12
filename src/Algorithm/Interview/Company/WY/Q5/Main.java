package Algorithm.Interview.Company.WY.Q5;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WY/Q5/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N 组数据
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] strs = sc.nextLine().split(" ");
            String s1 = strs[0];
            String s2 = strs[1];
            String s = strs[2];
            int cnt = Integer.parseInt(strs[3]);

        }

    }

    public static boolean shuffle(String s1, String s2, String s, int cnt){

        while (cnt > 0 && s.length() > 0 && (s1.length() > 0 || s2.length() > 0)){
            if (s.charAt(0) == s1.charAt(0)){

            }else if(s.charAt(0) == s2.charAt(0)){

            }else {
                return false;
            }
        }
        return s.length() == 0 ? true : false;
    }




}
