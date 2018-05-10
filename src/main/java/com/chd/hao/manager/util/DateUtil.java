package com.chd.hao.manager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghao68 on 2018/4/28
 */
public class DateUtil {


    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String format2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
