

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 打车派单场景, 假定有N个订单， 待分配给N个司机。每个订单在匹配司机前，会对候选司机进行打分，
 * 打分的结果保存在N*N的矩阵A， 其中Aij 代表订单i司机j匹配的分值。
 *
 * 假定每个订单只能派给一位司机，司机只能分配到一个订单。求最终的派单结果，使得匹配的订单和司机的分值累加起来最大，并且所有订单得到分配。
 *
 *
 * 输入描述:
 * 第一行包含一个整数N，2≤N≤10。
 *
 * 第二行至第N+1行包含N*N的矩阵。
 *
 *
 * 输出描述:
 * 输出分值累加结果和匹配列表，结果四舍五入保留小数点后两位
 * （注意如果有多组派单方式得到的结果相同，则有限为编号小的司机分配编号小的订单，
 * 比如：司机1得到1号单，司机2得到2号单，就比司机1得到2号单，司机2得到1号单要好）
 *
 * 输入例子1:
 * 3
 * 1.08 1.25 1.5
 * 1.5 1.35  1.75
 * 1.22 1.48 2.5
 *
 * 输出例子1:
 * 5.25
 * 1 2
 * 2 1
 * 3 3
 *
 * 例子说明1:
 * 第一行代表得到的最大分值累加结果5.25，四舍五入保留两位小数；
 *
 * 第二行至第四行代表匹配的结果[i j],其中i按行递增：
 *
 * 订单1被派给司机2，订单2被派给司机1，订单3被派给司机3。使得A12+ A21+ A33= 1.25 + 1.5 + 2.5 = 5.25在所有的组合中最大。
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 单数、人数
        float[][] record = new float[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                record[i][j] = sc.nextFloat();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        solution(n, res, new ArrayList<Integer>());
        float max = 0;
        ArrayList<Integer> r = new ArrayList<>();
        Iterator<ArrayList<Integer>> iterator = res.iterator();
        while (iterator.hasNext()){
            ArrayList<Integer> next = iterator.next();
            float cur_record = 0;
            for (int i = 0; i < next.size(); i++){
                Integer current = next.get(i);
                cur_record += record[i][current];
            }
            if (max < cur_record){
                max = cur_record;
                r = next;
            }
            if (max == cur_record){
                int n1 = 0;
                int n2 = 0;
                for (int k = 0; k < next.size(); k++){
                    n1 += Math.abs(r.get(k) - k);
                    n2 += Math.abs(next.get(k) - k);
                }

                if (n2 < n1) r = next;
            }

        }
        System.out.println(String.format("%.2f", max));
        for (int i = 0; i < r.size(); i++){
            System.out.println((i + 1)+" "+(r.get(i) + 1));
        }

    }

    /**
     * todo: 注意 回溯操作的 使用时同一个 path引用
     *      - 如果把 path 引用 保存到 结果中； 在后续回溯的过程中 结果中保存的path 引用也会改变
     *      - 所以 满足要求时，结果中保存的应该是 path的副本
     * @param n
     * @param res
     * @param path
     */
    public static void solution(int n,  ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path){
        if (path.size() == n){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++){
            if (path.contains(i))
                continue;
            path.add(i);
            solution(n, res, path);
            path.remove(path.size() - 1);
        }

    }
}
