package Algorithm.Interview.LeetCode.Recursion;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 *
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2:
 *
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3:
 *
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * 注意:
 *
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-atoms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class countOfAtoms {

    public static void main(String[] args) {
//        System.out.println(decode("K4(ON(SO3)2)2"));
       System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }

    /**
     * 先解压字符串，再遍历
     * @param formula
     * @return
     */
    public static String countOfAtoms(String formula) {
        String str = decode(formula);
        System.out.println(str);
        TreeMap<String, Integer> map = new TreeMap<>();

//        boolean matches = Pattern.matches("$[A-z]+", str);
        Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]*[0-9]{0,}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            String reg = matcher.group(0);
            String num = reg.replaceAll("[A-Za-z]", "");
            String key = reg.replaceAll("[0-9]", "");
            int cnt = num.length() > 0 ? Integer.parseInt(num) : 1;

            if (map.containsKey(key)){
                map.put(key, map.get(key) + cnt);
            }else{
                map.put(key, cnt);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> enrty : map.entrySet()){
            builder.append(enrty.getKey());
            if (enrty.getValue() > 1) builder.append(enrty.getValue());
//            System.out.println(enrty.getKey()+"=="+enrty.getValue());
        }
        return builder.toString();
    }

    public static String decode(String str){
        if (str.length() == 0) return "";
        int left = -1, right = -1, ni = -1, cnt = 0;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == '('){
                left = i;
            }
            if (str.charAt(i) == ')'){
                right = i;

                int inx = i + 1;
                while (inx < str.length() && str.charAt(inx) >= '0' && str.charAt(inx) <= '9'){
//                    System.out.println(str.charAt(inx));
                    inx++;
                }
//                System.out.println(str.substring(right + 1, inx));
                cnt = Integer.parseInt(str.substring(right + 1, inx));
                ni = inx;
                break;
            }
        }
        if (right != -1){
            String s1 = str.substring(0, left);
            String s2 = str.substring(left + 1, right);
            String s3 = str.substring(ni);

            StringBuilder builder = new StringBuilder(s1);
            while (cnt > 0){
                builder.append(s2);
                cnt--;
            }
            if (s3.length() > 0)builder.append(s3);
            return decode(builder.toString());
        }else{
            return str;
        }
    }
}
