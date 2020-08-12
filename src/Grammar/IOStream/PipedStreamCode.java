package Grammar.IOStream;

import Utils.Dump;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流
 */
public class PipedStreamCode {
    public static void main(String[] args) throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out);
        Read read = new Read(in);
        Write write = new Write(out);

        new Thread(read).start();
        new Thread(write).start();
//        in.close();
//        out.close();
    }
}

/**
 * 读线程
 */
class Read implements Runnable{
    private PipedInputStream in;
    Read(PipedInputStream in){
        this.in = in;
    }
    public void run(){
        try{
            byte[] buf = new byte[1024];
            int len = in.read(buf); //读数据到缓冲区
            System.out.println(new String(buf, 0, len));
            in.close();
        }catch (IOException e){
            Dump.dump(e.getMessage());
        }
    }
}

/**
 * 写线程
 */
class Write implements Runnable{
    private PipedOutputStream out;
    Write(PipedOutputStream out){
        this.out = out;
    }

    public void run(){
        try {
            out.write("Piped".getBytes());
            out.close();
        }catch (IOException e){
            Dump.dump(e.getMessage());
        }
    }
}
