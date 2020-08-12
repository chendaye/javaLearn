package Grammar.IOStream;

import Utils.Dump;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 处理IO异常
 */
public class IOExceptionCode {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            // ，没有就创建，有就覆盖文件
            fileWriter = new FileWriter("data/write.txt");
            // 将字符串写到内存中（流）
            fileWriter.write("chendaye666");
            // todo:将缓冲区的数据刷进目的地
            fileWriter.flush();
            // todo：关闭也会刷新缓存，将数据写到目的地
        }catch (IOException e){
            Dump.dump(e.getMessage());
        }finally {
            try{
                if (fileWriter != null)
                    fileWriter.close();
            }catch (IOException e){
                Dump.dump(e.getMessage());
            }
        }
    }
}
