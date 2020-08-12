package Grammar.NetWork;

import Utils.Dump;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class udpReceive {
    public static void main(String[] args) throws Exception{
        reveive();
    }

    /**
     * 接受UDP数据
     * @throws Exception
     */
    public static void reveive() throws Exception{
        //todo: 建立socket 并绑定监听端口
        DatagramSocket socket = new DatagramSocket(6666);

        while (true){
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            //todo:阻塞式方法，没有数据就等
            socket.receive(dp);

            java.lang.String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            String string = new String(dp.getData(), 0, dp.getLength());

            Dump.dump(ip+":"+port+":"+string);
        }

//        socket.close();

    }


}
