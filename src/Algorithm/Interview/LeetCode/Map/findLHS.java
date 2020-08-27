package Algorithm.Interview.LeetCode.Map;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 *
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class findLHS {

    /**
     * todo: 用 HashMap 记录 每个数字出现的个数
     *      -   求序列 就求 max{x-1 x x+1}
     *      -  涉及到求个数的有序表
     * @param nums
     * @return
     */
    public int dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i: nums){
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
        }

        for (int i : nums){
            if (map.containsKey(i - 1))
                max = Math.max(max, map.get(i) + map.get(i - 1));
            if (map.containsKey(i + 1))
                max = Math.max(max, map.get(i) + map.get(i + 1));
        }
        return max;
    }


    private int maxLen = 0;
    public int findLHS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        for(int i = 1; i <= nums.length; i++){
            trackback(new ArrayList<Integer>(), nums, 0, i);
        }
        return maxLen;
    }

    public void trackback(ArrayList<Integer> path, int[] nums, int start, int k){
        if(path.size() == k){
            // 找到一个组合
            if(path.size() > maxLen && harmmany(path)){
                maxLen = path.size();
            }
            return;
        }

        for(int i = start; i < nums.length; i++){
            path.add(nums[i]);
            trackback(path, nums, i+1, k);
            path.remove(path.size() - 1);
        }
    }
    public boolean harmmany(ArrayList<Integer> list){
        int min = list.get(0);
        int max = list.get(0);
        int index = 0;
        while(index < list.size()){
            min = Math.min(min, list.get(index));
            max = Math.max(max, list.get(index));
            index++;
        }
        return max - min == 1;
    }




    public static void main(String[] args) {
        findLHS findLHS = new findLHS();
        int[] arr = {1,3,2,2,5,2,3,7};
//        int[] arr = {1,1,1,1};
        int[] arr2 = {3,2,2,2,3};

        ArrayList<Integer> list = new ArrayList<>();
        for (int n: arr2)
            list.add(n);
//        findLHS.findLHS(arr);
//        System.out.println(findLHS.maxLen);

        System.out.println(findLHS.dp(arr));
    }
}
