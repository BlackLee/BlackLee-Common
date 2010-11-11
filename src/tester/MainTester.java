package tester;

import net.blacklee.common.boot.BootHelper;
import net.blacklee.common.cmd.ProcessInvoker;
import net.blacklee.common.date.DateFormatter;
import net.blacklee.common.date.DateUtils;
import net.blacklee.common.io.MyFileUtils;
import net.blacklee.common.io.OSUtils;
import net.blacklee.common.net.UrlUtils;
import net.blacklee.common.net.http.HttpGetter;
import net.blacklee.common.net.http.HttpResponseUtils;
import net.blacklee.common.string.MyStringUtils;
import net.blacklee.common.string.UrlStringCodecUtils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author LiHuiRong
 * @created 2010-10-21 09:23:22
 */
@RunWith(value=Suite.class)
@SuiteClasses(value={
		UrlUtils.class, 
		DateFormatter.class, 
		DateUtils.class, 
		BootHelper.class, 
		MyStringUtils.class, 
		MyFileUtils.class, 
		HttpGetter.class,
		HttpResponseUtils.class, 
		ProcessInvoker.class, 
		UrlStringCodecUtils.class,
		OSUtils.class
		})
public class MainTester {
	public static void main(String[] args) {
	    System.out.println("Hello Guys~~");
    }
}
