package Algorithm.Interview.Company.BZ.Q2;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static int GetFragment (String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        ArrayList<Integer> ans = new ArrayList<>();
        int len = 0;
        char pre = chars[0];
        for (char c : chars){
            if (pre == c){
                len++;
            }else{
                ans.add(len);
                len = 1;
                pre = c;
            }
        }
        ans.add(len);
        int sum = 0;
        for (int n : ans){
            sum += n;
        }
        return sum / ans.size();
    }
    public static void main(String[] args) throws FileNotFoundException {
        String str = "abc";
        System.out.println(GetFragment(str));


    }
}
