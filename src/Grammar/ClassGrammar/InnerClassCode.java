package Grammar.ClassGrammar;

/**
 * 内部类
 */
public class InnerClassCode {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();

        //todo:直接访问内部类
        // 没有静态(static)的类中类不能使用外部类进行.操作,必须用实例来进行实例化类中类.
        // Outer.Inner inner = new Outer.Inner(); X
        Outer.Inner inner = outer.new Inner();

        // todo：静态内部类 非静态成员 静态成员
        new Outer.Inner2().fun(); // 非静态成员访问
        Outer.Inner2.fun2(); // 静态成员访问


        //todo:局部每部类
        new Outer2().fun();
    }
}

/**
 * 内部类的访问规则：
 * 1、内部类可以直接访问外部类的成员，包括私有
 *      之所以可以访问外部类的成员，是因为，内部类中持有一个外部类的引用 格式： 外部类名.this
 * 2、外部类访问内部类，必须建立内部类的对象
 *
 *
 * 访问格式：
 * 1、如果内部类非私有，可以在外部其他类中建立对象
 *  外部类名.内部类名 变量名 = 外部类对象.new 内部类对象
 *
 * 2、内部类可以被 成员修饰符修饰 public private static protect （一般用private修饰）
 *      当内部类被static修饰后，只能访问外部类中的静态成员
 *
 * 注意：当内部类定义了静态成员，该内部类必须是静态的
 * 当外部内的静态方法访问内部类时， 内部类也必须是静态的
 *
 *
 * 内部类的使用场景：
 *      当描述事物时，事物的内部还有事物，该事物用内部类描述。因为内部类可以使用外部类的内容
 *      主要是内部类，可以直接访问外部类的内容
 */
class Outer{
    int x = 2;
    static int y = 6;
    void method(){
        System.out.println(x);
        Inner inner = new Inner();
        inner.fun();
    }
    // TODO:内部类
     public class Inner{
        int x = 3;
        void fun(){
            System.out.println("Inner Class 访问外层类的内容："+x); // 3 局部覆盖
            System.out.println("Inner Class 访问外层类的内容："+this.x); // 3  this指向 Inner
            System.out.println("Inner Class 访问外层类的内容："+Outer.this.x); // 2 Outer.this 指向 Outer
        }
    }

    // 静态内部类
    static class Inner2{
        void fun(){
            System.out.println(y);
        }

        static void fun2(){
            System.out.println("静态内部类的静态方法："+y);
        }
    }
}

/**
 * 定义在局部的内部类 ：
 * 1、不能用 修饰符 static public private protect
 * 2、可以直接访问外部类的成员
 * 3、不可以访问所在局部的变量，只能访问被final修饰的局部的变量
 */
class Outer2{
    int x = 6;
    void fun(){
        final int  y = 7;  // todo: 局部变量要被局部内部类访问 要用 final 修饰
        class Inner3{
            void method(){
                System.out.println("局部内部类："+Outer2.this.x + ": 局部变量："+y);
            }
        }
        //todo： 访问内部类只能用对象访问
        new Inner3().method();
    }
}

/**
 * 匿名内部类：
 * 1、匿名内部类其实就是内部类的简写
 * 2、定义匿名内部类的前提：
 *      内部类必须继承一个类或者实现接口
 *
 * 3、匿名内部类的格式： new 父类或接口(...arg){....子类内容....}
 *
 * 4、匿名内部类就是一个子类对象。 就是一个子类对象
 *
 * 5、匿名内部类中的方法一般不要超过3个 太多没有可读性。 匿名内部类是为了简化而存在的
 */
class Anonymous{
    public static void main(String[] args) {
//        new AnonymousOuter().new AnonymousInner().show();
        new AnonymousOuter().method();
    }
}

abstract class Abs{
    abstract void show();
}

class AnonymousOuter{
    int x = 6;
    public void method(){
        new AnonymousInner().show();

        //todo :匿名内部类 下面的内容是 Abs的子类对象
        new Abs(){
            void show(){
                System.out.println("匿名内部类");
            }
        }.show();

        // todo:另一种情形，一般匿名对象赋值没有什么意义，直接在后面定义方法。 匿名内部类的方法一般不要超过3个
        Abs tmp = new Abs(){
            void show(){
                System.out.println("匿名内部类");
            }
            void method(){
                System.out.println("匿名子类对象中特有的方法");
            }
        };

        tmp.show();
//        tmp.method(); 此种方式不行， 因为tmp指向的是一个父类对象 method() 是一个匿名子类的特有方法，父类中没有
    }

    // 内部类
    class AnonymousInner extends Abs{
        void show() {
            System.out.println("非匿名内部类");
        }
    }
}