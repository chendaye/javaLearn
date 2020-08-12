package Algorithm.Interview.NowCoder;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 链接：https://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387
 * 来源：牛客网
 *
 * 每个台阶可以看作一块木板，让青蛙跳上去，n个台阶就有n块木板，最后一块木板是青蛙到达的位子，
 * 必须存在，其他 (n-1) 块木板可以任意选择是否存在，
 * 则每个木板有存在和不存在两种选择，(n-1) 块木板 就有 [2^(n-1)] 种跳法，可以直接得到结果
 *
 *
 * 因为n级台阶，第一步有n种跳法：跳1级、跳2级、到跳n级
 * 跳1级，剩下n-1级，则剩下跳法是f(n-1)
 * 跳2级，剩下n-2级，则剩下跳法是f(n-2)
 * 所以f(n)=f(n-1)+f(n-2)+...+f(1)
 * 因为f(n-1)=f(n-2)+f(n-3)+...+f(1)
 * 所以f(n)=2*f(n-1)
 */
public class JumpFloorII {
    public static void main(String[] args) {
        JumpFloorII q4 = new JumpFloorII();
        System.out.println(q4.JumpFloorII(10));
    }

    //todo: 直接推数学公式
    public int JumpFloorII(int target) {
        if (target <= 0) return 0;
        return (int)Math.pow(2, target - 1);
    }
}
