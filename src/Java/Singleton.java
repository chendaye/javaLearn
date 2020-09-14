package Java;

/**
 * 单例模式（同步）
 */
class  Singleton{
    private static volatile Singleton install = null;

    private Singleton(){}

    public static Singleton getInstance(){
        if(install == null){
            synchronized(Singleton.class){
                if(install == null){
                    install = new Singleton();
                }
            }
        }
        return install;
    }
}


