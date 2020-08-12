package Grammar.Basic.Thread.ReturnValue;

public class CycleWait implements Runnable {
    private String value;

    @Override
    public void run(){
        try {
            Thread.currentThread().sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        value = "have value";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cycleWait = new CycleWait();
        Thread thread = new Thread(cycleWait);
        thread.start();
//        // 如果子线程 没有执行 给value 的赋值操作 就等待
//        while (cycleWait.value == null){
//            Grammar.Thread.currentThread().sleep(100);
//        }

        //使用 Grammar.Thread.join() 阻塞当前线程，以等待子线程的完成
        thread.join();
        System.out.println(cycleWait.value);
    }
}
