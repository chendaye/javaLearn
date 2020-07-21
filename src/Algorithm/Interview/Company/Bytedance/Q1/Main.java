package Algorithm.Interview.Company.Bytedance.Q1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * 1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 * 2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 * 3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 *
 * 输入描述:
 * 第一行包括一个数字N，表示本次用例包括多少个待校验的字符串。
 *
 * 后面跟随N行，每行为一个待校验的字符串。
 *
 * 输出描述:
 * N行，每行包括一个被修复后的字符串。
 *
 * 输入例子1:
 * 2
 * helloo
 * wooooooow
 *
 * 输出例子1:
 * hello
 * woow
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\Bytedance\\Q1\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        while (sc.hasNext()){
            solution(sc.nextLine());
        }
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param str
     */
    private static void solution(String str){
        int len = str.length();
        if (str.length() == 0) {
            System.out.println();
            return;
        }
        int i=1;
        int count = 1; // 1 或者 2
        int last_count=0; //之前的最长重复字符串长度
        StringBuilder res = new StringBuilder();
        res.append(str.charAt(0));
        int res_ind = 0;
        while (i<len){
            if (res.charAt(res_ind) == str.charAt(i)){
                if (last_count == 2){
                    i++;
                }else{ // last_count = 1
                    //进入一个相同的字符
                    if(count == 1){
                        res.append(str.charAt(i));
                        count++;
                        i++;
                        res_ind++;
                    }else{ // count == 2
                        i++;
                    }
                }

            }else {
                // 进入一个新的字符
                res.append(str.charAt(i));
                res_ind++;
                i++;
                last_count = count; //上一个字符的长度
                count=1; // 新的字符长度是 1
            }
        }
        System.out.println(res.toString());
    }
}
