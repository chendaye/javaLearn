package Grammar.Collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * - add() 方法的参数类型是Object ，以便接受任意类型的对象
 * - 集合中存储的都是对象额引用(地址)
 */
class CollectionCode {
    public static void dump(Object obj){
        System.out.println(obj);
    }
    public static void main(String[] args) {
        method_iterator();
    }

    /**
     * iterator
     * 什么是迭代器：去除集合元素的方式
     */
    public static void method_iterator(){
        ArrayList a1  = new ArrayList();
        //添加数据
        a1.add("aaa");
        a1.add("bbb");
        a1.add("ccc");

        //todo: 集合 a1 通过iterator 方法 获取了一个 Iterator 接口的子对象
        Iterator iterator = a1.iterator(); // 获取迭代器

        while (iterator.hasNext()){
            dump(iterator.next());
        }
        for (Iterator it = a1.iterator(); it.hasNext(); ){
            dump(it.next());
        }
    }

    /**
     * 取交集
     */
    public static void method_retainAll(){
        ArrayList a1  = new ArrayList();
        //添加数据
        a1.add("aaa");
        a1.add("bbb");
        a1.add("ccc");

        ArrayList a2  = new ArrayList();
        //添加数据
        a2.add("aaa");
        a2.add("bbb");
        a2.add("ddd");

        a1.retainAll(a2);
        dump(a1);
    }

    /**
     * collection 的基本操作
     */
    public static void base_learn(){
        //todo:创建一个集合容器，使用Collection 接口的子类 ArrayList
        ArrayList al  = new ArrayList();
        //添加数据
        al.add("aaa");
        al.add("bbb");
        al.add("ccc");

        //删除元素
        al.remove("aaa");
        //清空
//        al.clear();

        //判断是否存在
        dump("包含---"+al.contains("bbb"));
        // 是否为空
        dump("isEmpty---"+al.isEmpty());

        //打印集合
        dump(al);
        dump(al.size());
    }



}
