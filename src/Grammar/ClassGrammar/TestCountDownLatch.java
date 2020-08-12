package Grammar.ClassGrammar;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * todo:CountDownLatch目的是让一个线程等待其他N个线程到达条件后，自己再去做某个事
 *
 * 所有运动员跑完全程 再计分
 *
 */
public class TestCountDownLatch {
    private CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        testCountDownLatch.begin();
    }

    //运动员类
    private class Runner implements Runnable{
        private int result;
        public Runner(int ret){
            result = ret;
        }

        @Override
        public void run(){
            try {
                Thread.sleep(result*1000); // 模拟跑了多少秒
                countDownLatch.countDown(); // 计数器-1
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void begin(){
        System.out.println("赛跑开始");
        Random random = new Random(System.currentTimeMillis());
        // 开始跑
        for (int i=0; i<4; i++){
            int result = random.nextInt(3)+1; //随机设置运动员跑多少秒
            new Thread(new Runner(result)).start(); // 启动线程
        }

        try {
            countDownLatch.await(); // 线程等待倒数为0
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("所有运动员跑完，可以算分");

    }
}
