package Grammar.Collection;

import Utils.Dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 高级for循环
 * todo：个数
 *  for(数据类型 变量名：集合或（Grammar.Collection）者数组)
 *
 *  for对集合遍历的时候，只能获取元素，不能对集合进行操作
 *
 *  迭代器处理遍历，还可以进行remove的操作
 *  如果使用ListIterator 还可以在遍历过程中进行增删改查操作
 *
 *
 *  普通for 和增强的 for区别？
 *   - 增强for必须要有遍历对象
 *   - 数组变量推荐用普通for
 */
public class For {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add("qwe");
        strings.add("asdf");
        strings.add("asdfzx");

        for (String s : strings){
            Dump.dump(s);
        }


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "qqq");
        hashMap.put("2", "eee");

        for (String key: hashMap.keySet()){
            Dump.dump(hashMap.get(key));
        }

        for (Map.Entry<String, String> entries : hashMap.entrySet()){
            Dump.dump(entries.getKey()+".............."+entries.getValue());
        }


        params(1);
        params(1,2);
    }

    //todo: 可变参数，一定要定义在参数列表的最后面
    public static void params(int... params){
        // 得到 数组
        for (int param: params){
            Dump.dump(param);
        }
    }
}



