package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Utils.Dump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class combinationSum2 {
    public static void main(String[] args) {
        List<List<Integer>> res = null;
        res = combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for (List<Integer> list : res){
            Dump.iterator(list);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        int[] visited = new int[candidates.length];
        Arrays.sort(candidates); //todo: 先排序,方便去重
        back(res, path, visited,  0, candidates, target);

        return res;
    }

    public static void back(List<List<Integer>> res, ArrayList<Integer> path, int[] visited, int start, int[] candidates, int target){
        if (target < 0) return;
        if (target == 0){
            ArrayList<Integer> tmp = new ArrayList<>(path);
            res.add(tmp);
            return;
        }
        for (int i = start; i < candidates.length; i++){
            //todo:要去重的是“同一树层上的使用过”，如果判断同一树层上元素（相同的元素）是否使用过了呢。
            // 如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前一个树枝，
            // 使用了candidates[i - 1]，也就是说同一树层使用过candidates[i - 1]。
            if (visited[i] == 1 || i >= 1 && candidates[i] == candidates[i - 1] && visited[i - 1] == 0) continue;
            path.add(candidates[i]);
            target -= candidates[i];
            visited[i] = 1;
            back(res, path, visited, i, candidates,target);
            path.remove(path.size() - 1);
            target += candidates[i];
            visited[i] = 0;

        }
    }
}
