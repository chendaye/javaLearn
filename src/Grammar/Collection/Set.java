package Grammar.Collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Set元素是无序的 存入和取出的顺序不一致
 * 元素不重复
 *
 * Set集合的功能和Collection一样
 *
 * todo：Set
 *         - HashSet 底层数据结构哈希表。 对象比较：先比较hash值，hash值不一样对象一定不一样；
 *         hash值一样再用equals比较内容 看是否是同一个对象
 *         线程非同步
 *              - 如何判断元素的唯一性：通过元素的两个方法 hashCode equals
 *              - 如果hashCode相同，才会判断 equals
 *              - 如果 hashCode不同，不会调用 equals
 *              - 对于 contains remove 也是依赖于 hashCode equals
 *         - TreeSet： 可以对集合中的元素排序
 *              - 底层元素为排序二叉树
 *              - 判断元素是否相同 ===》 compareTo 返回 0
 *              - 元素排序的第一种方法：让元素实现 Comparable接口
 *              - 元素排序的第二种方法：当元素自身不具备比较性，或者具备的比较性不是需要的
 *                  这时需要让集合自身具备比较性，让集合一初始化就有比较方式
 */
class SetCode {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add("111");
        dump(hashSet.add("111")); // 添加失败返回 false
        hashSet.add("222");
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            dump(iterator.next());
        }

        HashSet hs = new HashSet();

        Person p = new Person("cxl", 18);

        //todo: 同一个对象 同一个hashcode； 之一 如果对象覆盖了hashCode方法，返回hashcode 就是覆盖方法返回的值
        dump(hs.add(p)); // true
        dump(hs.add(p)); // false

        //todo: new 生成另一个新的对象，一个新的hash值；尽管内容不同 但是hash值不同
        dump(hs.add(new Person("zzz", 18))); // true
        dump(hs.add(new Person("zzz", 18)));  // true

        hs.contains(p);


        //todo：对集合中的元素进行排序;
        //todo: 加入的对象必须实现  Comparable.compareTo 从而具备可比较性
        TreeSet treeSet = new TreeSet();
        treeSet.add("D");
        treeSet.add("C");
        treeSet.add("B");
        treeSet.add("A");
        Iterator iterator1 = treeSet.iterator();

        while (iterator1.hasNext()){
            dump(iterator1.next());
        }


    }

    public static void dump(Object obj){
        System.out.println(obj);
    }
}


class Person{
    private String name;
    private int age;
    Person(String name, int age){
        this.age = age;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    //todo:覆盖 hashcode
    public int hashCode(){
        return name.hashCode();
    }
    //todo: 覆盖equals
    public boolean equals(Object object){
        Person person = (Person)object;
        System.out.println("equals 。。。。。。。。。。。。。。。。。");
        return (person.name.equals(this.name))&& (person.age == this.age);
    }
}


/**
 * 实现 Comparable 让 Student类具有比较性
 */
class Student implements Comparable{
    private String name;
    private int age;
    Student(String name, int age){
        this.age = age;
        this.name = name;
    }

    //todo:实现 compareTo 方法
    public int compareTo(Object object){
//        return 1; // 二叉树变成一条线（向右）， -1 与之类似

        if(!(object instanceof Student))
            throw new RuntimeException("不是学生");
        Student s = (Student)object;

        if (this.age > s.age){
            return 1;
        }

        if (this.age == s.age)
            return 0;

        return -1;

    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

//    //todo:覆盖 hashcode
//    public int hashCode(){
//        return name.hashCode();
//    }
    //todo: 覆盖equals
    public boolean equals(Object object){
        Student person = (Student)object;
        System.out.println("equals 。。。。。。。。。。。。。。。。。");
        return (person.name.equals(this.name))&& (person.age == this.age);
    }
}


/**
 * 当元素自身 不具备比较性，或者具备的比较性不是需要的
 * 这时需要让容器自身具备比较性
 * 定义比较器，将比较器对象作为参数，传递给TreeSet的构造函数
 *
 * 如果 元素本身存在比较方法， 也存入比较器； 以比较器为准
 */
class TreeSetCode2{
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet(new MyCompare());
    }
}

class MyCompare implements Comparator {
    public int compare(Object obj1, Object obj2){
        Student s1 = (Student)obj1;
        Student s2 = (Student)obj2;
        if (s1.getAge() == s2.getAge())
            return 0;
        if (s1.getAge() > s2.getAge())
            return 1;
        return -1;
    }
}