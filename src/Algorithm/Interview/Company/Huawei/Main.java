package Algorithm.Interview.Company.Huawei;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 5a 90
 * todo: 5a
 * 5b 91
 * ba 186
 *
 * todo: 5b
 * 5b 91
 * bb 187
 *
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\test.txt"));
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> record = new ArrayList<>();
        while (sc.hasNextInt(16)){
            record.add(sc.nextInt(16));
        }

        //todo: 去掉首尾的 90 5a
//        record.remove(0);
//        record.remove(record.size()-1);

        Iterator<Integer> iterator = record.iterator();
        ArrayList<String> strings = new ArrayList<>();

        int len = 0; // 字节个数
        boolean flag = true;
        int index=0;
        int loc=0;
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next == 90){
                strings.add(next+"");
                len=0;
                flag=true;
                loc=strings.size()-1;
                index++;
            }else if (index+1 < record.size() && record.get(index+1) == 90){
                if (len != next){
                    for (int i=loc; i<strings.size()-1;i++)
                        strings.remove(i);
                    len=0;
                    flag=false;
                }else{
                    if (flag){
                        strings.add(next+"");
                    }
                }
                index++;
            }else {
                if (next != 91){
                    if (flag){
                        strings.add(next+"");
                        len+=2;
                    }
                    index++;
                }else{
                    if (index+1<record.size() && (record.get(index+1) == 186 || record.get(index+1) == 187)){
                        if (flag){
                            strings.add(next+"");
                            strings.add(iterator.next()+"");
                            len+=2;
                            index+=2;
                        }
                        index++;
                    }else{
                        for (int i=loc; i<strings.size()-1;i++)
                            strings.remove(i);
                        len=0;
                        flag=false;
                        index++;
                    }
                }
            }
        }

       for (String s: strings){
           int i = Integer.parseInt(s);
           Dump.dump(Integer.toHexString(i));
       }
    }
}
