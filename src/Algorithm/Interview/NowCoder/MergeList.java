package Algorithm.Interview.NowCoder;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class MergeList {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {

    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode next = head;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                next.next = list1;
                list1 = list1.next;
            }else {
                next.next = list2;
                list2 = list2.next;
            }
            next = next.next;
        }

        while (list1 != null){
            next.next = list1;
            list1 = list1.next;
            next = next.next;
        }
        while (list2 != null){
            next.next = list2;
            list2 = list2.next;
            next = next.next;
        }
        return head.next;
    }
}
