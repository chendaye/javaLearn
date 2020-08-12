package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 注意：如果没有解怎样？如果有多个解怎样
 *
 * todo：
 *    - 暴力解 O(n^2) 2层遍历
 *    - 二分搜搜 O(nlogn), 遍历一个数，在后面的元素中利用二分搜索查找 target-current。
 *          因为二分搜索是 O(logn) 有n个元素 最终的时间复杂度O(nlogn
 *    - 用两个指针从两边夹 O(n),学名对撞指针
 *
 */
public class TwoSum {
    public static void main(String[] args) {
//        int[] array = new int[20];
//        array = Random.get(20,40,true,true);
//        Dump.array(array,true);
//        int i = BinarySearch(array, 5, 30, 15);
//        Dump.dump(i);

        int[] test = {2, 7, 11, 15};
//        int[] test = {-1,0};
        Dump.array(Solution2(test,13),true);
    }

    /**
     * 用二分搜索查找
     */
    public static int[] Solution(int[] array, int target){
        int[] ret = new int[2];
        for (int i=0; i<array.length;i++){
            int tmp = target-array[i];
            if (tmp<=0){
                continue;
            }else {
                int index = BinarySearch(array,i+1,array.length-1,tmp);
                if (index==-1){
                    continue;
                }else {
                    ret[0] = i;
                    ret[1] = index;
                }
            }
        }
        return ret;
    }

    /**
     * 二分搜索
     * @param array
     * @param start
     * @param end
     * @param target
     */
    public static int BinarySearch(int[] array, int start, int end, int target){
        if (start == end && array[start]==target)
            return start;
        int l = start, r=end; // // 在[l....r]闭区间找 target
        while (l < r){
            int mid = (l+r)/2;
            if (array[mid] == target){
                return mid;
            }else if(array[mid] > target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }

    /**
     * 用两个指针夹:利用有序的条件
     * @param array
     * @param target
     * @return
     */
    public static int[] Solution2(int[] array, int target){
        int l = 0,r=array.length-1;
        int[] ret = new int[2];
        while (l < r){
            int sum = array[l] + array[r];
            if(sum == target){
                ret[0] = l;
                ret[1] = r;
                break;
            }else if (sum < target){
                l++; // sum 不够大 l向前移动
            }else{
                r--;
            }
        }
        return ret;
    }
}
