package Grammar.IOStream;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class SystemIOCode  {
    public static void main(String[] args) throws IOException {
        Properties properties = System.getProperties();

//        Dump.dump(properties);
        //重定向标准流
        System.setOut(new PrintStream("data/properties.txt"));
        properties.list(System.out);
    }
}
