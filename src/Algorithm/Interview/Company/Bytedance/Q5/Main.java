package Algorithm.Interview.Company.Bytedance.Q5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 找到每个城市只访问一次并返回起点的最小车费花销。
 *
 * 输入描述:
 * 城市个数n（1<n≤20，包括北京）
 *
 * 城市间的车票价钱 n行n列的矩阵 m[n][n]
 *
 * 输出描述:
 * 最小车费花销 s
 *
 * 输入例子1:
 * 4
 * 0 2 6 5
 * 2 0 4 4
 * 6 4 0 2
 * 5 4 2 0
 *
 * 输出例子1:
 * 13
 *
 *
 * 共 4 个城市，城市 1 和城市 1 的车费为0，城市 1 和城市 2 之间的车费为 2，
 * 城市 1 和城市 3 之间的车费为 6，城市 1 和城市 4 之间的车费为 5，依次类推。
 * 假设任意两个城市之间均有单程票可购买，且票价在1000元以内，无需考虑极端情况。
 *
 * 在图中找一个距离最短的环
 *
 * 19
 */
public class Main {
    private int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm.Interview\\Bytedance\\Q5\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //todo: n 个城市
        int[][] dist = new int[n][n];  //todo: 距离
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
               dist[i][j] = sc.nextInt();
            }
        }

        int[] visit = new int[n]; //todo:访问数组
        Main main = new Main();
        //todo: 这是一个排列问题
        for (int i=0; i<n; i++){
            Arrays.fill(visit, 0);
            visit[i] = 1;
            main.travel(dist, visit,  0, 0, i, i);
        }
        System.out.println(main.min);
    }

    /**
     * todo: 当前已经到达城市 i， 并且已知 消耗的费用； 求总费用
     * @param dist 距离数组
     * @param visit 访问数组
     * @param sum start->i 的总费用
     * @param cnt 除起点外 访问过的城市数量
     * @param start 起点城市
     * @param i 当前已经到达的 城市
     */
    private void travel(int[][] dist, int[] visit, int sum, int cnt,  int start, int i){
        if (cnt == visit.length-1) {
            System.out.println(i+"->"+start+"  "+dist[i][start]+ "  >>>>>>"+"  "+ (sum+dist[i][start]));
            min = Integer.min(sum+dist[i][start], min); //todo:回到原点
            return;
        }
        for (int j=0; j < visit.length; j++){
            if (visit[j] == 0){
                visit[j] = 1;
                cnt++;
                sum += dist[i][j]; //todo: start->j 的费用
                System.out.println(i+"->"+j+"  "+dist[i][j]);
                travel(dist, visit, sum, cnt, start, j);
                //todo:回溯
                visit[j] = 0;
                cnt--;
            }
        }
    }
}
