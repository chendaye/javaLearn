package Algorithm.meitunan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 给出一个序列包含n个正整数的序列A，你可以从中删除若干个数，
 * 使得剩下的数字中的最大值和最小值之差不超过x，请问最少删除多少个数字。
 *
 * todo:读取输入输出
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;

        ArrayList tmp = new ArrayList();

        while (sc.hasNextInt()){
            tmp.add(sc.nextInt());
        }

        Iterator iterator = tmp.iterator();

        while (iterator.hasNext()){
            dump(iterator.next());
        }


    }


    public static void dump(Object obj){
        System.out.println(obj);
    }




}
