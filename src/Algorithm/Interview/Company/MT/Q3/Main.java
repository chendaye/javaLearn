package Algorithm.Interview.Company.MT.Q3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/MT/Q3/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<String> start = new ArrayList<>();
        int cnt = 0;
        for (int i = 1; i <= n; i++){
            String cur = sc.nextLine();
            String[] s = cur.split(" ");
            start.add(s[0]);
            if(start.contains(s[1])){
                cnt++;
                start.clear();
            }
        }
        System.out.print(cnt);
    }


}
