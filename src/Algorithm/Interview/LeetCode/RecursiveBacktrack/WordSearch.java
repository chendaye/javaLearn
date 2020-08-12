package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Utils.Dump;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        Dump.dump(exist(board, "ABCCED"));
    }

    /**
     * todo: 回溯的点
     *      - 主方法 管理 回溯的全局变量： 题中给定的背景变量、 访问变量
     *      - 主方法调用递归方法
     *      - 递归方法 沿着一条路径 判断一条分支
     *      - 分支终止时 回溯到上一步
     *      - 递归方法就是找到一个符合条件的匹配(寻找的起始点 是任意中间状态)
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        //todo: 左 上 右 下 二维平面中的的偏移量数组，是一个技巧
        int[][] dire = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        int m = board.length; // m 行 y
        int n = board[0].length; // n 列 x
        //todo:记录点是否被访问过
        int[][] visit = new int[m][n];

        for (int y=0; y<board.length; y++){
            for (int x=0; x<board[y].length; x++){
                if (searchWord(board, word,  dire, m, n, visit, 0, x, y))
                    return true;
            }
        }
        return false;
    }

    /**
     * todo: 二维表 board中，(start_x,start_y)位置上下左右搜索，是否等于单词中的 index 位置
     *          先判断自己是否匹配 再搜索上下左右
     * @param board
     * @param word
     * @param index
     * @param start_x
     * @param start_y
     * @return
     *
     *
     * todo: 判断从 start_x start_y 出发能不能找到单词
     */
    public static boolean searchWord(char[][] board, String word,  int[][] dire, int m, int n, int[][] visit, int index, int start_x, int start_y){
        //todo:如果是最后一个字符
        if (index == word.length() -1)
            return board[start_y][start_x] == word.charAt(index);
        //todo:当前节点是否匹配
        if (board[start_y][start_x] == word.charAt(index)){
            visit[start_y][start_x] = 1; //todo: 标记访问
            //todo:搜索上下左右
            for (int i=0;i<3;i++){
                //todo: 下一个要判断的新的坐标
                int new_x = start_x + dire[i][0];
                int new_y = start_y + dire[i][1];
                //todo: 判断xy是否越界 并且没有被访问过
                if (new_x>=0 && new_x<n && new_y>=0 && new_y<m && visit[new_y][new_x] == 0){
                    if (searchWord(board, word, dire,  m, n, visit, index+1, new_x, new_y))
                        return true;
                }
            }
            visit[start_y][start_x] = 0; //todo: 回溯到上一步
        }
        return false;
    }
}
