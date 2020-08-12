package Grammar.Example;

public class PolymorphismExample2 {
    public static void main(String[] args) {
        MainBoard2 mainBoard2 = new MainBoard2();
        mainBoard2.run();
        mainBoard2.usePCI(new NetWork2());
    }
}

class MainBoard2{
    void run(){
        System.out.println("MainBoard Run");
    }

    // 接口型应用 指向之类对象
    public void usePCI(PCI pci){
        pci.open();
        pci.close();
    }
}


interface  PCI{
    abstract public  void open();

    abstract public  void close();
}

class NetWork2 implements PCI {
    public void open(){
        System.out.println("NetWork2 open");
    }

    public void close(){
        System.out.println("NetWork2 close");
    }
}