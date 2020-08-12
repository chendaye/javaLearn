package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Utils.Dump;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * todo:类似 130 417
 */
public class
NumIslands {
    static int islands = 0;
    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        numIslands(grid);
        Dump.dump(islands);
    }


    /**
     * todo: 回溯 在主函数中处理 结果逻辑
     *          - 递归函数起辅助作用
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int y = grid.length;
        if (y == 0) return 0;
        int x = grid[0].length;
        int[][] visit = new int[y][x];
        int[][] distance = {{-1,0}, {0,1}, {1,0}, {0, -1}};  //todo:左上右下
        //todo: 遍历每一个坐标，看是否构成一个岛屿
        for (int i=0; i< y; i++){
            for (int j=0; j<x; j++){
                //todo: 遇到没有访问到的 1 就 +1
                if (grid[i][j] == '1' && visit[i][j] == 0){
                    islands++;
                    //todo:标记所有访问到的 1
                    //todo:也可以把所有访问到的1 设置为0
                    Find(grid,y,x,visit,distance,i,j);
                }
            }
        }
        return islands;
    }

    /**
     * todo: 多叉树的遍历
     * 找到陆地 标记访问 设置为0
     * @param grid
     * @param y
     * @param x
     * @param visit
     * @param distance
     * @param a
     * @param b
     */
    public static void Find(char[][] grid,int y, int x, int[][] visit, int[][] distance, int a, int b){
        visit[a][b] = 1;
        for (int i=0; i<4; i++){
            int ny = a + distance[i][1];
            int nx = b + distance[i][0];
            //todo： 没有越界 & 没有被访问
            if (nx>=0 && nx < x && ny >= 0 && ny < y && visit[ny][nx] == 0 && grid[ny][nx] == '1'){
                Find(grid, y, x, visit, distance, ny, nx);
            }
        }
        return;
    }

}
