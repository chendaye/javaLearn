package Grammar.IOStream;

import Utils.Dump;

import java.io.*;

/**
 * 字节流: 使用字节数组 byte[]
 * 字符流使用字符数组 char[]
 *
 *
 * todo： 1 byte(B) = 8 bit(位)
 *          1KB = 1024byte
 */
public class ByteStreamCode {
    public static void main(String[] args)throws IOException {
//        writeFile();
//        readFile();
//        readFile3();
        copyPic();
    }


    /**
     * 写文件
     * @throws IOException
     */
    public static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("data/Iterator.txt");

        //最小单位操作文件 没有flash
        fos.write("lengo".getBytes());

        fos.close();
    }

    /**
     * 读文件
     * 个一个读
     * @throws IOException
     */
    public static void readFile() throws IOException{
        FileInputStream fis = new FileInputStream("data/Iterator.txt");

        int n = 0;
        while ((n = fis.read()) != -1){
            Dump.dump((char)n);
        }
    }

    /**
     * 读一个数组
     * @throws IOException
     */
    public static void readFile2() throws IOException{
        FileInputStream fis = new FileInputStream("data/Iterator.txt");

        byte[] b = new byte[1024];
        int n = 0;
        while ((n = fis.read(b)) != -1){
            Dump.dump(new String(b, 0, n));
        }
    }

    /**
     * 定义一个刚刚好的缓冲区
     * @throws IOException
     */
    public static void readFile3() throws IOException{
        FileInputStream fis = new FileInputStream("data/Iterator.txt");

        int num = fis.available();
        // 定义一个刚刚好的缓冲区。 不用在循环
        byte[] b = new byte[num]; // todo:慎用，因为内存不一定能容纳这个数组
        fis.read(b);
        Dump.dump(new String(b));
        fis.close();
    }


    /**
     * copy 图片
     */
    public static void copyPic(){
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream("data/Iterator.png");
            fis = new FileInputStream("resource/Conllection/Iterator.png");
            byte[] buf = new byte[1024];

            int num = 0;
            while ((num = fis.read(buf)) != -1){
                fos.write(buf, 0, num);
            }
        }catch (IOException e){
            Dump.dump(e.getMessage());
        }finally {
            try {
                fos.close();;
                fis.close();
            }catch (IOException e){
                Dump.dump(e.getMessage());
            }
        }
    }
}


/********************************************字节流缓冲区*************************************************/
class  CopyMp3
{
    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();
        copy_2();
        long end = System.currentTimeMillis();

        System.out.println((end-start)+"毫秒");
    }

    //todo：通过字节流的缓冲区完成复制。
    public static void copy_1()throws IOException
    {
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("c:\\0.mp3"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("c:\\1.mp3"));

        int by = 0;

        while((by=bufis.read())!=-1)
        {
            bufos.write(by);
        }
        bufos.close();
        bufis.close();
    }

    /**
     * 自定义BUffered
     * @throws IOException
     */
    public static void copy_2()throws IOException
    {
        MyBufferedInputStream bufis = new MyBufferedInputStream(new FileInputStream("c:\\9.mp3"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("c:\\3.mp3"));

        int by = 0;

        //System.out.println("第一个字节:"+bufis.myRead());

        while((by=bufis.myRead())!=-1)
        {
            bufos.write(by); //todo: write 只写 低8位（一个byte）
        }

        bufos.close();
        bufis.myClose();
    }
}

/**
 * 自定义缓冲区
 */
class MyBufferedInputStream
{
    private InputStream in;

    // todo: 4KB的缓冲区
    private byte[] buf = new byte[1024*4];

    private int pos = 0,count = 0;

    MyBufferedInputStream(InputStream in)
    {
        this.in = in;
    }

    /**
     * 一次从流里读取 1024*4 个字节，放入缓冲区
     * 一次读缓冲区一个字节，从缓冲区(字节数组)获取。
     *
     * todo: 存在一个问题---- 当字节流中有连续8个1 -> 当 11111111 提升为 int ==> -1; 这就与循环结束条件冲突了
     *       所以从缓存中读取一个字节后，返回 时 保留低8位，前面补0. 也就是 和 255相 &
     *
     * @return
     * @throws IOException
     */
    public int myRead()throws IOException
    {
        //通过in对象读取硬盘上数据，并存储buf中。
        if(count==0) // 缓存里面没有数据
        {
            // 字节读到缓冲区数组里面 count：从流里读取的字节数
            count = in.read(buf);
            if(count<0)
                return -1;
            pos = 0; // 数组游标
            byte b = buf[pos];  // 从缓存里获取一个字节

            count--;  // 缓存里的字节数 -1
            pos++; // 游标前移动一位
            return b&255;  //todo： 保留b最后八位前面补0。因为连续 8个1，提升成整数=-1 会导致误判 文件读取完成
        }
        else if(count>0)  // 缓存里面有数据
        {
            byte b = buf[pos];  // 读取缓存字节

            count--;
            pos++;
            return b&0xff; // todo: 0xff = 255
        }
        return -1;

    }
    public void myClose()throws IOException
    {
        in.close();
    }
}


/*
11111111-111111110000000000101001001010100101010010101001010


byte: -1  --->  int : -1;
00000000 00000000 00000000 11111111  255

11111111 11111111 11111111 11111111


11111111  -->提升了一个int类型 那不还是-1吗？是-1的原因是因为在8个1前面补的是1导致的。
那么我只要在前面补0，即可以保留原字节数据不变，又可以避免-1的出现。
怎么补0呢？

 11111111 11111111 11111111 11111111
&00000000 00000000 00000000 11111111 = 255
------------------------------------
 00000000 00000000 00000000 11111111

0000-0001
1111-1110
000000001
1111-1111  -1


todo：结论：
字节流的读一个字节的read方法为什么返回值类型不是byte，而是int。
因为有可能会读到连续8个二进制1的情况，8个二进制1对应的十进制是-1.
那么就会数据还没有读完，就结束的情况。因为我们判断读取结束是通过结尾标记-1来确定的。
所以，为了避免这种情况将读到的字节进行int类型的提升。
并在保留原字节数据的情况前面了补了24个0，变成了int类型的数值。


而在写入数据时，只写该int类型数据的最低8位。


*/