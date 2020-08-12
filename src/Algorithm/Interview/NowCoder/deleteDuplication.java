package Algorithm.Interview.NowCoder;

import java.util.HashMap;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class deleteDuplication {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }



    public static ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null) return null;
        HashMap<Integer, Integer> record = new HashMap<Integer, Integer>();
        ListNode p = pHead;
        while (p != null){
            if (record.containsKey(p.val)){
                record.put(p.val, record.get(p.val) + 1);
            }else {
                record.put(p.val, 1);
            }
            p = p.next;
        }
        ListNode h = new ListNode(0);
        h.next = pHead;
        ListNode p1 = h, p2 = pHead;
        while (p2 != null){
            if (record.get(p2.val) > 1){
                p1.next = p2.next;
                p2 = p2.next;
            }else {
                p1 = p2;
                p2 = p2.next;
            }
        }
        return h.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        int[] node = {0,1,2,3,3,4,4,5};

        for (int i = 0; i < node.length; i++){
            p.next = new ListNode(node[i]);
            p = p.next;
        }
        ListNode p1 = head;
        while (p1 != null){
            System.out.println(p1.val);
            p1 = p1.next;
        }

        ListNode h = deleteDuplication(head);

        System.out.println("++++++++++++++++++++++++");
        while (h != null){
            System.out.println(h.val);
            h = h.next;
        }

    }
}
