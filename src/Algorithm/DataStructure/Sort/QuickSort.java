package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import javax.swing.event.ListDataEvent;

/**
 * todo: 快速排序
 *  - 通过交换
 *  - l是区间的起始位置
 *          - arr[l]  就是这一轮要放到正确位置的元素
 *          - [l+1....j] < arr[l]
 *          - [j+1..... i] > arr[l]
 *          -  判断 arr[i+1] 是大于 还是小于 arr[l]
 *                      - 若 arr[i+] > arr[l] i++ , 大区间长度增长 1
 *                      - 若 arr[i+] < arr[l] 交换 arr[i+1] arr[j+1] ; j++ i++.
 *                              - 也就是把 arr[i+1] 放到大区间 末尾， 然后 大小区间的头都向前挪一位
 *           - 最后 交换 a[l] a[j]; a[i] 就到了中间位置
 */
public class QuickSort {


    /**
     * todo:三路快排  <V =V >V 三部分
     * todo: 最最关键，初始情况区间：  l [l+1,lt]  [lt+1,i) [gt,r]
     *      - 整个过程要保证区间定义不变
     *      - 初始： lt=l  i=l+1  gt=r+1  ====> 保证3个区间都不存在；i指向要 处理的 位置
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static void quickSort3Way(Comparable[] arr, int l, int r) {
        if (l>r) return;
        //todo: 数据量小的时候直接中插入排序
        if (r-l<15){
            sortInsert(arr, l, r);
            return;
        }
        //todo:标定点
        swap(arr, l, (int)(Math.random()*(r-l+1)+l));
        Comparable v = arr[l];
        //todo: 初始化
        int lt = l;     //todo: arr[l+1...lt] < v
        int i = l+1;    //todo: arr[lt+1...i) == v
        int gt = r + 1; //todo:  arr[gt...r] > v
        while (i<gt){
            if (arr[i].compareTo(v) < 0){
                swap(arr, i, lt+1);
                lt++;
                i++;
            }else if (arr[i].compareTo(v) == 0){
                i++;
            }else {
                swap(arr, i, --gt);
            }
        }
        swap(arr, l, lt); //todo: [l,lt-1] < v [lt, gt-1]==v [gt,r]>v
        //todo: 再对 大于和小于的两个区间进行排序
        quickSort3Way(arr, l, lt-1);
        quickSort3Way(arr, gt, r);
    }
    private static void quickSort3Way(Comparable[] arr){
        quickSort3Way(arr, 0, arr.length-1);
    }

    /**
     * todo: 对 [0, n-1] 范围进行快排
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort2Way(Comparable[] arr, int l, int r){
        //todo: 剩下的无序的 数据较少是 可以采用直接插入排序
        if (l>=r) return;

        //todo : 直接插入
//        if (r-l < 15){
//            sortInsert(arr, 0, arr.length-1);
//            return;
//        }

        //todo: 对整体进行分区
        int p = partition3(arr, l, r);
        //todo: 对左右进行分区
        quickSort2Way(arr, l, p-1);
        quickSort2Way(arr, p+1, r);

    }
    public static void quickSort2Way(Comparable[] arr){
        quickSort2Way(arr,0, arr.length-1);
    }


    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition(Comparable[] arr, int l, int r){

        //todo:防止算法在有序情况下 退化为 n^2 的算法，随机选取标定值
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        Comparable v = arr[l];
        /**
         * todo： arr[l+1...j] < v ; arr[j+1...i) > v
         *      - l [l+1,j] [j+1,i-1] i
         *      - 初始  j=l  i=j+1=l+1  ===> 2个区间都不存在，i指向要 处理的 位置
         */
        int j = l;
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i].compareTo(v) < 0 ){
                j ++;
                swap(arr, j, i);
            }
        swap(arr, l, j);
        return j;
    }

//    /**
//     * todo: [l+1,j] < l   [j+1, i] > l
//     * @param arr
//     * @param l
//     * @param r
//     * @return
//     */
//    private static int partition2(Comparable[] arr, int l, int r){
//        // todo: 标定值
//        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
//        Comparable v = arr[l];
//
//        //todo: 初始时 2个区间都不存在
//        int i=l,j=l;
//        for (int m=i+1; m <= r; m++){
//            if (arr[m].compareTo(v) >= 0){
//                i++;
//            }else{
//                swap(arr, m, j+1);
//                i++;
//                j++;
//            }
//        }
//        swap(arr, j, l);
//        return j;
//    }

    /**
     * todo:二路快排: 从两头找
     *
     * todo: 在有大量重复元素的情况下； 采用上面的 partition 方式快排也会退化为 O(n^2)
     *      - [l+1, j) < v  (i...r] > v
     *      - i 是最终的位置
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition3(Comparable[] arr, int l, int r) {
        swap(arr, l, (int)(Math.random()*(r-l+1)+l)); //随机选定标定点
        Comparable v = arr[l];
        //todo l [l+1, j) (i, r]
        int j = l+1;
        int i = r;
        while (true){
            //todo: 从两头各自找到最近的 返常点 进行交换  如： 2 1 3  1和3交换 2 再和3交换
            while (j<=r && arr[j].compareTo(v) < 0)
                j++;
            while (i>=l+1 && arr[i].compareTo(v)>0)
                i--;
            if (j>i) break;
            swap(arr, j++, i--); //todo: 交换之后改变区间的长度
        }
        swap(arr, l, i); //todo: i 是最终 了的位置
        return i;
    }



    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 区间内的插入排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sortInsert(Comparable[] arr, int l, int r){
        //todo: 插入排序，前面部分有序； 第一个元素默认是有序；所以从 l+1 开始
        for( int i = l + 1 ; i <= r ; i ++ ){
            Comparable e = arr[i];
            int j;
            //todo: [l, i) 找插入的位置
            for(j=i ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }


    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);

        Dump.array(arr);
        quickSort2Way(arr);
        Dump.array(arr);
    }



}

/**
 * todo: train 1
 */
