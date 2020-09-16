import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String s2 = "3[a]2[bc]";
        String s3 = "3[a2[c]]";
        String s = "3[a2[c2[bd]]e]";
        System.out.println(solution(s));
    }
    public static String solution(String s){
        int len = s.length();
        if (len == 0) return "";
        int left = -1, right = -1, cnt = 0;
        for (int i = 0; i < len; i++){
            Character cur = s.charAt(i);
            if (cur == ']'){
                right = i;
                break;
            }
            if (cur == '['){
                left = i;
            }
            if (cur >= '0' && cur <= '9') {
                cnt = Integer.valueOf(cur) - 48;
            }
        }
        if (left == -1 || right == -1) return s;
        String reoeat = s.substring(left+1, right);
        String l = s.substring(0, left-1);
        String r = s.substring(right+1, len);
        StringBuilder str = new StringBuilder();
        str.append(l);
        while (cnt > 0){
            str.append(reoeat);
            cnt--;
        }
        str.append(r);
        return solution(str.toString());
    }
}
