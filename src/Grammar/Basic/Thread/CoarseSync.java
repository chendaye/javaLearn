package Grammar.Basic.Thread;

public class CoarseSync {
    public static void main(String[] args) {
        int i=0;
        StringBuffer buffer = new StringBuffer();

        while (i<100){
            //StringBuffer 是线程安全的； 循环里面会反复加锁 解锁
            // 这是 jvm 就是进行锁粗化，这样就只用加一个锁
            buffer.append("222");
            i++;
        }
    }
}
