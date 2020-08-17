package Algorithm.Interview.LeetCode.KMP;

public class test {
    private int[][] dp;
    private String pat;

    public test(String str){
        pat = str;
        int M = this.pat.length();
        dp = new int[M][256];

        dp[0][pat.charAt(0)] = 1;
        int shadow = 0;

        for (int i = 1; i < M; i++){
            for (int j = 0; j < 256; j++){
                if (pat.charAt(i) == j){
                    dp[i][j] = i + 1;
                }else{
                    dp[i][j] = dp[shadow][j];
                }
            }
            shadow = dp[shadow][pat.charAt(i)];
        }
    }

    public int search(String s){
        int M = pat.length();
        int N = s.length();
        int status = 0;
        for (int i = 0; i < N; i++){
            status = dp[status][s.charAt(i)];
            if (status == M) return i - M + 1;
        }
        return -1;
    }
}
