package Algorithm.Interview.Company.TX.Q4;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    Integer top1=-1;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\TX\\Q4\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Main main = new Main();


        String[] st1 = new String[N];


        while (sc.hasNext()){
            String[] next = sc.nextLine().split(" ");
            main.solution(next, st1);
        }

    }

    private  void solution(String[] val, String[] st1){

        String op = val[0];
        if (op.equals("add")){
            st1[++top1] = val[1];
        }else if(op.equals("poll")){
            if (top1 == 0){
                top1--;
            }else{
                st1 = Arrays.copyOfRange(st1,1, top1+1);
//                Dump.array(st1);
                top1--;
            }

        }else{
            Dump.array(st1);
           System.out.println(st1[0]);
        }
    }

}
