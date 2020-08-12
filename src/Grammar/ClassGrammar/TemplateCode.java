package Grammar.ClassGrammar;

/**
 * 需求：获取一段程序运行的时间
 * 原理：结束时间 - 开始时间
 */
public class TemplateCode {
    public static void main(String[] args) {
        getTime getTime = new subGetTime();

        getTime.time();
    }
}


abstract class getTime{
    public final void time(){
        long start = System.currentTimeMillis();
        run();
        long end = System.currentTimeMillis();

        System.out.println("\n");
        System.out.println("运行时间 = "+ (end - start));
    }

    /**
     * 模板方法模式：
     * 定义一个功能时，一部分是确定的，一部分是不确定的。那么将不确定的暴露出去
     * 由该类的子类取完成
     *
     *
     * 可变部分提出来
     * 子类只需要复写这run 方法
     *
     * 这种思路就可以完成一类问题：
     *
     */
    public abstract void run();
}

class subGetTime extends getTime{
    public void run(){
        for (int i = 0; i< 20000; i++){
            System.out.print(i);
        }
    }
}