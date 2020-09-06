package Algorithm.Interview.Company.TX.Q7;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *5 3
 * 1 2 3 4 5
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/TX/Q7/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] l1 = new int[n];
        for (int i = 0; i < n; i++){
            l1[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] l2 = new int[m];
        for (int i = 0; i < m; i++){
            l2[i] = sc.nextInt();
        }

        int in = 0;
        int im = 0;
        while (in < n && im < m){
            if (l1[in] == l2[im]){
                System.out.print(l1[in] + " ");
                in++;
                im++;
            }else if(l1[in] > l2[im]){
                in++;
            }else{
                im++;
            }
        }

    }



}
