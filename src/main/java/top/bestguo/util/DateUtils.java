package top.bestguo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串与Date对象的互转工具类
 */
public class DateUtils {

    /**
     * 字符串格式日期转Date
     *
     * @param format 日期格式，比如：yyyy-MM-dd HH:mm:ss
     * @param source 日期字符串，比如：2021-5-25 12:34:56
     * @return 返回格式
     * @throws ParseException 可能导致日期解析异常
     */
    public static Date parseToDate(String format, String source) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(source);
    }

    /**
     * Date转字符串格式日期
     *
     * @param format 日期格式，比如：yyyy-MM-dd HH:mm:ss
     * @param source 日期对象
     * @return 格式化后的日期，比如：2021-5-25 12:34:56
     */
    public static String dateFormat(String format, Date source){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(source);
    }

    /**
     * 计算时间差（单位：分钟）
     *
     * @param endTime 最后时间
     * @param startTime 开始时间
     * @return 时间差（分钟）
     */
    public static long timeDistance(Date endTime, Date startTime) {
        long diff = endTime.getTime() - startTime.getTime();
        //计算两个时间之间差了多少分钟
        return diff / (1000 * 60);
    }

    /**
     * 计算时间差（单位：毫秒）
     *
     * @param endTime 最后时间
     * @param startTime 开始时间
     * @return 时间差（分钟）
     */
    public static long timeDistanceMillionSeconds(Date endTime, Date startTime) {
        //计算两个时间之间差了多少分钟
        return endTime.getTime() - startTime.getTime();
    }

}
