package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * todo:树形问题  递归
 *      - 递归就是回溯
 *      - 回溯是暴力解法的一个重要实现。
 *      - 当不能用多重循环暴力求解时（无法知道循环的层数），使用回溯法
 *      - 回溯法就是找到一个解 返回上一步
 *      - 提高回溯法的效率 -- 剪枝
 *
 * todo:回溯法可以解决的问题
 *      - 排序问题
 *
 * todo: 类似  93 131
 */
public class LetterCombinations {
    public static void main(String[] args) {

    }

    // 键盘映射
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    //todo: 用来保存结果
    List<String> output = new ArrayList<String>();

    /**
     *
     * @param combination  字符的组合
     * @param next_digits  还没有 用到的数字
     */
    public void backtrack(String combination, String next_digits) {
        // 数字已经用完 说明，组合已经完成
        if (next_digits.length() == 0) {
            // 把组合加到结果中
            output.add(combination);
        } else {
            /**
             * 如果数字没有用完，截取数字
             * 并取到数字代表的 字符串
             * 遍历字符串
             */
            String digit = next_digits.substring(0, 1); // 下一个数字
            String letters = phone.get(digit); // 下一个数字对应的字符串
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1); // 取每一个字符
                backtrack(combination + letter, next_digits.substring(1));  // 每一个字符都组合一次
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }



}
