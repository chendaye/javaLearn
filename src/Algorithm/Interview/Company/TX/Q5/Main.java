package Algorithm.Interview.Company.TX.Q5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 由于业绩优秀，公司给小Q放了 n 天的假，身为工作狂的小Q打算在在假期中工作、
 * 锻炼或者休息。他有个奇怪的习惯：不会连续两天工作或锻炼。
 * 只有当公司营业时，小Q才能去工作，只有当健身房营业时，小Q才能去健身，
 * 小Q一天只能干一件事。给出假期中公司，健身房的营业情况，求小Q最少需要休息几天。
 *
 * 输入描述:
 * 第一行一个整数  表示放假天数
 * 第二行 n 个数 每个数为0或1,第 i 个数表示公司在第 i 天是否营业
 * 第三行 n 个数 每个数为0或1,第 i 个数表示健身房在第 i 天是否营业
 * （1为营业 0为不营业）
 *
 * 输出描述:
 * 一个整数，表示小Q休息的最少天数
 *
 * 输入例子1:
 * 4
 * 1 1 0 0
 * 0 1 1 0
 *
 * 输出例子1:
 * 2
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/TX/Q5/test.txt"));
        Scanner sc = new Scanner(System.in);
        //todo:放假天数
        int N = sc.nextInt();
        sc.nextLine();
        //todo: 公司开门情况
        String[] line1 = sc.nextLine().split(" ");
        int[] works = Arrays.stream(line1).mapToInt(Integer::parseInt).toArray();
        //todo: 健身房开门情况
        String[] line2 = sc.nextLine().split(" ");
        int[] fits = Arrays.stream(line2).mapToInt(Integer::parseInt).toArray();

        Main main = new Main();
        main.dp(N, works, fits);
    }

    /**
     * 在放假期间 必须要休息的最小天数
     * @param N  放假天数
     * @param works 放假期间公司开门情况
     * @param fits 放假期间健身房开门情况
     *
     * todo:变量 （不会连续2天 工作/锻炼）
     *             - [0, i]
     *             - 第 i 天 0:休息  1：健身  2：上班
     *
     * todo:状态   [0, day] 内, day 这一条选择 status 最少要休息的天数
     *             d[day][status]  = min{d[day][0], d[day][1], d[day][2]} ; d[day][1] 可能取不到
     */
    private void dp(int N, int[] works, int[] fits){
        //状态数组
        int[][] dp = new int[N][3];
        for (int i=0; i<N; i++)
            for (int j=0; j<3; j++)
                dp[i][j] = N+1; //最小休息天数为无穷大
        dp[0][0] = 1;
        if (fits[0] == 1) dp[0][1] = 0;
        if (works[0] == 1) dp[0][2] = 0;

        for (int i=1; i<N; i++){
            // 第 i 天选择休息
            dp[i][0] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
            // 第 i 天选择健身
            if (fits[i] == 1)
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
            // 第 i 天选择上班
            if(works[i] == 1)
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.print(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }

}
