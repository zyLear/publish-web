package com.zylear.publish.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private final static SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat YMD_COMPACT = new SimpleDateFormat("yyyyMMdd");

    public static String formatToYDMHMS(Date date) {
        return YMDHMS.format(date);
    }

    public static String formatToYMDCompact(Date date) {
        return YMD_COMPACT.format(date);
    }

    public static String formatToYMD(Date date) {
        return YMD.format(date);
    }

    public static Date getDateFromYDMHMS(String string) {
        try {
            return YMDHMS.parse(string);
        } catch (ParseException e) {
            logger.info("getDateFromYDMHMS fail. string:{}", string, e);
            return null;
        }
    }


    public static void main(String[] args) {
//        System.out.println();
        System.out.println(formatToYDMHMS(getDateFromYDMHMS("2018-08-09 00:15:24".trim())));
    }
}
