package Grammar.Thread;

/**
 * todo: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 *       其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 */
class myRunnable implements Runnable {
    @Override
    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 5; i++){
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+":loop="+i);
                }
            } catch (InterruptedException e){

            }
        }
    }

    public static void main(String[] args) {
        myRunnable myRunnable = new myRunnable();
        //todo: t1 t2 获取的都是 myRunnable 这个对象锁（同一把锁 t1 t2 会相互阻塞）
        Thread t1 = new Thread(myRunnable, "t1");
        Thread t2 = new Thread(myRunnable, "t2");
        t1.start();
        t2.start();
    }
}

class myRunnable2 extends Thread {
    public myRunnable2(String name){
        super(name);
    }
    @Override
    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 5; i++){
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+":loop="+i);
                }
            } catch (InterruptedException e){

            }
        }
    }

    public static void main(String[] args) {

        //todo: t1 t2 获取的是 两个不同的对象锁 （两把锁锁 t1 t2 不会相互阻塞）
        Thread t1 = new myRunnable2("t1");
        Thread t2 = new myRunnable2("t2");
        t1.start();
        t2.start();
    }
}

/**
 * todo: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块
 */

class Count1 {
    //todo: 同步方法
    public void syncMethod(){
        synchronized (this){
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " :synMethod loop = " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }

    //todo: 非同步方法
    public void nonSyncMethod(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
            }
        } catch (InterruptedException ie) {
        }
    }


    public static void main(String[] args) {
        final Count1 count1 = new Count1();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count1.syncMethod();
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count1.nonSyncMethod();
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
}

/**
 * todo: 如果同步方法 使用的是同一把锁 this
 *  当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 *  其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 */
class Count2 {
    //todo: 同步方法
    public void syncMethod(){
        synchronized (this){
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " :synMethod loop = " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }

    //todo: 非同步方法
    public void syncMethod2(){
//        synchronized (Count2.class){
        synchronized (this){
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }

    }


    public static void main(String[] args) {
        final Count2 count = new Count2();
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.syncMethod();
                    }
                }, "t1");

        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.syncMethod2();
                    }
                }, "t2");

        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
}


/**
 * todo: synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率 (花时间更少)
 */
class Demo {

    public synchronized void synMethod() {
        for(int i=0; i<1000000; i++)
            ;
    }

    public void synBlock() {
        synchronized( this ) {
            for(int i=0; i<1000000; i++)
                ;
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        long start, diff;
        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synMethod();                                // 调用“synchronized方法”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synMethod() : "+ diff);

        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synBlock();                                // 调用“synchronized方法块”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synBlock()  : "+ diff);
    }
}

//todo: 不能被同时访问。因为isSyncA()和isSyncB()都是访问同一个对象(对象x)的同步锁！
class Something {
    public synchronized void isSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public synchronized void isSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }

    public static void main(String[] args) {
        Something x = new Something();
        Something y = new Something();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                x.isSyncA();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                x.isSyncB();
            }
        }, "t2");
        //todo: t1 t2 同一把锁
        t1.start();
        t2.start();
    }
}

//todo: 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
// LockTest2.java的源码
class Something2 {
    public synchronized void isSyncA() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : isSyncA");
            }
        } catch (InterruptedException ie) {
        }
    }

    public synchronized void isSyncB() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : isSyncB");
            }
        } catch (InterruptedException ie) {
        }
    }

    public static synchronized void cSyncA() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : cSyncA");
            }
        } catch (InterruptedException ie) {
        }
    }

    public static synchronized void cSyncB() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : cSyncB");
            }
        } catch (InterruptedException ie) {
        }
    }

    public static void main(String[] args) {
        Something2 x = new Something2();
        Something2 y = new Something2();

        // 比较(02) x.isSyncA()与y.isSyncA()
        // 新建t21, t21会调用 x.isSyncA()
        Thread t21 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t21");

        // 新建t22, t22会调用 x.isSyncB()
        Thread t22 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.isSyncA();
                    }
                }, "t22");


        t21.start();  // 启动t21
        t22.start();  // 启动t22
    }
}

//todo: 不能被同时访问。因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，
// todo:因此它们共用一个同步锁，不能被同时访问
// LockTest3.java的源码
class Something3 {
    public synchronized void isSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public synchronized void isSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }

    public static void main(String[] args) {
        Something3 x = new Something3();
        Something3 y = new Something3();
        // 新建t31, t31会调用 x.isSyncA()
        Thread t31 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.cSyncA();
                    }
                }, "t31");

        // 新建t32, t32会调用 x.isSyncB()
        Thread t32 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.cSyncB();
                    }
                }, "t32");


        t31.start();  // 启动t31
        t32.start();  // 启动t32
    }
}


//todo: 可以被同时访问。因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；而cSyncA()是静态方法，
// todo: Something.cSyncA()可以理解对使用的是“类的锁” Class 锁。因此，它们是可以被同时访问的。
// LockTest4.java的源码
class Something4 {
    public synchronized void isSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public synchronized void isSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }

    public static void main(String[] args) {
        Something4 x = new Something4();
        Something4 y = new Something4();
        // 新建t41, t41会调用 x.isSyncA()
        Thread t41 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t41");

        // 新建t42, t42会调用 x.isSyncB()
        Thread t42 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Something4.cSyncA();
                    }
                }, "t42");


        t41.start();  // 启动t41
        t42.start();  // 启动t42
    }
}

