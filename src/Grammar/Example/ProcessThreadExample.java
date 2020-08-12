package Grammar.Example;

/**
 * 多线程卖票程序
 * 多个窗口同时卖票
 *
 * todo: 通过实验发现 出现了 编号为负数的票  这就是多线程安全问题。
 * 有可能在测试的时候，不会出现，运行正常
 *
 *
 * 问题原因：
 * - 多条语句操作同一个线程共享数据时，一个线程对多条语句只执行了一部分，另一个线程参与进来
 * - 导致共享数据的错误  --- 并发读写问题
 *
 * 解决办法：
 * - 对共享的资源，只能让一个线程执行过程中，其他线程不参与
 *
 * todo:java对多线程的安全问题提供了解决方式： 同步代码块：
 * 哪些语句在操作共享数据，哪些就需要同步
 * synchronized (对象){
 *      //对象如同锁，持有锁的进程可以在同步中执行
 *      // 没有锁的进程，即使获取了cpu执行权，也进不来
 * }
 *
 * 同比前提:
 * - 必须要有两个以上的进程
 * - 必须是多个线程使用同一个锁
 * - 保证同步中只有一个线程运行
 *
 * 同步优劣：
 * - 好处，解决线程安全问题
 * - 弊端：线程需要判断锁，艺消耗资源
 */
public class ProcessThreadExample {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread ticket_1 = new Thread(ticket);
        Thread ticket_2 = new Thread(ticket);
        Thread ticket_3 = new Thread(ticket);
        Thread ticket_4 = new Thread(ticket);
        Thread ticket_5 = new Thread(ticket);
        Thread ticket_6 = new Thread(ticket);
        ticket_1.start();
        ticket_2.start();
        ticket_3.start();
        ticket_4.start();
        ticket_5.start();
        ticket_6.start();
    }
}


class Ticket implements Runnable{

    private static int tickets = 1000; //todo:票只能有一份 所以静态
    private Object obj = new Object();
    public void run(){
        while (true) {
            //todo:同步代码块. synchronized 里面有一个标志位， flag=1 允许进程进来 flag=0 不允许线程进来；
            // todo:当线程进来时， 设置flag=0；线程离开时 设置flag=1
            // todo: 江湖人称：：同步锁
            synchronized (obj) {
                if (tickets > 0) {
                    try {
                        //todo:让进程 sleep 10ms，模拟进程安全问题
                        /**
                         * 当还剩下一张票时， 进程A -> ticket=1 通过判断 准备运行
                         * 当 进程A 还没来得及远行是，也就是 ticket=1没变。
                         * 此时 进程B来了 -> ticket=1 判断通过
                         * 此时，进程A运行  ticket=0
                         * 然后进程B运行 ticket=-1  》》》》 错误结果
                         */
                        Thread.sleep(5);


                    } catch (Exception e) {
                        //todo: 因为父类么有抛异常，所以子类不能抛，只能catch
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + "...." + tickets--);
                } else {
                    break; // 线程结束
                }
            }
        }
    }
}

/************************************实现线程的第二种方法****************************************/

class Thread_4 implements Runnable{
    public void run(){
        for (int i=0;i<1000;i++){
            System.out.println("Runable 方法实现线程...."+Thread.currentThread()+i);
        }
    }
}

/**
 * 创建线程的第二个方法：
 * - 定义实现Runnable接口的类
 * - 覆盖Runnable接口的run()方法
 * - 通过Thread类建立线程对象
 * - 将Runnable接口的子类对象，作为参数传递给Thread对象的构造函数
 * - 调用Thread的start()方法；开启线程并调用Runnable子类的run()方法
 *
 * 实现方式和继承方式的区别
 * - Runnable方式的好处，避免了单继承的局限性，因为java只有单继承，继承了Thread就不能继承其他类。定义线程建议使用Runnable
 * - Thread方式：线程的代码存放在thread 子类的run方法中；
 * - Runnable方式：线程代码存放在 接口的子类的run方法中
 */
class ThreadCode{
    public static void main(String[] args) {
        //todo:实现了Runnable接口
        Thread_4 thread_4 = new Thread_4();
        //todo: 直接传到 Thread对象里，创建一个线程
        Thread thread_1 = new Thread(thread_4);
        Thread thread_2 = new Thread(thread_4);
        Thread thread_3 = new Thread(thread_4);
        thread_1.start();
        thread_2.start();
        thread_3.start();
    }
}

/******************************************第二个例子******************************************************/

/**
 * 第二个需求:
 * 银行有一个金库。
 * 两个储户分别存300，每次存100，存3次
 *
 * 是否有安全问题，有如何解决
 *
 * todo：找问题：
 * - 并且哪些语句是多线程的运行代码
 * - 明确共享数据
 * - 明确多线程运行代码中的 共享数据
 */
class BankMain{
    public static void main(String[] args) {
        Cus c = new Cus();
        Thread thread_1 = new Thread(c);
        Thread thread_2 = new Thread(c);
        thread_1.start();
        thread_2.start();
    }
}

class Bank{
    private int sum;
    private  Object obj = new Object();

    /**
     * todo：synchronized 修饰同步函数
     *
     * todo：同步函数使用的锁是 方法所属对象的引用 this。 如果两个进程共享数据，但是用的不同的锁，对于没有同步
     *
     * todo: 当同步函数被 static修饰，那么使用的锁不再是 this 因为，静态方法不再堆内，没有this
     * 静态进内存时，内存中没有本类对象，但是一定该类对应的 字节码文件队对象 ===>  类名.class
     * 该对象的类型是 Class
     *
     * ====》静态同步方法使用的锁是 该方法所在类的字节码文件对象 ==> 类名.class
     *
     * @param n
     */
    public synchronized void add(int n){
        //synchronized (obj){
            sum += n;
            try {
                Thread.sleep(5);
            }catch (Exception e){

            }
            System.out.println("sum = "+Thread.currentThread()+"...."+sum);
        //}

    }
}

class Cus implements Runnable{
    private Bank bank = new Bank();
    public void run(){
        for (int i=0;i<3;i++){
            bank.add(100);
        }
    }
}