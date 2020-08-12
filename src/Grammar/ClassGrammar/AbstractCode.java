package Grammar.ClassGrammar;

/**
 * 抽象类的特点：
 * 1、抽象方法定义 在抽象类中
 * 2、抽象方法和类都要用 abstract 修饰
 * 3、抽象类不能实例化
 * 4、继承抽象类的子类，要使用抽象方法，必须实现父类所有抽象方法。 如果只实现部分抽象方法；那么子类还是抽象类
 *
 */
abstract class Girl{
    abstract void makeLove();

    void sleep(){
        System.out.println("抽象类里面也可以定义一般方法。抽象类和一般类没有太大不同");
    }
}


public class AbstractCode {
}
