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
                //todo: 不返回 锁定左边界
                //todo: 想象 mid=target， 令right = mid - 1，接下来的循环中都是 left在移动
                //TODO:直到 left==right， 然后 left 再左移动一位 ==> left 在 right 右边(>= target)；
                // right 指向 所有小于 target的值中的最大值
                right = mid - 1;
            }
        }
        //todo: 检查left越界情况
//        if (arr[left] != target)
//            return -1;
        if (left >= arr.length)
            return -1;
        return arr[left]; //todo: 没有找到target 就返回 左边最接近 target的值
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
                //todo: 不返回 锁定右边界
                left = mid + 1;
            }
        }
        //todo: 检查right越界情况
//        if (arr[right] != target)
//            return -1;
        if (right < 0)
            return -1;
        return arr[right]; //todo: 没有找到target 就返回 右边最接近 target的值
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 11, 17, 19, 22, 25};
//        System.out.println(new BinarySearch().binary(arr, 18));
        System.out.println(new BinarySearch().rightBound(arr, 12));
//        System.out.println(new BinarySearch().leftBound(arr, 18));


//        System.out.println(new BinarySearch().test(arr, 11));
    }

    //返回左边界
    public int test(int[] arr, int target){
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] < target){
                left = mid + 1;
            }else if(arr[mid] > target){
                right = mid - 1;
            }else {
                // 求左侧边界
                left = mid + 1; // left 固定在 > mid 的位置，让right动
            }
        }
        if (right < 0) return -1;
        return arr[right]; // <= target
    }
}
