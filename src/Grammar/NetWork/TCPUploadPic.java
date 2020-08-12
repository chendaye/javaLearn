package Grammar.NetWork;

import Utils.Dump;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 利用tcp 上传图片
 */
class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.253.1", 5555);
        FileInputStream fis = new FileInputStream("resource/TCP/tcp.png");
        OutputStream out = socket.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;

        while ((len = fis.read(buf)) != -1){
            //todo:读到的文件数据写入，输出流
            out.write(buf, 0, len);
        }
        //todo:告诉服务端数据已经写完.否则服务端读数据的循环不会结束
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bufIn = new byte[1024];
        int num = inputStream.read(bufIn);
        Dump.dump(new String(bufIn, 0, num));
        fis.close();
        socket.close();
    }
}

class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket accept = serverSocket.accept();
        InputStream in = accept.getInputStream();
        FileOutputStream fos = new FileOutputStream("data/server.png");
        byte[] buf = new byte[1024];

        int len = 0;
        while ((len = in.read(buf)) != -1){
            fos.write(buf, 0, len);
        }

        OutputStream out = accept.getOutputStream();
        out.write("上传成功".getBytes());
        fos.close();
        accept.close();
        serverSocket.close();
    }
}