package Algorithm.Interview.Company.Bytedance.Q3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 总共有36张牌，每张牌是1~9。每个数字4张牌。
 * 你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
 * 14张牌中有2张相同数字的牌，称为雀头。
 * 除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌（例如234,567等），刻子的意思是相同数字的3个数字牌（例如111,777）
 *
 * 例如：
 * 1 1 1 2 2 2 6 6 6 7 7 7 9 9 可以组成1,2,6,7的4个刻子和9的雀头，可以和牌
 * 1 1 1 1 2 2 3 3 5 6 7 7 8 9 用1做雀头，组123,123,567,789的四个顺子，可以和牌
 * 1 1 1 2 2 2 3 3 3 5 6 7 7 9 无论用1 2 3 7哪个做雀头，都无法组成和牌的条件。
 *
 * 现在，小包从36张牌中抽取了13张牌，他想知道在剩下的23张牌中，再取一张牌，取到哪几种数字牌可以和牌。
 *
 * 输入描述:
 * 输入只有一行，包含13个数字，用空格分隔，每个数字在1~9之间，数据保证同种数字最多出现4次。
 *
 * 输出描述:
 * 输出同样是一行，包含1个或以上的数字。代表他再取到哪些牌可以和牌。若满足条件的有多种牌，
 * 请按从小到大的顺序输出。若没有满足条件的牌，请输出一个数字0
 *
 * 输入例子1:
 * 1 1 1 2 2 2 5 5 5 6 6 6 9
 *
 * 输出例子1:
 * 9
 *
 * 例子说明1:
 * 可以组成1,2,6,7的4个刻子和9的雀头
 *
 * 输入例子2:
 * 1 1 1 1 2 2 3 3 5 6 7 8 9
 *
 * 输出例子2:
 * 4 7
 *
 * 例子说明2:
 * 用1做雀头，组123,123,567或456,789的四个顺子
 *
 * 输入例子3:
 * 1 1 1 2 2 2 3 3 3 5 7 7 9
 *
 * 输出例子3:
 * 0
 *
 * 例子说明3:
 * 来任何牌都无法和牌
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm.Interview\\Bytedance\\Q3\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int[] num = new int[14];
        int i=0;
        while (sc.hasNextInt())
            num[i++] = sc.nextInt();

        Main main = new Main();
        StringBuilder res = new StringBuilder();
        main.Solution(num, res);
        if (res.length() == 0) System.out.println(0);
        System.out.println(res.toString().substring(0, res.length()));

    }

    private void Solution(int[] num, StringBuilder str){
        for (int i=1; i<2; i++) {
            int[] ints = Arrays.copyOfRange(num, 0, 14);// [0, 14)
            ints[13] = i;
            Arrays.sort(ints);
            int p=0,q=1;
            //todo: 看是否胡牌
            while (q<14){
                if (ints[p] == ints[q] && judge(ints, p, q)){
                    str.append(i+" ");
                    break;
                }
                p++;q++;
            }
        }
    }
    // p q  位置做将是否胡牌
    private boolean judge(int[] num, int p, int q){
        int[] tmp = new int[12];
        int j=0;
        for (int i=0; i<14; i++){
           if (i!=p && i!=q)
               tmp[j++] = num[i];
        }
//        Dump.array(tmp);
        boolean flag=true;
        int left=0,right=2;
        while (right<14){
            //[1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9]
            // [1, 2, 2, 3, 3, 4, 7, 8, 9, 9, 9, 9]
            // [1, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 8]
            if ((tmp[left] == tmp[right]) || (tmp[right] - tmp[right-1] ==1 && tmp[right-1]  -tmp[left] == 1)){
                left+=3;
                right+=3;
            }else if(left+5 <14 && (tmp[left] == tmp[left+1]) && (tmp[left+2] == tmp[left+3]) && (tmp[left+4] == tmp[left+5])
            && tmp[left+2]-tmp[left+1] ==1 && tmp[left+4]-tmp[left+3] == 1){
                left+=6;
                right+=6;
            }else if(left+5 <14 && tmp[left]+1 == tmp[left+1] && tmp[left+1] == tmp[left+2] && tmp[left+2]+1 == tmp[left+3]
                    && tmp[left+3] == tmp[left+4] && tmp[left+4]+1 == tmp[left+5]){
                left+=6;
                right+=6;
            }else if(left+9 <14 && tmp[left]+1 == tmp[left+1] && tmp[left+1] == tmp[left+2] && tmp[left+2]+1 == tmp[left+3] &&
                    tmp[left+3] == tmp[left+5] && tmp[left+5]+1 == tmp[left+6] && tmp[left+6] == tmp[left+7] &&
                    tmp[left+7] +1 == tmp[left+8] && tmp[left+8]+1 == tmp[left+9]){
                left+=9;
                right+=9;
            }else{
                flag=false;
                break;
            }
        }
        return flag;
    }
}
