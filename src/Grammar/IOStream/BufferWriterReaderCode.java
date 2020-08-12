package Grammar.IOStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 缓冲区是为了提高流操作的效率
 * 在建立缓冲区之前必须要现有流的对象
 *
 * 缓冲区就是先缓存 流读进来的数据，达到一定量之后写磁盘
 * 避免频繁的IO
 */
class BufferWriteCode {
    public static void main(String[] args) throws IOException {
        write1();
    }

    public static void write1() throws IOException {
        //todo:创建一个流
        FileWriter fw = new FileWriter("data/buffer.txt");

        //todo:缓冲区
        BufferedWriter bfw = new BufferedWriter(fw);

        bfw.write("chendaye666");
        bfw.newLine(); // 换行
        bfw.flush(); // 缓冲区一定有flash
        // 缓存区关闭 就关闭了流对象
        bfw.close();
//        fw.close();
    }
}
