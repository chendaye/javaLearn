package Algorithm.Interview.Company.WZYH.Q1;

import Grammar.LanguageElement.Array;
import Utils.Dump;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    static int max = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WZYH/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();


        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++){
            int tmp = sc.nextInt();
            if (!list.contains(tmp))
                list.add(tmp);
        }

        Collections.sort(list, (a, b) -> {
            return a - b;
        });
        int[] nums = new int[list.size()];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++){
            map.put(list.get(i), i);
            nums[i] = list.get(i);
        }

        for (int j = 0; j < M; j++){
            int n = sc.nextInt();
            int left = helper(n, nums);
//            System.out.print(n+"===="+left+"----");
            if(left == n || left == nums[0] || left == nums[N - 1]){
                System.out.println(left);
            } else {
                int index = map.get(left);
                int a = Math.abs(n - nums[index]);
                int b = Math.abs(n - nums[index + 1]);
                if(a == b){
                    System.out.println(Math.min(nums[index + 1], nums[index]));
                }else{
                    System.out.println(a < b ? nums[index] : nums[index + 1]);
                }
            }
        }

    }

    //left
    public static int helper(int n, int[] nums){
        int left = 0,  right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < n){
                left = mid + 1;
            }else if(nums[mid] > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if (right < 0) return nums[0];
        return nums[right];
    }



}
