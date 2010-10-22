package net.blacklee.common.io;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * named MyFileUtils, different from apache-commons-io.FileUtils
 * @author LiHuiRong
 * @created 2010-10-21 09:04:16
 */
@SuiteClasses(value = {})
public class MyFileUtils {
	/**
	 * @param f this file can be not exist.
	 * @return file name if file has no extension
	 * @throws IllegalArgumentException if param f is a directory.
	 */
	public static String getExtension(File f) {
		if (f.isDirectory()) throw new IllegalArgumentException("this mehtod is not suit for directory");
		String filename = f.getName();
		return StringUtils.substring(filename, StringUtils.lastIndexOf(filename, '.') + 1).toLowerCase();
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetExtention() {
		File f = new File("build.xml");
		Assert.assertEquals("xml", getExtension(f));
		f = new File("test/file_without_extention");
		Assert.assertEquals("file_without_extention", getExtension(f));
		f = new File("abcd");
		String s = getExtension(f);
		Assert.assertEquals("abcd", s);
		f = new File("C:/Drivers");
		getExtension(f);
	}
}
