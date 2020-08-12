package Grammar.Basic.JVM.GC;

public class Finalization {
    public static Finalization finalization;
    @Override
    protected void finalize(){
        System.out.println("Finalization");
        finalization = this; // 将自己赋值给成员变量，让自己重生
    }

    public static void main(String[] args) {
        Finalization finalization = new Finalization();
        System.out.println("first:"+finalization);
        finalization=null; //垃圾
        System.gc(); // 垃圾回收, 触发finalize 方法
        // 主线程 休息一段时间， 让 gc执行完（执行触发finalize）
        try {
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("second:"+finalization);
        //todo: 没有 sleep 时，这里打印 null 说明 gc没有执行 finalize
        //todo: 有sleep 时， 这里非空 说明 gc 执行了 finalize
        System.out.println("three:"+finalization.finalization);
    }
}
