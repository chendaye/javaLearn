package Algorithm.Interview.TX.Q3;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？
 * （当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 *
 * 输入第一行将包含一个数字n，代表楼的栋数，接下来的一行将包含n个数字wi(1<=i<=n)，代表每一栋楼的高度。
 * 1<=n<=100000;
 * 1<=wi<=100000;
 *
 * 输出一行，包含空格分割的n个数字vi，分别代表小Q在第i栋楼时能看到的楼的数量。
 *
 * 6
 * 5 3 8 3 2 5
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/TX/Q3/test.txt"));
        Scanner sc = new Scanner(System.in);
        Algorithm.Interview.TX.Q3.Main main = new Algorithm.Interview.TX.Q3.Main();
        int N = sc.nextInt();
        int[] build = new int[N];
        int i=0;
        while (sc.hasNext())
            build[i++] = sc.nextInt();

        int[] recordR = new int[N];
        int[] recordL = new int[N];
        //todo: 解决超时
        main.solution2(build, N, recordR, recordL);
    }

    //todo: 往右看 递增的个数 ； 往左看递增的个数
    private void solution2(int[] build, int N, int[] recordR, int[] recordL){
        int R = 1;
        int L = 1;
        int rv = build[0];
        int lv = build[N-1];
        //todo： i 往右递减；j 往左递增
        for (int i=1, j= N-2; i<N; i++,j--){
            int cur = build[i];
            //todo:往右看
            if (cur <= rv){
                R++;

            }

        }

    }

    private void solution1(int[] build, int N, int[] record){
        for (int j=0; j<N; j++){
            //todo: 查看record

            int right = j-1, left = j+1;
            // todo: 位置 j 可以看到的 建筑初始值
            int cnt = (right >= 0 ? 1 : 0)+(left < N ? 1 : 0) + 1;
            int rh=0, lh=0; //todo:记录最高的楼
            for(int f=right; f>=1; f--){
                rh = Math.max(rh, build[f]);
                if (rh <= build[f-1])
                    cnt++;
            }
            for (int b=left; b<N-1; b++){
                lh = Math.max(lh, build[b]);
                if (lh <= build[b+1])
                    cnt++;
            }
            record[j] = cnt;
        }
        for (int item: record)
            System.out.print(item+" ");
    }
}
