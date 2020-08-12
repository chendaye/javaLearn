package Algorithm.Interview.LeetCode.SearchTable;

import Utils.Dump;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * todo:思路： 就是在输入的点中找回旋镖
 *      - 到i的距离相等的点。
 *      - 用查找表记录 所有点到i的距离
 *
 * 示例:
 *
 * 输入:
 * [[0,0],[1,0],[2,0]]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 类似： 149
 */
public class NumberOfBoom {
    public static void main(String[] args) {
        int[][] array = {{0,0},{1,0},{2,0}};
        Dump.dump(Solution(array));
    }

    /**
     * 每一个元素都 用一个辅助数组
     * @param array
     * @return
     */
    public static int Solution(int[][] array){
        int total=0;
        for (int i=0;i<array.length;i++){
            HashMap<Long, Integer> record = new HashMap<>();
            // 每一个点到其他点的距离
            for (int j=0;j<array.length;j++){
                long dis = distence(array[i],array[j]);
                if (record.containsKey(dis))
                    record.put(dis, record.get(dis)+1);
                else
                    record.put(dis, 1);
            }
            // 计算每一个顶点的回旋镖
            Iterator<Integer> iterator = record.values().iterator();
            while (iterator.hasNext()){
                Integer next = iterator.next(); // todo: 循环体内 iterator.next() 只能有一次
                if (next >=2){
                    total += next*(next-1); // 排列组合 Cn#2
                }
            }

        }
        return total;
    }

    public static long distence(int[] array_1,int[] array_2){
        return (long) (Math.pow(array_1[0]-array_2[0], 2)+Math.pow(array_1[1]-array_2[1], 2));

    }
}
