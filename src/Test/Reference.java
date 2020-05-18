package Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {
    public static void main(String[] args) {
        //todo: 强引用
        Object obj1 = new Object();

        //todo: 软引用, softReference 是 obj2 的软引用
        Object obj2 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj2); // 缓存
        obj2 = null;
        softReference.get(); // 有时候会返回空

        //todo: 弱引用
        Object obj3 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj3);
        obj3=null;
        weakReference.get(); // 有时候返回空
        boolean enqueued = weakReference.isEnqueued(); // 返回是否被垃圾回收器标记为垃圾

        //todo: 虚引用
        Object obj4 = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj4, new ReferenceQueue<>());
        obj4=null;
        phantomReference.get(); // 永远返回null
        boolean enqueued1 = phantomReference.isEnqueued(); // obj4是否已经被 在内存中删除
    }
}
