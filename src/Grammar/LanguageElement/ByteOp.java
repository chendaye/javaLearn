package Grammar.LanguageElement;

/**
 * todo: 10进制转2进制  Integer.toBinaryString(n);
 * todo: 10进制转8进制  Integer.toOctalString(n);
 * todo: 10进制转16进制  Integer.toHexString(n);
 * todo: 10进制转 r 进制	  	Integer.toString(100, 16);
 */
public class ByteOp {
    /**
     * 不用第三个变量交换 m n值
     */
    public void switchNum(){
        int m =2;
        int n = 6;
        System.out.println("m="+m+",n="+n);
        m = m ^ n;
        n = m ^ n; // (m^n)^n = m  一个数异或同一个数2次 就还原

        m= m ^ n; // m^(m^n)
        System.out.println("m="+m+",n="+n);
    }

    /**
     * 二进制转十六进制
     * @return
     */
    public int BinaryToHex(){
        int raw = 60;

        // 获取最低4位
        int n1 = raw & 15; // 12

        if(n1 > 9){
            System.out.println(this.NumToChar(n1));
        }


        // 右移4位，继续获取低四位,注意是 >>> 全部补0
        int n2 = 60 >>> 4;

        // 取低4位
        int n3 = n2 & 15;
        return  n3;
    }

    /**
     * 0-9   A(10) B(11) C(12) D(13) E(14) F(15)
     * ASSCI  65     67   68    69    70     71
     * @return
     */
    public char NumToChar(int n){
        int dis = n -10;

        int res = 'A' + dis;
        return (char)res;
    }

}
