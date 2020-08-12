package Algorithm.Interview.NowCoder;

import Utils.Dump;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4  5 6 7 8   9 10 11 12   13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 */
public class printMatrix {

    //todo: 用四个标志位置就可以进行解决（代码中的low、high、left、right代表了即将访问的上下左右四条线
    public ArrayList<Integer> printMatrix2(int [][] matrix) {

        ArrayList<Integer> result = new ArrayList<>();
        if(matrix == null)return result;

        int low = 0;
        int high = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(low <= high && left <= right){

            //向右
            for(int i=left; i <= right; i++)
                result.add(matrix[low][i]);

            //向下
            for(int i = low+1; i <= high; i++)
                result.add(matrix[i][right]);

            //向左 有可能出现特殊的情况只有一行，为了避免重复访问
            if(low < high){
                for(int i= right-1; i >= left; i--)
                    result.add(matrix[high][i]);
            }

            //向上 有可能出现特殊的情况只有一列，为了避免重复访问
            if(left < right){
                for(int i = high-1; i >= low+1; i--)
                    result.add(matrix[i][left]);
            }

            low++;
            high--;
            left++;
            right--;
        }
        return result;
    }


    //todo: 右边 下边 左边 上边
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        //todo: 坐标轴的方向 X 垂直向下； Y 水平向左。 按顺时针-
        //todo: 这样设置坐标轴是为了 适应 二维数组下标
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 方向数组
        int n = matrix.length; // 高
        int m = matrix[0].length; // 宽
        int[][] visit = new int[n][m]; // 访问数组
        ArrayList<Integer> list = new ArrayList<>();

        int x = 0, y = 0, d = 0;
        while (x >= 0 && x < n && y >= 0 && y < m && visit[x][y] == 0){
            visit[x][y] = 1; // 访问
            list.add(matrix[x][y]);

            //todo: 沿着一个方向走
            while (x + dir[d][0] >= 0 && x + dir[d][0] < n && y + dir[d][1] >= 0 && y + dir[d][1] < m && visit[x + dir[d][0]][y + dir[d][1]] == 0){
                x += dir[d][0];
                y += dir[d][1];
                list.add(matrix[x][y]);
                visit[x][y] = 1;
            }
            //todo: 一个方向走到头了，换一个方向 d+1
            //todo: 保证下标不越界 d+1 取模
            d = (d + 1) % 4; // 新的方向
            x +=  dir[d][0];
            y +=  dir[d][1];
        }
        return list;
    }

    /**
     *  * *  *  *  *  *-> y
     *  * 1  2  3  4
     *  * 5  6  7  8
     *  * 9  10 11 12
     *  * 13 14 15 16
     *  *
     *  * x
     * @param args
     */
    public static void main(String[] args) {
        printMatrix printMatrix = new printMatrix();
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Dump.iterator(printMatrix.printMatrix(arr));
    }
}
