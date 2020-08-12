package Grammar.DesignPatterns;


/**
 * 单例模式：一个类在内存中只存在一个对象。 保证对象的数据唯一性
 *
 * java中有23中设计模式
 */

public class SinglePattern {
    public static void main(String[] args) {
        /**
         * 都指向堆中的同一个对象
         */
        Single.getInstance();
    }
}

class Single {
    // 私有的构造方法
    private Single(){

    }

    private static Single s = new Single();


    public static Single getInstance(){
        return s;
    }
}
