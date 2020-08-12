package Grammar.IOStream;

import java.io.Serializable;

public class Test implements Serializable {
    private String name;
    int age;
    // transient 修饰的也不能被序列化
    transient int girls = 6;
    //静态成员 不能被序列化
    static private String country = "cn";

    Test(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return name+"---"+age;
    }
}
