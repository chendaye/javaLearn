package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class generateParenthesis {
    public static void main(String[] args){
        generateParenthesis(5);
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        back(res, cur, n,0);
        for (String s : res){
            System.out.println(s);
        }
        return res;
    }

    public static void back(List<String> res, ArrayList<Integer> cur, int n,int start){
        if(cur.size() == n){
            StringBuilder builder = new StringBuilder();
            ArrayList<Integer> tmp = new ArrayList<>(cur);
            for (int i = 0; i < n*2; i++){
                if (tmp.contains(i)){
                    builder.append('(');
                }else{
                    builder.append(')');
                }
            }
            String str = builder.toString();
            if (!res.contains(str) && helper(str)) res.add(str);
            return;
        }
        for (int i = start; i < n*2; i++){
            cur.add(i);
            back(res, cur,n, i + 1);
            cur.remove(cur.size() - 1);
        }
    }

    // 判断是否是合法的括号组合
    public static boolean helper(String str){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if (ch == '('){
                stack.push(ch);
            }else{
                if (stack.isEmpty()){
                    return false;
                }else if(stack.peek() == ')'){
                    stack.push(ch);
                }else{
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
