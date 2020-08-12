package Grammar.NetWork;

import Utils.Dump;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * TCP 客户端
 * todo:步骤
 *  - 创建socket服务，并创建要连接的主机端口
 *  - 获取输出流
 *  - 发送数据
 */
class TCPClient{
    public static void main(String[] args) throws IOException {
        //todo:创建客户端socket服务，指定主机 和 端口（服务器 IP：PORT）
        Socket socket = new Socket("192.168.253.1", 6666);

        //todo:获取socket 流中的输出流， 用来发送数据
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("TCP learn".getBytes());

        socket.close();
    }
}

/**
 * TCP 服务端
 *
 * todo：步骤
 *  - 建立服务端的 socket 服务。 并监听一个端口
 *  - 获取连接过来的客户端对象。这一步是阻塞的
 *  - 客户端如果发来数据，服务端要使用对应的客户端对象，并获取到客户端对象的读取流来读取发过来的数据
 *  - 关闭服务端（可选，服务端一般不会关）
 */
class TCPServer{
    public static void main(String[] args)throws IOException {
        //todo:建立socket 服务，监听一个端口
        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            //todo:获取连接的客户端
            Socket accept = serverSocket.accept();

            Dump.dump(accept.getInetAddress().getHostName()+"............已连接");

            //todo:获取网络流
            InputStream inputStream = accept.getInputStream();

            int n = 0;
            byte[] buf = new byte[1024];
            while ((n = inputStream.read(buf)) != -1){
                Dump.dump(new String(buf,0,n));
            }

            //todo:关闭客户端
            accept.close();
        }
//        serverSocket.close();
    }
}

/************************************************客户端服务端相互通信***********************************************************/

/**
 * todo:步骤
 *  - 建立socket服务，指定要连接的主机 和端口
 *  - 获取输出流，将数据写入流中，发送个服务端
 *  - 获取输入流，将服务端的数据获取，并打印
 *  - 关闭
 */
class TCPClient2{
    public static void main(String[] args) throws IOException {
        //todo:创建客户端socket服务，指定主机 和 端口（服务器 IP：PORT）
        Socket socket = new Socket("192.168.253.1", 6665);

        //todo:输出流， 用来发送数据
        OutputStream outputStream = socket.getOutputStream();

        //todo:发送数据到服务端
        outputStream.write("服务端：你好！".getBytes());
        outputStream.flush();

        //todo: 输入流，接收数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        Dump.dump(new String(buf, 0, len));
        socket.close();
    }
}

/**
 * TCP 服务端
 *
 * todo：步骤
 *  - 建立服务端的 socket 服务。 并监听一个端口
 *  - 获取连接过来的客户端对象。这一步是阻塞的
 *  - 客户端如果发来数据，服务端要使用对应的客户端对象，并获取到客户端对象的读取流来读取发过来的数据
 *  - 关闭服务端（可选，服务端一般不会关）
 */
class TCPServer2{
    public static void main(String[] args)throws IOException {
        //todo:建立socket 服务，监听一个端口
        ServerSocket serverSocket = new ServerSocket(6665);

        while (true){
            //todo:获取连接的客户端
            Socket accept = serverSocket.accept();

            Dump.dump(accept.getInetAddress().getHostName()+"............已连接");

            //todo:输入流，获取客户端发送的数据
            InputStream inputStream = accept.getInputStream();

            int n = 0;
            byte[] buf = new byte[1024];
            while ((n = inputStream.read(buf)) != -1){
                Dump.dump(new String(buf,0,n));
            }

            //todo:输出流，反馈信息
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("客户端，你好！".getBytes());
            outputStream.flush();

            //todo:关闭客户端
//            accept.close();
        }
//        serverSocket.close();
    }
}


