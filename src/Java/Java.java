package Java;

/**
 * 单例模式（同步）
 */
class  singleton{
    private static volatile singleton install = null;

    private singleton(){}

    public static singleton getInstance(){
        if(install == null){
           synchronized(singleton.class){
               if(install == null){
                   install = new singleton();
               }
           }
        }
        return install;
    }
}


interface Car{
    void run();
}

class Car1 implements Car{
    public void run(){
        System.out.println("Car1");
    }
}

class Factory{
    public static Car getCar(String car){
        Car instance = null;
        if (car.equals("Car1")){
            return new Car1();
        }
        return instance;
    }
}