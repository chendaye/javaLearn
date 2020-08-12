package Grammar.Example;

import java.util.LinkedList;

/**
 * 用 LinkedList 模拟栈和队列
 */
public class LinkedListExample {
    public static void main(String[] args) {

    }
}


class Queue{
    private LinkedList link;

    Queue(){
        link = new LinkedList();
    }

    public void push(Object object){
        link.addFirst(object);
    }

    public Object pop(){
        return link.removeLast();
    }

    public boolean isEmpty(){
        return link.isEmpty();
    }
}