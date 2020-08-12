package Algorithm.Interview.NowCoder;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList {

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * todo:
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;
        ListNode listNode = new ListNode(0); // 头结点
        ListNode p = head;
        ListNode q = head.next;
        if (q == null) return head;
        while (p != null){
            p.next = listNode.next;
            listNode.next = p;
            p = q;
            if (q != null)q = q.next;
        }
        return listNode.next;
    }

    /**
     * todo: 原地逆置
     *          - 设置 pre 指向 新链表的头
     *          - 设置 next 指向就链表的头
     *          - head 作为中间节点
     */
    public ListNode ReverseList2(ListNode head) {
        if(head==null) return null;
        ListNode pre = null; //todo: 新链表的头
        ListNode next = null; //todo: 旧链表的头
        while(head!=null){
            next = head.next; // 保持不断链
            head.next = pre; // 指向新链表
            pre = head; // 更新新链表的头
            head = next; // 处理下一个节点
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode tmp = listNode;
        for (int i = 2; i < 6; i++){
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        ReverseList reverseList = new ReverseList();
        ListNode tmp1 = listNode;
        while (tmp1 != null){
            System.out.println(tmp1.val);
            tmp1 = tmp1.next;
        }
        ListNode list = reverseList.ReverseList2(listNode);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (list != null){
            System.out.println(list.val);
            list = list.next;
        }
    }
}
