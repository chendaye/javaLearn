package Algorithm.Interview.LeetCode.StackQueneHeap;

import Utils.Dump;
import Utils.Generate;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo: 思路
 *      - 先遍历，统计频率;对频率排序，找到前n个
 *      - 使用长度为 k 的优先队列，如果遍历到的元素比 队列中的最小频率要高，那么把最小的出队，当前元素入队，直到遍历结束
 *
 *
 * todo: 类似 23
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] array = Generate.get(10, 3, false, false);
        Dump.array(array,true);
        List<Integer> solution = Solution(array, 2);
        Dump.iterator(solution);
    }

    /**
     * todo:使用优先队列，队列要定义比较器
     *
     * todo:时间复杂度：
     *          - 以为优先队列底层是 小根堆，调整一次 时间复杂度 logk
     *          - 有n个元素，调整n次 所以时间复杂度 O(nlogk)
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> Solution(int[] nums, int k) {
        if (k <=0)
            return null;
        LinkedList<Integer> list = new LinkedList<>();
        //todo:<频率，节点>
        HashMap<Integer, Integer> record = new HashMap<>();
        //todo:统计频率
        for (int i=0;i<nums.length;i++){
            if (record.containsKey(nums[i])){
                record.put(nums[i], record.get(nums[i]) +1);
            }else {
                record.put(nums[i], 1);
            }
        }

        if (k > record.size())
            throw new RuntimeException("k超过最大范围！");

        //todo:优先队列 保存 <频率，节点>； 默认是小根堆，出队的是最小的元素
        //todo:AbstractMap.SimpleEntry 没有实现 Comparable 接口，所以用 Comparator 匿名类实现比较器
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<AbstractMap.SimpleEntry<Integer, Integer>>() {
            @Override
            public int compare(AbstractMap.SimpleEntry<Integer, Integer> o1, AbstractMap.SimpleEntry<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        //todo:遍历频率集合
        Set<Map.Entry<Integer, Integer>> entries = record.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if (priorityQueue.size() < k){
                //todo: 队列大小小于k 就直接入队
                priorityQueue.add(new AbstractMap.SimpleEntry<Integer, Integer>(next.getValue(),next.getKey()));
            }else {
                //todo:队头出队 peek 获取队头元素
                AbstractMap.SimpleEntry<Integer, Integer> top = priorityQueue.peek();
                if (top.getKey() < next.getValue()){
                    //todo:删除对头元素
                    priorityQueue.remove();
                    //todo:把频率更大的入队
                    priorityQueue.add(new AbstractMap.SimpleEntry<Integer, Integer>(next.getValue(),next.getKey()));
                }
            }
        }

        //todo:遍历队列取出元素
        Iterator<AbstractMap.SimpleEntry<Integer, Integer>> iterator1 = priorityQueue.iterator();
        while (iterator1.hasNext()){
            list.add(iterator1.next().getValue());
        }
        return list;
    }
}
