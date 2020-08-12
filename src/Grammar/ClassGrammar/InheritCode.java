package Grammar.ClassGrammar;

/**
 * 继承：
 * - 提高代码的复用性
 * - 让类之间产生关系，有了关系，才有多态
 *
 * 注意：
 * - 不要为了获取其他类的功能，简化代码而继承
 * - 必须是类之间有所属关系才用继承
 *
 * java: 原则上只支持单继承。 多继承有安全隐患，当多个父类中有同样的方法，字类不知道运行哪一个。
 *
 * java与多继承类似的有 多实现，相当于改良了多继承
 *
 * java支持多层继承
 *
 * 如何使用继承体系的功能：查阅父类功能，创建子类对象使用功能
 */
class Human {
    public int age = 1;
    public String name;

    /**
     * 构造函数
     */
    Human(){
        System.out.println("父类方法构造函数");
    }

    Human(int num){
        System.out.println("父类方法构造函数"+num);
    }

    public void sayAge(){
        System.out.println("父类方法"); // this 自己的引用
    }

    public void run(){
        System.out.println("run");
    }
}


class Student extends Human{
    public int age = 2;

    /**
     *
     * 子类的实例化：
     *
     * 子类构造函数
     *
     * 在对子类的进行初始化时，父类的构造函数也会运行
     * 因为子类的构造函数默认第一行有一条隐式语句 super() 来调用父类空参数的构造函数
     * 而且子类中所有构造函数的第一行都是 super()
     *
     * 注意：
     * super(...arg) 要放在构造函数的第一行
     * this(...arg) super(...arg) 不能同时出现在同一个构造函数中
     *
     * 子类的所有构造函数默认都会访问父类的空参数构造函数
     * 当父类中没有空参数构造函数时 ，子类必须手动通过 super(...arg)的形式访问父类中的构造函数
     * 当然：子类中的构造函数第一行也可以 this(...arg)来访问本类中的构造函数
     * 子类中至少有一个构造函数会访问父类中的构造函数
     */
    Student(){
        // super()  编译时会加上这句， 调用父类的构造函数，传什么参数就调用什么构造函数
        System.out.println("子类构造函数");
    }

    /**
     * 子类方法覆盖父类方法，前提是子类权限大于等于父类权限
     *
     * 静态只能覆盖静态
     */
    public void sayAge(){
        System.out.println(this.age); // this 自己的引用
        System.out.println(super.age);  // super 父类的引用

        this.sayAge();
        super.sayAge();
    }
}


class Teacher extends Human{
    public int age = 3;

    Teacher(){
        // 如果父类中没有空参数的构造函数，就要显示的调用 super(...arg),
        // 否则，找不到super() 会编译失败
        super(3);
        System.out.println("lll");
    }
}

class Demo{
    public static void main(String[] args) {
        Student student = new Student();

        student.sayAge();
    }
}