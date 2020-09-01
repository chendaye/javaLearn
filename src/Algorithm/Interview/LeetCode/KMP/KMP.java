package Algorithm.Interview.LeetCode.KMP;

import Utils.Dump;

/**
 *                                                                   ***n...***m..........
 * todo: KPM 的核心： 对于模式串 pat 每一个位置 x； 找出 对应 y  满足：  ***y...***x.....
 *  当 x位置不匹配时，直接移动 pat 串                                         ***y...***x.....
 */
public class KMP {
    private int[][] dp; //todo: 状态转移数组
    private String pat;  //todo: 模式串

    //todo: 构造函数
    public KMP(String pat){
        this.pat = pat;
        int M  = pat.length();
        //todo: dp[状态：已经匹配的个数][当前要匹配的字符] = 下个状态（可能前进，可能后退）
        dp = new int[M][256]; // 字符共有256个
        //todo: base case
        dp[0][pat.charAt(0)] = 1; // 第一个字符匹配了
        //todo: 影子状态 shadow 初始 0; 用于记录 与 x 对于的 y 位置
        int shadow = 0; // 对于如何构建这个 dp 数组，需要一个辅助状态 X，它永远比当前状态 j 落后一个状态，拥有和 j 最长的相同前缀，我们给它起了个名字叫「影子状态」
        //todo: 对pat 每个位置 ；遍历所有 256 个字符
        for(int i = 1; i < M; i++){
            for (int c = 0; c < 256; c++){
                if(pat.charAt(i) == c){
                    dp[i][c] = i+1; // 匹配个数 +1
                }else{
                    dp[i][c] = dp[shadow][c];
                }
            }
            //todo: 更新影子状态
            // 后者是在 txt 中匹配 pat，前者是在 pat 中匹配 pat[1..end]，状态 X 总是落后状态 j 一个状态，与 j 具有最长的相同前缀。
            shadow = dp[shadow][pat.charAt(i)];
            // 1 = dp[0][pat(0)]
            // 2 = dp[1][pat(1)]
            // 3 = dp[2][pat(2)]
        }
    }

    //todo: 匹配
    public int search(String txt){
        int M = pat.length();
        int N = txt.length();
        // todo: pat 初始状态为0, 可以看做匹配了 0 个
        int status = 0;
        for (int i = 0; i < N; i++){
            // 计算pat下一个状态
            status = dp[status][txt.charAt(i)];
            // 达到最终状态，返回结果
            if (status == M) return i - M + 1; //todo: 当前txt中的字符，能否把 pat的 状态数组推进到最后一个状态
        }
        // 没有达到最终状态，匹配失败
        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ABABC");
        System.out.println(kmp.search("AFERAABABCBC"));
    }
}
