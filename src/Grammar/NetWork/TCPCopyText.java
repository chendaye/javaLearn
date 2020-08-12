package Grammar.NetWork;

import Utils.Dump;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TextClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.253.1", 5555);
        BufferedReader bfw = new BufferedReader(new FileReader("data/input.txt"));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

        //todo:用时间戳做结束标记,并发送给服务端
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        long time = System.currentTimeMillis();
//        dataOutputStream.writeLong(time);

        String line = null;
        while ((line = bfw.readLine()) != null){
            printWriter.println(line);
        }
        //todo:告知数据以及发送完
        printWriter.println("over");
//        socket.shutdownOutput(); // todo:关闭客户端输出流，相当于给流中加入一个结束标记-1
        // 服务端返回的数据
        BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg = bfr.readLine();
        Dump.dump(msg);
        bfr.close();
        socket.close();
    }
}

class TextServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);

        Socket accept = serverSocket.accept();
        Dump.dump(accept.getInetAddress().getHostAddress()+"......连接成功");

        //todo:读取结束标记
//        DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
//        long l = dataInputStream.readLong();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new FileWriter("data/server.txt"), true);
        String line = null;
        while ((line = bfr.readLine()) != null){
            if(line.equals("over"))
                break;
            printWriter.println(line);
        }

        PrintWriter printWriter1 = new PrintWriter(accept.getOutputStream(), true);
        printWriter1.println("上传成功！");
        serverSocket.close();
        accept.close();
        printWriter.close();
        printWriter1.close();
    }
}
