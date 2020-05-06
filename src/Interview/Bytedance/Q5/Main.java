package Interview.Bytedance.Q5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Interview\\Bytedance\\Q5\\test.txt"));
        Scanner sc = new Scanner(System.in);

    }
}
