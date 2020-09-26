package Algorithm.Interview.LeetCode.Interval;

import java.util.Arrays;

/**
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 *
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 *
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 *
 *  
 *
 * 示例：
 *
 * 输入：intervals = [[1,4],[3,6],[2,8]]
 * 输出：2
 * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-covered-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class removeCoveredIntervals {
    public static void main(String[] args) {

    }

    public int removeCoveredIntervals(int[][] intervals) {
        //排序 起点递增排序， 起点相同就终点递减排序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int cnt = 0;
        int start = intervals[0][0],end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++){
            int[] region = intervals[i];
            //覆盖
            if(region[0] >= start && region[1] <= end){
                cnt++;
            }
            // 相交
            if(region[0] <= end && region[1] >= end){
                end = region[1];
            }
            // 不相交，更新起点和终点
            if (region[0] > end){
                start = region[0];
                end = region[1];
            }
        }

        return intervals.length - cnt;
    }
}
