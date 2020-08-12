package Algorithm.Interview.LeetCode.SearchTable;

import Utils.Dump;
import Utils.Generate;

import java.util.HashMap;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 就是在4个数组中各选一个元素，让其和为0； 问有多少种组合
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo：思路
 *      - 用暴力解法， 4重循环，时间复杂度 O(n^4)
 *      - 用查找表，将C D种所有和的情况存入查找表。注意和可能会有重复，所以查找表中记录的是和出现的次数
 *          查找表的长度 500*500 = 250000， 用Map
 *          另外 A B两重循环 O(n^2)
 *
 * todo： 总结
 *      - 并不是直接把简单的逻辑 放入查找表，而是把组合逻辑放入
 *      - 通过查找表把暴力算法复杂度降低
 *
 * 类似： 49
 */
public class FourSum {
    public static void main(String[] args) {
        int[] A = Generate.get(50, 200, false, false);
        int[] B = Generate.get(50, 200, false, false);
        int[] C = Generate.get(50, 200, false, false);
        int[] D = Generate.get(50, 200, false, false);
        Dump.dump(Solution(A,B,C,D));
    }

    /**
     * 时间复杂度： O(n^2)
     * 科技发展的 ：O(n^2)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static int Solution(int[] A, int[] B, int[] C, int[] D){
        HashMap<Integer, Integer> record = new HashMap<>();
        // 初始化查找表
        for (int i=0;i<C.length;i++){
            for (int j=0;j<D.length;j++){
                int sum = C[i]+D[j];
                if (record.containsKey(sum)){
                    Integer integer = record.get(sum);
                    record.put(sum, integer+1);
                }else {
                    record.put(sum,1);
                }
            }
        }

        int ret = 0;
        for (int i=0;i<A.length;i++) {
            for (int j = 0; j < B.length; j++) {
                int compare = 0-A[i]-B[j];
                if (record.containsKey(compare))
                    ret += record.get(compare); // 符合要求的组合种数
            }
        }
        return ret;
    }
}
