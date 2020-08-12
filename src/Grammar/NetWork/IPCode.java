package Grammar.NetWork;

import Utils.Dump;

import java.net.*;

public class IPCode {
    public static void main(String[] args)throws Exception {
        IPMethod();
    }

    public static void IPMethod() throws Exception{
        InetAddress localHost =  InetAddress.getLocalHost();
        Dump.dump(localHost);

        InetAddress lengo = InetAddress.getByName("www.chendaye666.top");
        Dump.dump(lengo.getHostName());
    }
}
