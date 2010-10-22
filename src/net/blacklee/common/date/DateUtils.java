package net.blacklee.common.date;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * calculate date
 * @author LiHuiRong
 * @created Sep 30, 2010 9:26:55 AM
 */
@SuiteClasses(value = {})
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
	private static final String[] unitStrs = new String[]{
		"秒",
		"分",
		"时",
		"天",//日?
	};
	/**
	 * @param str 30分钟前,1天前,22小时前
	 * @return
	 */
	public static Date getDateFromSometimeBeforeStr(String str) {
		long time = System.currentTimeMillis();
		int val = Integer.parseInt(str.replaceAll("\\D", ""));
		for (int i = 0; i < unitStrs.length; i++) {
			if (str.contains(unitStrs[i])) {
				time = time - val * millis[i];
				break;
			}
		}
		return new Date(time);
	}
	
	@Test
	public void testGetDateFromSometimeBeforeStr() {
		Date yestoday = new Date(System.currentTimeMillis() - 86400000);
		Date expectYestoday = getDateFromSometimeBeforeStr("1天前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(yestoday), DateFormatter.toYyyyMmddHHmmss(expectYestoday));
		
		Date lastHour = new Date(System.currentTimeMillis() - 3600000);
		Date expectLastHour = getDateFromSometimeBeforeStr("1小时前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(lastHour), DateFormatter.toYyyyMmddHHmmss(expectLastHour));
		
		Date lastMinute = new Date(System.currentTimeMillis() - 60000);
		Date expectLastMinute = getDateFromSometimeBeforeStr("1分钟前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(lastMinute), DateFormatter.toYyyyMmddHHmmss(expectLastMinute));
		
		System.out.println("当前点：  \t" + DateFormatter.toYyyyMmddHHmmss(new Date()));
		System.out.println("1天前：     \t" + DateFormatter.toYyyyMmddHHmmss(expectYestoday));
		System.out.println("1小时前：\t" + DateFormatter.toYyyyMmddHHmmss(expectLastHour));
		System.out.println("1分钟前：\t" + DateFormatter.toYyyyMmddHHmmss(expectLastMinute));
	}
}
