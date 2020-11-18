package Algorithm.Interview.Company.GFYH.Q1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    //todo: 如何 O(n) 时间内找到 每一个元素 两边比自己大的元素个数
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/GFYH/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] nums = new int[8];
        nums = new int[]{1176, 1176, 1155, 1200, 1160, 1128, 1197, 1220};
        System.out.println(solve(8, nums));

    }
    public static int solve(int n, int[] nums){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int cnt = 0;
            for(int m = 0; m < i; m++){
                if(nums[m] >= nums[i]){
                    cnt++;
                }
            }
            for (int k = i + 1; k < n; k++){
                if(nums[k] >= nums[i]){
                    cnt++;
                }
            }
            System.out.println(i+"-----"+nums[i]+"---"+cnt);
            min = Math.min(min, cnt);
        }
        return min;
    }




}
