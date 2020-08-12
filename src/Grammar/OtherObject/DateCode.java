package Grammar.OtherObject;

import Utils.Dump;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateCode {
    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();

        Dump.dump(format.format(date));


        Calendar instance = Calendar.getInstance();
        Dump.dump(instance.get(Calendar.YEAR)+"年"+instance.get(Calendar.MONTH));

        /**
         * 获取随机数
         */
        Random random = new Random();
        Dump.dump(random.nextInt(10));
        int r = (int)Math.random()*10 + 1;
        Dump.dump(r);
    }
}
