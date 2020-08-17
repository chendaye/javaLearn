# 常用单词

- Integer
- iterator
- import java.util.* 
- Comparator compare
- PriorityQueue
- array.length  string.length()

# 冷知识

# compare

> 排序其实是由三个数字同时决定的

**升序排列：同时满足下面三个**
```
o1 < o2 : return -1
o1 = o2 : return 0 //或者 1效果是一样的；-1相同元素会发生位置调整
o1 > o2 : return 1

# 等价于
o1 - o2
```

**降序排列：同时满足下面三个**
```
o1 < o2 : return 1
o1 = o2 : return 0 //或者 1效果是一样的；-1相同元素会发生位置调整
o1 > o2 : return -1

# 等价于
o2 - o1
```

**倒序**

```
return -1
```

**不改变顺序**

```
return 0或者1
```


