package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.util.*;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 类似：39 40 216 18 90 401
 *
 *
 * todo: 从集合中选择 子集 ====》 回溯法
 *
 * todo: 排列与组合的区别在于
 *          - 排列问题每次通过 contains ⽅法来排除在 track 中已经选择过的数字
 *          - 组合问题通过传⼊⼀个 start 参数，来排除 start 索引之前的数字。
 */
public class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);

        Iterator<List<Integer>> iterator = combine.iterator();

        while (iterator.hasNext()){
            Dump.iterator(iterator.next());
        }
    }

    /**
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n<=0 || k<= 0 || k>n)
            return res;
        Find(n, k, new ArrayList<Integer>(), res, 1);
        return res;
    }

    /**
     * todo:基于当前的已经找到的部分组合， 通过循环找到剩下的组合
     * @param n
     * @param k
     * @param cur 正在生成的一个组合
     * @param res 保存结果
     * @param start start之前的数字都已经组合过了
     *
     *todo: 组合比排列多了一个 游标 start， 每次循环从游标开始（树 一层一层找）
     *
     *  todo: cur 是已经选择 进组合的数字
     *        接下来就是要 从剩余的部分（start开始） 选择下一个组合数
     *        用一个循环，遍历剩下的数字
     *        每一次循环都新建一个容器 curPro 并且 初始化为 cur（一个新的组合）\
     *
     *
     * todo: 优化--回溯法剪枝，去掉不必要的循环
     * todo: 还有 k-cur.size() 个位置需要填补
     *  也就是 [i....n] 中至少要有 k-cur.size() 个元素
     *  n-i+1 >= k-cur.size()
     *  i <= n+1-k+cur.size()
     */
    public static void Find(int n, int k, ArrayList<Integer> cur,List<List<Integer>> res, int start){
        if (cur.size() == k){
            //todo: 加入结果集，要新建一个集合；因为 path在后续的回溯会变化
            ArrayList<Integer> curPro = new ArrayList<Integer>();  //todo:以当前组合为基础 初始化一个新的组合
            curPro.addAll(cur);
            res.add(curPro);
            return;
        }
        //todo: 回溯部分 用 start 来保证不会重复访问 组合事回溯 是不需要回头的
        for (int i=start;i<=n;i++){
//      for (int i=start;i<=n+1-k+cur.size();i++){
            cur.add(i);  // 组合新增一个值
            Find(n, k, cur, res,i+1);
            cur.remove(cur.size() - 1);
        }
    }


    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     *
     * 示例 1：
     *
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     *  
     *
     *
     * 提示：
     *
     * 1 <= k <= len(nums) <= 16
     * 0 < nums[i] < 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static class canPartitionKSubsets {
        public static void main(String[] args) {
            Integer[] nums = {4, 3, 2, 3, 5, 2, 1};
            canPartitionKSubsets(nums, 4);
        }

        /**
         * todo: 首先是否可以被 k 整除
         *      -- 假设 sum/k = n
         *      -- 不能有 > n 的数： 先排序
         * @param nums
         * @return
         */
        public static boolean canPartitionKSubsets(Integer[] nums, int k){
            Arrays.sort(nums, new Comparator<Integer>(){
                @Override
                public int compare(Integer a, Integer b){
                    return b - a;
                }
            });

    //        Arrays.sort(nums, (a, b) -> {
    //            return a - b;
    //        });

            int sum = 0;
            for (int n : nums){
                sum += n;
            }
            if (sum % k != 0) return false;
            int segment = sum / k;
            if (nums[0] > segment) return false;

            boolean[] used = new boolean[nums.length];
            return helper(nums, k, segment, used, 0, 0);

        }

        /**
         * todo： 回溯法
         *      - 功能定义： 把 nums 等分成 k 段
         *
         * 注意到这个时候的递归函数backtracking是有返回值的，为什么有的递归函数没有返回值，有的需要返回值呢？
         * 因为本题只要找到一个结果就行了，需要在找到这个结果的时候就立刻返回，也就是本题的
         * if (backtracking(nums, k, target, cur+nums[i], i+1, used)) return true;
         * 如果需要找到所有的结果（所有的划分），那么这个时候的backtracking( )不需要返回值，
         * 这个时候添加一个参数ans（一个容器），用来装全部的结果
         *
         * @param nums
         * @param k
         * @param target
         * @param used
         * @param start
         * @param current
         * @return
         */
        public static boolean helper(Integer[] nums, int k, int target, boolean[] used,int start,int current){
            if (k == 0) return true;
            if (current == target){
                // todo: 找到一个集合，拆解剩下的部分； 把 nums(剩余used部分) 拆成 k-1 段
                return helper(nums, k - 1, target, used, 0, 0);
            }

            // start 用来确定 每一条路径 不走回头路
            // used  每一轮消耗掉的元素
            for (int i = start; i < nums.length; i++){
                if (!used[i] && nums[i] <= target - current){
                    used[i] = true;
                    if(helper(nums, k, target, used, i + 1, current + nums[i])) return true;
                    used[i] = false;
                }
            }
            return false;
        }
    }
}
