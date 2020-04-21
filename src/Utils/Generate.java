package Utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class Generate {
    public static void main(String[] args) {
        int[] ints = new int[100];

        ints = get(10, 11, true, true);

        for(int i =0;i<10;i++) {
            Dump.dump(ints[i]);
        }

    }

    /**
     * 产生随机数组
     * @param len 数组长度
     * @param width 随机数范围
     * @param unique 元素是否重复
     * @param order 元素是否有序
     * @return
     */
    public static int[] get(int len, int width, boolean unique, boolean order){
        if (unique && len > width)
            throw new RuntimeException("随机数范围太小！");
        int[] array = new int[len];
        int[] exist = new int[width+1];
        Arrays.fill(exist, 0); // 填充0
        int  random = 0;

        //todo: Random 参数（seed）不变，产生的随机数就不变， 默认seed是System.currentTimeMillis()
        // https://juejin.im/post/5d75e0145188254c206055e6
        java.util.Random r = new java.util.Random();

        int i = 0;
        while (i<len){
            //todo: 不加1 则得到的是 0——99之间的随机数 加 1 则得到的时1——100之间的随机数
            random = r.nextInt(width);
//            random = r.nextInt(width) + 1;
            if (unique){
                if (exist[random] == 0){
                    java.util.Random r1 = new java.util.Random();
                    array[i] = r1.nextInt(2)==0? 0-random:random;
                    exist[random] = 1;
                    i++;
                }else {
                    continue;
                }
            }else {
                java.util.Random r1 = new java.util.Random();
                array[i] = r1.nextInt(2)==1? 0-random:random;
                exist[random] = 1;
                i++;
            }
        }
        if (order)
            Arrays.sort(array);
        return array;
    }

    public static String getString(int length,boolean unique) {
        if (unique && length >58)
            throw new RuntimeException("长度不对！");
        char[] table = new char[256]; // 所有256个字符
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int)(Math.random() * allCharacters.length());
            char tmp = allCharacters.charAt(randomIndex);
            if (unique)
                if (table[tmp] == 0){
                    randomString.append(tmp);
                    table[tmp] = 1;
                }else {
                    continue;
                }
            else
                randomString.append(tmp);

        }
        return randomString.toString();
    }

    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            //todo: (int) (Math.random() * n) 产生 [0...n) 之间的随机数
            arr[i] = new Integer((int)(Math.random() * (rangeR - rangeL + 1) + rangeL));
        return arr;
    }

    // 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes){

        Integer[] arr = new Integer[n];
        for( int i = 0 ; i < n ; i ++ )
            arr[i] = new Integer(i);

        for( int i = 0 ; i < swapTimes ; i ++ ){
            int a = (int)(Math.random() * n);
            int b = (int)(Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }


    // 打印arr数组的所有内容
    public static void printArray(Object[] arr) {

        for (int i = 0; i < arr.length; i++){
            System.out.print( arr[i] );
            System.out.print( ' ' );
        }
        System.out.println();

        return;
    }

    // 判断arr数组是否有序
    public static boolean isSorted(Comparable[] arr){

        for( int i = 0 ; i < arr.length - 1 ; i ++ )
            if( arr[i].compareTo(arr[i+1]) > 0 )
                return false;
        return true;
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, Comparable[] arr){

        // 通过Java的反射机制，通过排序的类名，运行排序函数
        try{
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort",new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null,params);
            long endTime = System.currentTimeMillis();

            assert isSorted( arr );

            System.out.println( sortClass.getSimpleName()+ " : " + (endTime-startTime) + "ms" );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
