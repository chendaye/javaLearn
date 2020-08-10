package Algorithm.Interview.Company.WY.Q1;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static int current = 0;
    public static int total = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WY/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            int cur = sc.nextInt();

            for (int k = 1; k <= cur; k++) {
                if (prime(k)) list.add(k); // 1~cur 素数
            }
//            Dump.iterator(list);
            current = 0;
            DFS(cur, 0, 0, list,  new ArrayList<Integer>());

            total += current;
        }
        System.out.println(total);
    }

    public static void DFS(int n, int index, int sum_num, ArrayList<Integer> p,  ArrayList<Integer> path){
        if (sum_num > n || index > p.size()) return ;
        if (sum_num == n){
            Dump.iterator(path);
            System.out.println("+++"+n);
            current = Math.max(path.size(), current);
        }

        for (int i = index; i < p.size(); i++){
            path.add(p.get(i));
            DFS(n, i+1, sum_num + p.get(i), p, path);
            path.remove(path.size() - 1);
        }
    }
    // 判断是不是素数
    public static boolean prime(int num){
        double sqrt = Math.sqrt(num);
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        // 偶数不可能是素数
        if (num % 2 == 0) return false;
        for (int i = 3; i <= sqrt; i += 2){
            if (num % i ==0)
                return false;
        }
        return true;
    }
}
