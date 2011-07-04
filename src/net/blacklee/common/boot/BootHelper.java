package net.blacklee.common.boot;

/**
 * class to get boot info
 * @author LiHuiRong
 * @created Sep 28, 2010 9:57:49 AM
 */
public class BootHelper {
	/**
	 * Sometimes we have different strategy to treat development/production environment.
	 * @return true if you're running junit
	 */
	public static boolean bootFromJunit() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		return stacks[stacks.length - 1].getClassName().contains("junit");
	}
}
