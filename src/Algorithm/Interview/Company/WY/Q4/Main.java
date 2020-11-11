package Algorithm.Interview.Company.WY.Q4;

import Utils.Dump;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WY/Q4/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N 张牌
        int M = sc.nextInt(); // 洗牌M次
        int[] card = new int[N];
        for (int i = 0; i < N; i++){
            card[i] = sc.nextInt();
        }
        int[] cur = Arrays.copyOf(card, card.length);
        for (int i = 1; i <= M; i++){
            int[] a = Arrays.copyOfRange(cur, 0, N / 2);
            int[] b = Arrays.copyOfRange(cur, N / 2, cur.length);
            cur = shuffle(N, a, b);
        }

        for (int i = 0; i < N; i++){
            System.out.print(cur[i]+ " ");
        }

    }

    public static int[] shuffle(int N, int[] a, int[] b){
        int[] ans = new int[N];
        int la = a.length;
        int lb = b.length;

        int i = 0, j = 0, inx = 0;
        boolean flag = true; // true 先发b
        while(i < la && j < lb){
            if (flag){
                ans[inx++] = b[j++];
                flag = false;
            }else{
                ans[inx++] = a[i++];
                flag = true;
            }
        }
        while (i < la)
            ans[inx++] = a[i++];
        while (j < lb)
            ans[inx++] = b[j++];
        return ans;
    }




}
