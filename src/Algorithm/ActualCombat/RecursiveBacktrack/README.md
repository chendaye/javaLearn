# 回溯算法的分类

```python
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
        // 无特定返回条件
        if(start == path.size()){
            // 获取一个结果
            // .....
            return;
        }

        for (int i=start;i< path.size();i++){
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
