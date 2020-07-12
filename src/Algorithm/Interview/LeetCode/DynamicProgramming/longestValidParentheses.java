package Algorithm.Interview.LeetCode.DynamicProgramming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class longestValidParentheses {
    /**
     * todo: 括号特性
     *      - 左括号个数要大于等于右括号个数
     *      - 最终  左括号 = 右括号
     * @param s
     * @return 0 1 2 3 4
     */
    public int longestValidParentheses(String s) {
        if (s.length() == 0) return 0;
        int len = s.length();
        //todo: 穷举所有可能，判断每一种可能
        while (len > 0){
            for (int i = 0; i <= s.length() - len; i++){
                if (match(s, i, len + i -1)) return len;
            }
            len--;
        }
        return 0;
    }

    // 判断 [l, r] 是否是合法括号
    public boolean match(String s, int l, int r){
        Stack<Character> stack = new Stack<>();
        for (int i = l; i <= r; i++){
            char c = s.charAt(i);
            if (c == '('){
                stack.add(c);
            }else{
                if (stack.empty() || stack.pop() != '(') return false;
            }
        }
        return stack.empty();
    }

    //todo: 使用栈
    public int method2(String s){
        // 用栈解决括号的合法性问题，向栈中存入下标
        Deque<Integer> stack = new LinkedList<Integer>();
        // 向栈中预置一个-1，将计算长度的方式转化成“）”的下标减去出栈后栈顶元素的下标
        stack.push(-1);
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('('==s.charAt(i)) {
                stack.push(i);
            }
            if (')'==s.charAt(i)) {
                stack.pop();
                // 如栈空，则注入新的i作为预置下标
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                len = Math.max(len, i-stack.peek());
            }
        }
        return len;
    }

    /**
     * todo:  dp[i]
     *          - 状态： [0, i] 范围内 最长的合法括号
     *          - 选择：
     *          - base case :
     *          - 求： dp[0][s.length()]
     * @param s
     * @return
     */
    public int dp(String s) {
        return 0;
    }

    public static void main(String[] args) {

        longestValidParentheses longestValidParentheses = new longestValidParentheses();
        String a = new String();
        int i = longestValidParentheses.longestValidParentheses(a);
        System.out.println(i);


    }
}
