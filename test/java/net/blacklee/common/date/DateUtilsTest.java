package net.blacklee.common.date;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class DateUtilsTest {
	
	@Test
	public void testGetDateFromSometimeBeforeStr() {
		Date yestoday = new Date(System.currentTimeMillis() - 86400000);
		Date expectYestoday = DateUtils.getDateFromSometimeBeforeCnstr("1天前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(yestoday), DateFormatter.toYyyyMmddHHmmss(expectYestoday));
		
		Date lastHour = new Date(System.currentTimeMillis() - 3600000);
		Date expectLastHour = DateUtils.getDateFromSometimeBeforeCnstr("1小时前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(lastHour), DateFormatter.toYyyyMmddHHmmss(expectLastHour));
		
		Date lastMinute = new Date(System.currentTimeMillis() - 60000);
		Date expectLastMinute = DateUtils.getDateFromSometimeBeforeCnstr("1分钟前");
		Assert.assertEquals(DateFormatter.toYyyyMmddHHmmss(lastMinute), DateFormatter.toYyyyMmddHHmmss(expectLastMinute));
		
		System.out.println("当前点：  \t" + DateFormatter.toYyyyMmddHHmmss(new Date()));
		System.out.println("1天前：     \t" + DateFormatter.toYyyyMmddHHmmss(expectYestoday));
		System.out.println("1小时前：\t" + DateFormatter.toYyyyMmddHHmmss(expectLastHour));
		System.out.println("1分钟前：\t" + DateFormatter.toYyyyMmddHHmmss(expectLastMinute));
	}
	
	@Test
	public void testBeginningOfTheDay() {
		Date d = DateUtils.beginningOfTheDay(new Date());
		Assert.assertEquals(DateFormatter.toYyyyMmdd(new Date()) + " 00:00:00", DateFormatter.toYyyyMmddHHmmss(d));
	}
}
