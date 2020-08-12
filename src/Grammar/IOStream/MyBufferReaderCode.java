package Grammar.IOStream;

import Utils.Dump;

import java.io.FileReader;
import java.io.IOException;

/**
 * 装饰器模式：
 * 对已有对象的功能进行增强
 * 可以定义类，将已有对象传入，基于已有的功能，并提供加强功能
 * 那么自定义的类成为装饰类
 *
 *
 * MyReader
 * 	|--MyTextReader
 * 		|--MyBufferTextReader
 * 	|--MyMediaReader
 * 		|--MyBufferMediaReader
 * 	|--MyDataReader
 * 		|--MyBufferDataReader
 *
 * 	class MyBufferReader extends MyReader
 * {
 * 	private MyReader r;
 * 	MyBufferReader(MyReader r)
 *        {}
 * }
 *
 *
 * MyReader
 * 	|--MyTextReader
 * 	|--MyMediaReader
 * 	|--MyDataReader
 * 	|--MyBufferReader
 *
 * 	以前是通过继承将每一个子类都具备缓冲功能。
 *  那么继承体系会复杂，并不利于扩展。
 *
 *  现在优化思想。单独描述一下缓冲内容。
 *  将需要被缓冲的对象。传递进来。也就是，谁需要被缓冲，谁就作为参数传递给缓冲区。
 *  这样继承体系就变得很简单。优化了体系结构。
 *
 *  装饰模式比继承要灵活。避免了继承体系臃肿。
 *  而且降低了类于类之间的关系。
 *
 *  装饰类因为增强已有对象，具备的功能和已有的是相同的，只不过提供了更强功能。
 *  所以装饰类和被装饰类通常是都属于一个体系中的。
 *
 * class
 * {
 * public static void main(String[] args)
 *         {
 *         System.out.println("Hello World!");
 *         }
 *         }
 */
public class MyBufferReaderCode {
    public static void main(String[] args)throws IOException {
        FileReader fr = new FileReader("data/input.txt");

        MyBufferReader mbfr = new MyBufferReader(fr);

        String s = mbfr.MyReadLine();

        String line = null;
        while ((line = mbfr.MyReadLine()) != null){
            Dump.dump(s);
        }


    }
}

/**
 * 模拟 BufferedWriter
 *
 */
class MyBufferReader{
    private FileReader fr;
    MyBufferReader(FileReader fr){
        this.fr = fr;
    }

    /**
     * 自定义读一行数据
     * @return
     */
    public String MyReadLine() throws IOException {
        //临时容器
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while ((index = fr.read()) != -1){
            if (index == '\r')
                continue;
            if (index == '\n')
                return sb.toString(); // 读到行尾返回一行
            else
                sb.append((char) index);
        }
        if (sb.length() != 0)
            return sb.toString();
        return null;
    }

    // 异常，谁调用谁处理
    public void Myclose() throws IOException{
        fr.close();
    }
}
