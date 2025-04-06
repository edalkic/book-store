package com.example.bookstore.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    public static Date getDateFromEpochSecond(Long time) {
        Date date = new Date(time * 1000L);
        return date;
    }

    public static String getMonthNameFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }
}
