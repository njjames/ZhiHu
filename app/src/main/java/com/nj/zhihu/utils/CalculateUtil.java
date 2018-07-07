package com.nj.zhihu.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018-07-07.
 */

public class CalculateUtil {
    public static String CalculatePraise(int num) {
        if (num > 1000) {
            double d = (double) num / 1000;
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(d) + "K";
        }
        return Integer.toString(num);
    }
}
