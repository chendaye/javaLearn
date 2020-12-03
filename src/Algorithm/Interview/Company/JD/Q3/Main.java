package Algorithm.Interview.Company.JD.Q3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 奇数： *3+1
 * 偶数： /2
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/JD/Q3/test.txt"));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solve(10, 0));

    }

    public static int solve(int n, int cnt) {
        if (n > 1){
            if (n % 2 == 0){
                n = n >> 1;
            }else{
                n = n * 3 + 1;
            }
            cnt++;
            return solve(n, cnt);
        }else if (n == 1){
            return cnt;
        }else {
            return 0;
        }
    }


}
