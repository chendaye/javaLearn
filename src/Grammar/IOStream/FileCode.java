package Grammar.IOStream;

import Utils.Dump;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 操作文件 目录 （操作属性 不是内容）
 *
 * todo: File 的常用方法
 *      - 创建
 *          - boolean createNewFile 没有就创建，有就不建
 *      - 删除
 *          - boolean delete() 删除失败返回false
 *          - void deleteOnExit() 在程序退出时删除指定文件
 *      - 判断
 *          - exists()
 *          - isDirectory()
 *          - isFile()
 *      - 获取信息
 */
public class FileCode {
    public static void main(String[] args)throws IOException {
//        method();
//        fileRoot();
        ArrayList<File> files = FileTree("../learnJava");
        for (File file : files){
            Dump.dump(file);
        }
    }



    public static void method() throws IOException {
        // (已有 或不存在 的)文件封装成 File 对象
        File file1 = new File("E:\\learnJava\\data\\input.txt");
        // 目录和文件分开成2个参数
        File file2 = new File("E:\\learnJava\\data", "input.txt");
        // 另一种方式
        File dir = new File("E:\\learnJava\\data");
        File file3 = new File(dir,"input.txt");

        Dump.dump(file1);
        Dump.dump(file2);
        Dump.dump(file3);

        // linux window是分割符不一样 +File.separator+ 统一分隔符
        File file4 = new File("E:"+File.separator+"learnJava"+File.separator+"data"+File.separator+"input.txt");

        file1.createNewFile(); // 创建文件
        file1.delete();
        boolean b = file1.canExecute();
        boolean exists = file1.exists();
        // 判断是否是 文件(目录)的前提是，文件(目录)存在
        boolean file = file1.isFile();
        boolean directory = file1.isDirectory();
        boolean hidden = file1.isHidden();
        boolean absolute = file1.isAbsolute();

        //创建文件夹爱
        File dir2 = new File("data", "testDir");
        dir2.mkdir(); // 只能创建一级目录
        dir2.mkdirs(); // 创建多级目录

    }

    /**
     * 列出系统盘符
     */
    public static void fileRoot(){
        File[] files = File.listRoots();
        for (File file:files){
            Dump.dump(file);
        }

        // 列出所有文件(文件夹) 名称 包含隐藏文件。
        // todo：必须判断是一个 已存在的目录, 可以传一个过滤器 FilenameFilter
        File file = new File("data");
        String[] data = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals("write.txt");
            }
        });
        for (String d : data){
            Dump.dump(d);
        }

        //todo: 获取文件夹下面 文件/文件夹 的File对象
        File[] files1 = file.listFiles();
    }

    /**
     * 递归找出所有文件
     */
    public static ArrayList<File> FileTree(String path){
        File file = new File(path);
        File[] objects = file.listFiles();
        ArrayList<File> current = new ArrayList<File>();
        for (File obj : objects ){
            if (obj.isDirectory()){
                //todo：递归
                ArrayList<File> files = FileTree(obj.getPath());
                Iterator<File> iterator = files.iterator();
                while (iterator.hasNext()){
                    current.add(iterator.next());
                }
            }else{
                current.add(obj);
            }
        }
        return current;
    }
}
