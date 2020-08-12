package Grammar.IOStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties 是 HashTable的子类
 * 具备map的功能，存的是键值对
 *
 * 可用于加载配置文件。 加载的文件要有固定格式 key=value
 */
public class PropertiesCode {
    public static void main(String[] args)throws IOException {
        Properties properties = new Properties();
        properties.setProperty("aaa", "111");
        properties.setProperty("bbb", "222");

        properties.getProperty("aaa");


        //加载配置
        FileInputStream fileInputStream = new FileInputStream("data/input.txt");
        properties.load(fileInputStream);

        properties.list(System.out);
        fileInputStream.close();
    }
}
