package Algorithm.Interview.Company.Bytedance.Q4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 第一行包含一个正整数N，代表测试用例的个数。
 *
 * 每个测试用例的第一行包含一个正整数M，代表视频的帧数。
 *
 * 接下来的M行，每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字是在特征的取值；
 * 比如样例输入第三行里，2代表该帧有两个猫咪特征，<1，1>和<2，2>
 * 所有用例的输入特征总数和<100000
 *
 * N满足1≤N≤100000，M满足1≤M≤10000，一帧的特征个数满足 ≤ 10000。
 * 特征取值均为非负整数。\
 *
 *
 * 输出描述:
 * 对每一个测试用例，输出特征运动的长度作为一行
 *
 * 输入例子1:
 * 1
 * 8
 * 2 1 1 2 2
 * 2 1 1 1 4
 * 2 1 1 2 2
 * 2 2 2 1 4
 * 0
 * 0
 * 1 1 1
 * 1 1 1
 *
 * 输出例子1:
 * 3
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm.Interview\\Bytedance\\Q4\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //todo: N个测试用例
        for(int i = 0; i < N; ++i){
            //todo: 只用管相邻2个帧的情况
            HashMap<String, Integer> mem = new HashMap<>(); //todo: 上一个帧里，每一个特征连续出现的个数
            HashMap<String, Integer> temp_mem = new HashMap<>(); //todo: 当前帧里每一个特征，连续出现的次数
            int M = sc.nextInt(); //todo: 测试用例里面有 M 帧
            int max = 1;
            for(int j = 0; j < M; j++){
                temp_mem.clear();
                int n = sc.nextInt(); //todo: 当前帧里面有 n 特征
                for(int k = 0; k < n; k++){
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    String key = String.valueOf(x) + " " + String.valueOf(y);
                    temp_mem.put(key, mem.getOrDefault(key, 0) + 1); //todo:当前帧里面 每一个特征出现的次数
                    max = Math.max(temp_mem.get(key), max); //todo: 最大的连续帧
                }
                mem.clear();
                mem.putAll(temp_mem);
            }
            if(max <= 1){
                System.out.println(1);
            }else{
                System.out.println(max);
            }
        }
    }
}
