package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;
import Utils.Generate;

/**
 * todo:滑动窗口
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 类似：438 76
 */
public class LongestSub {
    public static void main(String[] args) {
        String s = Generate.getString(20, false);
        Dump.dump(s);
        Dump.dump(Solution(s));
    }

    /**
     * 滑动窗口, 把握住窗口的边界
     * todo:要点：
     *      - 计数器数组
     *      - 滑动窗口上下界 开闭
     *      - 循环不变量
     *      - 注意数组越界问题
     * @param str
     * @return
     */
    public static int Solution(String str){
        if (str.equals("") || str.length() == 1)
            return str.length();
        int[] sign = new int[256]; // 标记数组。总共256个字符
        int l=-1,r=0; // (l....r) 前开后开 包含长度 r-l-1，l所指的元素不在非重复字符串中
        int maxLen = 0;
        while (r < str.length()){
            if (sign[str.charAt(r)] == 0){
                sign[str.charAt(r++)]++;
            }else{ // sign[str.charAt(r)] != 0 || r+1>=str.length()
                sign[str.charAt(++l)]--;
            }
            maxLen = Math.max(maxLen, r-l-1); // 每次循环重新计算 maxLen
        }
        return maxLen;
    }
}
