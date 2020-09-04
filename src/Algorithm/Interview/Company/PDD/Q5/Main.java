package Algorithm.Interview.Company.PDD.Q5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q5/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N][N];
        dp[0][1] = 2; dp[0][N-2] = 1;
        dp[N-1][1] = 5; dp[N-1][N-2] = 6;
        dp[1][0] = 3; dp[N-2][0] = 4;
        dp[1][N-1] = 8; dp[N-2][N-1] = 7;
        for (int[] arr : dp){
            for (int n : arr){
                System.out.print(n+" ");
            }
            System.out.println();
        }
    }
}
