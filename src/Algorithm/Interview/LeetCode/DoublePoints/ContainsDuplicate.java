package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
 * 并且 i 和 j 的差的 绝对值 至多为 k。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 类似：217
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
//        int[] array = Generate.get(12, 20, false, false);
        int[] array = {1,2,3,1,2,3};
        Dump.array(array,true);
        Dump.dump(Solution(array, 2));
    }

    /**
     * todo:思路：
     *      - 暴力求解 O(n^2)
     *      - 滑动窗口+动态查找表。 动态查找表的原因是：只有在窗口内的元素才记录在查找表中
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param array
     * @param k
     * @return
     */
    public static boolean Solution(int[] array, int k){
        int l=0,r=0; // [l...r] 区间长度 = r-l+1
        HashMap<Integer, Integer> record = new HashMap<>();
        while (r<array.length && l<=r){
            if (r-l <= k){
                if (record.containsKey(array[r]) && record.get(array[r]) > 0){
                    return true;
                }else {
                    // todo:一定要注意是否越界，判断循环里条件分支是否一定被执行到
                    record.put(array[r], record.containsKey(array[r]) ?record.get(array[r])+1 : 1);
                    r++;
                }
            }else {
                record.put(array[l],record.get(array[l])-1);  //todo 移出滑动窗口的要-1
                l++;
            }
        }
        return false;
    }
}
