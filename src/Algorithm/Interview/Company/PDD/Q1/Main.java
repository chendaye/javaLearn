package Algorithm.Interview.Company.PDD.Q1;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();
        int back = 0;
        int left = s;
        if(s ==0 && n == 0){
            System.out.println(0+" "+0);
            return;
        }
        if (n == 0){
            System.out.println(left+" "+back);
            return;
        }
        for (int i = 1; i <= n; i++){
            int cur = sc.nextInt();
            if(cur == left){
                if(i <= n-1){
                    System.out.println("paradox");
                    return;
                }else{
                    left=0;
                }
            }else if(cur < left){
                left -= cur;
            }else{
                left = cur - left;
                back++;
            }
        }
        System.out.println(left+" "+back);

    }
}
