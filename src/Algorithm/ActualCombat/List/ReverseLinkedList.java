package Algorithm.ActualCombat.List;

import Algorithm.DataStructure.ListNode;
import Utils.Dump;
import Utils.Generate;

/**
 * 反转一个单链表。
 *
 * 头插法
 *
 * 类似： 92 86 328
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        int[] array = Generate.get(5, 12, true, true);
        for (int item:array){
            p.next = new ListNode(item);
            p = p.next;
        }
        ListNode q = listNode;
        while (q != null){
            Dump.dump(q.val);
            q = q.next;
        }
        Dump.dump("+++++++++++++++++++++");
        ListNode soluton = Soluton(listNode);
        while (soluton != null){
            Dump.dump(soluton.val);
            soluton = soluton.next;
        }
    }

    public static ListNode Soluton(ListNode head) {
        if(head == null)
            return null;
        ListNode p = new ListNode(0);
        ListNode q = head.next;
        p.next = head;
        while (q != null){
            head.next = q.next; //  把head后面的节点取下来
            q.next = p.next; // 取下来的节点，放到头节点后面
            p.next = q;
            q = head.next; // 保存head后面的节点 防止断链
        }
        return p.next;
    }

    public static ListNode method(ListNode a){
        ListNode pre,cur,nxt;
        pre = null; cur = a;
        while (cur != null){
            nxt = cur.next; //todo: 保存cur后面的节点，防止断开
            cur.next = pre; //todo: cur 拿到最前面
            pre = cur; //todo: 更新头节点
            cur = nxt; //todo: 下一个节点
        }
        return pre;
    }
}
