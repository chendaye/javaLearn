package Algorithm;

import Utils.Dump;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;


public class WhiteboardCode {
    public static void main(String[] args) throws FileNotFoundException {
        //todo: 输入输出
        Scanner scanner = new Scanner(System.in);

        //todo: Int
        Integer i = 1;
        String s = Integer.toString(222);
        int i1 = Integer.parseInt("222");
        int minValue = Integer.MIN_VALUE;
        int minValue1 = Integer.MIN_VALUE;

        //todo: String
        Character c = 'a';
        String str = "abcdef";
        char[] chars = str.toCharArray();
        char c1 = str.charAt(1);
        int length = str.length();
        String abc = str.replace("abc", "");
        String substring = str.substring(0, 3); // [l, r)
        boolean yyy = str.equals("yyy");
        int i2 = str.indexOf("bc");
        int i3 = str.indexOf('c');
        //todo: 正则
        str.startsWith("a");
        str.endsWith("a");
        // ^ 【开始】 $ 【结束】  . 【任意】 *【>= 0】  + 【>=1】  ? 【0<= <= 1】
        // {n} 【=n】 {n,} 【>=n】 {n,m} 【>= n  <= m】
        // 此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串
        // x|y [xy|c] [a-z] [^a-z]
        // \d 等效 [0-9] \D 等效 【^0-9】  \w 等效 [A-Za-z0-9] \W 等效 [A-Za-z0-9]
        // \s \S 	 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
        boolean matches = Pattern.matches("^aa$", str);
        boolean matches1 = str.matches("^aaa$");
        System.out.println(i2);

        //todo: 进制转换
        Integer.toBinaryString(10); // 2
        Integer.toOctalString(10); // 8
        Integer.toHexString(10); // 16

        //todo: String -> int
        Integer.parseInt("123");
        //todo: char -> int
        Integer.parseInt(String.valueOf('2'));
        System.out.println('7' - 48);

        //todo: 数组
        int[] arr = {1,2,3,4,5,6,7,8,9};
        Arrays.sort(arr);
        Arrays.sort(arr, 0, 3);
        int[] ints = Arrays.copyOf(arr, arr.length);
        Arrays.copyOfRange(arr, 0, 4);

        //todo: ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        Collections.sort(list, (a, b) ->{
            return a - b;
        });

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(9);
        list.add(3, 2); // 修改
        list.get(1);
        list.contains(10);
        list.size();
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list.addAll(list1);
        list.remove(2);
        list.clear();

        //todo: Queue http://itmyhome.com/java-api/
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1); // addFirst  addLast
        queue.addAll(list);
        queue.contains(1);
        queue.get(0); // getLast() getFirst()
        queue.element(); //获取但不移除此列表的头（第一个元素）
        queue.peek(); // 获取但不移除此列表的头（第一个元素） peekFirst()  peekLast()
        queue.poll(); //    获取并移除此列表的头（第一个元素） 	pollFirst() pollLast()
        queue.remove();//     获取并移除此列表的头（第一个元素）。 remove(int index) remove(Object o) removeFirst() removeLast()
        System.out.println(queue.getFirst()+"----");
        PriorityQueue<Integer> queue1 = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return a - b;
            }
        });

//        PriorityQueue<Integer> queue2 = new PriorityQueue<>((a, b)->{
//            return a - b;
//        });
        // add element offer peek poll remove
        queue1.contains(6);
        queue1.add(2);
        queue1.remove(0);
        queue1.contains(2);
        queue1.size();
        queue1.isEmpty();
        queue1.offer(2);

        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.add('a');
        linkedList.add('b');
        linkedList.addFirst('c');
        linkedList.addLast('d');
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.remove(0);
        Dump.iterator(linkedList);

        //todo: ArrayList
        ArrayList<Character> arrayList = new ArrayList<>();
        arrayList.add('a');
        arrayList.add('b');
        arrayList.add('c');
        arrayList.remove(0);
        //Dump.iterator(arrayList);





        //todo: 栈
        Stack<Integer> stack = new Stack<>();
        stack.empty();
        stack.isEmpty();
        stack.peek(); // 查看堆栈顶部的对象，但不从堆栈中移除它。
        stack.pop(); //  移除堆栈顶部的对象，并作为此函数的值返回该对象。
        stack.push(2); // 把项压入堆栈顶部。

        //todo: HashMap
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.clear();
        hashMap.containsKey(22);
        hashMap.containsValue(222);
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        // 遍历方式
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()){
            entry.getValue();
            entry.getKey();
        }
        for(Integer key : hashMap.keySet()){}
        for (Integer val : hashMap.values()){}
        Iterator<Map.Entry<Integer, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
        }
        hashMap.get(1);
        hashMap.isEmpty();
        Set<Integer> integers = hashMap.keySet();
        Collection<Integer> values = hashMap.values();
        hashMap.remove(1); //   从此映射中移除指定键的映射关系（如果存在）。
        hashMap.size(); // 返回此映射中的键-值映射关系数。

        //todo: LinkedHashMap 有序 HashMao
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        // clone, containsKey, entrySet, isEmpty, keySet, put, putAll, remove, size, values
    }
}
