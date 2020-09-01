package Algorithm.Interview.LeetCode.DynamicProgramming.Other;

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



    /**
     * todo:  dp[i]
     *          - 状态： 以索引 s[i] 结尾的最长有效长度
     *          - 选择：每次考虑2个位置
     *              - s[i] = '(' dp[i] = 0
     *              - s[i] = ')' s[i-1] = '('  dp[i] = dp[i-2] + 2
     *              - s[i] = ')' s[i-1] = ')'  if s[i - dp[i-1] - 1] = '('  dp[i] = dp[i - dp[i-1] -1 -1] + 2 + d[i-1]
     *          - base case : dp[0] = 0 dp[1] = 0;
     *          - 求： max(dp[i])
     *
     * todo: 状态 dp[i] 一定跟 索引 i 关联
     * @param s
     * @return
     */
    public int dp(String s) {
        if (s.length() < 2) return 0;
        int max = 0;
        int[] dp = new int[s.length()]; //todo: 初始值全部为0, dp数组长 s.length() 与 字符串保持一致
        for (int i = 1; i < s.length(); i++){ // 遍历字符串
            if (s.charAt(i) == ')'){
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i- 2 >= 0 ? dp[i - 2] : 0) + 2;
                else
                    if(i - dp[i - 1] -1 >= 0 && s.charAt(i - dp[i - 1] -1) == '(') //todo: i - dp[i - 1] -1 是 i的对称位置；中间隔了一个 dp[i-1] 的距离
                        dp[i] = (i - dp[i - 1] -2 >= 0 ? dp[i - dp[i - 1] -2] : 0) + 2 + dp[i -1];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     *撇开方法一提及的动态规划方法，相信大多数人对于这题的第一直觉是找到每个可能的子串后判断它的有效性，但这样的时间复杂度会达到 O(n^3)O(n
     * 3
     *  )，无法通过所有测试用例。但是通过栈，我们可以在遍历给定字符串的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。
     *
     * 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，
     * 栈里其他元素维护左括号的下标：
     *
     * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * 我们从前往后遍历字符串并更新答案即可。
     *
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
     * 为了保持统一，我们在一开始的时候往栈中放入一个值为 −1 的元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int use_stack(String s){
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop(); //todo: 如果当前是 ‘（’；出栈
                if (stack.empty()) { //todo: 栈为空 说明 仍然合法
                    stack.push(i);
                } else { //todo: 如果出栈后，栈非空，找到一个最长 子串
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }


    /**
     * 在此方法中，我们利用两个计数器 left 和 right 。首先，我们从左到右遍历字符串，
     * 对于遇到的每个 ‘(’，我们增加 left 计数器，对于遇到的每个 ‘)’ ，
     * 我们增加 right 计数器。每当 left 计数器与 right 计数器相等时，
     * 我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当 right 计数器比 left 计数器大时，
     * 我们将 left 和 right 计数器同时变回 00。
     *
     * 这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑，
     * 重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。
     *
     * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：
     *
     * 当 left 计数器比 right 计数器大时，我们将 left 和 right 计数器同时变回 00
     * 当 left 计数器与 right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串
     * 这样我们就能涵盖所有情况从而求解出答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int doublePoint(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {

        longestValidParentheses longestValidParentheses = new longestValidParentheses();
        String a = new String(")()())");
//        int i = longestValidParentheses.longestValidParentheses(a);
//        int i = longestValidParentheses.dp(a);
        int i = longestValidParentheses.use_stack(a);
        System.out.println(i);


    }
}
