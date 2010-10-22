package net.blacklee.common.date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * use SimpleDateFormat to parse String,
 * and use FastDateFormat to format Date.
 * due to thread-unsafe problem
 * @author LiHuiRong
 * @created Sep 26, 2010 5:26:19 PM
 */
@SuiteClasses(value = {})
public class DateFormatter {
	
	public static final String toPattern(Date date, String pattern) {
		return FastDateFormat.getInstance(pattern).format(date);
	}
	
	private static final FastDateFormat mmddHHmmssFormatter = FastDateFormat.getInstance("MM-dd HH:mm:ss");
	public static final String toMmddHHmmss(Date date) {
		return date != null ? mmddHHmmssFormatter.format(date) : "";
	}
	
	private static final SimpleDateFormat yyyyMmddHHmmssParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final Date fromYyyyMmddHHmmss(String src) throws ParseException {
		return yyyyMmddHHmmssParser.parse(src);
	}
	
	@Test
	public void testAbc() {
		String str = "2010-09-01 11:11:11";
		Calendar c = Calendar.getInstance();
		c.set(2010, 8, 01, 11, 11, 11);
		c.set(Calendar.MILLISECOND, 0);
		assertEquals(str, toYyyyMmddHHmmss(c.getTime()));
		try {
			assertEquals(c.getTime().getTime(), fromYyyyMmddHHmmss(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(toMmddHHmmss(new Date()));
		System.out.println(toYyyyMmddHHmmss(new Date()));
	}

	private static final FastDateFormat yyyyMmddHHmmssFormatter = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	public static String toYyyyMmddHHmmss(Date date) {
		return date != null ? yyyyMmddHHmmssFormatter.format(date) : "";
	}
	
}
