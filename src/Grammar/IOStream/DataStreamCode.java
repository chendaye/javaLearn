package Grammar.IOStream;

import Utils.Dump;

import java.io.*;

/**
 *todo: 用于操作基本数据类型
 */
public class DataStreamCode {
    public static void main(String[] args)throws IOException {
//        write();
//        read();
        writeUTF8();
        readUTF();
    }

    public static void write() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data/DataOutputStream.txt"));
        // 要按写入的顺序读
        dos.writeInt(123);
        dos.writeFloat(2);
        dos.writeDouble(10.2);

        dos.close();
    }

    public static void read() throws IOException {
        DataInputStream dos = new DataInputStream(new FileInputStream("data/DataOutputStream.txt"));
        Dump.dump(dos.readInt());
        Dump.dump(dos.readFloat());
        Dump.dump(dos.readDouble());


        dos.close();
    }


    public static void writeUTF8() throws IOException {
        // writeUTF 写的内容只能用 readUTF 读
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data/writeUTF8.txt"));
        dos.writeUTF("陈");


        dos.close();
    }

    public static void readUTF() throws IOException {
        DataInputStream dos = new DataInputStream(new FileInputStream("data/writeUTF8.txt"));
        Dump.dump(dos.readUTF());
        dos.close();
    }


}
