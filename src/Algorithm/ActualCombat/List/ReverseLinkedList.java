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
