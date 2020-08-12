package Algorithm.Interview;

import Utils.Dump;
import Utils.Generate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理 OJ 中的 输入输出
 */
public class InputOutput {


    /**
     * 5 5
     * 11000
     * 01011
     * 00011
     * 00000
     * 00111
     */
    public static void Situation_1(){
        Scanner sc = new Scanner(System.in);
        //todo: 取第一行
        String line = sc.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int m = Integer.parseInt(line.split(" ")[1]);
        System.out.println();
        Dump.dump(n);
//        Dump.dump(n%1==0);
        Dump.dump(m);
        //todo: 读取后面的内容到二维数组中
        int[][] array = new int[m][n];
        int row=0;
        while (sc.hasNext()){
            String s = sc.nextLine();
            String[] arr = s.split("");
            int[] data = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            array[row++] = data;
            if (row>=m) break;
        }
        for (int i=0;i<m;i++){
            Dump.array(array[i]);
        }
    }

    /**
     * todo:常用方法
     */
    public static void Situation_2(){
        Scanner sc = new Scanner(System.in);
        //todo:读一个整数
        int n = sc.nextInt();
        //todo:读一个字符串
        String ss = sc.next();
        //todo:读一个浮点数
        double t = sc.nextDouble();
        //todo:读一整行
        String str = sc.nextLine();
        //todo:判断是否有下一行
        sc.hasNext();
        sc.hasNextInt();
        sc.hasNextDouble();
        sc.hasNextLine();
    }

    /**
     * todo:输出  format()与printf()是等价的
     */
    public static void Output(){
        //todo: 输出
        double d = 345.678;
        String s = "hello World";
        int a = 1234567;

        //浮点型的输出
        System.out.printf("%,d",a);            //格式化输出数据，每三个数据加上一个，
        System.out.println();
        System.out.printf("%f", d);            // 345.678000
        System.out.println();
        //todo:"9.2"中的9表示输出的长度，.2表示小数点后的位数。  345.678位数不够前面不上空格
        System.out.printf("%9.3f", d);
        System.out.println();
        System.out.printf("%+9.3f", d);            //"+"表示输出的数带正负号
        System.out.println();
        System.out.printf("%-9.3f", d);            //"-"表示输出的数左对齐（默认为右对齐）
        System.out.println();
        System.out.printf("%+-9.3f",d);            //"+-"表示输出的数带正负号且左对齐
        System.out.println();

        //整型的输出
        System.out.printf("%d", a);                //"d"表示输出十进制整数。
        System.out.println();
        System.out.printf("%04d", a);            //规定为4位，不够左边添加0
        System.out.println();
        System.out.printf("%d%%", a);            //如果想输出百分数，则连续用两个  %%
        System.out.println();
        System.out.printf("%o", a);                //"o"表示输出八进制整数。
        System.out.println();
        System.out.printf("%x", a);                //"x"表示输出八进制整数。


        //字符串的输出
        System.out.println();
        System.out.printf("%s%n", s);                //输出字符串,其中 %n表示换行

        System.out.printf("输出一个浮点数：%.3f,一个整数：%d,一个字符串：%s",d,a,s);        //输出多个变量

        int i =7;
        System.out.format("The value of i is: %d%n", i);
        long num = 461012;
        System.out.format("%d%n", num);      //  -->  "461012"
        System.out.format("%08d%n", num);    //  -->  "00461012" 输出8位 不够补0
        System.out.format("%+8d%n", num);    //  -->  " +461012" 输出8位 且输出正负号
        System.out.format("%,8d%n", num);    // -->  " 461,012"  输出8位 且每3位逗号分隔
        System.out.format("%+,8d%n%n", num); //  -->  "+461,012"  输出8位 且每3位逗号分隔  且输出正负号

        double pi = Math.PI;

        System.out.format("%f%n", pi);       // -->  "3.141593" 输出浮点数
        System.out.format("%.3f%n", pi);     // -->  "3.142" .保留3位并且换行

        System.out.format("%10.3f%n", pi);   // -->  "     3.142"  输出长度10 保留3位 右对齐
        System.out.format("%-10.3f%n", pi);  // -->  "3.142"
        System.out.format(Locale.FRANCE, "%-10.4f%n%n", pi); // -->  "3,1416"

        Calendar c = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", c, c, c); // -->  "May 29, 2006"

        System.out.format("%tl:%tM %tp%n", c, c, c);  // -->  "2:34 am"

        System.out.format("%tD%n", c);    // -->  "05/29/06
    }

    /**
     * 重定向输入，用文件模拟
     * @throws FileNotFoundException
     */
    private static void redirct() throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\NowcoderLeetcode\\test.txt"));
    }

    /**
     * todo:类型转换
     */
    public static void trans(){
        //todo:int[] 转 Integer[]
        int[] data = {1, 2, 3, 4};
        Integer[] integers = Arrays.stream(data).boxed().toArray(Integer[]::new);
        Dump.array(integers);

        //todo:Integer[] 转 int[]
        Integer[] integers1 = new Integer[]{1, 2, 3, 4};
        int[] data1 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
        Dump.array(data1);

        //todo:Integer[] 转 List
        Integer[] integers2 = new Integer[]{1, 2, 3, 4};
        List<Integer> list = Arrays.asList(integers2);

        //todo:List 转 Integer[]
        List<Integer> list1 = new ArrayList<>();
        list1.add(11);
        list1.add(22);
        Integer[] integers3 = list.toArray(new Integer[0]);
        Dump.array(integers3);

        //todo:int[] 转 List （装箱后转集合）
        int[] data2 = {1, 2, 3, 4};
        List<Integer> list2 = Arrays.stream(data).boxed().collect(Collectors.toList());

        //todo:List 转 int[] （拆箱后转数组）
        List<Integer> list5 = new ArrayList<>();
        list5.add(1);
        list5.add(2);
        int[] data3 = list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * todo:主函数
     * @param args
     */
    public static void main(String[] args) {
//        Situation_1();
//        System.out.format("%-10.4f%n", 3.1415926);
//        System.out.format("%o%n", 1415926);

//        trans();

        int[] arr = new int[20];
        arr = Generate.get(1000000, 1000001, true, true);
        long start = System.nanoTime(); //毫秒
        int mid = method(arr, 1000000, 122);
        long end = System.nanoTime();
        Dump.dump("start:"+start);
        Dump.dump("end:"+end);
        Dump.dump("运行时间："+(end - start)+" ms-----运行结果"+mid);
    }

    public static int method(int[] array, int len, int target){
        int l = 0, r = len-1; // 在[l....r]闭区间找 target
        while (l <= r){ // 当 l==r时， 区间[l....r]仍然有效
            int mid = (l+r)/2;
//            int mid = l+(r-l)/2;  // 避免 l+r整型溢出
            if (array[mid] == target)
                return mid;
            if(array[mid] < target){
                l = mid+1; // target 在区间[mid+1.....r]中
            }else {
                r = mid-1; // target 在区间[l.....mid-1]中
            }
        }
        return -1;
    }
}
