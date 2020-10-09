package Algorithm.Interview.Company.WZYH.Q3;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static int max = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WZYH/Q3/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = sc.nextInt();

        ArrayList<Integer> psth = new ArrayList<>();
        helper(nums, psth, 3, 0);
        System.out.print(max);
    }

    public static void helper(int[] nums, ArrayList<Integer> path, int len, int start){
        if (path.size() == len){
            max++;
            return;
        }

        for (int i = start; i < nums.length; i++){
            if (3-path.size() > nums.length - i ) return;
            if (path.size() == 0 || nums[i] >= path.get(path.size() - 1)){
                path.add(nums[i]);
                helper(nums, path, len, i + 1);
                path.remove(path.size() - 1);
            }

        }
    }

}
