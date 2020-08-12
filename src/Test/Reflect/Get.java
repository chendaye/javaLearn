package Test.Reflect;

// todo: 获取反射的三种方式
public class Get {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        //todo: 通过建立对象
        Student stu = new Student();
        Class aClass = stu.getClass();
        System.out.println(aClass.getName());

        //todo: 通过路径-相对路径
        Class aClass1 = Class.forName("Test.Reflect.Student");
        System.out.println(aClass1.getName());

        //todo： 通过类名
        Class<Student> aClass2 = Student.class;
        System.out.println(aClass2.getName());

//        Grammar.Thread.currentThread().sleep(10);
    }
}
