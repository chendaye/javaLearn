package Grammar.OtherObject;

/**
 *Runtime
 * 该类并没有提供构造函数
 * 说明该类不可以 new ，那么类中的方法可能都是静态的
 * 但是类中有非静态方法，说明该类提供了方法获取本类对象，而且方法是静态的，返回本类类型
 */
public class RunTimeCode {
    public static void main(String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process cmd = runtime.exec("notepad.exe");// 运行一个命令,打开记事本
        Thread.sleep(100);
        cmd.destroy(); // 杀掉进程
    }
}
