package Algorithm.ActualCombat.RecursiveBacktrack;

import Utils.Dump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            System.out.println("++++++++++++++++++++++++++++");
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
     *        每一次循环都新建一个容器 curPro 并且 初始化为 cur（一个新的组合）
     *
     */
    public static void Find(int n, int k, ArrayList<Integer> cur,List<List<Integer>> res, int start){
        if (cur.size() == k){
            res.add(cur);
            return;
        }
        for (int i=start;i<=n;i++){
            ArrayList<Integer> curPro = new ArrayList<Integer>();  //todo:以当前组合为基础 初始化一个新的组合
            curPro.addAll(cur);
            curPro.add(i);  // 组合新增一个值
            Find(n, k, curPro, res,i+1);
            curPro.remove(curPro.size() - 1);
        }
    }


    /**
     * todo: 优化--回溯法剪枝，去掉不必要的循环
     * @param n
     * @param k
     * @param cur
     * @param res
     * @param start
     *
     * todo: 还有 k-cur.size() 个位置需要填补
     *  也就是 [i....n] 中至少要有 k-cur.size() 个元素
     *  n-i+1 >= k-cur.size()
     *  i <= n+1-k+cur.size()
     */
    public static void Find2(int n, int k, ArrayList<Integer> cur,List<List<Integer>> res, int start){
        if (cur.size() == k){
            res.add(cur);
            return;
        }

        for (int i=start;i<=n+1-k+cur.size();i++){
            ArrayList<Integer> curPro = new ArrayList<Integer>();  //todo:以当前组合为基础 初始化一个新的组合
            curPro.addAll(cur);
            curPro.add(i);  // 组合新增一个值
            Find(n, k, curPro, res,i+1);
            curPro.remove(curPro.size() - 1);
        }
    }
}
