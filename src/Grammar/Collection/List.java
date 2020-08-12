package Grammar.Collection;

import java.util.*;

/**
 * Grammar.Collection
 *       - List:元素是有序的，元素可以重复，因为该集合体系有索引
 *              |--ArrayList: 底层使用的是 数组结构 特点：查询块，增删稍慢   线程不同步
 *              |--LinkedList： 底层使用的是链表结构  特点：增删块，查询快
 *              |--Vector： 底层是 数组结构，被ArrayList 替代了  特点： 线程同步
 *
 *             todo: List是可变长度的,Array 是不可变长度的，ArrayList中数组默认长度 10，超过10 则新建一个数组长度增加50%
 *                      把原先数据拷贝到新数组
 *       - Set：元素是无序的，元素不可重复
 *
 *  List：特有方法，凡是可以操作角标的方法都是该体系的方法
 *
 *  增加：
 *      - add(index,  element)
 *      - addAll(index, Grammar.Collection)
 *  删：
 *      - remove(index)
 *
 *  改：
 *      - set(index, element)
 *
 *  查：
 *      - get(index)
 *      - subList(from, to)
 *      - listIterator
 *
 *
 *  todo:列表集合特有的迭代器 ListIterator。 是Iterator 的子类
 *  在迭代时不可以通过集合对象的方法 操作集合中的元素。 会发生并发访问异常
 *
 *  所以，在迭代器迭代时，只能使用迭代器的方法操作元素，但是迭代器Iterator的方法有限
 *  只能对元素进行判断 取出 删除操作
 *  如果需要在迭代时对集合 进行 添加修改等操作，需要使用其子接口 ListIterator
 *  该接口只能通过  List集合的 listIterator 方法获得
 */
class ListCode {
    public static void dump(Object obj){
        System.out.println(obj);
    }
    public static void main(String[] args) {
        method_listIterator();
    }


    /**
     * 列表迭代器
     */
    public static void method_listIterator(){
        ArrayList arr = new ArrayList();
        arr.add("qwe");
        arr.add("asd");
        arr.add("fgh");

//        Iterator iterator = arr.iterator();
//        while (iterator.hasNext()){
//            Object next = iterator.next();
//
//            if (next.equals("asd")){
//                //todo: ConcurrentModificationException
//                //todo: iterator 和 add 都指向arr； 两者并发的操作（修改 添加 删除） arr，会导致并发操作异常
////                arr.add("111");
//
//                iterator.remove(); // 将 "qwe"的引用从集合中删除，不过在此之前 这个引用已经赋值给 next
//            }
//
//            dump(next);
//        }

        //在迭代过程中，添加或者删除元素
        ListIterator listIterator = arr.listIterator();
        while (listIterator.hasNext()){
            Object next = listIterator.next();
            if (next.equals("asd")){
//                listIterator.add("666");
                listIterator.set("000 ");
            }
        }

        //向前遍历
        while (listIterator.hasPrevious()){
            dump(listIterator.previous());
        }

        dump(arr);

    }

    /**
     * List 的基本用法
     */
    public static void method_list_base(){
        ArrayList arr = new ArrayList();
        arr.add("qwe");
        arr.add("asd");
        arr.add("fgh");

        //指定位置插入
        arr.add(1, "rrr");

        // 指定位置删除
        arr.remove(2);

        //修改元素
        arr.set(2, "chendaye666");

        //获取元素
        Object o = arr.get(2);

        dump(o);

        // 获取所有元素
        for (int x = 0; x < arr.size(); x++){
            dump(arr.get(x));
        }

        Iterator iterator = arr.iterator();

        while (iterator.hasNext())
            dump(iterator.next());


        //获取元素的下标
        int index = arr.indexOf("chendaye666");
        dump(index);

        // 获取子列表
        List list = arr.subList(1, 2);
        dump(list);
    }
}

/****************************************Vector*******************************************/

/**
 * 枚举是 Vector 特有的方式（早期版本中出现）
 *
 * 枚举和迭代是一样的， 枚举的名称和方法名称过长 被 iterator 取代了
 */
class VectorCode{
    public static void main(String[] args) {
        Vector vector = new Vector();

        vector.add("qqq");
        vector.add("www");
        vector.add("eee");

        // 枚举
        Enumeration elements = vector.elements();

        while (elements.hasMoreElements()){
            System.out.println(elements.nextElement());
        }
    }
}

/**************************************LinkedList***********************************************/

/**
 * LinkedList 的特有方法
 * - addFirst
 * - addLast
 *
 * - getFirst
 * - getLast
 * 获取元素，不删除
 *
 * - removeFirst
 * - removeLast
 * 删除元素，并返回. 如果集合中没有元素，会抛出异常
 *
 * JDK 1.6 之后又替代方法
 * offerFirst
 * offerLast
 *
 * peekFirst
 * peekLast
 *获取元素，不删除，没有返回 null
 *
 * pollFirst
 * pollLast
 * 删除元素并返回，没有返回null
 */
class LinkedListCode{
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        linkedList.add("qqq");
        linkedList.addFirst("www");
        linkedList.addLast("www");

        System.out.println(linkedList);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());

        while (!linkedList.isEmpty()){
            System.out.println(linkedList.removeFirst());
        }
    }
}