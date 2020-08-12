package Grammar.Basic.Thread.ReturnValue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过线程池获取返回值
 *
 * 把 callable 提交给线程池； 返回一个 Future； 可以提交多个
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //todo: 线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> task = pool.submit(new MyCallable());
        if (task.isDone())
            try {
                System.out.println(task.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }finally {
                pool.shutdown();
            }

    }
}
