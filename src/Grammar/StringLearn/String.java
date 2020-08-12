package Grammar.StringLearn;

/**
 * String 用于描述字符串事物。提供了操作字符串的方法
 *
 * 常见操作：
 * todo 获取
 *      - 字符串长度： int length()
 *      - 根据位置获取字符 ：char charAt(int index)
 *      - 根据字符获取位置：int indexOf(int ch)  返回ch第一次出现的位置 没有返回 -1
 *      - 根据字符获取位置：int indexOf(int ch, int fromIndex)  从指定位置开始索引
 *      - 根据字符串获取位置：int indexOf(String str)  返回str第一次出现的位置
 *      - 根据字符串获取位置：int indexOf(String str, int fromIndex)  从指定位置开始索引
 *      - 根据字符获取位置：int lastIndexOf(int ch)  反向索引
 *
 * todo 判断
 *      - 是否包含某子串 boolean contains(str)  或者  int indexOf(int ch)
 *      - 是否含有某内容  boolean isEmpty(str) 就是判断长度是否为0
 *      - 是否以指定内容开头  boolean startsWith(str)
 *      - 是否以指定内容结尾  boolean endsWith(str)
 *      - 判断字符串内容是否相同： boolean equals()
 *      - 判断内容是否相同，并忽略大小写 boolean equalsIgnoreCase()
 *
 *
 * todo 转换
 *      - 将字符数组转换成字符串
 *             - 构造函数 String(char[])
 *             - 构造函数 String(char[], offset, count) 将数组的一部分转成字符串
 *             - 静态方法 static String copyValueOf(char[] data)  valueOf(char[])
 *             - 静态方法 static String copyValueOf(char[] data , offset, count )
 *             - 静态方法 static String valueOf(char[])
 *      - 将字符串转换成字符数组  char[] toCharArray()
 *      - 将字节数组转换成字符串
 *             - 构造函数 String(byte[])
 *             - 构造函数 String(byte[], offset, count) 将数组的一部分转成字符串
 *      - 将字符串转换成字节数组
 *              - byte[] getBytes[]
 *      - 将基本数据类型转换成字符串
 *          -  static String valueOf(int)
 *          -  static String valueOf(double)
 *          - 特殊：字符串和字节数组在转换过程中，可以指定编码表
 *
 * todo: 替换
 *      - String replace(newChar, newChar) 若要替换的字符串不存在 返回原串
 *
 * todo: 切割
 *      - String[] split(reg)
 *
 * todo: 子串，获取字符串的一部分
 *      - String substring(begin)
 *      - String substring(begin, end)
 *
 * todo: 转换、去除空格、比较
 *      - 子串转换成大写 小写： String toUpperCase()  String toLowerCase()
 *      - 去除两端空格: String trim()
 *      - 对字符串进程字典顺序的比较: int compareTo(string)
 */
class StringCode {
    public static void main(String[] args) {
        /**
         * 2种方式结果一样
         * s1 和 s2 的区别：
         * s1在内存中有一个对象  "abcd"
         * s2 在内存中有2个对象  "abcd"  new String()
         */
        String s1 = new String("abcd");
        String s2 = "abcd"; // "abcd" 是一个对象， 一定被初始化就不能再改变
        String s3 = "abcd"; // todo: 因为内存中已经存在 字符串对象 "abcd" 所以 s2 s3 都指向了  "abcd"

        System.out.println(s1 ==s2); // false: 比较的是内存地址
        System.out.println(s2 ==s3); // false: 比较的是内存地址
        System.out.println(s1.equals(s2)); // true ： String 覆写了equals, 比较内容
    }
}
