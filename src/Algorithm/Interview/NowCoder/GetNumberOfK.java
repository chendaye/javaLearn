package Algorithm.Interview.NowCoder;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {
    public int GetNumberOfK(int [] array , int k) {
        if (array.length == 0) return 0;
        int cnt = 0;
        int ind = -1;
        for (int i = 0; i < array.length; i++){
            if (array[i] == k){
                ind = i;
                break;
            }
        }
        if (ind == -1) return 0;
        for (int i = ind; i < array.length; i++){
            if (array[i] != k) break;
            cnt++;
        }
        return cnt;
    }
}
