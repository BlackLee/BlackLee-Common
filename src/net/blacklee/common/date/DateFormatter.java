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
 * Use SimpleDateFormat to parse String, and use FastDateFormat to format Date.
 * Because SimpleDateFormat is thread-unsafe, but FastDateFormat doesn't have parse method..
 * @author LiHuiRong
 * @created Sep 26, 2010 5:26:19 PM
 */
@SuiteClasses(value = {})
public class DateFormatter {
	
	/**
	 * If the pattern is defined by user in configuration file, this method is useful.
	 */
	public static final String toStringWithPattern(Date date, String pattern) {
		return FastDateFormat.getInstance(pattern).format(date);
	}
	
	/**
	 * If you have several patterns and don't sure which to use, maybe you'd better change to
	 * <code>org.apache.commons.lang.time.DateUtils.parseDate(String str, String[] parsePatterns)</code>
	 * @see org.apache.commons.lang.time.DateUtils.parseDate(String str, String[] parsePatterns)
	 * @param src string source
	 * @param pattern pattern of the source
	 * @return a date
	 * @throws IllegalArgumentException if two arguments don't map.
	 */
	public static final Date parse(String src, String pattern) {
		try {
	        return new SimpleDateFormat(pattern).parse(src);
        } catch (ParseException e) {
	        throw new IllegalArgumentException("try to parse String[" + src + "] to date use Pattern[" + pattern + "]");
        }
	}
	
	private static final FastDateFormat mmddHHmmssFormatter = FastDateFormat.getInstance("MM-dd HH:mm:ss");
	/**
	 * @return MM-dd HH:mm:ss
	 */
	public static final String toMmddHHmmss(Date date) {
		return date != null ? mmddHHmmssFormatter.format(date) : "";
	}
	
	private static final SimpleDateFormat yyyyMmddHHmmssParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param src yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static final Date fromYyyyMmddHHmmss(String src) throws ParseException {
		synchronized (yyyyMmddHHmmssParser) {
	        return yyyyMmddHHmmssParser.parse(src);
        }
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
