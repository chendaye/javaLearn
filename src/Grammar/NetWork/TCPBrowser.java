package Grammar.NetWork;


/**
 * 演示客户端和服务端。
 *
 * 1，
 * 客户端：浏览器 (telnet)
 * 服务端：自定义。
 *
 * 2，
 * 客户端：浏览器。
 * 服务端：Tomcat服务器。
 *
 * 3，
 * 客户端：自定义。(图形界面)
 * 服务端：Tomcat服务器。
 */

import java.net.*;
import java.io.*;
class ServerDemo
{
    public static void main(String[] args) throws Exception
    {
        //todo: 可以在浏览器地址栏 输入 http://host:11000 来访问这段程序 并 在浏览器展示 “客户端你好”
        //todo: telnet host 11000 也可以 收到此代码发送的 “客户端你好”
        // Nginx Tomcat 都是类似的
        ServerSocket ss = new ServerSocket(11000);

        Socket s = ss.accept();
        System.out.println(s.getInetAddress().getHostAddress());

        InputStream in = s.getInputStream();

        byte[] buf = new byte[1024];

        int len = in.read(buf);

        System.out.println(new String(buf,0,len));


        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        out.println("<font color='red' size='7'>客户端你好</font>");

        s.close();

        ss.close();
    }
}

class BrowserCode
{
    public static void main(String[] args)
    {
        for(int x=130; x<145; x++)
        {
            try
            {

                Socket s = new Socket("192.168.1.254",x);

                System.out.println(x+"...open");

            }
            catch (Exception e)
            {
                System.out.println(x+"...closed");
            }
        }
    }
}

/**
 * http://192.168.1.254:11000/myweb/demo.html
 *
 * GET /myweb/demo.html HTTP/1.1
 * Accept: application/x-shockwave-flash, image/gif, image/x-xbitmap, image/jpeg, i
 * mage/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint, application
 * /msword, application/QVOD, application/QVOD,
 * Accept-Language: zh-cn
 * Accept-Encoding: gzip, deflate
 * User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0
 * .50727)
 * Host: 192.168.1.254:11000
 * Connection: Keep-Alive
 */
class MyIE
{
    public static void main(String[] args)throws Exception
    {
        Socket s = new Socket("192.168.1.254",8080);

        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        out.println("GET /myweb/demo.html HTTP/1.1");
        out.println("Accept: */*");
        out.println("Accept-Language: zh-cn");
        out.println("Host: 192.168.1.254:11000");
        out.println("Connection: closed");

        out.println();
        out.println();

        BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line = null;

        while((line=bufr.readLine())!=null)
        {
            System.out.println(line);
        }

        s.close();




    }
}

