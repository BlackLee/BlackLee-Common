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
		boolean yes = false;
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement[] stacks = e.getStackTrace();
			if (stacks[stacks.length - 1].getClassName().contains("junit")) yes = true;
		}
		return yes;
	}
	
//	/**
//	 * TODO I forget the purpose of writing this method's :-(
//	 * @param cls
//	 * @return
//	 */
//	public static boolean bootFromOwn(Class<?> cls) {
//		boolean yes = false;
//		try {
//			throw new Exception();
//		} catch (Exception e) {
//			StackTraceElement[] stacks = e.getStackTrace();
//			if (stacks[stacks.length - 1].getClassName().contains(cls.getName())) yes = true;
//		}
//		return yes;
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(bootFromOwn(BootHelper.class));
//	}
//	
//	@Test
//	public void testB() {
//		Assert.assertFalse(bootFromOwn(BootHelper.class));
//	}
//	
}
