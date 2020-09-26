package Algorithm.Interview.LeetCode.Interval;

import java.util.ArrayList;

/**
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 *输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class intervalIntersection {
    /**
     * todo: 区间有交集的情况： 不好考虑； 考虑区间没有交集的情况： B[0] > A[1] || A[0] > B[1]
     *          取反就得到，有交集的情况： B[0] <= A[1] && A[0] <= B[1]
     *        用两个 指针分别在区间上滑动， 右边端点 落后的向前移动
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int p1 = 0, p2 = 0;
        ArrayList<int[]> ans = new ArrayList<int[]>();
        while(p1 < A.length && p2 < B.length){
            int[] a = A[p1];
            int[] b = B[p2];
            //todo: 有交集
            if (b[0] <= a[1] && a[0] <= b[1]){
                int[] tmp = new int[2];
                tmp[0] = Math.max(a[0], b[0]);
                tmp[1] = Math.min(a[1], b[1]);
                ans.add(tmp);
            }
            // 没有交集
            if (a[1] > b[1])
                p2++;
            else
                p1++;
        }
        int[][] res = new int[ans.size()][2];
        int i = 0;
        for (int[] arr: ans){
            res[i++] = arr;
        }
        return res;
    }
    }
