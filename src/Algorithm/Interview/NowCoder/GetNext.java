package Algorithm.Interview.NowCoder;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * todo: 三种情况
     *      - pNode 有右子树 -> 右子树最左节点
     *      - pNode 无右子树 & 为 父节点 P 左子树 -> 最近的父节点
     *      - pNode 无右子树 & 为 父节点 P 右子树 -> 最近的一个父节点p1，使得 pNode所在树 为 p1 左子树
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null) return null;
        TreeLinkNode tmp = pNode.right;
        //todo: 右子树 存在--> 右子树的最左节点
        if (tmp != null){
            while (tmp.left != null)
                tmp = tmp.left;
            return tmp;
        }
        //todo: 右子树不存在
        while(pNode.next != null){
            if(pNode.next.left == pNode) //todo: PNode 为 左子树
                return pNode.next;
            else
                pNode = pNode.next; //todo: PNode 为右子树（要一直往上找 到一个父节点 P，使得 pNode = P的左子树的最右边节点）
        }
        return null;

    }
}
