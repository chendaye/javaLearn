package Algorithm.Interview.NowCoder;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩.
 *
 * 输入描述:
 * 输入包括多组测试数据。
 * 每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
 * 学生ID编号从1编到N。
 * 第二行包含N个整数，代表这N个
 * 学生的初始成绩，其中第i个数代表ID为i的学生的成绩,接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,
 *
 * 当C为'Q'的时候,表示这是一条询问操作，他询问ID从A到B  [A,B]的学生当中，成绩最高的是多少
 * 当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。 st[A] = B
 *
 * 输出描述:
 * 对于每一次询问操作，在一行里面输出最高成绩.
 *
 * 输入例子1:
 * 5 7
 * 1 2 3 4 5
 * Q 1 5
 * U 3 6
 * Q 3 4
 * Q 4 5
 * U 4 5
 * U 2 9
 * Q 1 5
 *
 * 输出例子1:
 * 5
 * 6
 * 5
 * 9
 */
public class Score {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\NowcoderLeetcode\\test.txt"));
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int N = Integer.parseInt(s[0]); //todo:N 个学生
        int M = Integer.parseInt(s[1]); //todo: M 个操作
        if (N==0 || M==0) return;
        //todo:分数
        String[] st = sc.nextLine().split(" ");
        int[] tmp = Arrays.stream(st).mapToInt(Integer::parseInt).toArray();
        if(N != tmp.length) return;
        int[] score = new int[tmp.length+1];
        for (int i=0; i<tmp.length; i++)
            score[i+1] = tmp[i];

        while (sc.hasNext()){
            String[] line = sc.nextLine().split(" ");
            if (line.length != 3) return;
            char opr = line[0].charAt(0);
            int A = Integer.parseInt(line[1]);
            int B = Integer.parseInt(line[2]);
            if (opr == 'Q'){
                int min = Math.min(A,B);
                int max = Math.max(A,B);
                if (min <0 || max > N) return;
                top(score, A, B);
            }else {
                if(A < 0 || A>N) return;
                score[A] = B;
            }
        }
    }

    /**
     * [A,B] 的最高成绩
     * @param score
     * @param A
     * @param B
     */
    private static void top(int[] score, int A, int B){
        if(A > B){
            int tmp = A;
            A=B;
            B=tmp;
        }
        int max = Integer.MIN_VALUE;
        for (int i=A; i<=B; i++)
            max = Math.max(max, score[i]);
        System.out.println(max);
    }
}
