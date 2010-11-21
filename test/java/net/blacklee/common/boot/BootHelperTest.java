package net.blacklee.common.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class BootHelperTest {
	@Test
	public void testBootFromJUnit() {
		Assert.assertTrue(BootHelper.bootFromJunit());
	}
}
