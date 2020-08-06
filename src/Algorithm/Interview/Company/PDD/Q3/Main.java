package Algorithm.Interview.Company.PDD.Q3;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/PDD/Q3/test.txt"));
        Scanner sc = new Scanner(System.in);
        int zc = sc.nextInt();
        int wc = sc.nextInt();
        int mwz = sc.nextInt();


        int[][] zcs = new int[zc][2];
        int[][] wcs = new int[wc][2];
        for (int i = 0; i < zc; i++){
            zcs[i][0] = sc.nextInt();
            zcs[i][1] = sc.nextInt();
        }
        for (int i = 0; i < wc; i++){
            wcs[i][0] = sc.nextInt();
            wcs[i][1] = sc.nextInt();
        }

        // 热量从大到小排序
        Arrays.sort(zcs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        Arrays.sort(wcs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int min = Integer.MAX_VALUE; // 满足美味的最少热量

        if (zc == 0){
            for (int j = 0; j < wc; j++){
                if (wcs[j][1] >= mwz){
                    min = Math.min(min,  wcs[j][0]);
                }else{
                    break;
                }
            }
        }else{
            for (int i = 0; i < zc; i++){
                int left = mwz - zcs[i][1]; // 中餐后剩余的美味值
                if (left <= 0){
                    min = Math.min(min, zcs[i][0]);
                }else {
                    for (int j = 0; j < wc; j++){
                        if (wcs[j][1] >= left){
                            min = Math.min(min, zcs[i][0] + wcs[j][0]);
                        }else{
                            break;
                        }
                    }
                }
            }

        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);


    }
}
