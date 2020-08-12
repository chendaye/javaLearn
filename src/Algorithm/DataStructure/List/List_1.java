package Algorithm.DataStructure.List;


import Algorithm.DataStructure.ListNode;

import java.util.ArrayList;


public class List_1 {
    public static void main(String[] args) {
        ListNode p = new ListNode(0);
        for(int i =1; i < 10; i++){
            ListNode tmp = new ListNode(0);;
            tmp.val = i;
            tmp.next = p.next;
            p.next = tmp;
        }
        p = p.next;

//        while(p != null){
//            System.out.println(p.val);
//            p=p.next;
//        }
        ArrayList<Integer> integers = printListFromTailToHead(p);

        for (Integer integer : integers) {
            System.out.println(integer);
        }

    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode p = new ListNode(0);
        ListNode q;
        if(listNode == null){
            System.out.println("NULL");
        }
        while(listNode != null){
            q = listNode.next;
            listNode.next = p.next;
            p.next = listNode;
            listNode = q;
        }
        p=p.next;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(p != null){
            list.add(p.val);
            p=p.next;
        }

        return list;
    }

}
