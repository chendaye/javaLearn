package Algorithm.Interview.Company.PDD.Q6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q6/test.txt"));
        Scanner sc = new Scanner(System.in);
        int  N = sc.nextInt();
        int  M = sc.nextInt();
        System.out.println(M);
        System.out.println(N);

    }
}
