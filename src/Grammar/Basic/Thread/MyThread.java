package Grammar.Basic.Thread;

public class MyThread extends Thread {
    private String name;

    MyThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
        for (int i=0; i<10; i++)
            System.out.println(name+" ---- "+i);
    }
}
