package Algorithm.Interview.LeetCode.KMP;

public class KMP {
    private int[][] dp; //todo: 状态转移数组
    private String pat;  //todo: 模式串

    //todo: 构造函数
    public KMP(String pat){
        this.pat = pat;
        int M  = pat.length();
        //todo: dp[状态][字符] = 下个状态
        dp = new int[M][256]; // 字符共有256个
        //todo: base case
        dp[0][pat.charAt(0)] = 1;
        //todo: 影子状态 shadow 初始 0
        int shadow = 0;
        //todo: 当前状态从 1 开始
        for(int i = 1; i < M; i++){
            for (int c = 0; c < 256; c++){
                if(pat.charAt(i) == c){
                    dp[i][c] = i+1; // 匹配状态进 1
                }else{
                    dp[i][c] = dp[shadow][c];
                }
            }
            //todo: 更新影子状态
            shadow = dp[shadow][pat.charAt(i)];
        }
    }

    //todo: 匹配
    public int search(String txt){
        int M = pat.length();
        int N = txt.length();
        // pat 初始状态为0
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
        System.out.println(kmp.search("AAABACCBC"));
    }
}
