package Algorithm.Interview.LeetCode.StackQueneHeap;

import Utils.Dump;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 类似：150 71
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "[";
        Dump.dump(Solution(s));
    }

    /**
     * todo:使用栈的性质,来解题
     *
     * todo:使用栈的原因  在一种嵌套的关系中，通过栈来获取最近的需要处理的元素
     * @param s
     * @return
     */
    public static boolean Solution(String s) {
        int len = s.length();
        if (len == 0)
            return true;
        char[] stack = new char[len]; //栈
        int top = -1; //栈顶
        char[] chars = s.toCharArray(); // 字符串转化为字符数组
        for (int i=0; i<len; i++){
            if (chars[i] == '(' || chars[i] == '{' || chars[i] == '['){
                // 进栈
                stack[++top] = chars[i];
            }

            if (chars[i] == ')' || chars[i] == '}' || chars[i] == ']'){
                // 出栈
                if (top == -1)
                    return false;
                char cur = stack[top--];
                switch (chars[i]){
                    case ')':
                        if (cur != '(')
                            return false;
                        break;
                    case '}':
                        if (cur != '{')
                            return false;
                        break;
                    case ']':
                        if (cur != '[')
                            return false;
                        break;
                }
            }
        }
        return top == -1 ? true : false;
    }
}
