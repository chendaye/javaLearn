package Grammar.LambdaStream;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.BinaryOperator;

public class Lambda {
    public static void main(String[] args) {
        //todo: 无参函数的简写
        new Thread(new Runnable(){// 接口名
            @Override
            public void run(){// 方法名
                System.out.println("Thread run()");
            }
        }).start();

        new Thread(() -> {
            System.out.println("success");
        });


        //todo: 带参函数的简写
        List<String> list = Arrays.asList("I", "love", "you", "too");
        Collections.sort(list, new Comparator<String>(){// 接口名
            @Override
            public int compare(String s1, String s2){// 方法名
                if(s1 == null)
                    return -1;
                if(s2 == null)
                    return 1;
                return s1.length()-s2.length();
            }
        });

        Collections.sort(list, (s1, s2) ->{// 省略参数表的类型
            if(s1 == null)
                return -1;
            if(s2 == null)
                return 1;
            return s1.length()-s2.length();
        });

        //todo: 简写依据
        // Lambda表达式的书写形式
        Runnable run = () -> System.out.println("Hello World");// 1
        ActionListener listener = event -> System.out.println("button clicked");// 2
        Runnable multiLine = () -> {// 3 代码块
            System.out.print("Hello");
            System.out.println(" Hoolee");
        };
        BinaryOperator<Long> add = (Long x, Long y) -> x + y;// 4
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;// 5 类型推断
    }


}
