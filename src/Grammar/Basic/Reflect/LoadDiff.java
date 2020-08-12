package Grammar.Basic.Reflect;

/**
 * Class.forName 得到的class 是已经完成了 初始化 的（类变量赋值 静态代码块）
 * ClassLoader.loadClass 的到的class 是还没有 链接  的
 */
public class LoadDiff {
    public static void main(String[] args) throws ClassNotFoundException {
        // todo: 没有执行 静态代码块 （构造函数是在实例化我对象的时候执行的）
        //todo: 因为不需要初始化；加快了类的加载速度。 在实际使用类的时候再去初始化 (延迟加载)
        ClassLoader classLoader = Person.class.getClassLoader();

        //todo: 执行了静态代码块
        //todo: 有一些类 里面包含 静态代码块 就是必须要初始化的 就使用 Class.forName； 比如 mysql Driver
        Class<?> rf = Class.forName("Grammar.Basic.Reflect.Person");
    }
}
