package Algorithm.Interview.LeetCode.Sort;

import Utils.Dump;
import Utils.Generate;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
 * 同时保持非零元素的相对顺序。
 */
public class MoveZero {
    public static void main(String[] args) {
        int[] array = new int[20];
        array = Generate.get(20,5,false,false);
        Dump.array(array,true);
        Solution(array);
        Dump.array(array,true);
    }

    /**
     * todo:思路 遍历数组把所有非0值全部取出，放到前面，后面的补0
     *      不用辅助数组。关键思维在意原数组中的0不用处理。直接补0
     * todo:时间复杂度 O(n) 空间复杂度 O(1)
     * @param arr
     */
    public static void Solution(int[] arr){
        int p = 0;
        for (int i = 0; i<arr.length; i++){
            if (arr[i] != 0){
                if (p != i)
                    arr[p] = arr[i]; // 全部都是非零元素不用重新赋值
                p++;
            }else{
                continue;
            }
        }
        while (p<arr.length){
            arr[p] = 0;
            p++;
        }
    }
}
