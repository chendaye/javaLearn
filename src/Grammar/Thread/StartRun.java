package Grammar.Thread;

class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }

    public void run(){
        System.out.println(Thread.currentThread().getName()+" is running");
    }
};

/**
 * Grammar.Thread.currentThread().getName()是用于获取“当前线程”的名字。当前线程是指正在cpu中调度执行的线程。
 * mythread.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
 * mythread.start()会启动“线程mythread”，“线程mythread”启动之后，会调用run()方法；此时的run()方法是运行在“线程mythread”上。
 */
public class StartRun {
    public static void main(String[] args) {
        Thread mythread=new MyThread("mythread");

        System.out.println(Thread.currentThread().getName()+" call mythread.run()");
        // todo: 普通方法调用 运行在主线程上
        mythread.run();

        System.out.println(Thread.currentThread().getName()+" call mythread.start()");
        // todo: 创建启动线程 运行在 mythread 线程上
        mythread.start();
    }
}

