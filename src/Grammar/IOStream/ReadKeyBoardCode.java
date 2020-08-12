package Grammar.IOStream;

import Utils.Dump;

import java.io.*;

/**
 * 读取键盘输入
 *
 * todo: 流操作基本规律
 *      - 源&目的
 *          - 源：输入流
 *              |-- InputStream : xxxInputStream
 *              |-- Reader : xxxReader
 *          - 目的：输出流
 *              |-- OutputStream : xxxOutputStream
 *              |-- Write : xxxWrite
 *       - 字符流&字节流
 *          - 字符流 xxxReader xxxWrite
 *          - 字节流 xxxInputStream  xxxOutputStream
 *       - 是否Buffer
 *          - BufferedReader
 *          - BufferedWriter
 *       - 体系明确后，再确定使用哪个具体对象，通过设备区分
 *          - 源设备： 内存 硬盘 键盘
 *          - 目的设备： 内存 硬盘 控制台
 *       - 转换流：InputStreamReader OutputStreamWriter
 *
 *
 * todo：转换流使用场景：字符 字节之间的桥梁。 通常涉及字符编码转换
 */
public class ReadKeyBoardCode {
    public static void main(String[] args)throws IOException {
        ByteToChar();
    }

    /**
     * 读一行
     * @throws IOException
     */
    public static void MyReadLine() throws IOException{
        InputStream in = System.in;
//        int key = 0;
//
//        while ((key = in.read()) != -1){
//            Dump.dump(key);
//        }

        StringBuilder sb = new StringBuilder();
        while (true){
            int ch =    in.read();
            if (ch == '\r')
                continue;
            if (ch == '\n'){
                String s = sb.toString();
                if("over".equals(s)){
                    break;
                }
                Dump.dump(s.toUpperCase());
                sb.delete(0,sb.length()); // 清空
            }else{
                sb.append((char)ch);
            }
        }
    }

    /**
     * todo:转换流
     *      字节流转换为字符流，进行操作
     */
    public static void ByteToChar() throws IOException{

        //todo: 改变标准输入输出源
        System.setIn(new FileInputStream("data/inptu.txt")); // 将 System.in 指向 data/inptu.txt
        System.setOut(new PrintStream("data/out.txt")); // 将 System.out 指向 data/out.txt

        //todo: 获取字节流
        InputStream in = System.in;
        // todo:转换流，字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(in);
        // todo:使用字符流 Buffer, 使用缓冲区提高效率
        BufferedReader bfr = new BufferedReader(isr);

        //todo: 字符流转化为字节流 ,可以指定输出的字符集
        OutputStreamWriter osw = new OutputStreamWriter(System.out, "UTF-8");
        BufferedWriter bfw = new BufferedWriter(osw);
        String line = null;
        while ((line = bfr.readLine()) != null){
            if("over".equals(line)) // todo:键盘输入要自己制定结束符，或者 ctrl+c
                break;
            bfw.write(line.toUpperCase());
            bfw.newLine(); // 换行，只有buffer有
            bfw.flush();
//            Dump.dump(line);
        }

        bfr.close();
        bfw.close();
    }


    public static void KeyboardToFile() throws IOException{
        //todo: 获取字节流
        InputStream in = System.in;
        // todo:转换流，字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(in);
        // todo:使用字符流 Buffer, 使用缓冲区提高效率
        BufferedReader bfr = new BufferedReader(isr);

        //todo: 字符流转化为字节流
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("data/FileOutputStream.txt"));
        BufferedWriter bfw = new BufferedWriter(osw);
        String line = null;
        while ((line = bfr.readLine()) != null){
            if("over".equals(line)) // todo:键盘输入要自己制定结束符，或者 ctrl+c
                break;
            bfw.write(line.toUpperCase());
            bfw.newLine(); // 换行，只有buffer有
            bfw.flush();
//            Dump.dump(line);
        }

        bfr.close();
        bfw.close();
    }
}
