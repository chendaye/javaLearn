package Algorithm.Interview.LeetCode.DoublePoints;

import Utils.Dump;
import Utils.Generate;

/**
 * LeetCode-75
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * todo:思路，分别统计0 1 2的个数，在放在对应位置，也就是计数排序
 */
public class SortColor {
    public static void main(String[] args) {
        int[] array = new int[20];
        array = Generate.get(30,3,false,false);
        Dump.array(array,true);
//        Solution(array);
        Solution2(array);
        Dump.array(array,true);
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * todo：技巧：辅助数组
     * @param color
     */
    public static void Solution(int[] color){
        int[] num = new int[3]; // key 就是0 1 2
        for (int i=0; i<color.length; i++){
            if (color[i] == 0)
                num[0]++;
            if (color[i] == 1)
                num[1]++;
            if (color[i] == 2)
                num[2]++;
        }
        int j = 0;
        for (int i=0; i<num.length; i++){
            while (num[i] > 0){
                color[j++] = i;
                num[i]--;
            }
        }
    }

    /**
     * 三路快排
     * todo：思路，把数组看成3段， 0 段 1段 2段
     *          用 left 和 right 作为分界， 也就是 1段的上下届
     *          [0....left] [left+1....right-1] [right....n-1]
     *          通过交换达成排序
     *
     * todo:变与不变
     * @param color
     */
    public static void Solution2(int[] color){
        int left = -1;
        int right = color.length; // 时 1段 的范围包含全部 [-1+1....color.length-1] = [0....n-1]
        for (int i=0; i<right;){  // 只关心中间一段，把中间一段的元素换到两边； i到达 1段的边界为止
            if (color[i] == 1){
                i++; // i指向的是1，啥也不做，往后搜索
            }else if(color[i] == 0){
                int tmp = color[i];
                color[i] = color[left+1];
                color[left+1] = tmp; // 把当前的0 和1段的第一个元素交换位置
                left++; // 0段加1
//                i++; // 不用继续向前，对1有处理了
            }else {
                int tmp = color[i];
                color[i] = color[right-1];
                color[right -1] = tmp; // 把当前的2 和 1段的最后一个元素交换位置
                right--; // 2段加1
//                i++; // 不用继续向前
            }
        }
    }
}
