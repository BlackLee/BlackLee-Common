package net.blacklee.common.string;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * named MyStringUtils, different from apache-commons-lang.StringUtils
 * @author LiHuiRong
 * Sep 26, 2010 5:26:28 PM
 */
public class MyStringUtils {
	/**
	 * @param arr1
	 * @param arr2
	 * @return a new String array 
	 */
	public static String[] merge(String[] arr1, String[] arr2) {
		if (arr1 == null && arr2 == null) return null;
		if (arr1 == null || arr2 == null) return arr1 == null ? arr2.clone() : arr1.clone();
		
		String[] dest = new String[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, dest, 0, arr1.length);
		System.arraycopy(arr2, 0, dest, arr1.length, arr2.length);
		return dest;
	}
	
	@Test
	public void testMerge() {
		String[] a1 = new String[]{"1", "2"};
		String[] a2 = new String[]{"3", "4", "5"};
		String[] a3 = merge(a1, a2);
		Assert.assertEquals("1,2,3,4,5", StringUtils.join(a3, ","));
	}
	
	/**
	 * @param input
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readStringFromInputStream(InputStream input, String charset) throws IOException {
		StringBuffer sb = new StringBuffer();
		byte[] bs = new byte[128];
		int len = -1;
		while ((len = input.read(bs)) >= 0) {
			sb.append(new String(bs, 0, len, charset));
		}
		input.close();
		return sb.toString();
	}
	
	/**
	 * @param content
	 * @param output
	 * @param charset
	 * @throws IOException
	 */
	public static void writeStringToOutputStream(String content, OutputStream output, String charset) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(output, Charset.forName(charset));
		writer.write(content);
		writer.flush();
		writer.close();
	}
}
