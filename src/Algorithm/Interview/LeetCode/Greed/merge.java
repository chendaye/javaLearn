package Algorithm.Interview.LeetCode.Greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class merge {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]-o2[0]; // 按区间左侧边界排序

            }
        });
        ArrayList<int[]> record = new ArrayList<>();
        for(int[] item : intervals){
            // record 为空 或者当前区间超过 当前最大区间
            if(record.size() == 0 || item[0] > record.get(record.size() -1)[1]){
                record.add(item);
            }else{
                int[] last = record.get(record.size() -1);
                last[1] = Math.max(last[1], item[1]);
            }
        }
        int[][] ans = new int[record.size()][2];
        int index = 0;
        for(int[] arr :  record){
            ans[index++] = arr;
        }
        return ans;
    }


}
