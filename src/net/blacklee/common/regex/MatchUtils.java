package net.blacklee.common.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiHuiRong
 * @created 2010-11-04 16:39:58
 */
public class MatchUtils {
	/**
	 * Fetch the first matched string by input pattern and string, 
	 * the pattern string has and just has one capturing group.
	 * Even if your input several capturing groups, this method just return the first group.
	 * @param pattern your pattern string, it has and just has one group: (...)
	 * @param inputString input string
	 * @return matched String or null
	 */
	public static String getFirstMatchString(String pattern, String inputString) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(inputString);
		if (m.find()) return m.group(1);
		return null;
	}
	
	/**
	 * Just return the Matcher Object
	 * @param pattern regex pattern
	 * @param inputString input string
	 * @return Matcher object
	 */
	public static Matcher getMatcher(String pattern, String inputString) {
		return Pattern.compile(pattern).matcher(inputString);
	}
}
