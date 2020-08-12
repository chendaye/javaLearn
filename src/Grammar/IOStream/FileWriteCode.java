package Grammar.IOStream;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteCode {
    public static void main(String[] args)throws IOException {
        // ，没有就创建，有就覆盖文件
        FileWriter fileWriter = new FileWriter("data/write.txt");
        // 将字符串写到内存中（流）
        fileWriter.write("chendaye666");
        // todo:将缓冲区的数据刷进目的地
        fileWriter.flush();
        // todo：关闭也会刷新缓存，将数据写到目的地
        fileWriter.close();
    }

    /**
     * 追加内容
     */
    public static void appendFile()throws Exception{
        //todo: true 代表不覆盖原有内容
        FileWriter fileWriter = new FileWriter("data/write.txt", true);
        // 将字符串写到内存中（流）
        fileWriter.write("7777");
    }
}
