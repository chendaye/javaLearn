package Grammar.Basic.Thread;

public class Test {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("老王");
        MyThread thread2 = new MyThread("老李");
        MyThread thread3 = new MyThread("老吴");
        thread1.start();
        thread2.start();
        thread3.start();

        // runnable 没有start 方法，要借助于 Grammar.Thread
        MyRunnable runnable1 = new MyRunnable("小王");
        MyRunnable runnable2 = new MyRunnable("小李");
        MyRunnable runnable3 = new MyRunnable("小吴");
        Thread tr1 = new Thread(runnable1);
        Thread tr2 = new Thread(runnable2);
        Thread tr3 = new Thread(runnable3);
        tr1.start();
        tr2.start();
        tr3.start();
    }
}
