package Grammar.Collection;

import Utils.Dump;


import java.util.*;

/**
 * Map集合：该集合存储键值对，保证唯一性
 *      - 添加
 *          - put(K key, V value)
 *          - putAll(Map<? extents K, ? extents V>m)
 *      - 删除
 *          - clear()
 *          - remove(Object key)
 *      - 判断
 *          - containsValue(Object value)
 *          - containsKey(Object key)
 *          - isEmpty()
 *      - 获取
 *          - get(Object key)
 *          - values()
 *          - entrySet()
 *              - 将map集合中的映射关系存到set中，这种关系的数据类型是 Map.Entry
 *          - keySet()
 *              - 获取map的所有键值，返回一个set，因为set有迭代器， 所以可以迭代set，用get方法获取map所有值
 *              - map集合的去除原理：将map转成set，再通过迭代器取出
 *   Map
 *    |--HashTable:底层是哈希表，不可以存 null键 null值。 该集合时线程同步的
 *    |--HashMap： 底层是hash表，允许存 null键 null 值。该集合时不同步的
 *    |--TreeMap： 底层是二叉树，线程不同步。可以用于给map集合中的键进行排序
 *
 *   和 Set很像， Set的底层就是使用了Map集合
 */
class MapCode {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("a", "aaabbb");  // 键一样会覆盖旧值，并将旧值返回
        map.put("b", "bbb");
        map.put("c", null); // 可以存空值
        dump(map.containsKey("a"));
        dump(map.get("a"));
        dump(map.get("c"));

        Collection<String> values = map.values();
        dump(values);

        //todo: keySet
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()){
            dump(map.get(iterator.next()));
        }

        //todo: entrySet

        Set<Map.Entry<String, String>> entries = map.entrySet();

        Iterator<Map.Entry<String, String>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> next = iterator1.next();
            dump(next.getKey()+"-----------"+next.getValue());
        }

    }

    public static void dump(Object object){
        System.out.println(object);
    }
}


/*******************************************练习 HashMap*********************************************/

class Student2 implements Comparable<Student2>
{
    private String name;
    private int age;
    Student2(String name,int age)
    {
        this.name = name;
        this.age = age;
    }

    // todo: implements Comparable 并实现 compareTo； 因为如果类要存到 TreeSet 或者 TreeMap中 要排序，所以
    //todo: 如果没有实现 compareTo，将类存入 二叉树结构的容器中会报错
    public int compareTo(Student2 s)
    {
        int num = new Integer(this.age).compareTo(new Integer(s.age));

        if(num==0)
            return this.name.compareTo(s.name);
        return num;
    }

    //todo: 如果类要存在 hash结构中，就要实现 hashCode() 和 equals() 方法
    //todo： 如hashSet  hashMap
    public int hashCode()
    {
        return name.hashCode()+age*34;
    }
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Student2))
            throw new ClassCastException("类型不匹配");

        Student2 s = (Student2)obj;

        return this.name.equals(s.name) && this.age==s.age;


    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String toString()
    {
        return name+":"+age;
    }
}



class  MapTest
{
    public static void main(String[] args)
    {
        HashMap<Student2,String> hm = new HashMap<Student2,String>();

        hm.put(new Student2("lisi1",21),"beijing");
        hm.put(new Student2("lisi1",21),"tianjin");
        hm.put(new Student2("lisi2",22),"shanghai");
        hm.put(new Student2("lisi3",23),"nanjing");
        hm.put(new Student2("lisi4",24),"wuhan");



        Set<Student2> keySet = hm.keySet();

        Iterator<Student2> it = keySet.iterator();

        while(it.hasNext())
        {
            Student2 stu = it.next();
            String addr = hm.get(stu);
            System.out.println(stu+".."+addr);
        }


        Set<Map.Entry<Student2,String>> entrySet = hm.entrySet();

        Iterator<Map.Entry<Student2,String>> iter = entrySet.iterator();

        while(iter.hasNext())
        {
            Map.Entry<Student2,String> me = iter.next();
            Student2 stu = me.getKey();
            String addr = me.getValue();
            System.out.println(stu+"........."+addr);
        }
    }
}

/********************************TreeMap*****************************************/

/**
 * 自定义比较器
 */
class StuNameComparator implements Comparator<Student2>
{
    public int compare(Student2 s1,Student2 s2)
    {
        int num = s1.getName().compareTo(s2.getName());
        if(num==0)
            return new Integer(s1.getAge()).compareTo(new Integer(s2.getAge()));

        return num;
    }
}


class  MapTest2
{
    public static void main(String[] args)
    {
        TreeMap<Student2,String> tm = new TreeMap<Student2,String>(new StuNameComparator());

        tm.put(new Student2("blisi3",23),"nanjing");
        tm.put(new Student2("lisi1",21),"beijing");
        tm.put(new Student2("alisi4",24),"wuhan");
        tm.put(new Student2("lisi1",21),"tianjin");
        tm.put(new Student2("lisi2",22),"shanghai");


        Set<Map.Entry<Student2,String>> entrySet = tm.entrySet();

        Iterator<Map.Entry<Student2,String>> it = entrySet.iterator();

        while(it.hasNext())
        {
            Map.Entry<Student2,String> me = it.next();

            Student2 stu = me.getKey();
            String addr = me.getValue();
            System.out.println(stu+":::"+addr);
        }
    }
}

/**
 * 当数据中存在映射关系，可以考虑使用 Map集合
 */
class  MapTest3
{
    public static void main(String[] args)
    {
        String s= charCount("ak+abAf1c,dCkaAbc-defa");
        System.out.println(s);
    }

