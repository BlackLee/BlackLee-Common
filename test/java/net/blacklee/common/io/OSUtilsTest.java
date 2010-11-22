package net.blacklee.common.io;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class OSUtilsTest {
	@Test
	public void testNotEquals() {
		Assert.assertNotSame(OSUtils.isLinux(), OSUtils.isWindows());
	}
}
