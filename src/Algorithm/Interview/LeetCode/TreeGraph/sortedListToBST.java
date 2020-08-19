package Algorithm.Interview.LeetCode.TreeGraph;

public class sortedListToBST {
    class ListNode{
        int val;
        ListNode next = null;
        public ListNode(){}
        public ListNode(int val){
            this.val = val;
        }
        public ListNode(int val, ListNode node){
            this.val = val;
            next = node;
        }
    }

    class TreeNode{
        int val;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(){}
        public TreeNode(int val){
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * todo: 快慢指针
     *      - 平衡二叉树：左右子树个数尽量系统
     *      - 找到链表的中位数，中位数左右两边的节点 就是左右子树的节点（找中位数 用快慢指针法）
     *      - 再分治法
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    //todo: [left, mid) [mid.next, right)
    public TreeNode buildTree(ListNode left, ListNode right){
        if(left == right){ // todo: [left, right) 前开后闭 相等时区间为空
            return null;
        }
        ListNode mid = getMid(left, right);
        TreeNode node = new TreeNode(mid.val);
        node.left = buildTree(left, mid);
        node.right = buildTree(mid.next, right);
        return node;
    }

    //todo: 快慢指针获取中位数
    public ListNode getMid(ListNode left, ListNode right){
        ListNode slow = left, fast = left;
        while(fast != right && fast.next != right){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
