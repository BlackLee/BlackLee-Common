package net.blacklee.common.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class MyStringUtilsTest {
	
	@Test
	public void testMerge() {
		String[] a1 = new String[] { "1", "2" };
		String[] a2 = new String[] { "3", "4", "5" };
		String[] a3 = MyStringUtils.merge(a1, a2);
		Assert.assertEquals("1,2,3,4,5", StringUtils.join(a3, ","));
	}

}
