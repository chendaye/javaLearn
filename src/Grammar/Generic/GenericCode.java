package Grammar.Generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 升级原则： 安全 高效 简化书写
 *
 * 泛型： 用于解决安全问题，1.5之后出现的类型安全机制，用于指定元素类型
 *
 * 好处：将运行时期的 ClassCastException 转移到了编译时期，方便程序员解决问题，让运行时期问题减少
 *       避免了强制转换的麻烦
 *
 * todo: 泛型格式： 通过<>来定义要操作的引用数据类型
 *          什么时候使用泛型？
 *              - 通常在集合框架中很常见 只要见到 <> 就要定义泛型
 *              - <> 用来接受类型，使用集合时，将集合要存储的数据类型作为参数传递给<>
 *
 * todo：
 *  - 静态方法不能操作类上的泛型（类上的泛型，实例化时才确定）
 *  - 如果静态方法要使用 泛型，就定义在飞机上
 */
public class GenericCode {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("abc");
        // 等同 arrayList.add(new Integer(4)); 集合里面只能装对象，不能装基本数据类型。
        // 自动装箱
        arrayList.add(4);

        Iterator iterator = arrayList.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //todo: 定义了一个ArrayList 容器 里面的类型是 String
        ArrayList<String> list = new ArrayList<String>();

        list.add("qwer");
        list.add("asdf");
//        list.add(7); 错误

        // todo:因为，集合定义了类型， 所以迭代器也要执行类型
        Iterator<String> iterator1 = list.iterator();
        while (iterator1.hasNext()){
            String s = iterator1.next();

            System.out.println(s);
        }

    }
}

/**********************************************泛型类***********************************************/

class Worker{

}

class Student{

}

class Tool{
    private Worker w;
    public void setW(Worker w){
        this.w = w;
    }

    public Worker getW(){
        return w;
    }
}

//todo: 泛型前的做法， 使用Object 以便接受任意类型
//todo: 是类具有扩展性，需要强制转换， 也存在类型安全问题
class Tool1{
    private Object w;
    public void setW(Object w){
        this.w = w;
    }

    public Object getW(){
        return w;
    }
}

// todo: 泛型类。 当类中要操作的引用类型 不确定时，使用泛型。早期使用Object
class Tool2<ABC>{
    private ABC w;
    public void setW(ABC w){
        this.w = w;
    }

    public ABC getW(){
        return w;
    }
}


class GenericCode2{
    public static void main(String[] args) {
        Tool t = new Tool();
        t.setW(new Worker());
        Worker w = t.getW();

        // 没有泛型
        Tool1 tool1 = new Tool1();
        tool1.setW(new Student()); // 可以传任意对象
        Worker ww = (Worker)tool1.getW(); // 类型强制转换

        //泛型
        Tool2<Worker> workerTool2 = new Tool2<>();
        workerTool2.setW(new Worker());  // 只能接收 泛型 指定的类型
        workerTool2.getW();

    }
}

/********************************************泛型方法********************************************************/


/**
 * 泛型方法
 */
class GenericCode3{
    public static void main(String[] args) {
        Demo<String> stringDemo = new Demo<>();
        stringDemo.show("aaa");

        Demo<Integer> stringDemo2 = new Demo<>();
        stringDemo2.show(new Integer(666));

        Demo2 demo2 = new Demo2();
        demo2.show(new Integer(12));
        demo2.show( "www");
    }
}

/**
 * 泛型定义在类上
 * @param <T>
 */
class Demo<T>{

    public void show(T t){
        System.out.println("show...."+t);
    }
}

/**
 * 把泛型定义在方法上
 *
 * todo：泛型类定义的泛型，在整个类中有效
 *  如果被方法使用，那么泛型对象明确操作的类型之后。所有操作的类型就股定理
 *  为了让不同方法可以操作不同的类型， 可以将泛型定义在方法上
 */
class Demo2<F>{

    public <T> void show(T t){
        System.out.println("show...."+t);
    }

    public <S> void show2(S s){
        System.out.println("show...."+s);
    }

    /**
     * 泛型类里面 还可以定义泛型方法
     * @param f
     */
    public void show3(F f){
        System.out.println("show...."+f);
    }

    //静态泛型函数
    public static <U> void show4(U u){
        System.out.println("show...."+u);
    }
}

/******************************************泛型接口****************************************************/

interface Inter<R>{
    void show(R t);
}

class Method<Y> implements Inter<String>{
    public void show(String s){
        System.out.println("fuck..."+s);
    }

    public void show2(Y t) {
        System.out.println("fuck..."+t);
    }
}



class GenericCode4{
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("qqq");
        list1.add("bbb");
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(111);
        list2.add(222);

        dump(list1);
        dump(list2);

    }

    /**
     * todo：
     *  泛型类型不确定，使用通配符 ？
     *  <? extents E>  可以接受 E或者E的子类 ，上限
     *  <? super E>  可以接受 E或者E的父类  ， 下限
     * @param list
     */
    public static void dump(ArrayList<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * T 代表一种具体类型
     * @param list
     * @param <T>
     */
    public static <T> void dump2(ArrayList<T> list){
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //todo: 泛型限定 <? extends Person>， 只接受 Person 和其子类
    public static void dump3(ArrayList<? extends Person> list){
        Iterator<? extends Person> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    class Person{

    }

    class Student extends Person{

    }
}