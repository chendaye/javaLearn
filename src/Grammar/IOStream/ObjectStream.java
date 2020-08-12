package Grammar.IOStream;

import Utils.Dump;

import java.io.*;

/**
 * 操作对象额流
 */
public class ObjectStream {
    public static void main(String[] args)throws IOException,ClassNotFoundException {
        writeObj();
        readObj();
    }

    /**
     * 保存对象
     * @throws IOException
     */
    public static void writeObj()throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/object.txt"));
        //todo:静态成员不能被序列化
        oos.writeObject(new Test("lengo", 18));
        oos.close();
    }

    public static void readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/object.txt"));
        Test t = (Test)ois.readObject();
        Dump.dump(t.toString());
        ois.close();
    }
}
