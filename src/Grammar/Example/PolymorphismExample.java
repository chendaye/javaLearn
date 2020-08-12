package Grammar.Example;

/**
 * 多态使用案例
 *
 * 电报运行实例
 * 基于主板 网卡 声卡
 */
public class PolymorphismExample {
    public static void main(String[] args) {
        MainBoard host = new MainBoard();
        host.run();
        host.NetCard(new NetCard());

    }
}

class MainBoard{
    void run(){
        System.out.println("主机运行");
    }

    void NetCard(NetCard netCard){
        netCard.open();
        netCard.close();
    }
}

class NetCard{

    void open(){
        System.out.println("NetCard 运行");
    }

    void close(){
        System.out.println("NetCard 关闭");
    }
}
