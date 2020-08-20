package Algorithm.Interview.LeetCode.DynamicProgramming;

import Utils.Dump;

import java.util.ArrayList;

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
    private int maxLen = 0;
    public int findLHS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        trackback(new ArrayList<Integer>(), nums, 0);
        return maxLen;
    }

    public void trackback(ArrayList<Integer> path, int[] nums, int start){
        if(start == nums.length){
            // 找到一个组合
//            System.out.println(path.size()+"----"+maxLen+" --"+harmmany(path));
            if(path.size() > maxLen && harmmany(path)){
                maxLen = path.size();
            }
            return;
        }

        for(int i = start; i < nums.length; i++){
            path.add(nums[i]);
            trackback(path, nums, i+1);
            path.remove(path.size() - 1);
        }
    }
    public boolean harmmany(ArrayList<Integer> list){
//        if (list.size() == 5 && list.get(0) == 3 && list.get(1) == 2)Dump.iterator(list);
        Dump.iterator(list);

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
        int[] arr2 = {3,2,2,2,3};

        ArrayList<Integer> list = new ArrayList<>();
        for (int n: arr2)
            list.add(n);
        findLHS.findLHS(arr);
        System.out.println(findLHS.maxLen);
    }
}
