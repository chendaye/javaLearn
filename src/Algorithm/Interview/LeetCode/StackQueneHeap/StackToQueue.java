package Algorithm.Interview.LeetCode.StackQueneHeap;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型
 */
class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        //todo:把 stack1 的元素 挪到 stack2
        while (!stack1.isEmpty()){
            Integer tmp = stack1.pop();
            stack2.push(tmp);
        }

        //todo:把新的元素放到 stack1
        stack1.push(node);

        //todo: 把stack2的元素在移回stack1
        while (!stack2.isEmpty()){
            Integer tmp = stack2.pop();
            stack1.push(tmp);
        }
    }

    public int pop() {
        // todo: stack1 中的顺序和 队列元素顺序一致
        return stack1.pop();
    }
}

public class StackToQueue {
    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);

        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }
}
