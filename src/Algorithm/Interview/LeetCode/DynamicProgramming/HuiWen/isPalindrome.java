package Algorithm.Interview.LeetCode.DynamicProgramming.HuiWen;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 */
public class isPalindrome {
    static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val){
            this.val = val;
        }
    }


    //todo: 常规逆置链表
    public static ListNode reverse1(ListNode head){
        ListNode pre = null, cur = head, next; //todo: pre -> cur=head -> next
        while (cur != null){
            next = cur.next; // 保留后继节点
            cur.next = pre; // 逆置
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //todo: 递归逆置链表
    public static ListNode reverse2(ListNode head){
        //todo: base case
        if (head.next == null) return head;
        ListNode listNode = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    //todo: 倒序遍历链表
    public static void traverse(ListNode list){
        if (list == null) return; //todo: base case
        traverse(list.next); //todo: 放入递归栈中
        System.out.println(list.val); //todo: 取栈顶
    }

    static ListNode left = null;
    public static boolean isPalindrome(ListNode head) {
        left = head;
        return traverse2(head);
    }
    public static boolean traverse2(ListNode list){
        if (list == null) return true;
        boolean res = traverse2(list.next);
        res = res && left.val == list.val;
        left = left.next;
        return res;
    }




    public static void main(String[] args){
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);

//        while (list != null){
//            System.out.println("----"+list.val);
//            list = list.next;
//        }
        traverse(list);

        ListNode reverse = reverse2(list);
        while (reverse != null){
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
    }
}
