package Algorithm.Interview.Company.Bytedance.Q7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 机器人正在玩一个古老的基于DOS的游戏。游戏中有N+1座建筑——从0到N编号，从左到右排列。
 * 编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位。
 *
 * 起初， 机器人在编号为0的建筑处。每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，
 * 且它现在的能量值是E, 下一步它将跳到第个k+1建筑。它将会得到或者失去正比于与H(k+1)与E之差的能量。
 * 如果 H(k+1) > E 那么机器人就失去 H(k+1) - E 的能量值，否则它将得到 E - H(k+1) 的能量值。
 *
 * 游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位。
 * 现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏？
 *
 * 输入描述:
 * 第一行输入，表示一共有 N 组数据.
 *
 * 第二个是 N 个空格分隔的整数，H1, H2, H3, ..., Hn 代表建筑物的高度
 *
 * 输出描述:
 * 输出一个单独的数表示完成游戏所需的最少单位的初始能量
 *
 * 输入例子1:
 * 5
 * 3 4 3 2 4
 *
 * 输出例子1:
 * 4
 *
 * 输入例子2:
 * 3
 * 4 4 4
 *
 * 输出例子2:
 * 4
 *
 * 输入例子3:
 * 3
 * 1 6 4
 *
 * 输出例子3:
 * 3
 *
 * 91593
 *
 * todo: 由题干可以得出  E(n) = E(n-1) + [E(n-1) - H(n)] = 2E(n-1) - H(n)
 *        E(n-1) = [E(n)+H(n)]/2
 *      由能量守恒： 从 0->n-1  E(n-1) = H(n-1)
 *
 * todo: 数学表达  逆向递推
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm.Interview\\Bytedance\\Q7\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N 个建筑
        sc.nextLine();
        String[] s = sc.nextLine().split(" ");
        int[] build = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
        double E=0;

        Main main = new Main();

        for (int i=N-1; i>=0; i--){
            E = Math.ceil((E+build[i])/2);
        }
        System.out.println((int)E);
    }


}
