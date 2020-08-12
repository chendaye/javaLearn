package Grammar.ClassGrammar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *参考：https://zhuanlan.zhihu.com/p/67570178
 * todo：java中创建进程  线程，都是调用操作系统底层的方法，在操作系统中创建
 *
 *JVM 启动的时候会有一个进程 ，
 * 该进程中至少有一个线程 负责程序执行
 * 该线程称为主线程
 *
 * 扩展：jvm启动，不止一个线程，还有负责垃圾回收机制的线程
 *
 *
 * 创建线程的第一种方法:
 * - 定义类继承 Thread类
 * - 重写 run() 方法
 * - 实例化创建的线程类，调用 start() 方法启动. start() 作用：启动线程、调用run方法
 *
 * 为什么要覆盖run方法？
 * -  run方法用于存放线程运行的代码
 */
public class ProcessThreadCode {
    public static void main(String[] args) {
        //new 一个对象就创建了一个线程
        Thread_1 thread_1 = new Thread_1();  // todo: 此时有2个进程： 一个main 进程， 一个thread_1；在cpu中交替执行
        thread_1.start();
//        thread_1.run();  //todo：仅仅是运行run方法，线程创建了并没有运行。 run仅仅是封装线程要用的带啊代码。还是在主线程中运行

        for(int i=0; i<100; i++){
            System.out.println("test=="+i);
        }
    }
}

/**
 * 创建线程的第一种方式
 */
class Thread_1 extends Thread{
    // todo:覆盖run方法
    public void run(){
        for(int i=0; i<100; i++){
            System.out.println("创建线程的第一种方式: 继承Thread，重写run方法=="+i);
        }
    }
}



//   /**********************************两个线程和主线程交替运行****************************************/

class ThreadTest{
    public static void main(String[] args) {
        Thread_3 one = new Thread_3("one");
        Thread_3 two = new Thread_3("two");
        one.start();;
        two.start();

        for (int i=0; i<100; i++){
            System.out.println("Main--"+"--"+i);
        }
    }
}

/**
 * 每个线程都有自己的名字 Grammar.Thread-编号（从0开始）
 * Grammar.Thread.currentThread() 获取当前线程对象，静态方法
 * getName() 获取线程名称
 * 设置线程名称： setName() 或者 调用 super(name)
 */
class Thread_3 extends Thread{
    private String name;
    Thread_3(String name){
        super(name); // 调用父类的构造方法，设置线程名称
    }

    public void run(){
        //todo: 局部变量 i， 在每一个线程中都有 独立的一份
        for (int i=0; i<100; i++){
            //todo:线程都有自己的名称 Grammar.Thread.currentThread()取当前运行线程
            System.out.println("线程--"+(Thread.currentThread() == this)+"........."+this.getName()+"--"+this.getPriority()+"---"+name+"--"+i);
        }
    }
}


/************************************单例模式*******************************************/

class SingleDemo{
    public static void main(String[] args) {

    }
}


/**
 * 饿汉式
 */
class Single{
    private static final Single s = new Single();

    private Single(){

    }

    public static Single getInstance(){
        return s;
    }
}


/**
 * 懒汉式： 特点实例的延迟加载
 * 在多线程访问时存在安全隐患，所以用  synchronized
 *
 * 面试： 单例懒汉式
 * - 特点：类的延迟加载
 * - 有什么问题： 多线程访问时会有问题
 * - 怎么解决：加同步锁
 * - 什么锁： 字节码文件队对象 类名.class
 * - 性能问题：判断锁，有性能消耗
 * - 怎么优化性能问题：在同步代码块外面再加一个 判断，避免检查锁
 * */
class Single2{
    private static  Single2 s = null;

    private Single2(){

    }

    /**
     * 延迟实例化
     * @return
     */
    public  static Single2 getInstance(){
        // todo: 静态方法的锁是 类名.class
        //todo: 每次调用都要判断锁 降低性能
        if (s == null){
            // todo: 再加一层判断，避免在判断锁
            synchronized (Single2.class){
                if (s == null)
                    s = new Single2();
            }
        }
        return s;
    }
}

/*****************************************死锁问题******************************************/

/**
 * 死锁：
 * 同步中嵌套同步，锁却不同
 *
 */
