package Algorithm.Interview.Company.BZ.Q3;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static int[] SpiralMatrix (int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[] ans = new int[M * N];
        if (matrix == null || M ==0 ) return null;
        int index = 0;
        int low = 0;
        int high = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(low <= high && left <= right){

            for(int i=left; i <= right; i++)
                ans[index++] = matrix[low][i];
            for(int i = low+1; i <= high; i++)
                ans[index++] = matrix[i][right];
            if(low < high){
                for(int i= right-1; i >= left; i--)
                    ans[index++] = matrix[high][i];
            }
            if(left < right){
                for(int i = high-1; i >= low+1; i--)
                    ans[index++] = matrix[i][left];
            }

            low++;
            high--;
            left++;
            right--;
        }

        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {

        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}};
        Dump.array(SpiralMatrix(arr));
    }
}
