package Grammar.IOStream;

import java.io.*;

/**
 * 打印流：
 * 该类提供了打印方法，将数据原样打印
 *
 * todo：字节打印流 PrintStream
 *          - 构造函数可接受类型
 *              - File对象
 *              - String 对象
 *              - OutputStream 字节输出流
 *       字符打印流 PrintWriter
 *           - 构造函数可接受类型
 *  *             - File对象
 *  *             - String 对象
 *  *             - OutputStream 字节输出流
 *                - Writer 字符输出流
 */
public class PrintCode {
    public static void main(String[] args)throws IOException  {
        PrintWrite();
    }


    public static void PrintWrite() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

//        PrintWriter pw = new PrintWriter(System.out);
        PrintWriter pw = new PrintWriter(System.out,true); // 自动Shaun缓存

        String line = null;
        while ((line = bfr.readLine()) != null){
            if (line.equals("over"))
                break;
            pw.write(line);
            pw.println();
//            pw.flush();

        }

        bfr.close();
        pw.close();
    }
}
