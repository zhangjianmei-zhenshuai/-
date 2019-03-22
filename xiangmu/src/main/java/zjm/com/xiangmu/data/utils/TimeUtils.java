package zjm.com.xiangmu.data.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    // 将long类型转为时间戳
    public static String longToDate(long lo) {

        Date date = new Date(lo);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sd.format(date);
    }
}
