package Grammar.IOStream;

import java.io.*;


/*
RandomAccessFile

该类不是算是IO体系中子类。
而是直接继承自Object。

但是它是IO包中成员。因为它具备读和写功能。
内部封装了一个数组，而且通过指针对数组的元素进行操作。
可以通过getFilePointer获取指针位置，
同时可以通过seek改变指针的位置。


其实完成读写的原理就是内部封装了字节输入流和输出流。

通过构造函数可以看出，该类只能操作文件。
而且操作文件还有模式：只读r，，读写rw等。

如果模式为只读 r。不会创建文件。会去读取一个已存在文件，如果该文件不存在，则会出现异常。
如果模式rw。操作的文件不存在，会自动创建。如果存则不会覆盖。

*/
class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        //writeFile_2();
        //readFile();

        //System.out.println(Integer.toBinaryString(258));

    }

    public static void readFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "r");

        //调整对象中指针。
        //raf.seek(8*1);

        //跳过指定的字节数
        raf.skipBytes(8);

        byte[] buf = new byte[4];

        raf.read(buf);

        String name = new String(buf);

        int age = raf.readInt();


        System.out.println("name=" + name);
        System.out.println("age=" + age);

        raf.close();


    }

    public static void writeFile_2() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw");
        raf.seek(8 * 0);
        raf.write("周期".getBytes());
        raf.writeInt(103);

        raf.close();
    }

    public static void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw");

        raf.write("李四".getBytes());
        raf.writeInt(97);
        raf.write("王五".getBytes());
        raf.writeInt(99);

        raf.close();
    }
}

class ByteNum{
    public static void main(String[] args) {
        count();
    }

    public static void count(){
//        System.out.println("byte的二进制位数为：" + Byte.SIZE);
        System.out.println("byte所占的字节数为：" + Byte.SIZE/8);
        System.out.println();

//        System.out.println("char的二进制位数为：" + Character.SIZE);
        System.out.println("char所占的字节数为：" + Character.SIZE/8);
        System.out.println();

//        System.out.println("short的二进制位数为：" + Short.SIZE);
        System.out.println("short所占的字节数为：" + Short.SIZE/8);
        System.out.println();

//        System.out.println("int的二进制位数为：" + Integer.SIZE);
        System.out.println("int所占的字节数为：" + Integer.SIZE/8);
        System.out.println();

//        System.out.println("float的二进制位数为：" + Float.SIZE);
        System.out.println("float所占的字节数为：" + Float.SIZE/8);
        System.out.println();

//        System.out.println("long的二进制位数为：" + Long.SIZE);
        System.out.println("long所占的字节数为：" + Long.SIZE/8);
        System.out.println();

//        System.out.println("double的二进制位数为：" + Double.SIZE);
        System.out.println("double所占的字节数为：" + Double.SIZE/8);

        System.out.println();
        String cn = "陈";
        System.out.println("中文字符 所占的字节数为：" + cn.getBytes().length);
        /*
        * String的getBytes()方法是得到一个字串的字节数组，这是众所周知的。
        * 但特别要注意的是，本方法将返回该操作系统默认的编码格式的字节数组。
        * 如果你在使用这个方法时不考虑到这一点，你会发现在一个平台上运行良好的系统，
        * 放到另外一台机器后会产生意想不到的问题*/
    }
}

