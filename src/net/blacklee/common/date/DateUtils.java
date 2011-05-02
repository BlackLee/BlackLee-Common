package net.blacklee.common.date;

import java.util.Calendar;
import java.util.Date;

/**
 * calculate date
 * @author LiHuiRong
 * @created Sep 30, 2010 9:26:55 AM
 */
public class DateUtils {
	
	/**
	 * @param time
	 * @return Integer value
	 */
	public static final int getIntegerTimestamp(Date time) {
		return (int) (time.getTime() / 1000); 
	}
	
	/**
	 * @return Integer value
	 */
	public static final int getCurrentIntegerTimestamp() {
		return getIntegerTimestamp(new Date());
	}
	
	private static final long[] millis = new long[]{
		org.apache.commons.lang.time.DateUtils.MILLIS_PER_SECOND,
		org.apache.commons.lang.time.DateUtils.MILLIS_PER_MINUTE,
		org.apache.commons.lang.time.DateUtils.MILLIS_PER_HOUR,
		org.apache.commons.lang.time.DateUtils.MILLIS_PER_DAY
	};
	private static final String[] cnstrUnit = new String[]{
		"秒",
		"分",
		"时",
		"天",//日?
	};
	/**
	 * Just for Chinese string [前]
	 * @param str 30分钟前,1天前,22小时前
	 * @return the parsed date 
	 */
	public static Date getDateFromSometimeBeforeCnstr(String str) {
		long time = System.currentTimeMillis();
		int val = Integer.parseInt(str.replaceAll("\\D", ""));
		for (int i = 0; i < cnstrUnit.length; i++) {
			if (str.contains(cnstrUnit[i])) {
				time = time - val * millis[i];
				break;
			}
		}
		return new Date(time);
	}
	
	/**
	 * @param date a date with value of yyyy-MM-dd HH:mm:ss
	 * @return a date with value of yyyy-MM-dd 00:00:00
	 */
	public static Date beginningOfTheDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		date.setTime(c.getTimeInMillis());
		return date;
	}
	
	/**
	 * @param start
	 * @param end
	 * @return days number of (end - start)
	 */
	public static int getDayDistanceBetween(Date start, Date end) {
		return (int) ((end.getTime() - start.getTime()) / 1000 / 86400);
	}
}