    public static String charCount(String str)
    {
        char[] chs = str.toCharArray();

        TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>();


        int count = 0;
        for(int x=0; x<chs.length; x++)
        {


            if(!(chs[x]>='a' && chs[x]<='z' || chs[x]>='A' && chs[x]<='Z'))
                continue;

            Integer value = tm.get(chs[x]);


            if(value!=null)
                count = value;
            count++;
            tm.put(chs[x],count);

            count = 0;
			/*
			if(value==null)
			{
				tm.put(chs[x],1);
			}
			else
			{
				value = value + 1;
				tm.put(chs[x],value);
			}
			*/


        }

        //System.out.println(tm);

        StringBuilder sb = new StringBuilder();

        Set<Map.Entry<Character,Integer>> entrySet = tm.entrySet();
        Iterator<Map.Entry<Character,Integer>>  it = entrySet.iterator();

        while(it.hasNext())
        {
            Map.Entry<Character,Integer> me = it.next();
            Character ch = me.getKey();
            Integer value = me.getValue();
            sb.append(ch+"("+value+")");
        }



        return sb.toString();
    }

}

/******************************************集合嵌套***********************************************/

/**
 * 集合嵌套
 */
class Nested{
    public static void main(String[] args) {
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("01", 18);
        map1.put("02", 18);

        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("01", 18);
        map2.put("02", 18);

        HashMap<String, HashMap<String, Integer>> stringHashMapHashMap = new HashMap<>();
        stringHashMapHashMap.put("map1", map1);
        stringHashMapHashMap.put("map2", map2);
    }
}


/****************************************工具类****************************************************/

class CollectionUtilsCode{
    public static void main(String[] args) {
//        sort();
//        search();
        reverse();
    }

    public static void sort(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ddd");
        strings.add("bb");
        strings.add("a");
        Dump.dump(strings);
        //Todo: 集合工具类
        Collections.sort(strings);
//        Dump.dump(strings);

        //todo: 使用比较器 按长度比较
        Collections.sort(strings, new LenCompare());
        Dump.dump(strings);

        //todo： max
        Dump.dump(Collections.max(strings)); // list元素必须具有可比性

        Dump.iterator(strings);
    }


    public static void search() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ddd");
        strings.add("bb");
        strings.add("a");
        // 先排序
        Collections.sort(strings);
        // 在二分搜索
        Dump.dump(Collections.binarySearch(strings, "bb"));

    }

    /**
     * 集合逆序
     */
    public static void reverse(){
        // 逆向比较器
//        TreeSet<String> strings = new TreeSet<>(Collections.reverseOrder());
        // 情形逆转比较器 new reserve() 的排序规则
        TreeSet<String> strings = new TreeSet<>(Collections.reverseOrder(new reserve()));
//        TreeSet<String> strings = new TreeSet<>(new reserve());
        strings.add("bbb");
        strings.add("aaa");
        strings.add("ccc");
        Dump.iterator(strings);
    }

}

/**
 * 按字符串长度比较
 */
class  LenCompare implements Comparator<String> {
    public int compare(String s1, String s2){
        if (s1.length() == s2.length())
            return 0;
        if (s1.length() > s2.length())
            return 1;
        return -1;
    }
}

/**
 * 逆序比较器
 */
class  reserve implements Comparator<String> {
    public int compare(String s1, String s2){
//        int i = s1.compareTo(s2);
//        return Math.multiplyExact(i, -1);
        return s2.compareTo(s1);
    }
}

/************************************Arrays***************************************/

/**
 * Arrays
 * 用于操作数组的工具类
 */
class ArraysUtilsCode{
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
//        Dump.dump(Arrays.toString(a));

        //todo: 将 数组变成 list
        //todo: 数组 -》List，可以使用集合的方法对元素进行操作
        // todo: 转换之后不能使用 List的增删方法，因为数组的长度是固定的
        String[] s = {"qqq", "ddd", "sdf"};
        List<String> asList = Arrays.asList(s);
        Dump.iterator(asList);

        //todo:如果数组中的元素都是对象，那么变成集合时，数组中的元素就直接转为集合元素
        //todo:如果数组中的元素是基本数据类型， 那么会将整个数组作为集合的一个元素
        List<int[]> ints = Arrays.asList(a);
        Dump.dump(ints);

        Integer[] num = {1,2,3,4 };
        List<Integer> integers = Arrays.asList(num);
        Dump.iterator(integers);
    }
}

/****************************************集合边数组**********************************************************/

/**
 * 为什么要集合变成数组？
 * 为了限定对元素的操作（增删），因为数组的长度固定
 */
class CollectionToArray{
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add("qwe");
        strings.add("rty");
        strings.add("uio");

        //todo:当之string类型的数组  小于集合的size，那么集合方法内部会创建一个新的数组，长度为size
        //todo:当之string类型的数组  大于集合的size，不会创建新数组，而是使用传进来的数组
        String[] strings1 = strings.toArray(new String[strings.size()]);
        Dump.dump(strings1.length);
    }
}

/******************************************单键值对类型********************************************************/

/**
 * todo:单键值对类型 new AbstractMap.SimpleEntry<String, String>
 */
class SignleMap{
    public static void main(String[] args) {
        Map.Entry<String,String> entry = new AbstractMap.SimpleEntry<String, String>("name", "野猿新一");
        System.out.println("new AbstractMap.SimpleEntry:" + entry);
        System.out.println("getKey:" + entry.getKey());
        System.out.println("getValue:" + entry.getValue());
        entry.setValue("野猿新二");
        System.out.println("setValue:" + entry);
    }
}