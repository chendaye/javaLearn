package Algorithm.Interview.NowCoder;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class GetUglyNumber {
    /**
     * 最开始的丑数是 2 3 5, 因为每一个丑数都由 2 3 5相乘得到。
     * 后续每一个丑数都由前面已经得到的丑数相乘得到
     * 2 * （2 3 5）
     * 3 * （2 3 5）
     * 5 * （2 3 5）
     * 但是有一个顺序问题
     *
     * todo: 一个丑数成以2/3/5，得到的还是一个丑数；有3个对列pos2/pos3/pos5，每次都取最小的数，放到数组中
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0)return 0;
        // todo: 所有丑数 * 2 3 5 还是丑数； 维护 2 3 5 三个栈 表示三种相乘情况，每次取三个栈最小
        // todo: p2 p3 p5 理论上都能遍历整个数组
        int p2=0; // *2的栈 从小到大
        int p3=0; // *3的栈 从小到大
        int p5=0; // *5的栈 从小到大
        int[] result = new int[index];
        result[0] = 1;
        for(int i=1; i < index; i++){
            //todo: 每次再数组中放入一个最小的 丑数
            result[i] = Math.min(result[p2] * 2, Math.min(result[p3] * 3, result[p5] * 5));
            if(result[i] % 2 == 0) p2++; // 指向下一个最靠近(最小)自己的丑数
            if(result[i] % 3 == 0) p3++;
            if(result[i] % 5 == 0) p5++;
        }
        return result[index-1];
    }

    public int GetUglyNumber_Solution2(int index) {
        if (index <= 0) return 0;
        int[] record = new int[index];
        record[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < index; i++){
            record[i] = Math.min(record[p2] * 2, Math.min(record[p3] * 3, record[p5] * 5));
            if (record[i] % 2 == 0) p2++;
            if (record[i] % 3 == 0) p3++;
            if (record[i] % 5 == 0) p5++;
        }
        return record[index - 1];
    }
}
