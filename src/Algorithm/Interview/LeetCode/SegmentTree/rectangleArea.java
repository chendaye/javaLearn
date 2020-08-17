package Algorithm.Interview.LeetCode.SegmentTree;

import java.util.*;
/**
 *
 * todo: 线段树
 *
 * 类似
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * https://leetcode-cn.com/problems/falling-squares
 *
 *
 * 我们给出了一个（轴对齐的）矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标，
 * （x2，y2）是该矩形右上角的坐标。
 *
 * 找出平面中所有矩形叠加覆盖后的总面积。 由于答案可能太大，请返回它对 10 ^ 9 + 7 取模的结果。
 *
 *
 * 示例 1：
 *
 * 输入：[[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示。
 * 示例 2：
 *
 * 输入：[[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 10^18 对 (10^9 + 7) 取模的结果， 即 (10^9)^2 → (-7)^2 = 49 。
 * 提示：
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= rectangles[i][j] <= 10^9
 * 矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class rectangleArea {
    public static void main(String[] args) {

    }

    /**
     * todo: 用 HashMap记录每一格的高度(因为高度是不连续的，所以行不通)
     *
     * todo: 线性扫描 垂直 Y轴进行扫描
     *
     * todo: 没看懂
     * @param rectangles
     * @return
     */
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]}; // 矩阵下边界
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]}; // 矩阵上边界
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0])); // 按 y 坐标递增排序

        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event: events) {
            // y坐标、上OR下边界、左右x坐标
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long query = 0;
            int cur = -1;
            for (int[] xs: active) {
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += query * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i)
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
            }
            cur_y = y;
        }
        ans %= 1_000_000_007;
        return (int) ans;
    }
}
