package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Utils.Dump;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class combinationSum {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = null;
        res = combinationSum(new int[]{2,3,5}, 8);
        for (ArrayList<Integer> list : res){
            Dump.iterator(list);
        }
    }

    public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        back(res, path, 0, candidates, target);

        return res;
    }

    public static void back(ArrayList<ArrayList<Integer>> res,ArrayList<Integer> path, int start, int[] candidates, int  target){
        if (target < 0) return;
        if (target == 0){
            ArrayList<Integer> tmp = new ArrayList<>(path);
            res.add(tmp);
            return;
        }
        for (int i = start; i < candidates.length; i++){
            path.add(candidates[i]);
            target -= candidates[i];
            back(res, path,i, candidates,target);
            path.remove(path.size() - 1);
            target += candidates[i];
        }
    }

    public static void back2(ArrayList<ArrayList<Integer>> res,ArrayList<Integer> path,  int[] candidates, int  target){
        if (target < 0) return;
        if (target == 0){
            ArrayList<Integer> tmp = new ArrayList<>(path);
            res.add(tmp);
            return;
        }
        for (int i : candidates){
            path.add(i);
            target -= i;
            back2(res, path,candidates,target);
            path.remove(path.size() - 1);
            target += i;
        }
    }
}
