package Grammar.Thread;

class ThreadTest extends Thread {
    private int ticket = 10;
    @Override
    public void run(){
        while (true){
            if (ticket > 0)
                System.out.println(this.getName()+"卖票：ticket="+ticket--);
            else
                break;
        }
    }

    /**
     * 每一个线程 卖10 张票
     * @param args
     */
    public static void main(String[] args) {
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();
        t1.start();
        t2.start();
    }
}

/**
 * 和上面“MyThread继承于Thread”不同；这里的MyThread实现了Thread接口。
 * 主线程main创建并启动3个子线程，而且这3个子线程都是基于“mt这个Runnable对象”而创建的。
 * 运行结果是这3个子线程一共卖出了10张票。这说明它们是共享了MyThread接口的。
 *
 */
class RunnableTest implements Runnable{
    private int ticket = 10;
    @Override
    public void run(){
        while (true){
            if (ticket > 0)
                System.out.println(Thread.currentThread().getName()+"卖票：ticket="+ticket--);
            else
                break;
        }
    }

    //todo: 两个线程一共 10 张
    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        //todo: 使用同一个 runnableTest
        Thread thread1 = new Thread(runnableTest);
        Thread thread2 = new Thread(runnableTest);

        thread1.start();
        thread2.start();
    }
}
