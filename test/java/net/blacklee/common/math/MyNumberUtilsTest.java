package net.blacklee.common.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author LiHuiRong
 * @created 2010-11-26 09:25:26
 */
@SuiteClasses(value = {})
public class MyNumberUtilsTest {
	@Test
	public void testIntAddMinus() {
		Assert.assertEquals(3, MyNumberUtils.intAdd("1", "2"));
		Assert.assertEquals(3, MyNumberUtils.intAdd("11", "-8"));

		Assert.assertEquals(-1, MyNumberUtils.intMinus("1", "2"));
		Assert.assertEquals(-3, MyNumberUtils.intMinus("-1", "2"));
	}

	@Test (expected=IllegalArgumentException.class)
	public void testIntCheck() {
		Assert.assertEquals(-3, MyNumberUtils.intMinus("-1", "2.1"));
	}
	
	@Test
	public void testPower() {
		Assert.assertEquals(3, MyNumberUtils.power(3, 1));
		Assert.assertEquals(9, MyNumberUtils.power(3, 2));
		Assert.assertEquals(27, MyNumberUtils.power(3, 3));
	}
}
