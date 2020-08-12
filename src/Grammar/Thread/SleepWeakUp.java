package Grammar.Thread;

// WaitTest.java的源码
class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

class WaitTest {

    /**
     * todo:
     *  - 注意：jdk的解释中，说wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
     *  - 这也意味着，虽然t1.wait()是通过“线程t1”调用的wait()方法，但是调用t1.wait()的地方是在“主线程main”中。
     *  - 而主线程必须是“当前线程”，也就是运行状态，才可以执行t1.wait()。所以，此时的“当前线程”是“主线程main”！
     *  - 因此，t1.wait()是让“主线程”等待，而不是“线程t1”！
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        //主线程获取锁
        synchronized(t1) {
            try {
                // 启动“线程t1” ；t1 线程获取锁
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start(); // t1 run() 方法运行 t1主线程进入锁池; 因为主线程持有锁

                System.out.println(Thread.currentThread().getName()+" wait()");
                // 主线程释放锁,t1 获取锁
                t1.wait(); // 主线程(当前在CPU上运行的线程) 调用 t1.wait(); 主程序等待(wait 只让当前线程等待，谁调用的无所谓)

                //t1 运行完 同步代码块，唤醒锁池中的 main线程
                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 *  wait(long timeout)会让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，
 *  或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
 * 下面的示例就是演示wait(long timeout)在超时情况下，线程被唤醒的情况。
 *
 * (01) 主线程中新建并且启动了3个线程"t1", "t2"和"t3"。
 * (02) 主线程通过sleep(3000)休眠3秒。在主线程休眠3秒的过程中，我们假设"t1", "t2"和"t3"这3个线程都运行了。
 * 以"t1"为例，当它运行的时候，它会执行obj.wait()等待其它线程通过notify()或额nofityAll()来唤醒它；相同的道理，
 * "t2"和"t3"也会等待其它线程通过nofity()或nofityAll()来唤醒它们。
 * (03) 主线程休眠3秒之后，接着运行。执行 obj.notifyAll() 唤醒obj上的等待线程，即唤醒"t1", "t2"和"t3"这3个线程。
 * 紧接着，主线程的synchronized(obj)运行完毕之后，主线程释放“obj锁”。这样，"t1", "t2"和"t3"就可以获取“obj锁”而继续运行了！
 */
class ThreadB extends Thread{

    public ThreadB(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " run ");
        int i = 0;
        // 死循环，不断运行。
        while(true)
            System.out.println(i++);
    }
}

class WaitTimeoutTest {

    public static void main(String[] args) {

        ThreadB t1 = new ThreadB("t1");
        //主线程获取锁
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " call wait ");
                t1.wait(3000); // 主线程等待 3秒
                // 3秒钟到 主线程唤醒
                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * notifyAll()的用法；它的作用是唤醒在此对象监视器上等待的所有线程
 *
 * Object中的wait(), notify()等函数，和synchronized一样，会对“对象的同步锁”进行操作。
 *
 * wait()会使“当前线程”等待，因为线程进入等待状态，所以线程应该释放它锁持有的“同步锁”，否则其它线程获取不到该“同步锁”而无法运行！
 * OK，线程调用wait()之后，会释放它锁持有的“同步锁”；而且，根据前面的介绍，我们知道：等待线程可以被notify()或notifyAll()唤醒。
 *
 * 现在，请思考一个问题：notify()是依据什么唤醒等待线程的？或者说，wait()等待线程和notify()之间是通过什么关联起来的？答案是：依据“对象的同步锁”。
 *
 * 负责唤醒等待线程的那个线程(我们称为“唤醒线程”)，它只有在获取“该对象的同步锁”(这里的同步锁必须和等待线程的同步锁是同一个)，
 * 并且调用notify()或notifyAll()方法之后，才能唤醒等待线程。虽然，等待线程被唤醒；但是，它不能立刻执行，因为唤醒线程还持有“该对象的同步锁”。
 * 必须等到唤醒线程释放了“对象的同步锁”之后，等待线程才能获取到“对象的同步锁”进而继续运行。
 *
 * 总之，notify(), wait()依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个！这就是为什么notify(), wait()等函数定义在Object类，
 * 而不是Thread类中的原因。
 */
class NotifyAllTest {

    private static Object obj = new Object();
    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        ThreadA t3 = new ThreadA("t3");
        //三个线程启动 ，run里面 立马 wait
        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println(Thread.currentThread().getName()+" sleep(3000)");
            // 当前线程（主线程）sleep
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程获取锁
        synchronized(obj) {
            // 主线程等待唤醒。 唤醒 t1 t2 t3
            System.out.println(Thread.currentThread().getName()+" notifyAll()");
            obj.notifyAll();
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name){
            super(name);
        }

        public void run() {
            synchronized (obj) {
                try {
                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " wait");

                    // 唤醒当前的wait线程
                    obj.wait();

                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}