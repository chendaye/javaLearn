package Grammar.IOStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 拼接多个流
 */
public class SequenceInputStream {
    public static void main(String[] args)throws IOException {
        SplitStream();
    }


    /**
     * 合并流
     * @throws IOException
     */
    public static void merge()throws IOException{
        Vector<FileInputStream> fis = new Vector<>();
        fis.add(new FileInputStream("data/input.txt"));
        fis.add(new FileInputStream("data/write.txt"));

        Enumeration<FileInputStream> elements = fis.elements();

        java.io.SequenceInputStream sis = new java.io.SequenceInputStream(elements);

        FileOutputStream fos = new FileOutputStream("data/merge.txt");


        byte[] buf = new byte[1024];
        int n = 0;

        while ((n = sis.read(buf)) != -1){
            fos.write(buf, 0, n);
        }


        fos.close();
        sis.close();
    }
    /**
     * 切割流
     */
    public static void SplitStream()throws IOException{
        FileOutputStream fos = null;
        FileInputStream fis = new FileInputStream("data/merge.txt");

        // 缓冲区一读满就写一个新文件
        byte[] buf = new byte[100];

        int len = 0;
        int count = 0;
        while ((len = fis.read(buf)) != -1){
            fos = new FileOutputStream("data/"+count+++"split.txt");
            fos.write(buf, 0, len);
            fos.close();
        }

        fis.close();

    }
}
