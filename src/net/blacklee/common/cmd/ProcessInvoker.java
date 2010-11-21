package net.blacklee.common.cmd;

import static net.blacklee.common.string.MyStringUtils.readStringFromInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A simple Process invoker, receive [command info], and return ProcessExecuteResult contains result information.
 * @author LiHuiRong
 * @created Oct 18, 2010 3:14:42 PM
 */
public class ProcessInvoker {
	
	private static final Logger log = Logger.getLogger(ProcessInvoker.class);
	
	private Process startProcess(String directory, String cmd, String... args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		if (StringUtils.isNotBlank(directory))
			pb.directory(new File(directory));
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

	/**
	 * Invoke a process, hold on current thread. 
	 * @param directory command execute directory
	 * @param cmd command name
	 * @param args arguments of the command
	 * @return result information, includes contents of StandOutputStream and StandErrorOutputStream
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public ProcessExecuteResult invoke(String directory, String cmd, String... args)
	        throws IOException, InterruptedException {
		Process proc = startProcess(directory, cmd, args);
		int value = proc.waitFor();
		int exitValue = proc.exitValue();
		InputStream inputStream = proc.getInputStream();
		InputStream errorStream = proc.getErrorStream();
		String standardString = readStringFromInputStream(inputStream, Charset.defaultCharset().displayName());
		String errorString = readStringFromInputStream(errorStream, Charset.defaultCharset().displayName());
		ProcessExecuteResult result = new ProcessExecuteResult(value, exitValue, standardString, errorString);
		return result;
	}
}
