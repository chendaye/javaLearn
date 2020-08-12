package Algorithm.Interview.NowCoder;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * todo: 链表操作一定要注意空指针
 */
public class EntryNodeOfLoop {
    public class ListNode{
        int val;
        ListNode next = null;
        public ListNode(int val){

        }
    }
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null) return null;
        ListNode p1 = pHead, p2 = pHead;
        //todo:快慢指针第一次相遇
        do {
            if (p1 == null || p2 == null || p2.next == null || p2.next.next == null) return null;
            p1 = p1.next;
            p2 = p2.next.next;
        }while (p1 != p2);
        //todo: 相遇之时， p1 走了 k， p2 走了 2k，
        //todo: 因为在换上相遇，所以 p2 比 p1 正好多走一圈 => 环的周长 k
        //todo: 设相遇点 距离环的起点 m， 那么 p2 再走 k-m 正好到达起点
        //todo: p1 重新指向出发点， 因为 p1走了距离k 所以起点到相遇点的距离 = p1 走的距离 = k; 距离环起点 k-m
        //todo: p1 p2 以相同的速度在走 k - m，就在环的起点相遇
        p1 = pHead;
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
