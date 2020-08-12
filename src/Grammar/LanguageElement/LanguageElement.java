package Grammar.LanguageElement;

public class LanguageElement {
    public static void main(String[] args) {
        //todo： 小数默认是双精度,后面加f 指定是单精度
        float f = 2.3f;
        double d = 3.66;
        long l = 100l;

        // 位运算
        int a =  3<<3;
        System.out.println("a="+a);
        int b = 12>>2;
        System.out.println("b="+b);

        // 与
        System.out.println(2 & 3);
        // 或
        System.out.println(2 | 3);
        // 反码
        System.out.println(~3);

        // TODO：异或实现变量交换
        ByteOp byteOp = new ByteOp();
        byteOp.switchNum();


        System.out.println((int)'a');
        System.out.println((int)'A');
        System.out.println((char) 97);
        System.out.println((char) 65);
    }


}
