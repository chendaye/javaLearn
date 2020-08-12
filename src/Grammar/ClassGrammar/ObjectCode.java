package Grammar.ClassGrammar;

/**
 * JAVA 所有对象的超类：Object
 * 该类中所有对象具有的功能
 */
public class ObjectCode {
    public static void main(String[] args) {
        Demos a = new Demos(1);
        Demos b = new Demos(2);
        Demos c = a;

        // equals 是定义在Object中， 比较的实际是内存中的地址。可以在子类中重写
        System.out.println(a.equals(b)); // false
        System.out.println(a.equals(c)); // true

        // toString  Grammar.ClassGrammar.Demos@1b6d3586  @ 后面是对象地址
        System.out.println(a.toString());
        System.out.println(Integer.toHexString(a.hashCode()));  // 地址
        System.out.println(a.getClass().getName()+"@"+Integer.toHexString(a.hashCode()));  // 地址

        // 获取class对象
        Class cla = a.getClass();
        System.out.println(cla.getName());
    }
}


class Demos{
    int num;
    Demos(int num){

        this.num = num;
    }

    public boolean equals(Object obj){
        return true;
    }
}