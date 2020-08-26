package Grammar.Basic.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //todo: 获取反射
        Class reflect = Class.forName("Grammar.Basic.Reflect.Person");
        Person person = (Person) reflect.newInstance();
        System.out.println(reflect.getName());

//        person.Say("直接用对象调用，算不算反射；并且不能调用私有方法");

        //todo: getDeclaredMethod 可以获取该类所有的方法（ private，public, protect default）；
        // todo:但是不能获取 继承的方法 或者实现的接口的方法
        Method run = reflect.getDeclaredMethod("Run", String.class);
        run.setAccessible(true); //todo:因为是私有方法 设置为可以访问
        //todo: 调用方法
        run.invoke(person, "反射");

        //todo: 只能获取该类的 public 方法； 但是可以获取 该类继承的方法 和 实现的接口的方法
        Method say = reflect.getMethod("Say", String.class);
        say.invoke(person, "getMethod");

        //todo: 获取私有属性
        Field name = reflect.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "修改私有方法");
        say.invoke(person, "getMethod");

        //todo: 获取共有属性
        Field sex = reflect.getField("sex");
        sex.set(person, "make love");
        run.invoke(person, "反射");

        // F:\java\jre\lib\ext;C:\Windows\Sun\Java.Java\lib\ext
        System.out.println(System.getProperty("java.ext.dirs"));  // ExtClassLoader
        System.out.println(System.getProperty("java.class.path")); // AppClassLoader
    }
}
