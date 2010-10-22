package net.blacklee.common.io;

/**
 * @author LiHuiRong
 * @created 2010-10-22 12:21:43
 */
public class OSUtils {
	/**
	 * @return true if your os is windows family
	 */
	public static boolean isWindows() {
		return System.getProperty("os.name").contains("Windows");
	}
	/**
	 * @return true if your os is linux family
	 */
	public static boolean isLinux() {
		return System.getProperty("os.name").contains("Linux");
	}
}
