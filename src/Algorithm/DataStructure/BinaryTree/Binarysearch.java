package Algorithm.DataStructure.BinaryTree;

import Utils.Dump;

import java.util.Arrays;

public class Binarysearch {
    public static void main(String[] args) {
        int[] arr = {-9, 6, -11, -12, 0, -4, -10, 5, -3, 7};
        Arrays.sort(arr);

        Dump.array(arr);
        Dump.dump(search(arr, -4));
    }

    public static int search(int[] arr, int target){
        int l=0;
        int r=arr.length-1;
        while (l<=r){
            int mid=l + (r-l)/2;

            if (arr[mid] == target) return mid;

            if (target < arr[mid]){
                // [l,mid-1]
                r = mid-1;
            }else {
                // [mid+1, r]
                l = mid+1;
            }
        }
        return -1;
    }
}
