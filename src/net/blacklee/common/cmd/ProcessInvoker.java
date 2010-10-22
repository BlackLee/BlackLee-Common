package net.blacklee.common.cmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

import net.blacklee.common.io.OSUtils;
import net.blacklee.common.string.MyStringUtils;

/**
 * @author lihr
 * @created Oct 18, 2010 3:14:42 PM
 */
@SuiteClasses(value = {})
public class ProcessInvoker {
	
	private static final Logger log = Logger.getLogger(ProcessInvoker.class);
	
	public ProcessExecuteResult invoke(String directory, String cmd, String... args) throws IOException, InterruptedException {
		return invokeAtBackground(directory, cmd, 0, args);
	}
	private Process startProcess(String directory, String cmd, String... args)
			throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		if (StringUtils.isNotBlank(directory)) pb.directory(new File(directory));
		List<String> commands = new ArrayList<String>();
		commands.add(cmd);
		for (String str : args) {
			commands.add(str);
		}
		pb.command(commands);
		log.info("execute:\t" + StringUtils.join(commands.iterator(), ' '));
		Process proc = pb.start();
		return proc;
	}
	public ProcessExecuteResult invokeAtBackground(String directory, String cmd, long sleepMilliSeconds, String... args) throws IOException, InterruptedException {
		Process proc = startProcess(directory, cmd, args);
		int value = proc.waitFor();
		int exitValue = proc.exitValue();
		InputStream inputStream = proc.getInputStream();
		InputStream errorStream = proc.getErrorStream();
		if (sleepMilliSeconds > 0) {
			Thread.sleep(sleepMilliSeconds);
		}
		String standardString = MyStringUtils.readStringFromInputStream(inputStream, Charset.defaultCharset().displayName());
		String errorString = MyStringUtils.readStringFromInputStream(errorStream, Charset.defaultCharset().displayName());
		ProcessExecuteResult result = new ProcessExecuteResult(value, exitValue, standardString, errorString);
		return result;
	}
	
	@Test
	public void testInvode () {
		try {
			String str = "abcde";
			ProcessExecuteResult result;
			if (OSUtils.isWindows()) {
				result = invoke(null, "cmd", "/C", "echo", str);
			} else {
				result = invoke(null, "echo", str);
			}
			Assert.assertEquals(str, result.getStandardOutput().trim());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
