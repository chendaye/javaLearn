package Grammar.LambdaStream;

import java.util.*;
import java.util.stream.IntStream;

public class Stream {
    public static void main(String[] args) {

    }

    /**
     * 创建流
     *
     * 大部分流操作都支持 lambda 表达式作为参数，
     * 正确理解，应该说是接受一个函数式接口的实现作为参数。
     */
    public static void createStream(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream() // 创建流
                .filter(s -> s.startsWith("c")) // 执行过滤，过滤出以 c 为前缀的字符串
                .map(String::toUpperCase) // 转换成大写
                .sorted() // 排序
                .forEach(System.out::println); // for 循环打印

        //todo: 集合上调用stream()方法会返回一个普通的 Stream 流。
        Arrays.asList("a1", "a2", "a3")
                .stream() // 创建流
                .findFirst() // 找到第一个元素
                .ifPresent(System.out::println);  // 如果存在，即输出




        //todo: Java 8还附带了一些特殊类型的流，用于处理原始数据类型int，long以及double。IntStream，LongStream还有DoubleStream。
        IntStream.range(1, 4).forEach(System.out::println); // 相当于 for (int i = 1; i < 4; i++) {}

        //todo:原始类型流装换成对象流，您可以使用 mapToObj()来达到目的
        Arrays.asList("a1", "a2", "a3").stream()
                .map(s -> s.substring(1)) // 对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt) // 转成 int 基础类型类型流
                .max() // 取最大值
                .ifPresent(System.out::println);  // 不为空则输出

        Arrays.asList(1.0, 2.0, 3.0).stream()
                .mapToInt(Double::intValue) // double 类型转 int
                .mapToObj(i -> "a" + i) // 对值拼接前缀 a
                .forEach(System.out::println); // for 循环打印
    }

    public static void execOrder(){

    }

}
