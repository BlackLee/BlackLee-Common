package net.blacklee.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.FastDateFormat;

/**
 * Use SimpleDateFormat to parse String, and use FastDateFormat to format Date.
 * Because SimpleDateFormat is thread-unsafe, but FastDateFormat doesn't have parse method..
 * @author LiHuiRong
 * @created Sep 26, 2010 5:26:19 PM
 */
public class DateFormatter {
	                                             
	/**
	 * If the pattern is defined by user in configuration file, this method is useful.
	 */
	public static final String toStringWithPattern(Date date, String pattern) {
		return FastDateFormat.getInstance(pattern).format(date);
	}
	
	public static final String toStringWithPattern(Date date, String pattern, Locale loc) {
		return FastDateFormat.getInstance(pattern, loc).format(date);
	}
	
	/**
	 * If you have several patterns and don't sure which to use, maybe you'd better change to
	 * <code>org.apache.commons.lang.time.DateUtils.parseDate(String str, String[] parsePatterns)</code>
	 * @see org.apache.commons.lang.time.DateUtils.parseDate(String str, String[] parsePatterns)
	 * @param src string source
	 * @param pattern pattern of the source
	 * @param loc Locale, use default if null
	 * @return a date
	 * @throws IllegalArgumentException if two arguments don't map.
	 */
	public static final Date parse(String src, String pattern, Locale loc) {
		try {
			if (loc == null) loc = Locale.getDefault();
	        return new SimpleDateFormat(pattern, loc).parse(src);
        } catch (ParseException e) {
	        throw new IllegalArgumentException("try to parse String[" + src + "] to date use Pattern[" + pattern + "]");
        }
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
		return parse(src, pattern, Locale.getDefault());
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

	private static final SimpleDateFormat yyyyMmddParser = new SimpleDateFormat("yyyy-MM-dd");
	public static Date fromYyyyMmdd(String strDate) throws ParseException {
		synchronized (yyyyMmddParser) {
	        return yyyyMmddParser.parse(strDate);
        }
    }
	
	private static final FastDateFormat yyyyMmddHHmmssFormatter = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	public static String toYyyyMmddHHmmss(Date date) {
		return date != null ? yyyyMmddHHmmssFormatter.format(date) : "";
	}

	private static final FastDateFormat yyyyMmddFormatter = FastDateFormat.getInstance("yyyy-MM-dd");
	public static String toYyyyMmdd(Date date) {
		if (date == null) return "";
	    return yyyyMmddFormatter.format(date);
    }
	
}
