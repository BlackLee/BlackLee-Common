package net.blacklee.common.log4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * A log4j appender that separate log messages by logger-invoker thread name.
 * @author LiHuiRong
 * @created 2010-10-29 21:38:38
 */
public class ThreadNameAppender extends RollingFileAppender {
	private static Hashtable<String, CountingQuietWriter> writers = new Hashtable<String, CountingQuietWriter>();
	protected synchronized void subAppend(LoggingEvent event) {
		CountingQuietWriter writer = writers.get(event.getThreadName());
		if (writer == null) {
			try {
				writer = new CountingQuietWriter(new FileWriter(folder + "/" + event.getThreadName() + ".log", true), super.errorHandler);
			} catch (IOException e) {
				e.printStackTrace();
			}
			writers.put(event.getThreadName(), writer);
		}
		this.qw = writer;
		super.subAppend(event);
	}
	
	private String folder = "logs/";
	public void setFolder(String folder) {
		this.folder = folder;
	}
}
