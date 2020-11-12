package Algorithm.Interview.LeetCode.SlideWindow;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * todo: 滑动窗口
 *         - 窗口 [left, right)
 *         - 两重while循环， left right 在 原字符串上游动
 *  求最长无重复子串
 */
public class NoneRepeatSub {

    public static void main(String[] args){
        String s = "aasfs";
//        System.out.println(solution(s));



        System.out.println('a' - 'A');
    }

    public static int solution(String s){
        int left = 0, right = 0;
        LinkedList<Character> win = new LinkedList<>();
        int len = s.length();
        int max = 0;
        //todo: 两重 while 循环  left right 在字符串上移动 不是在窗口容器上移动
        //todo: win [left, right)
        while(right < len){
            win.addFirst(s.charAt(right));
            right++;
            // 缩小窗口指导满足不重复条件
            while(!helper(win)){ // [left, right)
                left++;
                win.removeLast();
            }
            max = Math.max(max, win.size());
        }
        return max;
    }

    // 判断窗口内的内容是否重复
    public static boolean helper(LinkedList<Character> win){
        ArrayList<Character> list = new ArrayList<>();
        for (char c : win){
            if (list.contains(c)) return false;
            list.add(c);
        }
        return true;
    }
}
