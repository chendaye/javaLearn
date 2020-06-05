package Algorithm.Interview.Offer;

/**
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算 连续子向量 的最大和,
 * 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
 * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray {

    //todo: 暴力解法
    public int force(int[] array){
        int max = 0;
        //从每一个位置出发遍历
        for (int i = 0; i < array.length; i++){
            int current_sum = 0;
            for (int j = i; j < array.length; j++){
                int tmp = current_sum;
                current_sum += array[j];
                current_sum = Math.max(tmp, current_sum);
            }
            max = Math.max(max, current_sum);
        }
        return max;
    }

    /**
     * todo: 动态规划
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int sum = 0; // 最大和
        int[] record = new int[array.length]; // 记忆数组， record[i] 表示 i ~ n-1 连续子序列最大和
        for (int i = 0; i < array.length; i++){
            sum = Math.max(sum, find(array, i, record));
        }
        return sum;
    }

    public int find(int[] array, int index, int[] record){
        return 0;
    }

    public static void main(String[] args) {

    }
}
