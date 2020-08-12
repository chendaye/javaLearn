package Algorithm.Interview.NowCoder;

/**
 * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，
 * 所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class FindFirstCommonNode {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * todo: 先求出长度，将链表对齐； 在同步遍历查找第一个相同的点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) return null;
        ListNode p1 = pHead1, p2 = pHead2;
        int len1 = 0, len2 = 0;
        while (p1 != null){
            len1++;
            p1 = p1.next;
        }
        while (p2 != null){
            len2++;
            p2 = p2.next;
        }
        if (len1 >= len2){
            int diff = len1 - len2;
            while (diff > 0){
                pHead1 = pHead1.next;
                diff--;
            }
        }else  {
            int diff = len2 - len1;
            while (diff > 0){
                pHead2 = pHead2.next;
                diff--;
            }
        }
        while (pHead1 != null && pHead2 != null){
            if (pHead1 == pHead2) return pHead1;
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }
}
