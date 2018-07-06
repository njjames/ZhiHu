package com.nj.zhihu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018-07-05.
 */

public class DateUtils {
    /**
     * 把yyyyMMdd格式的日期转化为MM月dd日  EEEE的格式
     * @param date
     * @return
     */
    public static String transform(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date parse = dateFormat.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日  EEEE", Locale.CHINA);
            return format.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
