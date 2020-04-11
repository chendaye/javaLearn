package Utils;

import java.util.Arrays;
import java.util.Random;

public class Generate {
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

        //todo: Random 参数（seed）不变，产生的随机数就不变， 默认seed是System.currentTimeMillis()
        // https://juejin.im/post/5d75e0145188254c206055e6
        java.util.Random r = new java.util.Random();

        int i = 0;
        while (i<len){
            //todo: 不加1 则得到的是 0——99之间的随机数 加 1 则得到的时1——100之间的随机数
            random = r.nextInt(width);
//            random = r.nextInt(width) + 1;
            if (unique){
                if (exist[random] == 0){
                    java.util.Random r1 = new java.util.Random();
                    array[i] = r1.nextInt(2)==0? 0-random:random;
                    exist[random] = 1;
                    i++;
                }else {
                    continue;
                }
            }else {
                java.util.Random r1 = new java.util.Random();
                array[i] = r1.nextInt(2)==1? 0-random:random;
                exist[random] = 1;
                i++;
            }
        }
        if (order)
            Arrays.sort(array);
        return array;
    }

    public static String getString(int length,boolean unique) {
        if (unique && length >58)
            throw new RuntimeException("长度不对！");
        char[] table = new char[256]; // 所有256个字符
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int)(Math.random() * allCharacters.length());
            char tmp = allCharacters.charAt(randomIndex);
            if (unique)
                if (table[tmp] == 0){
                    randomString.append(tmp);
                    table[tmp] = 1;
                }else {
                    continue;
                }
            else
                randomString.append(tmp);

        }
        return randomString.toString();
    }
}
