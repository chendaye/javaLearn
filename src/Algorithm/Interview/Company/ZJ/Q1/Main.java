package Algorithm.Interview.Company.ZJ.Q1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    static int max = 0;
    public static int GetMaxConsecutiveOnes (int[] arr, int k) {
        if (arr.length == 0) return 0;
        int cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <  arr.length; i++){
            if (arr[i] == 0) {
                map.put(i, cnt++);
            }
        }
        k = Math.min(k, cnt);
        ArrayList<Integer> path = new ArrayList<>();
        back(arr, map, k, path, 0);
        return max;
    }

    public static void back(int[] arr, HashMap<Integer, Integer> map, int k, ArrayList<Integer> path, int start){
        if (path.size() == k){
            int len = 0;
            int maxLen = 0;
            for (int i = 0; i < arr.length; i++){
                if (arr[i] == 0 && !path.contains(map.get(i))){
                    maxLen = Math.max(len, maxLen);
                    len = 0;
                }else{
                    len++;
                }
            }
            maxLen = Math.max(len, maxLen);
            max = Math.max(maxLen, max);
        }

        for (int i = start; i < map.size(); i++ ){
            path.add(i);
            back(arr, map, k, path, i+1);
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}; // [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1],3
        int k = 3;
        System.out.println(GetMaxConsecutiveOnes(arr, k));
    }
}
