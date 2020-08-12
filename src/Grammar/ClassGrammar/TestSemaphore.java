package Grammar.ClassGrammar;

import java.util.concurrent.Semaphore;

/**
 * todo: 控制访问资源的线程数
 */
public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5); //机器数目5个，即5个许可
        for (int i=0; i<8; i++){
            new Worker(i, semaphore).start(); // 8个工人去争
        }
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run(){
            try {
                semaphore.acquire(); // 获取许可
                Thread.sleep(1000); // 执行
                semaphore.release(); // 释放许可
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
