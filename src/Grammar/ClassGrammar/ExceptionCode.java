package Grammar.ClassGrammar;

/**
 * 异常：程序在运行时出现不正常情况，对java中不正常情况的描述
 *
 * 问题分两类：
 *      严重的：用 Error 类描述。 不编写针对性的代码处理。
 *      不严重的： 用 Exception 类描述。编写针对性的方式处理
 *
 * 无论 Error 或者 Exception 都有共性，如:不正常信息、引发原因等
 * Throwable
 *      - Error
 *      - Exception
 *
 *
 * 异常的处理
 * try{
 *     被检测的代码
 * }catch(异常类1 变量名1){
 *     处理异常的代码
 * }catch(异常类2 变量名2){
 *      处理异常的代码
 * }finally{
 *     一定会执行的语句
 * }
 *
 *
 * 多个异常的处理：
 *      1、声明异常时，建议声明的更具体，这样可以具体的处理异常
 *      2、对方声明几个异常就 几个 catch；不要定义多余的catch
 *      3、如果多个catch中的类出现继承关系，父类的catch放在下面
 *
 *
 * Exception中有一个特殊的子类异常RuntimeException （运行时异常）：
 *      1、如果在函数内抛出该异常，函数上可以不用声明
 *      2、如果在函数上进行了声明 ，调用者可以不用进行处理
 *      3、RuntimeException 的目的是让程序停下来
 *
 *
 * 异常分2中：
 *      - 编译时被检测的异常
 *      - 编译时不被检测的异常（RuntimeException 及其子类)
 *
 *  之所以不用再函数声明，是因为不需要让调用者处理。
 *  该异常发生时希望程序停止。因为在运行时出现了无法继续运算的情况。需要程序停止后程序员对代码修正
 *
 *
 *  finally： 通常用于关闭资源
 *
 *  异常处理的几种格式：
 *      - try+catch
 *      - try+catch...+finally
 *      -try+finally
 */
public class ExceptionCode {
    public static void main(String[] args) {
        try{
            double ret = new math().method(0, -10);
            int res = new math().method2();
            System.out.println("结果："+ res);
        }catch (ArithmeticException | ArrayIndexOutOfBoundsException | CustomException e){ // e用来接收对应的异常类 Exception 的子类
            System.out.println(e.getMessage());
            System.out.println(e.toString()); // 异常名
            e.printStackTrace(); // 对栈中的跟踪信息， jvm默认调用
        } finally {
            System.out.println("除数不能为0");
        }

    }
}

// todo: throws用着函数后 抛出多个异常，逗号分隔； throw用在函数内，抛出单个异常
// todo: 注意：当在函数抛出了异常， 调用的时候必须要catch 或者 throw Exception；
// todo: 但是 RuntimeException 的子类在函数内抛出了，不用再函数 后面声明
class math{
    //todo: throws Exception 表示 调用此段代码的地方必须要 catch 处理异常，不然报错，或者再 throws Exception 抛给别人
    double method(double x, double y) throws ArithmeticException,CustomException{
        if(y<0)
            throw new CustomException("除数是负数"); // 抛出自定义异常
        return x/y;
    }

    //todo: 抛出多异常
    int method2() throws ArrayIndexOutOfBoundsException,ArithmeticException{
        int[] arr = new int[6];
        int a = arr[10] / 0;
        return arr[10];
    }
}


/**
 * 自定义异常：
 * 项目中出现特有的异常，而这些问题并没有被java封装。此时就要自定义异常
 *throw new CustomException() 手动抛出，并且要 函数() throws CustomException{}
 *
 *
 *
 * 在自定义时：因为父类已经把异常信息的操作都完成了，
 * 所有子类构造时，只用将异常信息传递给 父类的构造方法 super(msg)
 *
 * todo：自定义异常时，如果希望该异常发生，无法继续进行运算，就继承 RuntimeException
 */
class CustomException extends Exception{
    private String msg;

    private int val;

    CustomException(){
        super();
    }

    CustomException(String msg){
        super(msg); // 调用父类的构造函数 就不用重写 getMessage 了
//        this.msg = msg;
    }

    CustomException(String msg, int val){
        super(msg);
        this.val = val;
    }

//    public String getMessage(){
//        return msg;
//    }

    // 当然可以定义自己的方法
    public int getValue(){
        return val;
    }
}


class format{
    // todo:抛出的异常如果 内部处理了就不用再在函数声明
    // catch 用于处理异常，有catch说明异常已经被处理
    void test(){
        try{
            throw new Exception();
        }catch (Exception e){
            try{
                throw e;
            }catch (Exception ex){

            }
        }


        try{
//            throw new Exception();
        }finally{

        }
    }


}



class AException extends Exception{

}

class BException extends AException{

}

class CException extends BException{

}

class Fu{
    void show() throws Exception{
        throw new BException();
    }
}

/**
 * 子父类异常的覆盖:
 *      1、子类在覆盖父类时，如果父类方法抛出了异常，那么子类的覆盖方法，只能抛出父类异常或者改异常的子类
 *      2、如果父类抛出多个异常，那么子类抛出异常时只能抛出父类异常的子集
 *      3、如果父类或者接口没有异常抛出，那么子类在覆写方法时，也不可以抛出异常。
 *          如果确实发生了异常，就必须进行try处理，不能抛出去
 */
class Zi extends Fu{
    void show() throws BException{
        try{
            // todo: 如果在覆写的子类方法中要抛出，非父类的异常，只能在内部处理
            throw new CException();
        }catch (CException ce){

        }
    }
}

/*
总结：
# 什么是异常
- 异常时对问题的描述，将问题进行对象的封装

# 异常体系
Throwable
    |--Error
    |--Exception
          |--RuntimeException

# 异常体系的特点
- 异常体系中所有的类，已经对象都具有可抛性，也就是可以被 throws throw 关键字操作

# throw throws的用法
- throw 用在函数内，用于抛出异常对象
- throws 用在函数上，用于抛出异常类，抛出多个用逗号分隔


# 异常有2中
- 编译时被检测异常：该异常在编译时没有处理（没有throws 或 try），则编译失败
- 运行时异常(编译时不检测)，在编译时不需要处理，变压器不检查。该异常发生，让程序停止，需要对代码进行修正。

# finally
- 通常用于关闭资源
- System.exit(0) 系统推出后 finally 不再执行，jvm结束

# 异常的好处
- 将问题进行封装
- 将正常流程代码和问题处理代码分离

# 异常的处理原则
- try 或者 throws
- 调用了抛出异常的方法时， 方法抛出几个就处理几个。 一个try可以有多个catch
- 多个catch，父类异常的catch放在最下面
- 当捕获到处理不了的异常时，可以继续在catch中抛出
- 如果捕获的异常处理不了，或者说不属于该功能的异常；可以将异常转换后，抛出和功能相关的异常
- 如果异常可以处理，但是需要将异常产生的和本功能相关的问题提供出去，让后续调用者处理。 也可以将捕获的异常转换成新的异常


# 注意
- 当函数内容有throw 抛出异常，并且并未进行 try 处理；则必须在函数上声明。
- 上述规则除 RuntimeException 异常体系外。也就是如果函数内部抛出 RuntimeException 类的异常，函数上可以不用声明
- 如果函数声明了异常，调用者需要进行处理，处理方法： throws 或者 try
- 子父类覆盖时：
        1、子类抛出的异常必须是父类抛出的异常的子类(本身)或者子集
        2、如果父类或者接口没有异常抛出，子类也不能抛出异常；如果子类出现异常，只能try catch 不能抛
 */