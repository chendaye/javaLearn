package Algorithm.Interview.Company.TX.Q2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 小Q想要给他的朋友发送一个神秘字符串，但是他发现字符串的过于长了，
 * 于是小Q发明了一种压缩算法对字符串中重复的部分进行了压缩，
 * 对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且1<=m<=100)，
 * 例如字符串ABCABCABC将会被压缩为[3|ABC]，现在小Q的同学收到了小Q发送过来的字符串，你能帮助他进行解压缩么？
 *
 * 输入第一行包含一个字符串s，代表压缩后的字符串。
 * S的长度<=1000;
 * S仅包含大写字母、[、]、|;
 * 解压后的字符串长度不超过100000;
 * 压缩递归层数不超过10层;
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/TX/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        String line = sc.nextLine();
        StringBuilder sb = new StringBuilder(line);
//        char[] split = line.toCharArray(); //todo:转化为字符数组
        main.decode(sb);
        System.out.println(sb.toString());
    }

    /**
     * todo: 解码 [3|B[2|CA]]
     */
    private void decode(StringBuilder string){
        int i=0;
        int start = -1, mid=-1, end = -1;
        boolean flag = false;
        //todo: start mid end 指向最内层的 要解码的字符串 ;类似于栈
        while (i < string.length()){
            if (string.charAt(i) == '[')
                start = i; //todo:找到最右边的 [
            if (string.charAt(i) >= '|')
                mid=i; //todo:找到最右边的一个 |
            if (string.charAt(i) == ']'){
                end=i; break; //todo: 遇到第一个 ] 就退出循环了， 所以 start < end 的最右边的一个 [
            }
            i++;
        }
        //todo: 字符串中没有 [ 时说明解码完成
        if (start == -1)
            return;
        int num = Integer.parseInt(string.substring(start+1, mid)); //要复制的数量
        String cur = string.substring(mid+1, end); // 要复制的内容
        StringBuilder sb = new StringBuilder();
        for (int j=1; j <= num; j++)
            sb.append(cur);
        string.replace(start, end+1, sb.toString());
        decode(string);
    }



}
