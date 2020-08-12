package Algorithm.Interview.NowCoder;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Sum_Solution {
    public int Sum_Solution(int n) {
        int a = (int)Math.pow(n, 2)+n;;
        return a >> 1;
    }


    public static void main(String[] args) {
        System.out.println(new Sum_Solution().Sum_Solution(5));
    }
}
