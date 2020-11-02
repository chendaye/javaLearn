package Algorithm.Interview.LeetCode.RecursiveBacktrack;

import Utils.Dump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations {
    List<List<Integer>>  list = null;

    /**
     * 因为ArrayList和HashMap的Iterator都是上面所说的“fail-fast Iterator”，
     * Iterator在获取下一个元素，删除元素时，都会比较expectedModCount和modCount，不一致就会抛出异常。
     *
     * 所以当使用Iterator遍历元素(for-each遍历底层实现也是Iterator)时，
     * 需要删除元素，一定需要使用 Iterator的remove()方法 来删除，
     * 而不是直接调用ArrayList或HashMap自身的remove()方法,
     * 否则会导致Iterator中的expectedModCount没有及时更新，
     * 之后获取下一个元素或者删除元素时，expectedModCount和modCount不一致，
     * 然后抛出ConcurrentModificationException异常。
     *
     * https://zhuanlan.zhihu.com/p/101532181
     * @param args
     *
     * 类似： 47
     */
    public static void main(String[] args) {
        int[] nums = {2,1,3};
        permute(nums);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result= new ArrayList<List<Integer>>();

        int[] visited = new int[nums.length];
        fillNums2(result,new ArrayList<Integer>(),nums, visited, 0);
//        fillNums(result,new ArrayList<Integer>(),nums);
        Iterator<List<Integer>> iterator = result.iterator();
        while (iterator.hasNext()){
            Dump.iterator(iterator.next());
        }
        return result;
    }

    /**
     * todo:基于当前的已经找到的部分排列， 通过循环找到剩下的排列
     * @param result 全局结果Collection
     * @param nums 排列数字
     */
    public static void fillNums(List<List<Integer>> result,List<Integer> path,int[] nums)
    {
        if(path.size()==nums.length) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(path);
            result.add(temp);
            return;
        }
        for(int j=0;j<nums.length;j++) {
            if(path.contains(nums[j])) continue;
            path.add(nums[j]);
            fillNums(result,path,nums); //todo: 已经得到了一个子结果，回溯
            path.remove(path.size()-1);
        }
    }

    public static void fillNums2(List<List<Integer>> result,List<Integer> path,int[] nums, int[] visited, int start)
    {
        if(path.size()==nums.length) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(path);
            result.add(temp);
            return;
        }
        visited[start] = 1;
        for(int j=0;j<nums.length;j++) {
            if(visited[j] == 0){
                path.add(nums[j]);
                fillNums2(result,path,nums, visited, j); //todo: 已经得到了一个子结果，回溯
                path.remove(path.size()-1);
            }

        }
        visited[start] = 0;
    }



}
