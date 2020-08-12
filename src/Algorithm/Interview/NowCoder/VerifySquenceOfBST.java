package Algorithm.Interview.NowCoder;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST {
    /**
     * todo: 定义： 左孩子 < 根结点 < 右孩子  左子树或者右子树都是 BST
     *
     * todo: 思路
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;
        return  helper(sequence, 0, sequence.length - 1);
    }

    // todo: 对于一个子树来说，根总是在最后。 从 根节点开始递归判断  左孩子 < 根结点 < 右孩子 是否成立
    private boolean helper(int[] sequence, int l, int r){
        if (l >= r) return true;
        int i = l;
        for (; i < r; i++){
            if (sequence[i] > sequence[r])
                break;
        }
        if (i == r - 1) return true;
        for (int j = i; j < r; j++){
            if (sequence[j] < sequence[r])
                return false;
        }
        return helper(sequence, l, i - 1) && helper(sequence, i, r - 1);
    }

    public static void main(String[] args) {
        VerifySquenceOfBST verifySquenceOfBST = new VerifySquenceOfBST();
        int[] sequence = {1,5,4,7,10,9,6};
        System.out.println(verifySquenceOfBST.VerifySquenceOfBST(sequence));
    }
}
