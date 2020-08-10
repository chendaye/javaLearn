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
public class test
{
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
        Find(n,  new ArrayList<Integer>(), res, 1);
        return res;
    }


    public static void Find(int n,  ArrayList<Integer> cur,List<List<Integer>> res, int start){
        if (n+1 == start){
            //todo:以当前组合为基础 初始化一个新的组合
            ArrayList<Integer> curPro = new ArrayList<Integer>(cur);
            res.add(curPro);
            return;
        }
        for (int i = start; i <= n; i++){
            cur.add(i);  // 组合新增一个值
            Find(n, cur, res,i+1);
            cur.remove(cur.size() - 1);
        }
    }


}
