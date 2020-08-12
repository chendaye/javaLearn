package Grammar.ClassGrammar;

/**
 * final: 最终。
 * 1、可以修饰 函数 变量 类
 * 2、被 final修饰的类不能被继承
 * 3、被 final修饰的方法不能被覆盖
 * 4、被 final修饰的变量是一个常量，只能赋值一次。既可以修饰成员变量，也可以修饰局部变量。
 * 5、内部类被定义在局部位置上，只能该局部被final修饰的局部变量
 *
 */
public class FinalCode {
    public static void main(String[] args) {

    }
}


final class demo{

}

class demo2{

    public static final double PI = 3.14;
    final int x=3;
    final void fun(){

    }

    void fun2(){
        final int y = 6;
    }
}