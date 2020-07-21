package Algorithm.Interview.Company.Huawei;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\test.txt"));
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        //todo: m 个数
        int m = Integer.parseInt(line[0]);
        //todo: k 个序列
        int k = Integer.parseInt(line[1]);
        String[] s = sc.nextLine().split(" ");
        int[] nums = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();


    }
}
