package Algorithm.DataStructure.Sort;

import Utils.Dump;
import Utils.Generate;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    /**
     * todo: 插入排序
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

    /**
     * todo: O(nlogn)
     * @param arr
     */
    public static void sort(Comparable[] arr){
//        mergeSort(arr, 0, arr.length - 1);  //todo:自顶向下
        mergeSortUp(arr); //todo:自底向上
    }

    /**
     * todo: 自顶向下的 归并排序
     * todo:递归使用 归并排序， 对 [r.....l] 范围元素进行排序
     * @param arr
     * @param r
     * @param l
     */
    private static void mergeSort(Comparable[] arr, int l, int r){

        //todo: 这种是归并到底
        if (l >= r) return; // 归并结束

        //todo: 当剩下的无序的元素很少时，可以用插入排序
//        if (r-l < 15){
//            sortInsert(arr, l, r);
//            return;
//        }


        int mid = (l+r)/2; // 求mid
        mergeSort(arr, l, mid); // 递归对每一段进行归并排序
        mergeSort(arr, mid+1, r);
        //todo: 优化 只有当 前一段大于后面一段时 才需要归并
        //todo: 对于基本有序的数据，加上这一个判断 可以提升性能
        if (arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r); // 归并两段
    }

    /**
     * todo: 自底向上的归并排序
     * @param arr
     */
    private static void mergeSortUp(Comparable[] arr){
        int n = arr.length;
        //todo: 每一轮归并段的小=sz, 没经过一轮 sz 翻倍
        for (int sz=1; sz<= n; sz=sz*2){
            //todo: 按 sz 大小 给数组分段， 没 2个sz进行归并
            for (int i=0; i+sz-1<n; i += sz*2)
                merge(arr, i, i+sz-1, Math.min( i+2*sz-1 , n-1)); // todo:注意越界问题
        }
    }


    /**
     * todo: 归并逻辑
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Comparable[] arr, int l, int mid, int r){
        //todo: 复制原数组 [l.....r] 位置的元素， 用来排序
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r+1);
        int i=l,j=mid+1; //:todo: [l, mid] [mid+1, r]
        for (int k=l; k<=r; k++){
            if (i>mid){
                /**
                 *       0 1 2 3 4 5 6 7
                 * index 0 1 2 3 4 5 6 7
                 *       3 4 5 6 7
                 * index 0 1 2 3 4
                 *
                 * 临时数组的偏移量是 l
                 */
                arr[k] = tmp[j-l]; //todo: j-l 是 j 相对 位移
                j++;
            }else if (j>r){
                arr[k] = tmp[i-l];
                i++;
            }else{
                if (tmp[j-l].compareTo(tmp[i-l]) < 0){
                    arr[k] = tmp[j-l];
                    j++;
                }else{
                    arr[k] = tmp[i-l];
                    i++;
                }
            }
        }
    }


    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
//        Generate.testSort("Algorithm.DataStructure.Sort.MergeSort", arr);
        Dump.array(arr);

//        Dump.dump("+++++++++++++++++++++++++++++++");
//        Integer[] test = Generate.generateRandomArray(10, 1, 30);
//        Dump.array(test);
//        sortInsert(test, 5, 9);
//        Dump.array(test);
    }

}

/**
 * todo: train 1
 */
class train_merge_1{
    private static void merge_tran(Comparable[] arr){
        mergeSort_tran(arr, 0, arr.length-1);
    }

    private static void mergeSort_tran(Comparable[] arr, int l, int r){
        if (l>=r) return; //todo: 循环终止 l>=r

        int mid = (l+r)/2;
        //todo: 堆两段排序
        mergeSort_tran(arr, l, mid);
        mergeSort_tran(arr, mid+1, r);
        //todo: 优化：只有前一段 > 后一段 才需要比较
        if (arr[mid].compareTo(arr[mid+1]) > 0)
            merge_train(arr, l, mid, r);
    }

