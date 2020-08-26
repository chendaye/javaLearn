package Algorithm.Interview.Company.TX.Q6;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *5 3
 * 1 2 3 4 5
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/TX/Q6/test.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++){
            list.add(sc.nextInt());
        }
        Dump.iterator(list);
    }
}
