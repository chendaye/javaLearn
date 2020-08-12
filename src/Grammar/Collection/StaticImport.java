package Grammar.Collection;


//todo:导入  java.util.Arrays 里面的所有静态成员
//todo:当类重名时要指定具体的包名； 当方法重名时要指定具体的类
import static  java.util.Arrays.*;

/**
 * 静态导入
 */
public class StaticImport {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,};
        sort(a); //排序
    }
}