    private static void merge_train(Comparable[] arr, int l, int mid, int r){
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r + 1);//todo: 复制 [l,r]范围内的值
        int i=l; //todo: [l, mid]
        int j=mid+1; //todo: [mid+1, r]
        for (int k=l; k<=r; k++){
            if (i>mid){ //todo: [l, mid] 归并干净了
                arr[k] = tmp[j-l]; //todo: -l 是偏移量
                j++;
            }else if(j>r){ //todo: [mid+1, r] 归并干净了
                arr[k] = tmp[i-l];
                i++;
            }else{
                if (tmp[i-l].compareTo(tmp[j-l]) < 0){ //todo: 注意是比较 tmp[] 的值
                    arr[k] = tmp[i-l];
                    i++;
                }else{
                    arr[k] = tmp[j-l];
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        merge_tran(arr);
        Dump.array(arr);
    }
}

class train_merge_2{
    private static void mergeSort(Comparable[] arr, int l, int r){
        if (l >=r ) return; //todo: 只有一个元素的时候不需要 merge
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid); //todo: 归并左边
        mergeSort(arr, mid+1, r); //todo: 归并右边
        if (arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r);  //todo: 合并左右
    }

    private static void merge(Comparable[] arr, int l, int mid, int r){
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l; // [l, mid]
        int j = mid + 1; // [mid+1, r]
        //todo: 遍历数组 arr [l, r]
        for (int k = l; k <= r; k++){
            if (i > mid){
                arr[k] = tmp[j - l];
                j++;
            }else if(j > r){
                arr[k] = tmp[i - l];
                i++;
            } else {
               if (tmp[i - l].compareTo(tmp[j - l]) < 0){ // todo: 最容易错的地方
                   arr[k] = tmp[i - l];
                   i++;
               }else {
                   arr[k] = tmp[j - l];
                   j++;
               }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        mergeSort(arr, 0, arr.length - 1);
        Dump.array(arr);
    }
}

class train_merge_3{

    private static void mergeSort(Comparable[] arr, int left, int right){
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, 0, mid);
        mergeSort(arr, mid + 1, right);

        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, left, mid, right);
    }

    private static void merge(Comparable[] arr, int left, int mid, int right){
        int i = left;
        int j = mid + 1;
        Comparable[] tmp = Arrays.copyOfRange(arr, left, right + 1);
        for (int n = left; n <= right; n++){
            if (i > mid){
                arr[n] = tmp[j - left];
                j++;
            }else if(j > right){
                arr[n] = tmp[i - left];
                i++;
            }else {
                if (tmp[i - left].compareTo(arr[j - left]) <= 0){
                    arr[n] = tmp[i - left];
                    i++;
                }else{
                    arr[n] = tmp[j - left];
                    j++;
                }
            }
        }

    }



    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        mergeSort(arr, 0, arr.length - 1);
        Dump.array(arr);
    }
}


class train_merge_4{

    private static void mergeSort(Comparable[] arr, int left, int right){
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, 0, mid);
        mergeSort(arr, mid + 1, right);

        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, left, mid, right);
    }

    private static void merge(Comparable[] arr, int left, int mid, int right){
        int i = left;
        int j = mid + 1;
        Comparable[] tmp = Arrays.copyOfRange(arr, left, right + 1);
        int inx = left;
        while (i <= mid && j <= right){
            if (tmp[i - left].compareTo(tmp[j - left]) < 0){
                arr[inx++] = tmp[i - left];
                i++;
            }else {
                arr[inx++] = tmp[j - left];
                j++;
            }
        }
        while (i <= mid){
            arr[inx++] = tmp[i - left];
            i++;
        }
        while (j <= right){
            arr[inx++] = tmp[j - left];
            j++;
        }

    }



    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        mergeSort(arr, 0, arr.length - 1);
        Dump.array(arr);
    }
}

class train_merge_5{

    private static void mergeSort(Comparable[] arr, int l, int r){
        if (l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(Comparable[] arr, int l, int mid, int r){
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        int inx = l; //todo: 重点
        while (i <= mid && j <= r){
            if (tmp[i - l].compareTo(tmp[j - l]) <= 0){
                arr[inx++] = tmp[i - l];
                i++;
            }
            if (tmp[i - l].compareTo(tmp[j - l]) > 0){
                arr[inx++] = tmp[j - l];
                j++;
            }
        }
        while (i <= mid){
            arr[inx++] = tmp[i - l];
            i++;
        }
        while (j <= r){
            arr[inx++] = tmp[j - l];
            j++;
        }
    }



    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        mergeSort(arr, 0, arr.length - 1);
        Dump.array(arr);
    }
}


class train_merge_6{

      public static void mergeSort(Comparable[] arr, int l, int r){
          if (l >= r) return;
          int mid = (l + r) / 2;
          mergeSort(arr, l, mid);
          mergeSort(arr, mid+1, r);
          merge(arr,l, mid, r);
      }
      public static void merge(Comparable[] arr, int l, int mid, int r){
          Comparable[] temp = Arrays.copyOfRange(arr, l, r + 1);
          int inx = l;
          int i = l, j=mid+1;
          while (i <= mid && j <= r){
              if (temp[i -l].compareTo(temp[j - l]) <= 0){
                  arr[inx] = temp[i-l];
                  inx++;i++;
              }else {
                  arr[inx] = temp[j-l];
                  inx++;j++;
              }
          }
          while (i <= mid){
              arr[inx] = temp[i - l];
              inx++;i++;
          }
          while (j <= r){
              arr[inx] = temp[j - l];
              inx++;j++;
          }
      }


    public static void main(String[] args) {
        Integer[] arr = Generate.generateRandomArray(20, 1, 30);
        Dump.array(arr);
        mergeSort(arr, 0, arr.length - 1);
        Dump.array(arr);
    }
}