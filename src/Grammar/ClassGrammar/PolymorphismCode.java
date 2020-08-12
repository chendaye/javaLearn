package Grammar.ClassGrammar;

/**
 * 类的多态， 类似于函数的重载和覆盖
 *
 * 1、多态的体现：
 *      父类的引用指向了自己的子类对象
 *      父类的引用也可以接受自己的子类对象
 *
 * 2、多态的好处
 *      多态的出现提高了程序的扩展性
 *
 * 3、多态的前提
 *      类与类之间必须有关系，要么继承 要么实现
 *
 * 4、多态的弊端
 *      提高了扩展性，但是只能使用父类的引用访问父类中的成员
 *
 *
 * 5、多态成员函数的特点:
 *      在编译时期：参阅引用变量所属的类中是否有调用的方法。如果没有编译失败
 *      在运行时期：参阅对象所属的类中是否有调用的方法
 *      总结：成员函数在多态调用时：编译看左边，运行看右边
 *
 * 6、在多态中，成员变量的特点：
 *      无论编译还是运行，都参考引用变量所属的类（左边）
 *      可以认为子类抽象为了父类
 *
 *
 * 7、多态中静态成员函数的特点：
 *      无论编译和运行都参考左边
 */
public class PolymorphismCode {
    public static void main(String[] args) {
        Cat c1 = new Cat();
        /*类型提升，也称 向上转型*/
        Animal c2 = new Cat();  // Cat 既是 Animal 也是 Cat。
//        c1.run();
//        c2.run();

        /*
        * 向上转型 中 已经把 c2 变成了一个 Animal类型。 此时c2不能再调用 Cat类中特有的方法
        * 如果想调用 Cat 中特有的方法。可以强制将父类引用转成子类型 ===> 向下转型
        *
        * 注意：父类无法转成子类
        * */
        Cat c = (Cat)c2;
        c.catchMouse();


        fun(new Cat());
        fun(new Dog());


        System.out.println("____________________________多态特点__________________________");
        Animal fu =  new Cat();
        Cat zi = new Cat();
        //在多态中，成员变量的特点：无论编译还是运行，都参考引用变量所属的类（左边）
        System.out.println(fu.num);  // 结果：2
        System.out.println(zi.num); // 结果：3


        //todo: 动态绑定：绑定在实例上。 静态绑定：绑定在类上
        // 动态方法 动态绑定到对象， fu指向的对象 是 new Cat()  ; 绑定的类是 Animal
        fu.method_1(); // Cat_method_1
        zi.method_1(); // Cat_method_1

        // 静态方法 绑定在类上
        fu.method_2(); // Animal_static_method_2
        zi.method_2(); // Cat_static_method_2


    }

    private static void fun(Animal a){
        a.run();

        if(a instanceof Cat){
            Cat b = (Cat)a;
            ((Cat) a).catchMouse();
        }

        if (a instanceof Dog){
            Dog c  = (Dog)a;
            c.bark();
        }
    }
}

abstract class Animal{
    int num = 2;
    abstract void run();

    void method_1(){
        System.out.println("Animal_method_1");
    }

    static void method_2(){
        System.out.println("Animal_static_method_2");
    }
}

class Cat extends Animal{
    int num = 3;
    void run(){
        System.out.println("猫会跑");
    }

    void catchMouse(){
        System.out.println("猫会抓老鼠！");
    }

    void method_1(){
        System.out.println("Cat_method_1");
    }

    static void method_2(){
        System.out.println("Cat_static_method_2");
    }

    void method_3(){
        System.out.println("Cat_method_3");
    }

}

class Dog extends Animal{
    void run(){
        System.out.println("狗会跑");
    }

    void bark(){
        System.out.println("狗会叫！");
    }

}