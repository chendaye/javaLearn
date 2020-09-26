package Algorithm.Interview.Company.KDXF.Q2;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/KDXF/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String[] split = sc.nextLine().split(",");
        int[] nums = new int[split.length];
        int i = 0;
        for (String s : split){
            nums[i++] = Integer.parseInt(s);
        }
        sort(nums);

        for (int k = 0; k < nums.length; k++){
            System.out.print(nums[k]);
            if (k < nums.length - 1)
                System.out.print(",");
        }

    }


    public static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++){
            int ind = i;
            for (int j = i; j < arr.length; j++){
                if (arr[ind] > arr[j]){
                    ind = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[ind];
            arr[ind] = tmp;
        }
    }
}
