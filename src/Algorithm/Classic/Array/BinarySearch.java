package Algorithm.Classic.Array;

import Utils.Dump;
import Utils.Generate;

/**
 * 二分查找法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[20];
        arr = Generate.get(1000000, 1000001, true, true);
        long start = System.nanoTime(); //毫秒
        int mid = method(arr, 1000000, 122);
        long end = System.nanoTime();
        Dump.dump("start:"+start);
        Dump.dump("end:"+end);
        Dump.dump("运行时间："+(end - start)+" ms-----运行结果"+mid);
    }

    /**
     * todo:循环不变量 [l....r]闭区间
     *        在定义变量时一定要明确变量的意义。 在循环时一定要维护住原始定义.也就是变量的定义在循环过程中是不变的
     *        也就是循环不变量
     *        如初始定义 int l = 0, r = len; 区间变成 [l....r)开区间
     *        这样就不用死记硬背程序
     *
     *  todo:注意小数据量调试，定位错误，理解算法
     *          大数据量测试
     * @param array
     * @param len
     * @param target
     * @return
     */
    public static int method(int[] array, int len, int target){
        int l = 0, r = len-1; // 在[l....r]闭区间找 target
        while (l <= r){ // 当 l==r时， 区间[l....r]仍然有效
            int mid = (l+r)/2;
//            int mid = l+(r-l)/2;  // 避免 l+r整型溢出
            if (array[mid] == target)
                return mid;
            if(array[mid] < target){
                l = mid+1; // target 在区间[mid+1.....r]中
            }else {
                r = mid-1; // target 在区间[l.....mid-1]中
            }
        }
        return -1;
    }
}
