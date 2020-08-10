package Algorithm.Interview.Company.WY.Q2;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public  static int total = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WY/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] record = new int[n];
        for (int i = 0; i < n; i++){
            record[i] = sc.nextInt();
        }
        helper(record,0,  new ArrayList<Integer>());
        System.out.println(total);
    }

    /**
     *  todo: dp[i] : 1~i 范围内最高分
     *        base case: dp[0] = 0
     *
     * @param record
     * @return
     */
   public static void helper(int[] record, int index,  ArrayList<Integer> path){
        if (index > record.length){
            return;
        }
        //todo: index == record.length 时 说明 [0, record.length] 已经考虑完了
        if (index == record.length){
            Iterator<Integer> iterator = path.iterator();
            Dump.iterator(path);
            int sum = 0;
            while (iterator.hasNext()){
                sum += iterator.next();
            }
            String s = Integer.toString(sum);
            if (s.indexOf('5') == -1)
                total = Math.max(total, sum);
        }

        for (int i = index; i < record.length; i++){
            path.add(record[i]);
            helper(record, i+1, path); //todo: i+1 最大值 = record.length
            path.remove(path.size() - 1);
        }
   }
}
