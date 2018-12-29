package com.zylear.publish.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private final static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    private final static String YMDHM = "yyyy-MM-dd HH:mm";
    private final static String YMD = "yyyy-MM-dd";
    private final static String YMD_COMPACT = "yyyyMMdd";

    public static String formatToYDMHMS(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YMDHMS);
        return formatter.format(date);
    }

    public static String formatToYDMHM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YMDHM);
        return formatter.format(date);
    }

    public static String formatToYMDCompact(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YMD_COMPACT);
        return formatter.format(date);
    }

    public static String formatToYMD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YMD);
        return formatter.format(date);
    }

    public static Date getDateFromYDMHMS(String string) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(YMDHMS);
            return formatter.parse(string.trim());
        } catch (Exception e) {
            logger.info("getDateFromYDMHMS fail. string:{}", string, e);
            throw new RuntimeException("getDateFromYDMHMS fail. string:" + string, e);
        }
    }

}
