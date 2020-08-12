package Algorithm.Interview.NowCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 */
public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        if(str == "") return -1;
        char[] chars = str.toCharArray();
        HashMap<Character, ArrayList<Integer>> record = new HashMap<>();
        for (int i = 0; i < chars.length; i++){
            if (record.containsKey(chars[i])){
                ArrayList<Integer> list = record.get(chars[i]);
                list.add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                record.put(chars[i], list);
            }
        }
        Iterator<Map.Entry<Character, ArrayList<Integer>>> iterator = record.entrySet().iterator();
        //todo: 只要遍历数组 找到对应的hash值=1 就行
        for (int i = 0; i < chars.length; i++)
            if (record.get(chars[i]).size() == 1) return i;

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstNotRepeatingChar().FirstNotRepeatingChar("google"));
    }
}
