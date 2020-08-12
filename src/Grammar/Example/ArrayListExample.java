package Grammar.Example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayList 去重
 */
public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(111);
        arrayList.add(111);
        arrayList.add(222);
        arrayList.add(222);
        arrayList.add(666);
        System.out.println(single(arrayList));
    }

    public static ArrayList single(ArrayList arr){
        ArrayList tmp = new ArrayList();
        Iterator iterator = arr.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if (!tmp.contains(next))
                tmp.add(next);
        }
        return tmp;
    }
}

/****************************************存自定义元素************************************************************/

/**
 * List 集合判断元素是否相同 依据是 equals()方法
 */
class ArrayListCode2{
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Person("a", 16));
        arrayList.add(new Person("b", 17));
        arrayList.add(new Person("b", 17));
        arrayList.add(new Person("c", 18));
        arrayList = single(arrayList);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            Person next = (Person)iterator.next(); //强行转换类型
            System.out.println(next.getName()+"..."+next.getAge());
        }

//        System.out.println(single(arrayList));
    }

    public static ArrayList single(ArrayList arr){
        ArrayList tmp = new ArrayList();
        Iterator iterator = arr.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if (!tmp.contains(next))
                tmp.add(next);
        }
        return tmp;
    }
}


class Person{
    private String name;
    private int age;
    Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    /**
     * List 判断元素是否相等 contains， 删除元素 remove 底层调用的是 元素.equals(Object obj) 方法。来判断元素是否相等
     * 自定义两个对象是否相等
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if (!(obj instanceof Person))
            return false;
        Person tmp = (Person)obj;
        return this.name.equals(tmp.name) && this.age == tmp.age;
    }
}