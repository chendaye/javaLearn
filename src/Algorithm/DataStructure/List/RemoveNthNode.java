package Algorithm.DataStructure.List;

import Algorithm.DataStructure.ListNode;
import Utils.Dump;
import Utils.Generate;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * todo:删除、交换等 需要知道前一个节点信息的情况 用 虚拟节点
 *
 * 类似：61    143 234
 */
public class RemoveNthNode {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        int[] array = Generate.get(10, 10, true, true);
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
        ListNode soluton = Solution(listNode,5);

        StringBuilder list2 = new StringBuilder();
        while (soluton != null){
            list2.append("->"+soluton.val);
            soluton = soluton.next;
        }
        Dump.dump(list2.toString());
    }

    /**
     * todo:两个指针 一个先走
     * @param head
     * @param n
     * @return
     */
    public static ListNode Solution(ListNode head, int n) {
        //todo: 因为要删除节点，就要保存删除节点前一个节点的信息
        //todo:进而出现了删除头节点的问题
        //todo:于是 建立一个虚拟头结点
        ListNode vm = new ListNode(0);
        vm.next = head;
        ListNode p = vm;
        ListNode q = vm;
        int len = 0;
        while (len < n && q !=null){
            q=q.next;
            len++;
        }

        if (len < n)
            return null;

        while (q.next != null){
            q=q.next;
            p=p.next;
        }
        p.next = p.next.next; // 删除
        return vm.next;
    }
}
