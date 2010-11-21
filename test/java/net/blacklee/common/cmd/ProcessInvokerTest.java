package net.blacklee.common.cmd;

import java.io.IOException;

import net.blacklee.common.io.OSUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class ProcessInvokerTest {
	@Test
	public void testInvode() {
		ProcessInvoker pi = new ProcessInvoker();
		try {
			String str = "abcde";
			ProcessExecuteResult result;
			if (OSUtils.isWindows()) {
				result = pi.invoke(null, "cmd", "/C", "echo", str);
			} else {
				result = pi.invoke(null, "echo", str);
			}
			Assert.assertEquals(str, result.getStandardOutput().trim());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
