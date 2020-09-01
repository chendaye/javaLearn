# 多叉树的遍历

```bash
void traverse(TreeNode root) {
    for (TreeNode child : root.childern)
        // 前序遍历需要的操作
        traverse(child);
        // 后序遍历需要的操作
}

```

# 树的层序遍历

```bash
void traverse(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()){
        TreeNode node = queue.offer();
        for(TreeNode n : node.chrild){
            queue.add(n);
        }
    }
}

```

# 图的深度优先

```bash
void dfs(List<List<Integer>> G, int[] visited, int v) {
    visited[v] = 1;
    List<Integer> list = G.get(room);
    for (Integer node : list){
        if(visited[node] == 0){
            dfs(G, visited, node);
        }
    }
}
```


# 图的广度优先(层序)

```bash

```