class train_quick_1{
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);

        Dump.array(arr);
//        quick2way_train(arr);
        quick3way_train(arr);
        Dump.array(arr);
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void quick2way_train(Comparable[] arr){
        quick2_train(arr, 0, arr.length-1);
    }

    private static void quick2_train(Comparable[] arr, int l, int r){
        if (l > r) return;  //todo: 递归终止条件
        int p = partition_train2(arr, l, r);
        quick2_train(arr, l, p-1);
        quick2_train(arr, p+1, r);
    }

    //todo: 从两边找  [l+1, j) (i, r] j=l+1 i=r
    private static int partition_train(Comparable[] arr, int l, int r){
        swap(arr, l, (int)(Math.random()*(r-l+1)+l)); //随机选定标定点
        Comparable v = arr[l];
        //todo l [l+1, j) (i, r]
        int j = l+1;
        int i = r;
        while (true){
            //todo: 从两头各自找到最近的 返常点 进行交换  如： 2 1 3  1和3交换 2 再和3交换
            while (j<=r && arr[j].compareTo(v) < 0)
                j++;
            while (i>=l+1 && arr[i].compareTo(v)>0)
                i--;
            if (j>i) break;
            swap(arr, j++, i--); //todo: 交换之后改变区间的长度
        }
        swap(arr, l, i); //todo: i 是最终 了的位置
        return i;
    }

    //todo: l [l+1, j] [j+1, i); i 是要处理的值;  j=l i=l+1
    private static int partition_train2(Comparable[] arr, int l, int r){
        swap(arr, l, (int)(Math.random()*(r-l+1) + l));
        Comparable v = arr[l];
        int j = l;
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i].compareTo(v) < 0 ){
                j ++;
                swap(arr, j, i);
            }
        swap(arr, l, j);
        return j;
    }


    //todo: 3路快排
    private static void quick3way_train(Comparable[] arr){
        quick3way_train(arr, 0, arr.length-1);
    }

    private static void quick3way_train(Comparable[] arr, int l, int r){
        if (l>r) return;
        //todo: l [l+1, lt] [lt+1, i) (gt, r]
        //todo: 始终保持区间的定义； 保证初始时 区间无效
        swap(arr, l, (int)(Math.random()*(r-l+1) +l));
        Comparable v=arr[l];
        int lt = l;
        int i= lt+1;
        int gt= r;
        while (i<=gt){
            if (arr[i].compareTo(v) == 0){
                i++;
            }else if(arr[i].compareTo(v) < 0){
                swap(arr, i, lt+1);
                i++;
                lt++;
            }else {
                swap(arr,i, gt);
                gt--;
            }
        }
        swap(arr, lt, l); //todo: 最终位置是lt

        quick3way_train(arr, l, lt-1);
        quick3way_train(arr, lt+1, r);

    }
}

/**
 * todo:快排第二次训练
 */
class train_quick_2{
    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(10, 1, 20);
        Dump.array(arr);
        quickSort(arr);
        Dump.array(arr);
    }

    private static void swap(Object[] arr, int i, int j){
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void quickSort(Comparable[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    //todo: 两边 l [l+1, i) (j, r];  初始 i=l+1  j=r
    private static void quickSort(Comparable[] arr, int l, int r){
        if (l > r) return;
        swap(arr, l, (int)(Math.random()*(r-l+1)) +l);
        Comparable v = arr[l];
        int i = l + 1;
        int j = r;
        while(true){
            while (i <= r && arr[i].compareTo(v) <= 0)
                i++;
            while (j >= l && arr[i].compareTo(v) > 0)
                j--;
            if (i>j) break;
            swap(arr, i++, j--);
        }
        swap(arr, l, j); // 4 5 3 8  4 3 5 8

        quickSort(arr, l, j-1);
        quickSort(arr, j+1, r);
    }
}