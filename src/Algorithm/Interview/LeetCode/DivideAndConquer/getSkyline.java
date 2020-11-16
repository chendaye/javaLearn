package Algorithm.Interview.LeetCode.DivideAndConquer;

import java.util.*;

/**
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，
 * 请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 *
 *
 * 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。
 * 可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
 *
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 *
 * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。
 * 关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。
 * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 *
 * 说明:
 *
 * 任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。
 * 输入列表已经按左 x 坐标 Li  进行升序排列。
 * 输出列表必须按 x 位排序。
 * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
 * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class getSkyline {
    public static void main(String[] args) {

    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        // 从小到大排序，如果第一个相等，比较第二个,升序排列
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        // 按坐标递增入队
        for (int[] build : buildings){
            // 左边高度入队， 设为负
           pq.offer(new int[] {build[0], -build[2]});
           // 右边高度入队
           pq.offer(new int[] {build[1], build[2]});
        }

        // 保存结果
        List<List<Integer>> res = new ArrayList<>();

        // 保存 当前没有走完的的建筑，按高度降序排列
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>((a, b) -> {
           return b - a;
        });
        treeMap.put(0, 1);
        int left = 0, height = 0;

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            if (cur[1] < 0){
                // 左端点：次数+1
                treeMap.put(-cur[1], treeMap.getOrDefault(-cur[1], 0) + 1);
            }else{
                // 右端点：次数-1
                treeMap.put(cur[1], treeMap.get(cur[1]) - 1);
                // 左右端点遍历完
                if (treeMap.get(cur[1]) == 0) treeMap.remove(cur[1]);
            }
            int max = treeMap.keySet().iterator().next(); // 当前还没有走完的最高的建筑
//            Set<Map.Entry<Integer, Integer>> a = treeMap.entrySet();
            if (max != height){
                left = cur[0];
                height = max;
                res.add(Arrays.asList(left, height));
            }
        }
        return res;
    }
}
