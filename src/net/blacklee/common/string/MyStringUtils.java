package net.blacklee.common.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

/**
 * named MyStringUtils, different from apache-commons-lang.StringUtils
 * @author LiHuiRong 
 * @created Sep 26, 2010 5:26:28 PM
 */
public class MyStringUtils {
	/**
	 * Merge String arrays
	 * @param arr1
	 * @param arr2
	 * @return a new String array
	 */
	public static String[] merge(String[] arr1, String[] arr2) {
		if (arr1 == null && arr2 == null)
			return null;
		if (arr1 == null || arr2 == null)
			return arr1 == null ? arr2.clone() : arr1.clone();
		
		String[] dest = new String[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, dest, 0, arr1.length);
		System.arraycopy(arr2, 0, dest, arr1.length, arr2.length);
		return dest;
	}
	
	/**
	 * Read String content from InputStream, like HttpResponseStream, FileInputStream...
	 * @param input an InputStream
	 * @param charset use Charset.defaultCharset() if error occurs.
	 * @return String content
	 * @throws IOException
	 */
	public static String readStringFromInputStream(InputStream input, String charset) throws IOException {
		Charset cs = null;
		if (Charset.isSupported(charset))
			cs = Charset.forName(charset);
		else 
			cs = Charset.defaultCharset();
		return readStringFromInputStream(input, cs);
	}
	
	public static String readFileContent(File f) throws FileNotFoundException, IOException {
		return readStringFromInputStream(new FileInputStream(f), "");
	}
	
	/**
	 * Read String content from InputStream, like HttpResponseStream, FileInputStream...
	 * @param input an InputStream
	 * @param cs use Charset.defaultCharset() if error occurs.
	 * @return String content
	 * @throws IOException
	 */
	public static String readStringFromInputStream(InputStream input, Charset cs) throws IOException {
		if (cs == null)
			cs = Charset.defaultCharset();
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(input, cs));
		String line = null;
		while ((line = br.readLine()) != null) sb.append(line).append('\n');
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		br.close();
		input.close();
		return sb.toString();
	}
	
	/**
	 * Write String content to OutputStream
	 * @param content String content
	 * @param output an OutputStream
	 * @param charset use Charset.defaultCharset() if error occurs.
	 * @throws IOException
	 */
	public static void writeStringToOutputStream(String content, OutputStream output, String charset)
	        throws IOException {
		if (StringUtils.isBlank(charset) || !Charset.isSupported(charset))
			charset = Charset.defaultCharset().displayName();
		OutputStreamWriter writer = new OutputStreamWriter(output, Charset.forName(charset));
		writer.write(content);
		writer.flush();
		writer.close();
	}
}
