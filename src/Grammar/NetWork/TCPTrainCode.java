package Grammar.NetWork;

import Utils.Dump;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文本转换服务器： 客户端发送文本，服务器转化为大写，返回客户端
 *
 * 用IO技术思考：
 * 源：键盘输入
 * 目的地：网络输出流
 *
 * todo:步骤
 *  - 建立服务
 *  - 获取键盘输入
 *  - 获取服务端返回的数据
 *  - 关闭资源
 *
 *  都是文本数据使用字符流进行操作， 可以加缓冲
 */
class Client {
    public static void main(String[] args) throws IOException {
        //todo:创建客户端socket服务，指定主机 和 端口（服务器 IP：PORT）
        Socket socket = new Socket("192.168.253.1", 5555);

        //todo:将键盘输入的字节流，通过转换流，变成字符流，再加上buffered
        BufferedReader bfrIN = new BufferedReader(new InputStreamReader(System.in));

        //todo:将数据写入 socket输出流
        BufferedWriter bfwOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //todo: 读取服务端返回的数据,大写信息
        BufferedReader serverData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while ((line = bfrIN.readLine()) != null){
            if (line.equals("over"))
                break;
            bfwOut.write(line); // todo:键盘读取的数据，写入到输出流
            bfwOut.newLine(); // 每一行加一个回车符，告诉接收方 一行结束
            bfwOut.flush(); //缓冲区刷新
            // 服务端的返回数据
            String s = serverData.readLine();
            Dump.dump("server:"+s);
        }
        socket.close();
        bfrIN.close();
        bfwOut.close();
    }
}

/**
 * todo：出现的问题：
 *      - client server 都在等待：客户端、服务端都是阻塞式方法。这些方法都没有读到结束标记（换行）。
 *                                  那么会一直等待。
 */
class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);

        Socket accept = serverSocket.accept();
        Dump.dump(accept.getInetAddress().getHostAddress()+"......连接成功");
        //todo:从socket流中读取数据
        BufferedReader bfr = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        //todo:socket 输出流大写数据
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

        String line = null;
        while ((line = bfr.readLine()) != null){
            bfw.write(line.toUpperCase());
            bfw.newLine();
            bfw.flush();
        }
        serverSocket.close();
        accept.close();

    }
}

/***********************************PrintWriter***************************************************/

class Client2 {
    public static void main(String[] args) throws IOException {
        //todo:创建客户端socket服务，指定主机 和 端口（服务器 IP：PORT）
        Socket socket = new Socket("192.168.253.1", 5555);

        //todo:将键盘输入的字节流，通过转换流，变成字符流，再加上buffered
        BufferedReader bfrIN = new BufferedReader(new InputStreamReader(System.in));

        //todo:将数据写入 socket输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        //todo: 读取服务端返回的数据,大写信息
        BufferedReader serverData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while ((line = bfrIN.readLine()) != null){
            if (line.equals("over"))
                break;
            printWriter.println(line.toUpperCase());
            // 服务端的返回数据
            String s = serverData.readLine();
            Dump.dump("server:"+s);
        }
        socket.close();
        bfrIN.close();
        printWriter.close();
    }
}

/**
 * todo：出现的问题：
 *      - client server 都在等待：客户端、服务端都是阻塞式方法。这些方法都没有读到结束标记（换行）。
 *                                  那么会一直等待。
 */
class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);

        Socket accept = serverSocket.accept();
        Dump.dump(accept.getInetAddress().getHostAddress()+"......连接成功");
        //todo:从socket流中读取数据
        BufferedReader bfr = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        //todo:socket 输出流大写数据
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);

        String line = null;
        while ((line = bfr.readLine()) != null){
            printWriter.println(line);
        }
        serverSocket.close();
        accept.close();

    }
}
