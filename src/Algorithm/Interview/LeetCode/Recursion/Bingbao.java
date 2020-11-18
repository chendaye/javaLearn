package Algorithm.Interview.LeetCode.Recursion;

/**
 * 给定一个数 n； 如果是偶数 除以2； 如果是奇数 乘以3再加一
 * 重复操作多少次可以 变成 1
 */
public class Bingbao {
    /**
     * 递归冰雹猜想
     * @param n
     * @param cnt
     * @return
     */
    public static int solution(int n, int cnt){
        if (n > 1){
            if (n % 2 == 0){
                n = n >> 1;
            }else{
                n = n * 3 + 1;
            }
            cnt++;
            return solution(n, cnt);
        }else if (n == 1){
            return cnt;
        }else{
            return 0;
        }
    }
}
