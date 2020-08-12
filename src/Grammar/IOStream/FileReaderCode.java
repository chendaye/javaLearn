package Grammar.IOStream;

import Utils.Dump;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderCode {
    public static void main(String[] args)throws IOException {
        FileReader fileReader = new FileReader("data/write.txt");

        // 一次读一个字符，而且自动往后读
        int read = fileReader.read();
        Dump.dump((char)read);

        int read1 = 0;
//        while ((read1 = fileReader.read()) != -1){
//            Dump.dump((char)read1);
//        }

        Dump.dump("++++++++++++++++++++++++++++");

        //todo: 内容读到一个数组里
        //todo: 当数组装满了，再从头开始装覆盖数组旧值。返回值是本次读取的字符数量
        // todo: 游标一直往后
        char[] a = new char[3];

        int num = 0;

        while((num = fileReader.read(a)) != -1){
            // 截取 本次读取的字符
            Dump.dump(new String(a, 0, num));
        }
        fileReader.close();

        readDemo();

//        copyFileDemo();
        copyFileDemo2();

    }

    /**
     * 读取一个代码文件
     * @throws IOException
     */
    public static void readDemo() throws IOException{
        FileReader fileReader = new FileReader("E:\\learnJava\\src\\Grammar.IOStream\\IOExceptionCode.java");
        char[] a = new char[1024]; // 1024个字符 1024*2 个比特

        int num = 0;

        while((num = fileReader.read(a)) != -1){
            // 截取 本次读取的字符
            Dump.dump(new String(a, 0, num));
        }
        fileReader.close();

    }

    /**
     * 复制文件
     * @throws IOException
     *
     * 读一个写一个
     */
    public static void copyFileDemo() throws IOException{
        //读取的文件
        FileReader fr = new FileReader("E:\\learnJava\\src\\Grammar.IOStream\\IOExceptionCode.java");
        // 写入的文件
        FileWriter fw = new FileWriter("data/input.txt");

        int ch = 0;
        while ((ch = fr.read()) != -1){
            fw.write(ch);
        }
        fr.close();
        fw.close();

    }

    public static void copyFileDemo2(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //读取的文件
            fr = new FileReader("E:\\learnJava\\src\\Grammar.IOStream\\IOExceptionCode.java");
            // 写入的文件
            fw = new FileWriter("data/input.txt");

            char[] buffer = new char[1024];
            int len = 0;
            while ((len = fr.read(buffer)) != -1){
                fw.write(buffer, 0,len);
            }
        }catch (IOException e){
            Dump.dump(e.getMessage());
        }finally {
            try {
                fr.close();
                fw.close();
            }catch (IOException e){
                Dump.dump(e.getMessage());
            }
        }
    }

}