class DeadLock{
    public static void main(String[] args) {
        //todo:进程交替运行
        Thread thread_1 = new Thread(new Lock(true));
        Thread thread_2 = new Thread(new Lock(false));
        thread_1.start();
        thread_2.start();
    }
}

class Lock implements Runnable{

    private boolean flag;

    Lock(boolean flag){
        this.flag = flag;
    }

    public void run(){
        if(flag){
            synchronized (MyLock.left){
                System.out.println("if left");
                synchronized (MyLock.right){
                    System.out.println("if right");
                }
            }
        }else{
            synchronized (MyLock.right){
                System.out.println("else left");
                synchronized (MyLock.left){
                    System.out.println("else right");
                }
            }
        }
    }
}

class MyLock{
    public static Object left = new Object();
    public static Object right = new Object();
}


/***********************************线程间通讯*********************************************/

/**
 * 线程通讯：等待唤醒机制
 * 多个线程在操作同一个资源，但是操作不同
 *
 *
 *- 进程 A  往对象 B 里面存（更新）内容   进程 C 从对象 B里面取内容
 * todo:问题一   A、B 读 和 取 都需要，都需要时间；这就出现问题：
 *         - B 取到重复的内容（A没来得及更新C）
 *         - B 取到新旧混合的内容（A没来的及更新完成全部内容）
 *         - B 还没取完 A就更新
 *    所有这些问题都是因为 进程 A B 共享数据
 *
 *  todo：解决方式：同步锁---->在 A B run() 方法里面加同一个锁
 *
 *
 * todo:问题二  B取得太快 取了多次重复数据
 *
 * todo：解决方式： 等待唤醒机制
 *         - 在B里面设置一个标志位 flag
 *         - 如果A发现 flag=false A更新C里面的数据， flag=true； A唤醒C； A.wait(). 如果A发现 flag=true 继续 wait()
 *         - 如果C发现 flag=true B 取 C里面的数据， flag=false；C唤醒A；  C。wait(). 如果C发现 flag=false 继续wait()
 *
 *
 *
 * wait  notify  notifyAll 都用在同步中，因为要对持有监视器（锁）的线程操作
 * 所以要使用到同步中，因为只有同步才有锁
 *
 * 为什么操作线程的方法要定义在 Object中？
 * - 因为这些方法在操作同步中线程时，都必须要标识他们所操作的线程持有的锁
 * - 只有在同一个锁上等待的线程，可以在同一个锁上的notify唤醒
 * - 不可以对不同锁中线程进行唤醒
 * -  也就是 wait notify 必须是在同一个锁上
 * - 锁可以是任意对象，因此可以被任意对象调用的方法 定义在Object中
 */
class TX{
    public static void main(String[] args) {
        Res res = new Res();
        new Thread(new Input(res)).start();
        new Thread(new Output(res)).start();
    }
}


class Res{
    String name;
    String sex;
    Boolean flag = false;

