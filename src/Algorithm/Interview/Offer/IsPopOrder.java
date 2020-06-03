package Algorithm.Interview.Offer;

/**
 *输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 *
 *   在原序列中相对位置比它靠前的，也就是比它先入栈的，出栈顺序必须是逆序；
 * 　　在原序列中相对位置比它大的，也就是比它后入栈的，出栈顺序没有要求；
 * 　　以上两点可以间插进行
 */
public class IsPopOrder {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        return false;
    }

    public static void main(String[] args) {

    }
}
