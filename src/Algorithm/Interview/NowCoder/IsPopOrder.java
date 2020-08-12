package Algorithm.Interview.NowCoder;

import java.util.Stack;

/**
 *输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 *
 *   比它先入栈的，出栈顺序必须是逆序；
 * 　比它后入栈的，出栈顺序没有要求；
 * 　　以上两点可以间插进行
 */
public class IsPopOrder {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>(); //todo: 用一个辅助栈模拟 进出
        int i = 0, j = 0;
        while (i < pushA.length){
            if (popA[j] != pushA[i]){
                //todo: 如果当前要进栈的元素， 与将要出栈的元素不相同； 压入辅助栈
                stack.push(pushA[i++]);
            }else {
                //todo: 如果当前要进栈的元素， 与将要出栈的元素相同； 压入辅助栈
                //todo: 然后 判断辅助栈的 顶部元素 与要出栈的元素是否相同，相同就出栈
                stack.push(pushA[i++]);
                while (j < popA.length && stack.peek() == popA[j]){
                    stack.pop();
                    j++;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        int[] pushA =  {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        IsPopOrder isPopOrder = new IsPopOrder();
        System.out.println(isPopOrder.IsPopOrder(pushA, popA));
    }
}
