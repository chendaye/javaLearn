package Algorithm.Interview.LeetCode.Other;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class countBinarySubstrings {
    public static int countBinarySubstrings(String s) {
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(helper(s, i)) cnt++;
        }
        return cnt;
    }
    // 以 index 开始 是否有满足题意的子串
    public static boolean helper(String s, int index){
        char c = s.charAt(index);
        int cnt = 0;
        for(int i = index; i < s.length(); i++){
            if(c == s.charAt(i)){
                cnt++;
            }else{
                //todo: 加速循环
                while (cnt > 0 && i < s.length()){
                    if (c != s.charAt(i++)){
                        cnt--;
                    }else {
                        break;
                    }
                }
                break;
            }
        }
        return cnt == 0;
    }

    public static void main(String[] args) {
        String s = "10101";
        int n = countBinarySubstrings(s);
        System.out.println(n);
    }

    /**
     * 我们可以将字符串 ss 按照 00 和 11 的连续段分组，存在 \rm countscounts 数组中，
     * 例如 s = 00111011s=00111011，可以得到这样的 \rm countscounts 数组：{\rm counts} = \{2, 3, 1, 2\}counts={2,3,1,2}。
     *
     * 这里 \rm countscounts 数组中两个相邻的数一定代表的是两种不同的字符。假
     * 设 \rm countscounts 数组中两个相邻的数字为 uu 或者 vv，它们对应着 uu 个 00 和 vv 个 11，或者 uu 个 11 和 vv 个 00。
     * 它们能组成的满足条件的子串数目为 \min \{ u, v \}min{u,v}，即一对相邻的数字对答案的贡献。
     *
     * 我们只要遍历所有相邻的数对，求它们的贡献总和，即可得到答案。
     *
     * 这个实现的时间复杂度和空间复杂度都是 O(n)O(n)。
     *
     * 对于某一个位置 ii，其实我们只关心 i - 1i−1 位置的 \rm countscounts 值是多少，
     * 所以可以用一个 \rm lastlast 变量来维护当前位置的前一个位置，这样可以省去一个 \rm countscounts 数组的空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-binary-substrings/solution/ji-shu-er-jin-zhi-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        public int countBinarySubstrings(String s) {
            int ptr = 0, n = s.length(), last = 0, ans = 0;
            while (ptr < n) {
                char c = s.charAt(ptr);
                int count = 0; //todo: 从 ptr 位置开始，连续相同的 0 或 1 的个数
                while (ptr < n && s.charAt(ptr) == c) {
                    ++ptr;
                    ++count;
                }
                ans += Math.min(count, last); //todo: min{u,v}  u v 分别是相邻且连续的 0 或 1 的个数
                last = count;
            }
            return ans;
        }
    }

}