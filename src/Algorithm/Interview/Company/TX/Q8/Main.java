package Algorithm.Interview.Company.TX.Q8;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *5 3
 * 1 2 3 4 5
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/TX/Q8/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        int[] nums = new  int[n];
        for (int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }

        int[] temp = Arrays.copyOf(nums, nums.length);

        Arrays.sort(nums); //todo: 排序

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> position = new HashMap<>();
        int inx = 0;
        for (int i : nums){
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
            position.put(i, inx++); // i的最后索引
        }

        int len = (n-2) / 2 + 1;
        for (int i: temp){
            int k = map.get(i); // i的个数
            int p = position.get(i);
            int start = p - k + 1; // i 在 nums 中起始位置
            if (start < len){
                int d = len - start;
                System.out.println(nums[start+d]);
            }else if(start == len){
                System.out.println(nums[start-1]);
            }else{
                int d = start- len;
                System.out.println(nums[start - d-1]);
            }
        }

    }


}
