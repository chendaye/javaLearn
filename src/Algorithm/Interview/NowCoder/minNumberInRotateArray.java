package Algorithm.Interview.NowCoder;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class minNumberInRotateArray {

    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len == 0) return 0;
        int p = 0;
        int q = len - 1;
        while (true){
            if (p + 1 <= len - 1 && array[p + 1] >= array[p]) p++;
            if (q - 1 >= 0 && array[q - 1] <= array[q])q--;
            if (q - p <= 1) break;
        }
        return array[q];
    }

    public static void main(String[] args) {
//        int[] array = {3,4,5,1,2};
        int[] array = {};
        minNumberInRotateArray q1 = new minNumberInRotateArray();
        System.out.println(q1.minNumberInRotateArray(array));
    }
}
