package Algorithm.Interview.Company.KDXF.Q1;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static int max = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/KDXF/Q1/test.txt"));
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");
        int M  = Integer.parseInt(split[0]);
        int N  = Integer.parseInt(split[1]);
        int[][] nums = new int[M][N];
        for (int i  = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                nums[i][j] = sc.nextInt();
            }
        }

        int[][] dict = new int[2][2];
        dict[0][0] = 1;
        dict[0][1] = 0;
        dict[1][0] = 0;
        dict[1][1] = 1;
        int[][] visited = new int[M][N];
        ArrayList<Integer> path = new ArrayList<>();
        helper(M, N, nums, visited,dict,0,0, path);
        System.out.println(max);

    }

    /**
     * todo: 回溯递归函数的目的就是： 穷尽某个方向的所有可能
     *       - 从一个点出发，把这个点的所有可能方向都试一遍（每一个方向上都调用回溯一次）
     *       - 每次试完一个方向，退回起点状态
     *       - 然后退步一，换上一轮的其他方向
     *       - 每一次只用处理 当前位置
     *
     *  todo: 回溯要素
     *      - 起点（需要处理的点）
     *      - 前进方向， 每一个方向考虑完，要退回起点
     *      - 方向终点，
     */
    public static void helper(int m, int n, int[][] nums, int[][] visited, int[][] dict, int x, int y, ArrayList<Integer> path){
        //todo: 当前点
        path.add(nums[x][y]);
        if (x == m - 1 && y == n -1){
            int sum = 0;
            for (int k : path)
                sum += k;
            max = Math.max(max, sum);
            return;
        }
        //todo: 考虑当前点，标记为访问
        visited[x][y] = 1;
        //todo: 当前点有2个前进方向（每一个方向要没有访问过）
        for (int i = 0; i < dict.length; i++){
            int[] cur = dict[i];
            int cx = x + cur[0];
            int cy = y + cur[1];
            if (cx < m && cy < n && visited[cx][cy] == 0){
                helper(m,n,nums,visited, dict,cx,cy,path); //todo: 尝试一个方向
                path.remove(path.size() - 1); //todo: 尝试完一个方向，退回起点，准备尝试下一个方向
            }
        }
        //todo: 当前点考虑完，退一步
        visited[x][y] = 0;
    }


}
