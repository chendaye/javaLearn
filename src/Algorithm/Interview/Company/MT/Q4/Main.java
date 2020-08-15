package Algorithm.Interview.Company.MT.Q4;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/MT/Q4/test.txt"));
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 订单数量
        int n = sc.nextInt(); // 关系数量
        HashMap<Integer, PriorityQueue<Integer>> res = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        for (int i = 1; i <= n; i++){
            int d1 = sc.nextInt();
            int d2 = sc.nextInt();

            // 都没有小区
            if(!parent.containsKey(d1) && !parent.containsKey(d2)){
                int p = Math.min(d1, d2);
                parent.put(d1, p); // key => val  值 => 小区
                parent.put(d2, p);
                PriorityQueue<Integer> queue = new PriorityQueue<>();
                queue.add(d1);
                queue.add(d2);
                res.put(p, queue);
            }
            // d2 有小区 p
            if(!parent.containsKey(d1) && parent.containsKey(d2)){
                int p = d2;
                while (parent.containsKey(p) && parent.get(p) != p){
                    p = parent.get(p);
                }
                parent.put(d1, p);
                res.get(p).add(d1);

            }
            // d1 有小区 p
            if(parent.containsKey(d1) && !parent.containsKey(d2)){
                int p = d1;
                while (parent.containsKey(p) && parent.get(p) != p){
                    p = parent.get(p);
                }
                parent.put(d2, p);
                res.get(p).add(d2);
            }
        }
        System.out.println(res.size());

        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        Iterator<Map.Entry<Integer, PriorityQueue<Integer>>> iterator = res.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, PriorityQueue<Integer>> next = iterator.next();

            map.put(next.getValue().peek(), next.getValue());
        }

        Iterator<Map.Entry<Integer, PriorityQueue<Integer>>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, PriorityQueue<Integer>> next = it.next();
            int tmp = -1;
            for (int num : next.getValue()){
                if(tmp != num){
                    System.out.print(num+" ");
                    tmp = num;
                }


            }
            System.out.println();
        }

    }


}
