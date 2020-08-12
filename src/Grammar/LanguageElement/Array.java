package Grammar.LanguageElement;

public class Array {
    public static void main(String[] args) {
        /**
         * new 会生成一个实体，实体存在堆中
         * 实体赋值给一个变量， 实际是把实体在堆中的地址赋值给变量
         * 相当于引用
         */
        int[] a = new int[3];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;

        int[] b = new int[]{1,2,3,3,5};

        int[] c = {1,2,3,45,6};


        //遍历数组
        for (int i = 0; i<c.length; i++){
            System.out.println(c[i]);
        }

        // 打印数组  [I@1b6d3586  : 以@为界  [ 表示数组  I 表示整型  1b6d3586 数组在堆中的地址（十六进制）
        // 地址是hash算法算出来的hash值
        System.out.println(c);

        // 转2进制
        toBin(120);
        toBin2(120000000);
        // 转16进制
        toHex(120);
        toHex2(120);


        // 二维数组
        int[][] d = new int[2][3];
        d[0] = new int[1]; // d[0] 里面也是存的 新数组的 地址
        d[1] = new int[3];

        System.out.println(d.length);
        System.out.println(d[0].length);


        int[][] f = {{1,1,2}, {2,2,2}};
    }

    /**
     * 转二进制
     * @param num
     */
    public static void toBin(int num){
        StringBuffer sb = new StringBuffer();
        while (num > 0){
            sb.append(num%2);
            num = num / 2;
        }
        // 翻转
        System.out.println(sb.reverse());
    }

    /**
     * 转换二进制。查表法
     * @param num
     */
    public static void toBin2(int num)
    {
        StringBuffer sb = new StringBuffer();
        char[] table = {'0','1'};
        for (int i = 1; i<64; i++){
            int tmp = num & 1;
            sb.append(table[tmp]);
            num = num >>> 1;
        }
        System.out.println(sb.reverse());
    }
    /**
     * 转十六进制
     * @param num
     */
    public static void toHex(int num){
        StringBuffer sb = new StringBuffer();
        // 16 * 4=64 位
        for (int i=0; i< 16; i++){
            int tmp = num & 15; //取后4位
            if(tmp > 9){
                sb.append((char)(tmp-9 + 'A'));
            }else{
                sb.append(tmp);
            }
            // 右移4位，补0
            num = num >>> 4;
        }
        System.out.println(sb.reverse());
    }

    /**
     * 转16进制查表法
     * @param num
     */
    public static void toHex2(int num){
        StringBuffer sb = new StringBuffer();
        char[] table = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        for (int i=0; i< 16; i++) {
            int tmp = num & 15; //取后4位
            sb.append(table[tmp]);
            // 右移4位，补0
            num = num >>> 4;
        }
        System.out.println(sb.reverse());
    }

    /**
     * 转换 2 8 16 进制
     * @param num int 要转换的数
     * @param base int 用二进制表示的位数
     * @param offset 偏移
     */
    public static void trans(int num, int base, int offset){
        if (num == 0){
            System.out.println(0);
        }
        char[] table = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] arr = new char[64];
        int pos = arr.length;

        while (num != 0){
            int tmp = num & base;
            arr[--pos] = table[tmp];
            num = num >>> offset;
        }
        for (int i = 0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
