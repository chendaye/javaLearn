package Algorithm.Interview.NowCoder;

import java.util.Arrays;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 *
 * 数据范围：
 *
 * 	对于%50的数据,size<=10^4
 *
 * 	对于%75的数据,size<=10^5
 *
 * 	对于%100的数据,size<=2*10^5
 *
 * 示例1
 * 输入
 * 复制
 * 1,2,3,4,5,6,7,0
 * 输出
 * 复制
 * 7
 */
public class InversePairs {
    //todo:暴力解法 超时
    public int InversePairs(int [] array) {
        if (array.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < array.length; i++){
             for (int j = i + 1; j < array.length; j++){
                 if (array[i] > array[j]) count++;
             }
         }
        return count;
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/96bd6684e04a44eb80e6a68efc0ec6c5?answerType=1&f=discussion
     * 来源：牛客网
     *
     * 利用归并排序的思想：
     *
     * 在数组分裂的过程中，把left和right从第i个位置开始进行比较，哪个小就把它加入merged中，由于此时left和right是分别有序的，
     * 如果left[i]>right[j]，证明left[i:]都大于right[j]，此时的逆序对要加len(left)-i
     *
     * 例如，left = [5, 7], right = [4, 6]
     *
     * i = 0, j = 0, 5>4, 此时5和7都是大于4的，逆序对加2-0=2, merged = [4]
     *
     * i = 0, j = 1, 5<6, 此时无逆序对，merged = [4, 5]
     *
     * i = 1, j = 1, 7>6, 逆序对加2-1=1，merged = [4, 5, 6]
     * @param array
     * @return
     */
    private int count = 0;
    public int InversePairs2(int[] array){
        mergeSort(array, 0, array.length - 1);
        return count;
    }

    //todo: 归并排序
    public void mergeSort(int[] array, int l, int r){
        if (l >= r) return ;
        int mid = (l + r) / 2;
        mergeSort(array, l, mid);
        mergeSort(array, mid + 1, r);
        merge(array, l, mid, r);
    }


    public void merge(int[] array, int l, int mid, int r){
        int[] temp = Arrays.copyOfRange(array, l, r + 1);
        int i = l, j = mid + 1, inx = l;
        while (i <= mid && j <= r){
            if (temp[i] > temp[j]){
                count += mid - i +1; //todo: 归并的同时 统计逆序数的个数. mid - i +1 范围内的 元素都大于 temp[j]
                array[inx++] = temp[j - l];
                j++;
            }else {
                array[inx++] = temp[i - l];
                i++;
            }
        }
        while (i <= mid){
            array[inx++] = temp[i - l];
            i++;
        }
        while (j <= r){
            array[inx++] = temp[j - l];
            j++;
        }
    }



    public static void main(String[] args) {
        int[] array = {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575};
        System.out.println(new InversePairs().InversePairs(array));
    }
}
