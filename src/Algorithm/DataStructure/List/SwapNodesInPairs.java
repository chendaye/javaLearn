package Algorithm.DataStructure.List;

import Algorithm.DataStructure.ListNode;
import Utils.Dump;
import Utils.Generate;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 类似：25 147 148
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        int[] array = Generate.get(5, 12, true, true);
        for (int item:array){
            p.next = new ListNode(item);
            p = p.next;
        }
        listNode = listNode.next;
        ListNode q = listNode;
        StringBuilder list = new StringBuilder();
        while (q != null){
            list.append("->"+q.val);
            q = q.next;
        }
        Dump.dump(list.toString().substring(2));
        Dump.dump("+++++++++++++++++++++");
        ListNode soluton = Solution(listNode);

        StringBuilder list2 = new StringBuilder();
        while (soluton != null){
            list2.append("->"+soluton.val);
            soluton = soluton.next;
        }
        Dump.dump(list2.toString().substring(2));

    }

    /**
     * todo:链表问题无非是 画图 多设置几个指针,保存要操作的节点
     * @param head
     * @return
     */
    public static ListNode Solution(ListNode head) {
        ListNode vm = new ListNode(0);  // 虚拟头指针,始终在头部
        vm.next = head;
        ListNode p = vm;
        while (p.next != null && p.next.next != null){
            //todo:在循环内部定义节点，而不是在循环外部，这个很精髓
            ListNode node1 = p.next;
            ListNode node2 = node1.next;

            // todo:交换
            node1.next = node2.next;
            node2.next = node1;
            p.next = node2;
            //todo: p 后移2位
            p = node1;
        }
        return vm.next;
    }
}
