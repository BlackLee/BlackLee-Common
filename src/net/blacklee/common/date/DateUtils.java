package net.blacklee.common.date;

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
}
