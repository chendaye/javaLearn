package Algorithm.Interview.NowCoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * ​
 *    矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
 *    路径不能再次进入该格子。
 *
 *    https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
 */
public class hasPath {
    /**
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     *
     * todo: 要点
     *      - [x, y] -> index = x + y * cols
     *      - dfs -> 先根遍历
     *      - 在选择列表外 回溯
     */
    int[][] dict = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //todo: 上 右 下 左
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        int[] visit = new int[matrix.length];
        //todo: 从每一个点出发
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                Arrays.fill(visit, 0);
                if (DFS(matrix, rows, cols, str, visit, 0, j, i))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * todo: [x, y] -> index
     *      - index = x + y * cols
     *
     *
     * todo: 回溯- 每一步对应一个子问题，如果当前子问题匹配，继续下一步； 否则回溯；
     *          - 回溯是渐进式的
     */
    public boolean DFS(char[] matrix, int rows, int cols, char[] str, int[] visit, int len, int x, int y){
        //todo: 当前位置是否满足继续回溯条件
        int index = x + y * cols;
        if(matrix[index] != str[len]) return false; //todo: 子问题要成功
        if(len == str.length-1) return true;
        visit[index] = 1; //todo: 还是先根遍历

        //todo: 当前位置满足，尝试选择列表（不同方向），如果选择列表中有匹配的选择，就返回 不要回溯节点；
        //todo: 选择列表中没有匹配的，就回溯一步
        for (int i = 0; i < 4; i++){
            int cur_x = x + dict[i][0];
            int cur_y = y + dict[i][1];
            if (cur_x >= cols || cur_x < 0 || cur_y >= rows || cur_y < 0) continue;
            int inx = cur_x + cur_y * cols;
            if (visit[inx] == 0)
                if (DFS(matrix, rows, cols, str, visit, len + 1, cur_x, cur_y)) return  true;
        }

        //todo: 回溯判断的当前步骤，而非整个问题的解
        visit[index] = 0; //todo: 回溯节点
        return false;
    }

    public static void main(String[] args) {
//        ArrayList<Integer> integers = new ArrayList<>();
//        int n = 0;
//        test(integers, n);
//        test(integers, n);
//
//        Dump.iterator(integers);
//        System.out.println("--------"+n);

        hasPath hasPath = new hasPath();
        System.out.println(hasPath.hasPath("ABCESFCSADEE".toCharArray(),3,4,"ABCCED".toCharArray()));

    }

    public static void test(ArrayList<Integer> list, int n){
        for (int i = 0; i < 5; i++)
            list.add(i);
        n++;
    }
}
