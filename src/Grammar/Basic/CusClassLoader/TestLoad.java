package Grammar.Basic.CusClassLoader;


public class TestLoad {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
//        CusClassLoader load = new CusClassLoader("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\Grammar.Basic\\Class\\");
        CusClassLoader load = new CusClassLoader("E:\\load\\");
        // todo: 返回 Test.class 的Class对象
        Class rf = load.loadClass("Test");

        System.out.println(rf.getClassLoader());
//        System.out.println(rf.getName());

//        Object test = rf.newInstance();
    }
}
