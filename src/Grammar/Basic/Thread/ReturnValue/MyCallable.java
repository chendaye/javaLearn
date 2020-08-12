package Grammar.Basic.Thread.ReturnValue;

import java.util.concurrent.Callable;

/**
 * 通过 Callable 获取返回值
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws InterruptedException {
        String value = "callable";
        System.out.println("ready to work");
        Thread.currentThread().sleep(5000);
        System.out.println("work done");
        return value;

    }
}
