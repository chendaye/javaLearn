package Algorithm.Interview.LeetCode.TreeGraph;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo:思路
 *  - 整个问题转化为一个图论问题
 *  - 从n到0每个数字表示一个节点
 *  - 如果两个数字x y相差一个完全平方数，则连接一条边
 *  - 这样得到一个无权图
 *  - 问题转化成，求无全图中从n到0的最短路径
 *
 *
 *  类似：127 126
 */
public class PerfectSquares {
    public static void main(String[] args) {
        Map.Entry<String,String> entry = new AbstractMap.SimpleEntry<String, String>("name", "野猿新一");
        System.out.println("new AbstractMap.SimpleEntry:" + entry);
        System.out.println("getKey:" + entry.getKey());
        System.out.println("getValue:" + entry.getValue());
        entry.setValue("野猿新二");
        System.out.println("setValue:" + entry);
    }

    /**
     * todo：广度优先遍历 求最短路径
     *      因为广度优先遍历就是层序遍历；一层一层，第一个num=0 的就是最早的那个0
     *
     * todo：原问题有多种路径---》 将原问题转化为 图的遍历问题
     * @param n
     * @return
     */
    public static int Solution(int n){
        //todo： 队列Queue -- LinkedList
        Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
        //todo:从n出发；到达数字 n 一共花了 0 步
        queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(n,0));
        //todo: 记录已经访问过的点
        int[] visit = new int[n + 1];
        visit[n] = 1;
        //todo:一层一层入队
        while (!queue.isEmpty()){
            //todo:出队一个节点
            AbstractMap.SimpleEntry<Integer, Integer> top = queue.remove();
            Integer num = top.getKey();  // 数字
            Integer step = top.getValue(); // 到达的步数
            //todo：一定可以走到这一步，因为1也是完全平方数
            if (num == 0)
                return step;
            //todo:当前到达的数字 num,可以包含哪些完全平方数
            //todo: 每一个 完全平方数都是 一条路径
            for (int i=1; num - i*i >= 0; i++){
                if (visit[num-i*i] == 0){
                    queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(num-i*i, step+1));
                    visit[num-i*i] = 1;
                }
            }
        }
        throw new RuntimeException("不应该走到这一步!");
    }
}
