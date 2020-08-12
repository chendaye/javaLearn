package Grammar.Basic.CusClassLoader;

import java.io.*;

/**
 * 自定义 ClassLoader 取加载 .class 字节码文件 然后解析
 * CusClassLoader
 */
public class CusClassLoader extends ClassLoader {

    private String path;

    public  CusClassLoader(String path){
        this.path = path;
    }

    @Override
    public Class findClass(String name){
        // todo: 读取字节码文件内容，到一个字节数组
        byte[] data = loadClassData(name);
        // todo: 调用 ClassLoader.defineClass 方法将 读取的字节流进行解析；返回 Class对象
        //todo: Class reflect = Class.forName("Grammar.Basic.Reflect.Person"); 反射也是返回的 Class 对象
        // todo: 后续可以通过 Class 对象。生成对应的要查找的对象
        return defineClass(name, data, 0, data.length);
    }

    /**
     * 加载类文件
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        path = path+name+".class"; // 字节码文件位置
//        path = "E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\Grammar.Basic\\Class\\Test.class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(path));
            out = new ByteArrayOutputStream();
            int i=0;
            while ((i = in.read()) != -1){
                out.write(i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }


}
