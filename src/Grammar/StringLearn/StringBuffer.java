package Grammar.StringLearn;

/**
 * StringBuffer 字符串缓冲区， 是一个容器（有点像数组）
 *      - 长度是可变化的
 *      - 可以操作多个数据类型
 *      - 最终通过 toString()  变成字符串
 *      - 当数据类型不确定，个数不确定，最终要转成字符串 时使用
 *
 *
 *
 * todo： CURD create update read delete
 *      - 存储:
 *          StringBuffer append() 将指定数据追加到已有数据的结尾处
 *          StringBuffer insert(offset, str) 将指定数据追加到已有数据的结尾处
 *      - 删除：
 *          StringBuffer  delete(start, end) [start,end)  delete(0, stringBuffer.length()) 清空
 *          StringBuffer deleteCharAt(index)  删除指定位置的字符
 *      - 获取：
 *          char charAt(index)
 *          int indexOf(String str)
 *          int lastIndexOf(String str)
 *          int length()
 *          String substring(int start, int end)
 *      - 修改
 *          stringBuffer replace(start,end,str);
 *          stringBuffer.setCharAt(3,'k'); // 替换没有返回
 *      - 翻转
 *          reverse()
 *          getChars(int srcBegin,
 *                      int srcEnd,
 *                      char[] dst,
 *                      int dstBegin) 字符从该序列复制到目标字符数组dst
 *
 */
class StringBufferCode {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();

        StringBuffer stringBuffer1 = stringBuffer.append(6);
        dump(stringBuffer == stringBuffer1); // true ,指向同一个缓冲区
        dump(stringBuffer1.toString());

        stringBuffer.append(5).append(7);

        stringBuffer.insert(2, "qq");
        stringBuffer.delete(1,2);
        stringBuffer.deleteCharAt(1);
//        stringBuffer.delete(0, stringBuffer.length());  清空缓冲区
        stringBuffer.replace(1,2,"java");
        stringBuffer.setCharAt(3,'k');
        dump(stringBuffer1.toString());

    }

    public static void dump(String str){
        System.out.println(str);
    }

    public static void dump(Boolean str){
        System.out.println(str);
    }
}
