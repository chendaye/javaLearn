package Algorithm.DataStructure.List;
import Algorithm.DataStructure.ListNode;
import Utils.Dump;
import Utils.Generate;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * 类似：82 21
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        int[] array = Generate.get(5, 12, true, true);
        for (int item:array){
            p.next = new ListNode(item);
            p = p.next;
        }
        ListNode q = listNode;
//        while (q != null){
//            Dump.dump(q.val);
//            q = q.next;
//        }
        q.next = new ListNode(1);
        q = q.next;
        q.next = new ListNode(1);
        Dump.dump("+++++++++++++++++++++");
        ListNode soluton = Solution(listNode.next, 1);

        while (soluton != null){
            Dump.dump(soluton.val);
            soluton = soluton.next;
        }
    }

    /**
     * todo: 思路：
     *      - 建立一个虚拟的头结点
     *      - 先不处理头结点，循环结束之后在处理
     * @param head
     * @param val
     * @return
     */
    public static ListNode Solution(ListNode head, int val) {
        if(head == null)
            return null;
        //todo: 因为需要一个节点保存位置信息防止不断链，从第二个节点开始处理
        //todo: 因为第一个节点没有处理，所以最后要处理第一个节点。
        ListNode p = head;
        ListNode q = p.next;

        while (q!=null){
            if (q.val == val){
               q=q.next; // q不再指向 head
               p.next=q;
            }else {
                p=q;
                q=q.next;
            }
        }
        //todo:处理头节点
        if (head.val == val){
            head = head.next;
        }
        return head;

    }
}
