package Algorithm.Interview.Company.PDD.Q8;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q8/test.txt"));
        Scanner sc = new Scanner(System.in);
        int  N = sc.nextInt(); // 整数范围
        int  M = sc.nextInt(); // 特征值个数
        int[] nums = new int[M];

        for (int i = 0; i < M; i++){
            nums[i] = sc.nextInt();
        }

        int res = solution(nums, N);
        System.out.println(res);
    }

    public static int solution(int[] nums, int N){
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++){
            for (int n : nums){
//                System.out.println(i+"%"+n+"="+i % n);
                if (!list.contains(i) && i % n == 0){
                    cnt++;
                    list.add(i);
                }
            }
        }
        return cnt;
    }
}
