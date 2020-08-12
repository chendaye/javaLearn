package Grammar.ClassGrammar;

public class InterfaceCode {
}


/**
 * 接口格式：
 * 1、接口中常用定义：常量、抽象方法
 * 2、接口中的成员函数都有固定的修饰符
 *      常量：public static final
 *      方法：public abstract
 *
 *
 * 一个类可以支持多个接口
 */
interface inter{

    public static final int A=10;
    public abstract void run();
}

interface inter1{

    public static final int A=10;
    public abstract void run1();
}

class obj{
    public void test(){

    }
}

/**
 * 可以实现多个接口
 * 同事也可以继承类
 */
class test extends obj implements inter,inter1{
    public void run(){

    }

    public void run1(){

    }
}

interface A{
    void methodA();
}

interface B extends A{
    void methodB();
}

/**
 * 接口之间可以互相继承
 */
interface C extends B{
    void methodC();
}