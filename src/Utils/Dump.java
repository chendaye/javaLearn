package Utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public  class Dump {

    public static <T> void dump(T object){
        System.out.println(object);
    }

    /**
     * 遍历集合
     * @param object
     * @param <T>
     */
    public static <T extends Collection> void iterator(T object){
        Iterator iterator = object.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next()+ " ");
        }
        dump(stringBuilder.toString());
    }

    /**
     * 遍历数组的几种方式
     * https://juejin.im/post/5e042ba5518825127324b275 优雅的打印数组
     * @param items
     * @param isString
     */
    public static void array(int[] items, boolean isString){
        if (!isString)
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        else
            System.out.println(Arrays.toString(items));
    }

    public static void array(int[] items){
        System.out.println(Arrays.toString(items));
    }

    public static void array(char[] items, boolean isString){
        if (!isString)
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        else
            System.out.println(Arrays.toString(items));
    }

    public static void array(char[] items){
        System.out.println(Arrays.toString(items));
    }

    public static void array(Object[] items){
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < items.length; i++) {
            str.append(items[i]);
            str.append(", ");
        }
        str.deleteCharAt(str.length()-1);
        str.append(']');

        String s = str.toString();
        System.out.println(s);
    }



    public static <T> void array1(T[] items, boolean isString){
        if (!isString)
            for (T item : items) {
                System.out.println(item);
            }
        else
            System.out.println(items.toString());
    }
    public static <T> void array2(T[] items, boolean isString){
        if (!isString)
            Arrays.asList(items).forEach(item -> System.out.println(item));
        else
            System.out.println(items.toString());
    }
    public static <T> void array3(T[] items, boolean isString){
        if (!isString)
            Arrays.asList(items).forEach(System.out::println);
        else
            System.out.println(items.toString());
    }


}
