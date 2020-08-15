package Algorithm.Interview.Company.MT.Q1.Q6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
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
    private int min;
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/Algorithm/Interview/TX/Q6/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //眼的个数
        int L = sc.nextInt(); //河道长度
        int[][] temp = new int[n][2];
        int i=0;
        while (sc.hasNextInt()){
            temp[i][0] = sc.nextInt();
            temp[i][1] = sc.nextInt();
            i++;
        }

        //todo : 获得了数组，进行排序 按从小到大排序
        Arrays.sort(temp,new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int index = 0;
        int count = 0;
        int pre = 0;   //todo：当前选择的眼的范围的右边界
        while(pre < L) {
            //比右边界大，有间隔
            if(temp[index][0] > pre) {
                System.out.println(-1);
            }
            //todo：贪心： 找到所有眼中覆盖范围最右的，但是要与先前覆盖范围有交集
            int max = 0;
            while(index < n && temp[index][0] <= pre) {
                max = Math.max(max, temp[index][1]);
                index++;
            }
            count++;
            pre = max;
            if(pre >= L) {
                System.out.println(count);
                return;
            }
            if(index >= n) {
                System.out.println(-1);
                return;
            }
        }
    }
}
