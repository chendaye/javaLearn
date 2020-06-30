package Algorithm.Interview.Offer;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 *
 * 请问该机器人能够达到多少个格子？
 */
public class movingCount {
    int[][] dict = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //todo: 上 右 下 左
    int sum = 0;
    public int movingCount(int threshold, int rows, int cols)
    {
        int[][] visit = new int[rows][cols]; //todo: 访问标记
        DFS(visit, threshold, rows, cols, 0, 0);
        return sum;
    }

    public void DFS(int[][] visit, int threshold, int rows, int cols, int x, int y){

        if (enter(x, y, threshold)) sum++;
        for (int i = 0; i < 4; i++){
            int cur_x = x + dict[i][0];
            int cur_y = y + dict[i][1];
            //todo: 判断是否越界
            if (cur_x >= rows || cur_y >= cols || cur_x < 0 || cur_y < 0) return;
            //todo: 没有被访问过，且可以进入
            if (visit[cur_x][cur_y] == 0 && enter(cur_x, cur_y, threshold)){
                sum++;
                visit[cur_x][cur_y] = 1;
                DFS(visit, threshold, rows, cols, cur_x, cur_y);
            }
        }

    }
    //todo: 判断是否可以进入
    public boolean enter(int x, int y, int threshold){
        int sum = 0;
        while (x != 0){
            sum += x % 10;
            x /= 10;
        }
        while (y != 0){
            sum += y % 10;
            y /= 10;
        }
        return sum <= threshold ;
    }

    public static void main(String[] args) {
        movingCount movingCount = new movingCount();
        movingCount.movingCount(10,1,100); //29
        System.out.println(movingCount.sum);
    }
}
