package Grammar.ClassGrammar;

import java.util.concurrent.CyclicBarrier;

/**
 * todo: CyclicBarrier的目的是让N多线程互相等待直到所有的都达到某个状态，然后这N个线程再继续执行各自后续
 */
public class TestCyclicBarrier {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        new TestCyclicBarrier().begin();
    }

    public void begin(){
        for (int i=0; i<5; i++){
            new Thread(new Student()).start();
        }
    }

    private class Student implements Runnable{
        @Override
        public void run(){
            try {
                Thread.sleep(1000); // 学生都在上学路上
                cyclicBarrier.await(); // 到了就等着，学生都到齐了就开始吃饭
            }catch (Exception e){
                e.printStackTrace();
            }

            //todo: 大家都到了，开始吃饭
        }
    }
}
