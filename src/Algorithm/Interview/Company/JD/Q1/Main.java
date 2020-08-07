package Algorithm.Interview.Company.JD.Q1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 题目描述：
 * 现有一个正整数，希望去掉这个数中某一个数字之后可以得到一个回文素数。
 *
 * 回文素数是指一个素数同时还是一个回文数（回文数即从左到右和从右到左均一样的数，例如12321）。【注意：一位数也被认为是回文数】
 *
 * 输入两个正整数N和M，满足N<M，请编写一个程序统计N和M之间存在多少个满足上述要求的数？
 *
 *
 *
 * 输入描述
 * 单组输入。
 *
 * 输入一行，包含两个正整数N和M，1<=N<M<=1000000，两个数字之间用空格隔开。
 * 110 120
 * 输出描述
 * 输出在N和M之间（包含N和M）满足要求的数的个数。
 * 10
 *
 *
 * 样例输入
 * 110 120
 * 样例输出
 * 10
 *
 * 提示
 * 样例解释
 * 在110和120之间存在10个满足要求的数，分别是110、111、112、113、114、115、116、117、118和119，
 * 它们去掉最后一位数都可以得到一个回文素数11。120不符合。故最终结果为10
 */
public class Main {
    /**
     *现有一个正整数，希望去掉这个数中某一个数字之后可以得到一个回文素数
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/JD/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        Integer N = sc.nextInt();
        Integer M = sc.nextInt();
        if (N > M){
            System.out.println(0);
            return;
        }
        int count = 0;
        for (int i = N; i <= M; i++){
            String s = Integer.toString(i);
            boolean flag = false;
            // 去掉每一个位置字符
            for (int k = 0; k < s.length(); k++){
                String tmp;
                if (k == 0){
                    tmp = s.substring(1);
                    while (tmp.length() > 0 && tmp.charAt(0) == '0'){
                        tmp = tmp.substring(1);
                    }
                    if (tmp.length() == 0){
                        continue;
                    }
                }else if(k == s.length() - 1){
                    tmp = s.substring(0, s.length() - 1);
                }else {
                    tmp  = s.substring(0, k) + s.substring(k + 1);
                }

                //是否是回文素数
                if (helper(tmp)) {
                    flag = true;
                    break;
                }
            }
            if (flag) count++;
        }
        System.out.println(count);
    }

    public static boolean helper(String str){
        //是否是素数
        if (!prime(Integer.parseInt(str))) return false;
        //是否是回文
        return huiwen(str);
    }
    // 判断是不是素数
    public static boolean prime(int num){
        double sqrt = Math.sqrt(num);
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        // 偶数不可能是素数
        if (num % 2 == 0) return false;
        for (int i = 3; i <= sqrt; i += 2){
            if (num % i ==0)
                return false;
        }
        return true;
    }

    //是否是回文
    public static boolean huiwen(String str){
        int start = 0;
        int end = str.length() - 1;
        while (start <= end){
            if (str.charAt(start) != str.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}
