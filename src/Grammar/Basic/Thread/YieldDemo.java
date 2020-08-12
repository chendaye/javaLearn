package Grammar.Basic.Thread;

public class YieldDemo {
    public static void main(String[] args) {
        Runnable yieldTask = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    System.out.println(Thread.currentThread().getName()+" --- "+i);
                    if (i==5) Thread.yield(); // 暗示我可以让出资源；决定权在jvm
                }
            }
        };

        new Thread(yieldTask, "A").start();
        new Thread(yieldTask, "B").start();
    }
}
