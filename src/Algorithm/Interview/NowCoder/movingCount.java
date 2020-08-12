package Algorithm.Interview.NowCoder;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 *
 * 请问该机器人能够达到多少个格子？
 *
 * TODO：要点
 *      - rows = y ； cols = x
 *      - 尝试不同方向时越界，继续下一个方向
 *      - dfs 是进入函数先访问节点
 */
public class movingCount {
    int[][] dict = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //todo: 上 右 下 左
    int sum = 0;
    public int movingCount(int threshold, int rows, int cols)
    {
        if (threshold <= 0) return 0;
        int[][] visit = new int[rows][cols]; //todo: 访问标记 rows:行:y  cols:列:x
        DFS(visit, threshold, rows, cols,   0, 0);
        return sum;
    }

    /**
     *
     * todo: 标准的 DFS框架
     *void dfs(int v){
     *         visited[v]=true;
     *         componentId[v] = count;
     *         for (Integer next: G.adj(v))
     *             if (!visited[next])
     *                 dfs(next);
     *     }
     */
    public void DFS(int[][] visit, int threshold, int rows,  int cols,  int x, int y){
        //todo:先访问，后面再遍历（能进来的都是可以访问的）
        sum++;
        visit[y][x] = 1;
        //todo: 访问不同的方向
        for (int i = 0; i < 4; i++){
            int cur_x = x + dict[i][0];
            int cur_y = y + dict[i][1];
            //todo: 判断是否越界，有越界换一个方向
            if (cur_x >= cols || cur_x < 0 || cur_y >= rows || cur_y < 0) continue;
            //todo: 没有被访问过，且可以进入
            if (visit[cur_y][cur_x] == 0 && enter(cur_x, cur_y, threshold))
                DFS(visit, threshold, rows, cols, cur_x, cur_y);
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
//        movingCount.movingCount(5,10,10); //21
//        movingCount.movingCount(15,100,1); //79
//        movingCount.movingCount(15,1,1); //0
        System.out.println(movingCount.sum);
    }
}
