package Algorithm.DataStructure.List;

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

    /**
     *todo: 维护好 pre cur nxt 的初始定义
     *      - pre 新链表的头
     *      - cur 当前要处理的节点
     *      - next 当前处理节点的洗衣柜节点，防止断链
     */
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



    /**
     * 递归翻转整个链表
     *
     * todo: reverse 函数定义是这样的：输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点
     * @param head
     * @return
     */
    ListNode reverse(ListNode head) {
        if (head.next == null) return head; // 只有一个节点直接返回
        ListNode last = reverse(head.next); // 反转 head 之后的所有节点，返回反转链表的起始节点
        head.next.next = head; // 剩下 head 节点还没有反转，把 head 放到最后 然后置空
        head.next = null;
        return last;
    }

    /**
     * todo: 反转以 head 为起点的 n 个节点，返回新的头结点
     */
    ListNode successor = null; // 后驱节点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    /**
     * todo: 反转任意区间
     * @param head
     * @param m
     * @param n
     * @return
     */
    ListNode reverseBetween(ListNode head, int m, int n) {
        // base case ：递归栈的最外层的一个节点
        if (m == 1) return reverseN(head, n);
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }





    /**
     * todo: 倒序输出链表
     *    - 链表也有先序 后续遍历
     *    - 树是链表的扩展
     *    - 递归的实质是，把链表的的节点按顺序压栈，然后出栈
     * @param head
     */
    public static void traverse(ListNode head){
       if (head == null) return;
       //todo: 先序
       traverse(head.next);
       //todo: 后序
       System.out.println(head.val);
    }


    /**
     * todo:通过后序遍历 判断链表是否是回文（双指针）
     *    - 也可以新建一个逆向的链表和原来的链表比较是否相同
     *
     *
     * todo: ⾸先，
     *       - 寻找回⽂串是从中间向两端扩展
     *       - 判断回⽂串是从两端向中间收缩。
     *          - 对于单链表，⽆法直接倒序遍历，可以造⼀条新的反转链表，
     *          - 可以利⽤链表的后序遍历
     *          - 也可以⽤栈结构倒序处理单链表
     * @param head
     * @return
     */
    ListNode left; //todo: 左侧指针
    public  boolean isPalindrome(ListNode head){
        left = head;
        return traverse2(head);
    }
    //todo: right 是一个右指针
    public  boolean traverse2(ListNode right){
        if (right == null) return true;
        boolean res = traverse2(right.next);  //todo:将链表节点放入递归栈中
        //todo: 后序
        res = res && (left.val == right.val);
        left = left.next;
        return  res;
    }
}
