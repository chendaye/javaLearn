package Algorithm.Interview.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * todo： 回溯
 */
public class BackTrack {
    public static void main(String[] args) {
        
    }

    /**
     * todo： 组合
     * @param n
     * @param k
     * @param cur
     * @param res
     * @param start
     */
    public static void Combination(int n, int k, ArrayList<Integer> cur, List<List<Integer>> res, int start){
        if (cur.size() == k){
            ArrayList<Integer> curPro = new ArrayList<Integer>();  //todo:以当前组合为基础 初始化一个新的组合
            curPro.addAll(cur);
            res.add(curPro);
            return;
        }
        for (int i = start; i <= n; i++){
            cur.add(i);  // 组合新增一个值
            Combination(n, k, cur, res,i+1);
            cur.remove(cur.size() - 1);
        }
    }


    /**
     * todo: 排列
     * @param result
     * @param path
     * @param nums
     */
    public static void Permutation(List<List<Integer>> result,List<Integer> path,int[] nums)
    {
        if(path.size()==nums.length) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(path);
            result.add(temp);
            return;
        }
        for(int j=0;j<nums.length;j++) {
            if(path.contains(nums[j])) continue;
            path.add(nums[j]);
            Permutation(result,path,nums); //todo: 已经得到了一个子结果，回溯
            path.remove(path.size()-1);
        }
    }


    /**
     * todo: visited[], 二维表格每一个位置进行回溯
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
