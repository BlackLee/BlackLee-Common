package net.blacklee.common.math;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * named MyNumberUtils, different from apache-commons-lang.NumberUtils
 * @author LiHuiRong
 * @created 2010-11-26 08:52:40
 */
public class MyNumberUtils {
	
	public static int power(int base, int exponent) {
		if (exponent < 1) throw new IllegalArgumentException("This method doesn't support exponent small than 1, please use Math.power to calc this.");
		int result = base;
		for (int i = 1; i < exponent; i++) result = result * base;
		return result;
	}
	
	/**
	 * I don't check exceptions, do it by yourself!
	 */
	public static int[] toInt(String[] ss) {
		int[] is = new int[ss.length];
		for (int i = 0; i < is.length; i++) {
	        is[i] = Integer.parseInt(ss[i]);
        }
		return is;
	}
	/**
	 * @param a int number
	 * @param b int number
	 * @return a + b
	 */
	public static int intAdd(String a, String b) {
		checkInteger(a, b);
		return NumberUtils.toInt(a) + NumberUtils.toInt(b);
	}

	private static void checkInteger(String a, String b) {
	    if (!NumberUtils.isNumber(a) || !NumberUtils.isNumber(b) || StringUtils.contains(a, '.') || StringUtils.contains(b, '.'))
			throw new IllegalArgumentException("Params error, expect int, actual is: [" + a + "] and [" + b + "]");
    }
	
	/**
	 * @param a int number
	 * @param b int number
	 * @return a - b
	 */
	public static int intMinus(String a, String b) {
		checkInteger(a, b);
		return NumberUtils.toInt(a) - NumberUtils.toInt(b);
	}
}
