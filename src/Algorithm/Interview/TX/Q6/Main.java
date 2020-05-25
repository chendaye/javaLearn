package Algorithm.Interview.TX.Q6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 小Q在进行一场竞技游戏,这场游戏的胜负关键就在于能否能争夺一条长度为L的河道,即可以看作是[0,L]的一条数轴。
 * 这款竞技游戏当中有n个可以提供视野的道具−真视守卫,第i个真视守卫能够覆盖区间[xi,yi]。
 * 现在小Q想知道至少用几个真视守卫就可以覆盖整段河道。
 *
 * 输入描述:
 * 输入包括n+1行。
 *
 * 第一行包括两个正整数n和L(1<=n<=105,1<=L<=109)
 *
 * 接下来的n行,每行两个正整数xi,yi(0<=xi<=yi<=109),表示第i个真视守卫覆盖的区间。
 *
 *
 * 输出描述:
 * 一个整数，表示最少需要的真视守卫数量, 如果无解, 输出-1。
 *
 * 输入例子1:
 * 4 6
 * 3 6
 * 2 4
 * 0 2
 * 4 7
 *
 * 输出例子1:
 * 3
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/TX/Q6/test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //眼的个数
        int L = sc.nextInt(); //河道长度

        int[][] dist = new int[N][2];
        int i=0;
        while (sc.hasNextInt()){
            dist[i][0] = sc.nextInt();
            dist[i][1] = sc.nextInt();
            i++;
        }

        Main main = new Main();
        main.dp(L, N, dist);
    }

    /**
     * todo： 背包问题
     * @param L 河道长度
     * @param N 眼的个数
     * @param dist 眼的范围
     */
    private void dp(int L, int N, int[][] dist){

    }
}
