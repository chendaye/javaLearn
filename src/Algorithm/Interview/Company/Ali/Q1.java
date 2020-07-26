package Algorithm.Interview.Company.Ali;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Q1 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/Ali/test.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] record = new int[n + 1];
        for (int i = 1; i <= n; i++){
            record[i] = sc.nextInt();
        }

        solution(n, record);
        //超时
        if(false){
            if (n == 0) {
                System.out.println(0);
                return;
            }
            if (n == 1) {
                System.out.println(record[1]);
                return;
            }
            //贪心
            int zero = n+1;
            int numbers = 0;
            int inx = n;
            for (int k = 1; k <= n; k++){
                if (record[k] == 0) {
                    inx = k-1;
                }
            }
            if (inx < 1){
                System.out.println(0);
                return;
            }

            while (inx >= 1){
                for (int i = inx; i > 0; i--){
                    numbers += 1;
                    record[i] = record[i] - 1;
                    if (record[i] == 0){
                        inx = i-1;
                    }

                }
            }
            System.out.println(numbers);
        }
    }

    public static void solution(int n, int[] record){
        //前缀数组 前面的最小值
        HashMap<Integer, AbstractMap.SimpleEntry<Integer, Integer>> map = new HashMap<>();
        map.put(1, new AbstractMap.SimpleEntry<>(1, record[1]));

        for (int i = 2; i <= n; i++){
            AbstractMap.SimpleEntry<Integer, Integer> entry = map.get(i - 1);
            if (record[i] < entry.getValue()){
                map.put(i, new AbstractMap.SimpleEntry<Integer, Integer>(i, record[i]));
            }else{
                map.put(i, new AbstractMap.SimpleEntry<Integer, Integer>(entry.getKey(), entry.getValue()));
            }
        }
        Set<Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> next = iterator.next();
            System.out.println(next.getKey()+"--"+next.getValue());
        }
        int inx = n;
        for (int i = inx; inx > 0; i--){
            AbstractMap.SimpleEntry<Integer, Integer> entry = map.get(i);

            record[i] -= entry.getValue();

            inx = entry.getKey() - 1;
        }

    }


}
