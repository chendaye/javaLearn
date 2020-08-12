package Algorithm.DataStructure.List;

import Algorithm.DataStructure.ListNode;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteNode {
    public static void main(String[] args) {

    }

    /**
     * todo:思路： 题目只给出了 要被删除的一个节点
     *              没有给出整个链表，没发拿到药删除节点的前一个节点
     *              所以一般的改变指针指向的删除方式不行
     * todo: 把要删除节点的下一个节点值 赋值给要删除的节点 同时把 要删除的节点的值赋值给下一个节点
     *          也就是交换 要删除节点与下一个节点的值，然后删除下一个节点，这里是一个值的操作
     *
     * todo: 一般链表的操作都是操作指针。 不过特殊情况也有操作值
     * @param node
     */
    public static void Solution(ListNode node){
        int tmp = node.val;
        node.val = node.next.val;
        node.next.val = tmp;
        node.next = node.next.next;
    }
}
