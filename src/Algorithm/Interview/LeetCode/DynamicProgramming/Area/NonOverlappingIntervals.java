package Algorithm.Interview.LeetCode.DynamicProgramming.Area;

import Utils.Dump;

import java.util.Collections;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: {{1,2}, {2,3}, {3,4}, {1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: {{1,2}, {1,2}, {1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: {{1,2}, {2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
//        int[][] test = {{1,2}, {2,3}, {3,4}, {1,3}};
        int[][] test = {{1,100},{11,22},{1,11},{2,12}};
        sort(test);

        for (int[] item : test){
            Dump.array(item, true);
        }

        Dump.dump(eraseOverlapIntervals(test));
    }

    /**
     * todo: F(i) = 1 + max{F(x) | 0<=x<i, F(x)<=F(i)}
     *  - F(i): 在 [0,i] 区间内最长的 上升序列
     *
     *  todo: 动态规划
     *       - 状态： dp[i]  在 [0,i] 区间内最长的 上升序列
     *       - base case: dp[0] = 1;
     *       - 状态转移： dp[i] = max{dp[x] | dp[x] < dp[i], 0<=x<i}
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n==0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        for (int i=1; i<n;i++){
            int len = 0;
            for (int j=0; j<i; j++){
                if (intervals[i][0] >= intervals[j][intervals[j].length -1]){
                    len = Math.max(len, dp[j]);
                }
            }
            dp[i] = len+1;
            max=Math.max(max, dp[i]);
        }
        return n-max;
    }

    /**
     * 对区间排序
     * @param intervals
     */
    public static void sort(int[][] intervals){
        for (int i=0; i<intervals.length; i++){
            for (int j=1; j<intervals.length-i; j++){
                int[] m = intervals[j - 1];
                int[] n = intervals[j];
                int[] tmp = null;
                if ((m[0] > n[0]) || (m[0] == n[0] && m[m.length-1] >= n[n.length -1])){
                    tmp = intervals[j - 1];
                    intervals[j - 1] = intervals[j];
                    intervals[j] = tmp;
                }
            }
        }
    }


}
