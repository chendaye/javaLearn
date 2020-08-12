package Grammar.Basic.Thread;

public class ThreadTest {
    private static void attack(){
        System.out.println("Fight");
        System.out.println("current thread is: "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t = new Thread(){
          public void run(){
              attack();
          }
        };
        System.out.println("main thread is: "+ Thread.currentThread().getName());
//        t.start();
        t.run(); // z只是 t的一个普通方法调用； start 会创建一个新的线程
    }
}
