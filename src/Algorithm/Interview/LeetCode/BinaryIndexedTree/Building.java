package Algorithm.Interview.LeetCode.BinaryIndexedTree;

import Utils.Dump;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * todo: 树状数组
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）
 * 上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线（图B）。
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
public class Building {
    public static void main(String[] args) {
        int[][] build = {{2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}};
        getSkyline1(build);
    }

    /**
     * todo: 扫描线法
     *      - 核心思想：
     *          - 优先递减队列， 左边界入队 有边界出队
     *          - 如果当前 线段入队之后 队列最大值（队头） 和上一轮的最大值 一样
     *              - 说明 队列中存在更高的线段，并且还没有到达有边界 ==》 当前线段会被覆盖
     *          - 如果  线段入队之后 队列最大值（队头） 和上一轮的最大值 不一样； 那么当前线段一定是队列中的最大值
     *              - 因为队头有变化，那只能是当前值
     *
     * @param buildings
     * @return
     */
    public static ArrayList<ArrayList<Integer>> getSkyline1(int[][] buildings) {
        // todo：1.create points
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        //todo: 统计每一条线段 x坐标 及其高度
        for(int i = 0;i < buildings.length;i++) {
            ArrayList<Integer> leftUpper = new ArrayList<>();
            // 左边坐标 矩形高度 记为负数
            leftUpper.add(buildings[i][0]);
            leftUpper.add(-buildings[i][2]);
            points.add(leftUpper); // 添加左边的点
            // 右边坐标 矩形高度 记为整数
            ArrayList<Integer> rightUpper = new ArrayList<>();
            rightUpper.add(buildings[i][1]);
            rightUpper.add(buildings[i][2]);
            points.add(rightUpper); // 添加右边的点
        }
//        Dump.iterator(points);
        //todo：2.sort points
        Collections.sort(points, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int x1 = o1.get(0);
                int h1 = o1.get(1);
                int x2 = o2.get(0);
                int h2 = o2.get(1);
                if(x1 != x2) {
                    return x1 - x2; // 按横坐标递增排序
                }else {
                    return h1 - h2; // 横坐标相同 按高度递增排序
                }
            }
        });
        //todo: 3.line sweep 优先队列按递减排列
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {  // 大顶堆
                return o2 - o1;
            }
        });
        queue.offer(0);
        int preMaxh = 0; //todo: 上一轮最高线段
        // point 横坐标是递增的
        for (ArrayList<Integer> point : points) {
            int x = point.get(0);
            int h = point.get(1);
            // 一个矩形只入队 1 个高度
            if(h < 0) {
                queue.offer(-h);   // leftUpper 矩形左边界  h入队
            }else {
                queue.remove(h);   // rightUpper 矩形有边界 移除h
            }
            int curMaxh = queue.peek(); //todo: 当前队列中的最高线段 队列是递减的，队头最大
            //todo: curMaxh != preMaxh 说明是拐点
            // 如果上一轮的最高线段 和 当前队列里面最高线段 高度一致， 说明point 入队之后被 覆盖
            // 如果不一致 一定是当前 线段入队之后是最高的（如果不是最高的，就一定存在比它高的，并且还没有到达右边界，那么当前线段就会被覆盖）
            if(curMaxh != preMaxh) {    // curMax > preMax
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(x);
                ans.add(curMaxh);
                res.add(ans);
                preMaxh = curMaxh;
            }
        }
        // 4.return
        return res;
    }

}
