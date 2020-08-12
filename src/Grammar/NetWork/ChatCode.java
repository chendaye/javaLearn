package Grammar.NetWork;

import Utils.Dump;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 一个聊天程序：
 * 两个线程： 一个收 一个发
 */
public class ChatCode {
    public static void main(String[] args) throws Exception{
        DatagramSocket s = new DatagramSocket();
        DatagramSocket r = new DatagramSocket(6666);

        new Thread(new Send(s)).start();
        new Thread(new Receive(r)).start();
    }
}

/**
 * todo:发送
 */
class Send implements Runnable{
    private DatagramSocket socket;
    public Send(DatagramSocket socket){
        this.socket = socket;
    }

    public void run(){
        BufferedReader bfr = null;
        try {
            // 获取键盘输入
            bfr = new BufferedReader(new InputStreamReader(System.in));
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
        }catch (Exception e){
            Dump.dump(e.getMessage());
        }finally {
            try{
                bfr.close();
            }catch(Exception e){
                Dump.dump(e.getMessage());
            }
        }
    }
}

/**
 * todo:接收
 */
class Receive implements Runnable{
    private DatagramSocket socket;
    public Receive(DatagramSocket socket){
        this.socket = socket;
    }

    public void run(){
        while (true){
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            //todo:阻塞式方法，没有数据就等
            try {
                socket.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            java.lang.String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            String string = new String(dp.getData(), 0, dp.getLength());

            Dump.dump(ip+":"+port+":"+string);
        }
    }
}
