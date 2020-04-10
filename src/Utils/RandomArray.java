package Utils;

import java.util.Arrays;
import java.util.Random;

public class RandomArray {
    public static void main(String[] args) {
        int[] ints = new int[100];

        ints = get(10, 11, true, true);

        for(int i =0;i<10;i++) {
            Dump.dump(ints[i]);
        }

    }

    /**
     * 产生随机数组
     * @param len 数组长度
     * @param width 随机数范围
     * @param unique 元素是否重复
     * @param order 元素是否有序
     * @return
     */
    public static int[] get(int len, int width, boolean unique, boolean order){
        if (unique && len > width)
            throw new RuntimeException("随机数范围太小！");
        int[] array = new int[len];
        int[] exist = new int[width+1];
        Arrays.fill(exist, 0); // 填充0
        int  random = 0;

        Random r = new Random(len);

        int i = 0;
        while (i<len){
            //todo: 不加1 则得到的是 0——99之间的随机数 加 1 则得到的时1——100之间的随机数
            random = r.nextInt(width);
//            random = r.nextInt(width) + 1;
            if (unique){
                if (exist[random] == 0){
                    array[i] = random;
                    exist[random] = 1;
                    i++;
                }else {
                    continue;
                }
            }else {
                array[i] = random;
                exist[random] = 1;
                i++;
            }
        }
        if (order)
            Arrays.sort(array);
        return array;
    }
}
