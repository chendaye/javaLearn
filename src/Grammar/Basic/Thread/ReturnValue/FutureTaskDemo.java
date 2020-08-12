package Grammar.Basic.Thread.ReturnValue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 直接 把 callable 提交给 FutureTask
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //todo: FutureTask 继承 RunnableFuture； RunnableFuture 继承 Runnable, Future<V>
        FutureTask<String> task = new FutureTask<>(new MyCallable());

        new Thread(task).start();

        if (task.isDone())
            System.out.println(task.get());

    }
}
