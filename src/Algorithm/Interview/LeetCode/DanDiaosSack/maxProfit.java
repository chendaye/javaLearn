package Algorithm.Interview.LeetCode.DanDiaosSack;

import java.util.Stack;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 类似
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * https://leetcode-cn.com/problems/maximal-rectangle/
 */
public class maxProfit {
    public static void main(String[] args) {
//        int[] arr = {7,6,4,3,1};
        int[] arr = {7,1,5,3,6,4};

        System.out.println(maxProfit(arr));
    }
    /**
     * todo: 单调栈
     *      - 首先确定 ：对当前元素来说，是想要更大的还是更小的
     *      - 然后：遍历每一个元素时 都要入栈，并且入栈后要保证，它是单调栈的栈顶
     *      - 这样就保证了每一个元素出栈的时候，它都是前面所有遍历元素中的 单调极值
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        Stack<Integer> stack = new Stack<>();
        int profit = 0; // 最大利润
        int bottom = prices[0];
        for (int i = 0; i < prices.length; i++){
            // 栈空 或者 栈顶小于当前遍历值 直接入栈
            if (stack.empty() || prices[i] >= stack.peek()){
                stack.add(prices[i]);
                if (stack.size() == 1) bottom = stack.peek(); // 记录栈底元素
            }else{
                // 让 prices[i] 成为单调栈中的极值
                while (!stack.empty() && prices[i] < stack.peek()){
                    Integer pop = stack.pop(); // 对于每一个元素。当它出栈时都是 单调极值
                    profit = Math.max(profit, pop - bottom);
                }
                stack.add(prices[i]);
                if (stack.size() == 1) bottom = stack.peek(); // 记录栈底元素
            }
        }
        // 最后栈不为空，还要判断一次极值
        if(!stack.empty()) {
            int tmp = stack.pop();
            return Math.max(profit, tmp - bottom);
        }
        return profit;
    }
}
