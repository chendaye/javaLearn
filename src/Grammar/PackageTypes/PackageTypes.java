package Grammar.PackageTypes;

/**
 * 基本数据类型包装类:把整数包装成对象
 *
 * byte     Byte
 * short    short
 * int      Integer
 * long     Long
 * boolean  Boolean
 * float    Float
 * double   Double
 * char     Character
 *
 *
 * todo：基本数据类型的包装类最常见的作用
 *        用于基本数据类型和字符串类型之间额转换
 *        基本数据类型---》字符串
 *              基本数据类型+""
 *              包装数据类型.toString(基本数据类型)
 *        字符串---》基本数据类型
 *               Integer.parseInt(str)
 *               Double.parseDouble(str)
 *               Boolean.parseBoolean(str)
 *               int i  = new Integer("123")
 *               i.intValue()
 *
 *
 *   十进制转其他进制：
 *          toBinaryString 二进制
 *          toHexString     十六进制
 *          toOctalString     八进制
 *
 *   其他进制转十进制：
 *          Integer.parseInt(String str, int 2|8|16|10 )
 *         -
 */
public class PackageTypes {
    public static void main(String[] args) {
        dump("最大整数："+Integer.MAX_VALUE);

        int num = Integer.parseInt("123");
        dump("num="+num);

        dump("num:"+ Integer.parseInt("11001",2 ));
        dump("num:"+ Integer.toBinaryString(12));
        dump("num:"+ Integer.toHexString(12));
        dump("num:"+ Integer.toOctalString(12));


        Integer x = new Integer("123");

        Integer y = new Integer(123);

        Integer z = 4; //todo:JDK 1.5 以后； 自动装箱

        z = z/*z.intValue()*/ +3; // todo:自动拆箱 ==> 4 + 3; 再讲结果装箱 赋值给 z

        dump("--"+(x == y));  // false
        dump("--"+x.equals(y)); // true


        //TODO: 当数值在 byte范围内 -128~127； Integer 不会开辟新的空间
        //TODO: 所以 127 in -128~127 ， m1 m2 指向同一个内存区域  128 out -128~127 n1 n2 指向不同区域
        Integer n1 = 128;
        Integer n2 = 128;
        dump("-n-"+ (n1 == n2));  // false

        Integer m1 = 127;
        Integer m2 = 127;
        dump("-m-"+ (m1 == m2));  // true
    }

    public static void dump(String str){
        System.out.println(str);
    }
}
