package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import java.util.ArrayList;

/**
 * 从左上角 一直到右下角 和最大
 * 只能往下 或者往右
 */
public class maxSum {
    static int max = 0;
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