    /**
     * 设置资源
     * todo：锁是this
     * @param name
     * @param sex
     */
    public synchronized void set(String name, String sex){
        if (flag)
            try {
                this.wait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        this.name = name;
        this.sex = sex;
        flag = true;
        this.notify();  // todo:唤醒线程池中的线程 notifyAll()唤醒所有

    }

    /**
     * 取资源输出
     */
    public synchronized void out(){
        if (!flag)
            try {
                this.wait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        System.out.println("name="+name+"..."+sex);
        flag = false;
        this.notify();  // todo:唤醒线程池中的线程 notifyAll()唤醒所有
    }
}

class Input implements Runnable{
    private Res r;
    Input(Res r){
        this.r = r;
    }
    public void run(){
        int x = 1;
        while (true){
            if(x == 1){
                r.set("男", "小刚");  // 同步方法
            }else{
                r.set("女", "小红");  // 同步方法
            }
            x = (x+1)%2;
        }
    }
}

class Output implements Runnable{
    private Res r;
    Output(Res r){
        this.r = r;
    }
    public void run(){
        while (true){
            //todo: name=小红...男
            //todo: name=小刚...女
            r.out();  // 同步方法
        }
    }
}

/***************************************生产者消费者***********************************************/

class ProducerConsumer{
    public static void main(String[] args) {
        Resource resource = new Resource();

        Produce produce = new Produce(resource);
        Consumer consumer = new Consumer(resource);

        //todo:多个生产者 多个消费者。 保证生产一条 消费一条
        Thread thread_p = new Thread(produce);
        Thread thread_p2 = new Thread(produce);
        Thread thread_c = new Thread(consumer);
        Thread thread_c2 = new Thread(consumer);
        thread_p.start();
        thread_p2.start();
        thread_c.start();
        thread_c2.start();
    }
}


class Resource{
    private String name;
    private int count = 0;
    private boolean flag = false;

    public synchronized void set(String name){

        /**
         * 如果 flag=true if只会判断一次
         * 如果 flag=true while会无限判断。 这就避免了 消费者把 flag=false后，
         * 2个生产者 在 flag=false 这个阶段；同时通过 flag=false 判断，进行后面的生产代码
         * 其中一个生产者 生产一个消息 设置 flag=true, 而此时另一个生产者在之前 已经通过 flag=false
         * 此时即使  flag=true 已经为 true ，它已经不需要判断，然后它生产另一条消息
         * 然后消费者 消费了各个生产的2条消息的其中一条 flag=false ===> 另一条消息就不会被消费了
         *
         * todo: if ===> while 就避免了这种情况，
         *
         *todo：然而 前一个生产者 生产完之后 进入等待wait
         * 然后notify 唤起线程池中的一个线程（此时线程池中第一个处于wait的 线程是第二个生产者。消费者处于后面的优先级 ，诶呦唤起）
         * 第二个生产者 醒来之后 flag=true，进入 while循环，卡主
         * 所以用notiAll 唤起所有进程
         *
         * ===》 多个生产者+多个消费者 ==》 while+ notifyAll
         */
//        if (flag)
        while (flag)
            try {
                this.wait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        this.name = name+"..."+count++;
        System.out.println(Thread.currentThread()+"..生产者.."+this.name);
        flag = true;
//        this.notify();
        this.notifyAll();
    }

    public synchronized void out(){
//        if (!flag)
        while (!flag)
            try {
                this.wait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        System.out.println(Thread.currentThread()+"..消费者.."+name);
        flag = false;
//        this.notify();
        this.notifyAll();
    }
}

/**
 * 生产者
 */
class Produce implements Runnable{
    private Resource resource;
    Produce(Resource res){
        resource = res;
    }

    public void run(){
        while (true){
            resource.set("job");
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private Resource resource;
    Consumer(Resource res){
        resource = res;
    }

    public void run(){
        while (true){
            resource.out();
        }
    }
}


/************************************************Lock**************************************************/

/**
 * JDK1.5 之后提供了多线程的升级解决方案： 显示的锁机制 显示的对象等待唤醒
 * 将同步synchronized 替换成了 实现 Lock 操作
 * 将 Object中的 wait notify notifyAll 替换成了 Condition 对象
 *
 * 这个示例中实现了 本方 只唤起 对方，而不是 唤起全部进程。
 * 避免了多个进程的争用，导致性能下降
 */
class ProducerConsumer_Lock{
    public static void main(String[] args) {
        Resource_Lock resource = new Resource_Lock();

        Produce_Lock produce = new Produce_Lock(resource);
        Consumer_Lock consumer = new Consumer_Lock(resource);

        //todo:多个生产者 多个消费者。 保证生产一条 消费一条
        Thread thread_p = new Thread(produce);
        Thread thread_p2 = new Thread(produce);
        Thread thread_c = new Thread(consumer);
        Thread thread_c2 = new Thread(consumer);
        thread_p.start();
        thread_p2.start();
        thread_c.start();
        thread_c2.start();
    }
}


class Resource_Lock{
    private String name;
    private int count = 0;
    private boolean flag = false;
    private ReentrantLock lock = new ReentrantLock(true);
    // todo: 锁可以有多个 condition ，加锁 解锁粒度更细
    private Condition condition_producer = lock.newCondition();
    private Condition condition_consumer = lock.newCondition();

    public  void set(String name) throws InterruptedException{
        lock.lock(); // 枷锁
        try {
            while (flag)
                condition_producer.await(); //todo： 生产者挂起
            this.name = name+"..."+count++;
            System.out.println(Thread.currentThread()+"..生产者.."+this.name);
            flag = true;
            condition_consumer.signal(); //todo： 唤醒 消费者  不需要用signalAll唤起所有
        }finally {
            lock.unlock(); // 解锁，一定要执行
        }
    }

    public  void out() throws InterruptedException{
        lock.lock(); // 枷锁
        try{
            while (!flag)
                condition_consumer.await(); //todo:挂起消费者
            System.out.println(Thread.currentThread()+"..消费者.."+name);
            flag = false;
            condition_producer.signal();  //todo: 唤起生产者 不需要用signalAll唤起所有
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者
 */
class Produce_Lock implements Runnable{
    private Resource_Lock resource;
    Produce_Lock(Resource_Lock res){
        resource = res;
    }

    public void run(){
        while (true){
            try{
                // resource抛出了异常，这里要catch
                resource.set("job");
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer_Lock implements Runnable{
    private Resource_Lock resource;
    Consumer_Lock(Resource_Lock res){
        resource = res;
    }

    public void run(){
        while (true){
            try{
                // resource抛出了异常，这里要catch
                resource.out();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

/****************************************停止线程**************************************************/

/**
 * 停止线程的方法只有一种： run() 方法结束
 *
 * 开启多线程通常都是循环结构，只要控制住循环就可以让线程结束
 *
 * todo:特殊情况：
 * 当线程处于冻结状态，就不会读取到标记flag ，那么线程就不会结束
 *当没有指定的方式让冻结的线程恢复到运行状态时，需要手动取消线程的冻结状态，是指恢复的运行状态
 * 强制恢复线程运行，这样就可以操作 flag让线程结束
 * Grammar.Thread 类中提供了该方法： interrupt()
 */
class StopThread{
    public static void main(String[] args) {
        ST st = new ST();


        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);

//        t1.setDaemon(true); // 设置t1为 守护进程。当所有前台进程结束，后台(守护)进程自动结束
        t1.start();
        t2.start();

        int num = 0;
        while (num++ < 1000){
            if (num > 10){
                st.change(); //todo:循环结束---线程结束
                t1.interrupt(); // todo: 将冻结的进程，强行恢复到运行状态
                t2.interrupt(); // todo: 将冻结的进程，强行恢复到运行状态
                break;
            }
            System.out.println("Main..."+Thread.currentThread().getName());
        }
    }
}


class ST implements Runnable{
    boolean flag = true;
    public synchronized void run(){
        while (flag){
            try {
                wait();
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"......线程异常！");
                //todo: 有异常发生，说明有强制恢复到运行状态，让 flag=false， 结束run()方法--结束线程
                flag = false;
            }
            System.out.println(Thread.currentThread().getName()+"......线程运行中！");
        }
    }

    public void change(){
        flag = false;
    }
}

/************************************join*****************************************/

/**
 * join 的特点：
 * 当A线程执行到了 B 线程的 join() 方法时， A就会等待
 * 等 B 线程执行完成， A 才会执行
 *
 * join 可以用来临时加入线程执行
 */
class JoinTest{
    public static void main(String[] args) throws Exception {
        join join = new join();

        Thread thread = new Thread(join);
        Thread thread2 = new Thread(join);
        thread.start();
        //todo: 抢过当前线程的执行权，自己来执行
//        thread.join();

        thread.setPriority(Thread.MAX_PRIORITY); // 设置优先级 一共10级， 1  5  10 最特殊
        thread2.start();
    }
}

class join implements Runnable{
    public void run(){
        int i = 0;
        while (i++ < 100){
            System.out.println(Thread.currentThread()+"................"+"......进程"+i);
            Thread.yield(); //todo： 临时，交出进程的执行权
        }
    }
}

/****************************************多线程的使用**************************************/

/**
 * 3 个线程交替运行
 */
class useThread{
    public static void main(String[] args) {

        //todo:线程 1
        // 匿名类
        new Thread(){
            public void run(){
                for (int i=0; i<1000; i++){
                    System.out.println("two Grammar.Thread"+Thread.currentThread().getName()+"..."+i);
                }
            }
        }.start();
        //todo:线程 2
        Runnable run = new Runnable(){
            public void run(){
                for (int i=0; i<1000; i++){
                    System.out.println("three Grammar.Thread"+Thread.currentThread().getName()+"..."+i);
                }
            }
        };
        new Thread(run).start();

        //todo:线程 3
        for (int i=0; i<1000; i++){
            System.out.println("one Grammar.Thread"+Thread.currentThread().getName()+"..."+i);
        }
    }
}