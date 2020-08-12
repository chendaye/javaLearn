package Grammar.NetWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 使用UDP
 * 通过UDP发送一段文字
 *
 * todo:发送步骤
 *      - 建立udpSocket
 *      - 封装数据到数据包
 *      - 通过socket发送数据
 *      - 关闭资源
 *
 * todo: 接收步骤
 *      - 定义一个udpSocket服务
 *      - 定义一个数据包用于存储接收的数据
 *      - 通过receive方法接收数据
 *      - 用数据包对象的功能处理数据
 *      - 关闭资源
 */
public class udpSend{
    public static void main(String[] args) throws Exception {
        send2();
//        send();
    }

    /**
     * UDP 发送数据
     * @throws Exception
     */
    public static void send() throws Exception{
        // todo: 通过 DatagramSocket 建立udp服务
        DatagramSocket socket = new DatagramSocket();

        //todo:封装数据
        byte[] buf = "第一次编写 Socket".getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.253.1"), 6666);

        //todo:通过socket发送数据
        socket.send(dp);
        //todo:关闭资源
        socket.close();
    }

    /**
     * 使用udp
     * 发送键盘输入数据到指定端口
     */
    public static void send2()throws Exception{
        //建立udp
        DatagramSocket socket = new DatagramSocket();
        // 获取键盘输入
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        // 缓冲区
        byte[] buf = new byte[1024];
        String line = null;
        while ((line = bfr.readLine()) != null){
            //结束
            if (line.equals("over"))
                break;
            // 数据转换为字节
            byte[] bytes = line.getBytes();

            // 封装数据
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.253.1"), 6666);
            // 发送数据
            socket.send(dp);
        }
        // 发送完毕
        socket.close();
    }
}
