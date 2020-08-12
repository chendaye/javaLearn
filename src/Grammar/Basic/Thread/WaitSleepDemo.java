package Grammar.Basic.Thread;

public class WaitSleepDemo {
    public static void main(String[] args) {
        final Object lock = new Object();
        // 线程A
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A 等待去获取锁");
                synchronized (lock){
                    System.out.println("线程A获取锁");
                    try {
                        System.out.println("A wait");
//                        lock.wait(1000);
                        lock.wait(); // 无限等待 需要显示唤醒
                        System.out.println("线程A 完成");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //主线程 sleep 10,ms

        try {
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // 线程B
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 等待去获取锁");
                synchronized (lock){
                    System.out.println("线程B获取锁");
                    try {
                        System.out.println("B sleep");
                        Thread.sleep(20);
                        System.out.println("线程B 完成");
                        //todo:唤醒 A
//                        lock.notifyAll();
                        lock.notify();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

