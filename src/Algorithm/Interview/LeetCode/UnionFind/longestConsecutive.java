package Algorithm.Interview.LeetCode.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class longestConsecutive {
    /**
     * todo: 使用 hash 表记录 集合中的每一个数字，方便在 O(1) 的时间范围内判断
     *          - 判断有没有 n - 1
     *          - hashset 去重
     * @param nums
     * @return
     */
    public int longestConsecutive1(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : nums)
            hashSet.add(i); // todo: 用 HashSet 记录并且去重
        int max = 0, cur;
        for (Integer n : hashSet){
            int len = 0;
            // todo: 只考虑 比 n 大的数； 因为如果存在 n - 1,在遍历中也一定会找到
            if (!hashSet.contains(n - 1)){
                cur = n;
                len = 1;
                while (hashSet.contains(cur + 1)){
                    len++;
                    cur++;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

    //todo: 整个map 看成一个 组（查并集）
    public int find(int x, Map<Integer,Integer> map) {
        return map.containsKey(x) ? find(x+1, map) : x;
    }
    public int longestConsecutive2(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int max=0;
        for (int i:nums) {
            if (!map.containsKey(i - 1)) {
                max=Math.max(max,find(i+1 , map)-i);
            }
        }
        return max;
    }

    //todo： 并查集
    class unionFind {
        private int count = 0;
        private int[] parent; // 自己指向自己 key(节点) => value(节点所属的组)
        private int[] level; // 每一个组的深度
        public unionFind(int count){
            this.count = count;
            parent = new int[count];
            level = new int[count];
            for (int i = 0; i < count; i++){
                parent[i] = i; // 自己指向自己
                level[i] = 1; // 深度1
            }
        }

        // 找集合号
        public int find(int p){
            assert p >= 0 && p < count;
            while (parent[p] != p){
                parent[p] = parent[parent[p]]; // todo: 路径压缩 p 指向 p爷爷
                p = parent[p];
            }
            return p;
        }

        // 是否连接
        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }

        // 合并集合
        public void union(int p, int q){
            int top_p = find(p);
            int top_q = find(q);
            if (top_p == top_q) return;

            if (level[top_p] < level[top_q]){
                parent[top_p] = top_q; // 矮的接到高的上
            }else if(level[top_p]  > level[top_q]){
                parent[top_q] = top_p;
            }else {
                parent[top_q] = top_p;
                level[top_p] += 1; // 不是严格意义的层数
            }
        }
    }
}
