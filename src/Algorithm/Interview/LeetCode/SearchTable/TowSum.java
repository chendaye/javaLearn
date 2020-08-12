package Algorithm.Interview.LeetCode.SearchTable;

import Utils.Dump;
import Utils.Generate;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * todo:类似： 15 18 16
 *
 * todo:思路
 *      - 先排序 再指针对撞
 *      - 查找表：将所有元素a放入查找表， 对每个元素查找 target-a 是否存在
 */
public class TowSum {
    public static void main(String[] args) {
        int[] array = Generate.get(100, 120, false, false);
        Dump.array(array,true);
        Dump.array(Solution(array, 70),true);
    }

    public static int[] Solution(int[] array, int target){
        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i=0; i<array.length; i++){
            record.put(array[i], i); // 有可能覆盖重复元素
        }

        int[] ret = new int[2];
        for (int i=0; i<array.length; i++){
            int tmp = array[i];
            if(record.containsKey(target - array[i]) && i != record.get(target - array[i])){
                ret[0] = i;
                ret[1] = record.get(target - array[i]);
                break;
            }
        }
        return ret;
    }
}
