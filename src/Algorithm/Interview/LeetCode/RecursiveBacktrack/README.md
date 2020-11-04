# 回溯算法的分类

```python
# 线性（多叉树的遍历）
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择

```


```python
# 矩阵
def backtrack(路径, 方向列表):
    todo: 处理当前点
    if 满足结束条件:
        todo：...
        return
    visited[当前点] = 1；
    for 选择 in 方向列表:
        backtrack(路径, 方向列表)
        todo：...
    visited[当前点] = 0；


```


> 多叉树遍历

```python
void traverse(TreeNode root) {
    for (TreeNode child : root.childern)
        // 前序遍历需要的操作
        traverse(child);
        // 后序遍历需要的操作
}
```

> 回溯算法 是决策时遍历，决策树也是一颗多叉树

- 路径
    - 在遍历决策节点孩子之前，会判断路径是否满足标准；添加满足标准的路径时，新建一个路径引用
- 选择列表（关键：不能重复选择）
    - 通过路径包含遍历选择（选择列表 = 路径(visited)&选择）
    - 通过index定位已经选择的位置
    

# 组合型回溯


> 场景 从集合中选取所有可能组合

- 用 index+选择集合 确定选择列表
- 退出条件 index > 选择.length()

```python
public static void Find2(int n, int k, ArrayList<Integer> path, List<List<Integer>> res, int start){
        // 有特定返回条件
        if (cur.size() == k){
            ArrayList<Integer> tmp = new ArrayList<Integer>();  //todo:以当前组合为基础 初始化一个新的组合
            tmp.addAll(path);
            res.add(cur);
            return;
        }
        // 无特定返回条件 组合结果一定以 n-1结尾
        if(start == n){
            // 获取一个结果
            // .....
            return;
        }

        for (int i=start;i< n;i++){
            path.add(i);  // 组合新增一个值
            Find(n, k, path, res,i+1);
            path.remove(path.size() - 1);
        }
    }
```

# 排列型回溯

> 所有可能的排列

```path
public static void fillNums(List<List<Integer>> result,List<Integer> path,int[] nums,HashMap<Integer, Boolean> visit)
    {
        if(temp.size()==nums.length) {
            List<Integer> tempPro = new ArrayList<Integer>(temp);
            result.add(tempPro);
            return;
        }
        for(int j=0;j<nums.length;j++) {
            if(!path.contains(nums[j])){
                path.add(nums[j]);
                fillNums(result,path,nums,visit); //todo: 已经得到了一个子结果，回溯
                path.remove(path.size() - 1);
            }
        }
```

# visited[] 型

> 对于二维表格
> 每一个方格都要进行一次回溯。 用回溯来找是否有匹配的路径
> 或者是多叉树的遍历
> 使用访问数组进行回溯；回溯的点事 在当前方格 遍历分叉 的前后位置；遍历分叉就是把当前点的所有
> 方向都尝试一遍；也就是 考察经过当前点的路径是否符合；自然，也就对当前点进行回溯

```python
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

```