package Algorithm.Interview.Company.TX.Q1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\TX\\Q1\\test.txt"));

        Scanner sc = new Scanner(System.in);
       int N = sc.nextInt();
       int M = 0;
        sc.nextLine();
        LinkedList<String> queue = new LinkedList<>();
        while (sc.hasNext()){
           String[] cur = sc.nextLine().split(" ");
            String first = cur[0];
            if (first.charAt(0) >= '0' && first.charAt(0) <= '9'){
               M = Integer.parseInt(cur[0]);
           }else if (first.equals("PUSH")){
               queue.add(cur[1]);
           }else if(first.equals("TOP")){
                if (queue.size() >= 1) {
                    String s = queue.getFirst();
                    System.out.println(s);
                } else
                    System.out.println(-1);
            }else if (first.equals("POP")){
                if (queue.size() >= 1) {
                    String s = queue.remove();
                } else
                    System.out.println(-1);
            }else if (first.equals("SIZE")){
                System.out.println(queue.size());
            }else {
                queue.clear();
            }
       }


    }
}
