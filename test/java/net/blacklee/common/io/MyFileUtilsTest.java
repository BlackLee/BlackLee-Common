package net.blacklee.common.io;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class MyFileUtilsTest {
	@Test (expected=IllegalArgumentException.class)
	public void testGetExtention() {
		File f = new File("build.xml");
		Assert.assertEquals("xml", MyFileUtils.getExtension(f));
		f = new File("test/file_without_extention");
		Assert.assertEquals("file_without_extention", MyFileUtils.getExtension(f));
		f = new File("abcd");
		String s = MyFileUtils.getExtension(f);
		Assert.assertEquals("abcd", s);
		f = new File("C:/Drivers");
		MyFileUtils.getExtension(f);
	}
}
