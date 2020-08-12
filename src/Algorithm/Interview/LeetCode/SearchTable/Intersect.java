package Algorithm.Interview.LeetCode.SearchTable;

import Utils.Dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo:使用Map Set的数据结构，的时间复杂度要看底层的数据结构实现
 *
 * todo: 类似 242 202 290 205 451
 */
public class Intersect {
    public static void main(String[] args) {
//        int[] array_1 = Generate.get(10, 8, false, false);
        int[] array_1 = {4,9,5};;
//        int[] array_2 = Generate.get(20, 80, false, false);
        int[] array_2 = {9,4,9,8,4};

        Dump.array(array_1,true);
        Dump.array(array_2,true);
        Dump.array(Solution2(array_1,array_2),true);
    }

    /**
     * 空间复杂度 O(n)
     * 时间复杂度 O(n)
     * @param array_1
     * @param array_2
     * @return
     */
    public static int[] Solution(int[] array_1, int[] array_2){
        //空间O(n)
        ArrayList<Integer> list1 = new ArrayList<>();
        //空间O(n)
        ArrayList<Integer> list2 = new ArrayList<>();
        //时间O(n)
        for (int item:array_1){
            list1.add(item);
        }
        //时间O(n)
        for (int item:array_2){
            list2.add(item);
        }
        //ArrayList 底层为数组 查找一个元素 O(1) n个元素===> O(n)
        Stream<Integer> stream = list1.stream().filter(item -> list2.contains(item));
        Object[] objects = stream.toArray();
        int[] ret = new int[objects.length];
        //时间O(n)
        for (int i=0;i<objects.length;i++){
            ret[i] = (int)objects[i];
        }
        return ret;
    }

    /**
     * HashMap 底层是hash表 查找是 O(1)
     * @param array_1
     * @param array_2
     * @return
     */
    public static int[] Solution2(int[] array_1, int[] array_2){
        HashMap<Integer, Integer> record = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i=0;i<array_1.length;i++){
            //todo: 元素每出现一次 计数器+1
            if (record.containsKey(array_1[i])){
                int tmp = record.get(array_1[i]);
                Dump.dump(tmp);
                record.put(array_1[i],++tmp);
            } else{
                record.put(array_1[i],1);
            }
        }

        for (int i=0;i<array_2.length;i++){
            if (record.containsKey(array_2[i]) && record.get(array_2[i]) > 0){
                //todo: 元素每重复一次 计数器-1；当计数器=0时，两种情况
                //todo: 此元素之前有有此元素，已经匹配过了 或 没有重复
                //todo: 比如： array_1 有 2个A array_2 有3个A；  起始 count(A)=2；比较到array_2第3个A时 count(A)=0
                int tmp = record.get(array_2[i]);
                result.add(array_2[i]);
                record.put(array_2[i],--tmp);
            }
        }
        Object[] objects = result.toArray();
        int[] ret = new int[objects.length];
        for (int i=0;i<objects.length;i++){
            ret[i] = (int)objects[i];
        }
        return ret;
    }
}
