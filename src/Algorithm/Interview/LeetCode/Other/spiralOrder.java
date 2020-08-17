package Algorithm.Interview.LeetCode.Other;

import Utils.Dump;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * todo: 注意坐标系的原点在 左上角，和 二维数组最匹配
 *---------> X
 * 1 2 3
 * 4 5 6 
 * 7 8 9
 *
 * Y
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class spiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Dump.array(spiralOrder(matrix));
    }
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        //todo: 初始时 注意坐标系的原点在左上角
        int l = 0, t = 0, r = matrix[0].length - 1, b = matrix.length - 1;
        int index = 0;
        int[] ans = new int[matrix.length * matrix[0].length];
        while (true){
            for (int i = l; i <= r; i++){
                ans[index++] = matrix[t][i]; //todo: left -> right
            }
            if(++t > b) break;
            for (int i = t; i <= b; i++){
                ans[index++] = matrix[i][r]; //todo: top -> bottom
            }
            if(--r < l) break;
            for (int i = r; i >= l; i--){
                ans[index++] = matrix[b][i]; //todo: right -> left
            }
            if (--b < t) break;
            for (int i = b; i >= t; i--){
                ans[index++] = matrix[i][l]; //todo: bottom -> top
            }
            if (++l > r) break;
        }
        return ans;
    }


}
