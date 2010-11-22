package net.blacklee.common.io;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * named MyFileUtils, different from apache-commons-io.FileUtils
 * @author LiHuiRong
 * @created 2010-10-21 09:04:16
 */
public class MyFileUtils {
	/**
	 * Get the extension of a file, without dot symbol.
	 * @param f this file can be not exist.
	 * @return file name if file has no extension
	 * @throws IllegalArgumentException if param f is a directory.
	 */
	public static String getExtension(File f) {
		if (f.isDirectory()) throw new IllegalArgumentException("this mehtod is not suit for directory");
		String filename = f.getName();
		return StringUtils.substring(filename, StringUtils.lastIndexOf(filename, '.') + 1).toLowerCase();
	}
}
