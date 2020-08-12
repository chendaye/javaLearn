package Algorithm.Interview.NowCoder;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOf1 {

    public int NumberOf1(int n) {
        char[] chars = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        for (char c:chars){
            if (c == '1') count++;
        }
        return count;
    }


    public static int NumberOf12(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }
    public static void main(String[] args) {
        NumberOf1 q6 = new NumberOf1();

        System.out.println(q6.NumberOf1(-3));

    }
}
