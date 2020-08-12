package Grammar.Basic.Collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SafeHashMap {
    public static void main(String[] args) {
        //todo: HashTable 类似的原理
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> synchronizedMap = Collections.synchronizedMap(hashMap);

        synchronizedMap.put("a", "aaa");
        synchronizedMap.put("b", "bbb");
        System.out.println(synchronizedMap.get("a"));
    }
}
