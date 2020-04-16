package cn.nuaa.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: wpc
 * @Date: 2020/4/6 23:43
 * @Description: <用于给时间加月份或减月份>
 */

public class StepOrSubMonthUtil {

    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static Date subMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, -month);
        return c.getTime();
    }
}
