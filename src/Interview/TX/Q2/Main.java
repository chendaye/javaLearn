package Interview.TX.Q2;

import LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
        System.setIn(new FileInputStream("src/Interview/TX/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        String line = sc.nextLine();
        char[] split = line.toCharArray(); //todo:转化为字符数组
        StringBuilder sb = new StringBuilder();
        main.decode(split,  sb);
        System.out.println(sb.toString());
    }

    /**
     * todo: 解码 [3|B[2|CA]]
     */
    private void decode(char[] split,   StringBuilder sb){
        int i=0;
        while (i < split.length){
            if (split[i] >= 'A' && split[i] <= 'Z')
                sb.append(split[i++]);
            if(split[i] == '['){
                int tmp = i;
                int cnt=1;
                StringBuilder st = new StringBuilder();
                while (cnt>0){
                    i++;
                    if (split[i] == '[')
                        cnt++;
                    if (split[i] == ']')
                        cnt--;
                }

                decode(Arrays.copyOfRange(split, tmp, i+1),  st);
                i++;
            }
        }

    }



}
