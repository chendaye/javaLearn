package Algorithm.Interview.LeetCode.SearchTable;

import Utils.Dump;
import Utils.Generate;

import java.util.HashSet;
import java.util.stream.Stream;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Intersection {
    public static void main(String[] args) {
        int len = 12;
        char[] array_1 = Generate.getString(len, false).toCharArray();
        char[] array_2 = Generate.getString(len, false).toCharArray();
        Dump.array(array_1, true);
        Dump.array(array_2, true);
        Dump.array(Solution(array_1, array_2),true);


    }


    /**
     * 把一个数组存入 set中， 再判断另一个数组是够有交集
     * @param a
     * @param b
     * @return
     */
    public static char[] Solution(char[] a, char[] b){
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();
        for (Character item: a){
            if (!set.contains(item))
                set.add(item);
        }
        for (Character item: b){
            if (!set2.contains(item))
                set2.add(item);
        }

        Stream<Character> characterStream = set.stream().filter(item -> set2.contains(item));

        Object[] objects = characterStream.toArray();
        char[] ret = new char[objects.length];
        for (int i=0;i<objects.length;i++){
            ret[i] = (char)objects[i];
        }
        return ret;
    }
}
