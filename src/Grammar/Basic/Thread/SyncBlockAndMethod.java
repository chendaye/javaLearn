package Grammar.Basic.Thread;

public class SyncBlockAndMethod {
    public void syncTask(){
        synchronized (this){
            System.out.println("hello");
            //todo: 可重入锁
            //todo: 当一个个对象再次访问自己持有对象锁的临界资源时就叫重入
            synchronized (this){
                System.out.println("hello");
            }
        }
    }

    public synchronized void sync2Task(){
        System.out.println("world");
    }

    public static void main(String[] args) {
        SyncBlockAndMethod obj = new SyncBlockAndMethod();

        obj.syncTask();
        obj.sync2Task();
    }
}
