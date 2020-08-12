package Grammar.ClassGrammar;

public class ClassCode {
    public static void main(String[] args) {
        Car car = new Car();
        car.num = 15;
        car.run();

        /**
         * 匿名对象使用方式一：
         * 当对象方法只使用一次，可以用匿名对象来完成，这样比较简洁。
         * 匿名对象操作成员变量没有意义，匿名对象在堆中创建后会被回收
         * 如果对一个对象的多个成员进行调用，必须给类取个名字
         *
         * 第二个用法： 作为实参传递
         */
        new Car().run();
        new Car().num = 10; // 没意义

        // 匿名对象作为实参传递
        show(new Car());

    }

    public static void show(Car c){
        c.num = 2;
        c.color = 'k';
        c.run();
    }
}

class Person {
    private int age;

    private String name;

    /**
     * 修饰的成员，在内存中单独存储在（数据区、方法区、共享区），由类的所有对象共享
     *
     * 特点：
     * 1、随类的加载而加载。随着类的消失而消失。生命周期最长
     * 2、优先于对象存在
     * 3、被所有对象共享
     * 4、可以直接用类名调用
     *
     * 实例变量 和 类变量（静态变量）区别：
     * 1、存放位置：
     *      类变量随着类的加载而存在于方法区中
     *      实例变量随着对象的创建而存在于堆内存中
     * 2、 生命周期：
     *      类变量的生命周期最长，随类的消失而消失
     *      实例变量随对象的消失而消失
     *
     *  使用注意事项：
     *
     *  1、静态方法只能访问静态成员
     *      非静态方法既可以访问静态也可以访问非静态
     *
     *  2、静态方法中不可以定义this super等关键字
     *      因为静态优先于对象存在，所以静态方法中不能出现this
     *
     * 静态的利弊：
     * 1、利
     *      对象的共享数据用单独空间存储，节省空间
     * 2、弊
     *      生命周期过长，访问有局限性，只能访问静态
     *
     *
     * 什么时候使用static：
     * 1、什么时候定义static变量
     *      当对象中出现共享值
     *      对象中的特有数据定义成非静态，存在于堆内存中
     * 2、什么时候定义静态函数
     *      函数没有调用对象的特有数据，可以定义成静态
     *
     */
    static String country = "cn";

    /**
     * 主函数是一个特殊的函数。作为程序的入口，可以被jvm调用
     *
     * 主函数的定义：
     * public：代表最大的访问权限
     * static：表示主函数随着类的加载而存在
     * void：主函数没有具体额返回值
     * main：不是关键字，是一个特殊的单词，可以被jvm识别
     * String[] args：字符串数组类型的参数
     *
     * 主函数是固定格式的，jvm识别
     *
     * jvm在调用主函数时传入的是 new String[0]
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 类加载的过程：
         * 1、因为new 用到文件Car.java，所以先找到文件并加载到内存中
         * 2、执行该类中 static 静态代码块（如果有），进行初始化
         * 3、在堆内存中开辟空间，并且分配内存
         * 4、在堆内存中建立对象额特有属性，并进行默认初化
         * 5、对属性进行显示初始化
         * 6、执行构造代码块
         * 7、执行对应的构造函数
         * 8、将类在堆中的地址返回给 car 变量
         */
        Car car = new Car();
    }

    /**
     * todo:构造代码块
     * 1）用于给对象进行初始化
     * 2）在构造函数之前运行
     * 3）所有实例创建都会运行构造代码块（如果有）。不同实例传参不同运行的构造函数不同
     *
     * 构造代码块中定义的是不同对象中，共有的初始化内容
     */
    {
        System.out.println("构造代码块");
    }

    /**
     * 构造函数没有返回值类型
     * 对象一建立就会运行
     *
     * 当一个类中没有构造函数时，系统会默认给类加入一个 没有参数的构造函数
     * 自定义了构造函数，默认构造函数就没有了
     *
     *
     * 默认的构造函数权限与 类的权限一致
     * @param num
     */
    Person(int num){
        age = num;
    }

    /**
     * 构造函数的重载
     * @param num
     * @param str
     */
    Person(int num, String str){
//        age = num;
        // 构造函数间的互相调用,且this()只能放在的构造函数的第一行
        this(num);
        name = str;
    }

    /**
     * this 关键字：
     * 1)本类功能内部 用到 本类对象 ===> 用this
     * 2)this用于构造函数间的互相调用。 可能有多个构造函数但是只开放一个，内部用this()相互调用
     * @param p
     * @return
     */
    public Boolean compare(Person p){
        return p.age > this.age;
    }

    /**
     * 静态代码块：
     * 随着类的加载而执行，只执行一次。 并且优先于主函数执行
     * 用于给对象初始化
     *
     * todo：构造代码块：在构造函数之前执行，用于初始化对象的公共部分
     */
    static {
        System.out.println("静态代码块");
    }
}

class
Car {
    public char color;
    public int num;

    public void run(){
        System.out.println("兰博基尼");
    }



}