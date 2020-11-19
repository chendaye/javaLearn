package Algorithm.Interview.Company.JD.Q2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/JD/Q1/test.txt"));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println(0.0);
            return;

        }
        Double res = 0.0;
        for (int i=1; i<=n; i++){
            int m = (2*i*i - i) * 10;
            int k = 1;
            DecimalFormat df = new DecimalFormat("0.000000");
            double tmp = Double.parseDouble(df.format((double)k/m));
            res += tmp;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        String s = decimalFormat.format(res);
        System.out.println(s);

    }
}
