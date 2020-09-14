package Java;

import java.util.*;




interface Car{
    void run();
}

class Car1 implements Car{
    public void run(){
        int[] a = new int[2];
        int b = a.length;
        String s = "aaa";
        s.length();
        System.out.println("Car1");
        /*ArrayList*/
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.contains(2);
        list.addAll(list2);
        list.remove(2);
        list.size();
        list.add(2);
        list.clear();

        /*LinkedList*/
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.size();
        linkedList.add(2);
        linkedList.addFirst(2);
        linkedList.addLast(2);
        linkedList.getLast();
        linkedList.getFirst();
        linkedList.contains(2);
        linkedList.clear();

        /*Stack*/
        Stack<Integer> stack = new Stack<>();
        stack.add(2);
        stack.push(2);
        stack.empty();
        stack.peek();
        stack.pop();
        stack.clear();
        stack.contains(2);

        /*HashMap*/
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(2, 3);
        hashMap.containsKey(2);
        hashMap.get(2);
        hashMap.clear();
        hashMap.isEmpty();
        hashMap.size();
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
    }
}

class Factory{
    public static Car getCar(String car){
        Car instance = null;
        if (car.equals("Car1")){
            return new Car1();
        }
        return instance;
    }
}