package Algorithm.DataStructure.Sort;

public class BinarySearch {


    /**
     * todo: 基础情况
     * @param target
     * @return
     */
    public int binary(int[] arr, int target){
        int left = 0, right = arr.length - 1; // [0, arr.length - 1]
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                return arr[mid];
            }
        }
        return -1;
    }

    /**
     * todo: 返回左侧边界
     * @param arr
     * @param target
     * @return
     */
    public int leftBound(int[] arr, int target){
        int left = 0, right = arr.length - 1; // [0, arr.length - 1]
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                //todo: 不返回 锁定左边界
                right = mid - 1;
            }
        }
        //todo: 检查left越界情况
        if (left >= arr.length || arr[left] != target)
            return -1;
        return arr[left];
    }

    /**
     * todo: 返回右侧边界
     * @param arr
     * @param target
     * @return
     */
    public int rightBound(int[] arr, int target){
        int left = 0, right = arr.length - 1; // [0, arr.length - 1]
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                //todo: 不返回 锁定右边界
                left = mid + 1;
            }
        }
        //todo: 检查right越界情况
        if (right < 0 || arr[right] != target)
            return -1;
        return arr[right];
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 11, 17, 19, 22, 25};
        System.out.println(new BinarySearch().binary(arr, 18));
        System.out.println(new BinarySearch().leftBound(arr, 18));
        System.out.println(new BinarySearch().rightBound(arr, 19));
    }
}
