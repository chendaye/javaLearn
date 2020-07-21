package Algorithm.Interview.Company.Bytedance.Q8;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * P为给定的二维平面整数点集。定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），
 * 则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）
 *
 * 如下图：实心点为满足条件的点的集合。请实现代码找到集合 P 中的所有 ”最大“ 点的集合并输出。
 *
 * 输入描述:
 * 第一行输入点集的个数 N， 接下来 N 行，每行两个数字代表点的 X 轴和 Y 轴。
 * 对于 50%的数据,  1 <= N <= 10000;
 * 对于 100%的数据, 1 <= N <= 500000;
 *
 * 输出描述:
 * 输出“最大的” 点集合， 按照 X 轴从小到大的方式输出，每行两个数字分别代表点的 X 轴和 Y轴。
 *
 * 输入例子1:
 * 5
 * 1 2
 * 5 3
 * 4 6
 * 7 5
 * 9 0
 *
 * 输出例子1:
 * 4 6
 * 7 5
 * 9 0
 */
public class Main {
    // 一个点右上方没有点，也就是一个点右边的点全都在它的下边。
    public void solution() throws IOException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/Bytedance/Q8/test.txt"));
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int[][] record = new int[row][2];
        int i = 0;
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
        int maxx = 0, maxy = 0;
        while (row > 0){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            record[i][0] = x;
            record[i][1] = y;
            minx = Math.min(minx, x);
            maxx = Math.max(maxx, x);

            miny = Math.min(miny, y);
            maxy = Math.max(maxy, y);
            row--;
            i++;
        }
        //数组排序
        Arrays.sort(record, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //todo: 横坐标 大于 x的点， 其纵坐标 必须小于 x
        for (int j = 0; j < record.length; j++){
            boolean flag = true;
            for (int n = j; n < record.length; n++){
                if (record[n][1] > record[j][1]){
                    flag = false;
                    break;
                }
            }
            if (flag)System.out.println(record[j][0]+" "+record[j][1]);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
