package Algorithm.Interview.NowCoder;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber {
    // 比较两个字符串s1, s2大小的时候，先将它们拼接起来，比较s1+s2,和s2+s1那个大，如果s1+s2大，
    // 那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                int num1 = Integer.parseInt(numbers[i] + "" + numbers[j]);
                int num2 = Integer.parseInt(numbers[j] + "" + numbers[i]);
                if (num1 > num2){
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int n : numbers)
            builder.append(Integer.toString(n));
        return builder.toString();
    }

    public static void main(String[] args) {

    }
}
