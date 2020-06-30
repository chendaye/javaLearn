package Algorithm.Interview.Offer;

import LanguageElement.Array;
import Utils.Dump;

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
     * todo: 每一行的起始元素 索引： 0 + (rows - 1) * cols
     *      - 回溯 ： 路径 + 选择列表
     *      - dfs
     */
    int[][] dict = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //todo: 上 右 下 左
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        int[] visit = new int[matrix.length];
        ArrayList<Character> path = new ArrayList<>();
        //todo: 从每一个点出发
        for (int i = 0; i < matrix.length; i++){
            Arrays.fill(visit, 0);
            path.clear();
            if (DFS(matrix, rows, cols, str, visit, path, i))
                return true;
        }
        return false;
    }

    /**
     *todo: index -> [x, y]
     *      - x = index % rows
     *      - y = index / rows
     *
     * todo: [x, y] -> index
     *      - index = x + y * cols
     */
    public boolean DFS(char[] matrix, int rows, int cols, char[] str, int[] visit, ArrayList<Character> path, int index){
        int len = path.size();
        if (len > 0){
            if (path.get(len - 1) != str[len -1]) return false;
            if (len == str.length) return true;
        }
        visit[index] = 1;
        path.add(matrix[index]);
        int x = index % rows;
        int y = index / rows;
        boolean flag = true;
        for (int i = 0; i < 4; i++){
            int cur_x = x + dict[i][0];
            int cur_y = y + dict[i][1];
            if (cur_x >= cols || cur_x < 0 || cur_y >= rows || cur_y < 0) continue;
            int inx = cur_x + cur_y * cols;
            if (visit[inx] == 0)
                flag = DFS(matrix, rows, cols, str, visit, path, inx);
        }
        visit[index] = 0;
        return flag;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        int n = 0;
        test(integers, n);
        test(integers, n);

        Dump.iterator(integers);
        System.out.println("--------"+n);


    }

    public static void test(ArrayList<Integer> list, int n){
        for (int i = 0; i < 5; i++)
            list.add(i);
        n++;
    }
}
