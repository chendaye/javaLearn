package Algorithm.Interview.NowCoder;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //todo: p q 双指针
    public ListNode FindKthToTail(ListNode head,int k) {
        if (k == 0) return null;
        if (head == null) return null;
        ListNode p = head;
        ListNode q = head;
        int len = 1;
        while (q.next != null && len < k){
            q = q.next;
            len++;
        }
        if (len < k) return null;
        while (q.next != null){
            q = q.next;
            p = p.next;
        }
        return p;
    }

    public static void main(String[] args) {

    }
}
