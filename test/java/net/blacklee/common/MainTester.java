package net.blacklee.common;

import net.blacklee.common.boot.BootHelperTest;
import net.blacklee.common.cmd.ProcessInvokerTest;
import net.blacklee.common.date.DateFormatterTest;
import net.blacklee.common.date.DateUtilsTest;
import net.blacklee.common.io.MyFileUtilsTest;
import net.blacklee.common.io.OSUtilsTest;
import net.blacklee.common.math.MyNumberUtilsTest;
import net.blacklee.common.net.UrlUtilsTest;
import net.blacklee.common.net.http.HttpGetterTest;
import net.blacklee.common.net.http.HttpResponseUtilsTest;
import net.blacklee.common.string.MyStringUtilsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author LiHuiRong
 * @created 2010-10-21 09:23:22
 */
@RunWith(value=Suite.class)
@SuiteClasses(value={
		UrlUtilsTest.class, 
		DateFormatterTest.class, 
		DateUtilsTest.class, 
		BootHelperTest.class, 
		MyStringUtilsTest.class, 
		MyFileUtilsTest.class, 
//		HttpGetterTest.class,
//		HttpResponseUtilsTest.class, 
		ProcessInvokerTest.class, 
		MyNumberUtilsTest.class,
		OSUtilsTest.class
		})
public class MainTester {
	public static void main(String[] args) {
	    System.out.println("Hello Guys~~");
    }
}
