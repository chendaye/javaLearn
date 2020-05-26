package Algorithm.ActualCombat.RecursiveBacktrack;

import Utils.Dump;

import java.util.*;

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
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            Dump.iterator(integers);

            iterator.remove();
            Dump.dump("++++++++++++++++++++++");
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result= new ArrayList<List<Integer>>();
        HashMap<Integer, Boolean> visit = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            visit.put(nums[i], false);
        }
        fillNums(result,new ArrayList<Integer>(),nums,visit);
        return result;
    }

    /**
     * todo:基于当前的已经找到的部分排列， 通过循环找到剩下的排列
     * @param result 全局结果Collection
     * @param temp  一个正在生成的排列
     * @param nums 排列数字
     * @param visit 访问数组
     */
    public static void fillNums(List<List<Integer>> result,List<Integer> temp,int[] nums,HashMap<Integer, Boolean> visit)
    {

        if(temp.size()==nums.length) {
            // d生成了一个排列
            result.add(temp);
            return;
        }
        //todo: 组合 start 向后移动 到最后一个数字
        //todo: 排列每次都要扫描全部数字， 结合访问数组
        for(int j=0;j<nums.length;j++) {
            //todo: 以当前排列为基础，初始化一个新的排列
            //todo: temp 要重复使用
            List<Integer> tempPro = new ArrayList<Integer>();
            tempPro.addAll(temp);
            //todo: contains 一一比较，时间复杂度O(n)
//            if(!tempPro.contains(nums[j]))
            if(!visit.get(nums[j]))
            {
                tempPro.add(nums[j]);
                visit.put(nums[j],true); // 一个元素已经用过
                fillNums(result,tempPro,nums,visit); //todo: 已经得到了一个子结果，回溯
                visit.put(nums[j],false); // 一个排列生成 visit恢复
            }
        }
    }

}
