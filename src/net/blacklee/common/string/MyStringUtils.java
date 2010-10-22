package net.blacklee.common.string;

import java.io.FileInputStream;
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
	public static String[] merge(String[] arr1, String[] arr2) {
		
		if (arr1 == null && arr2 == null) return null;
		if (arr1 == null || arr2 == null) return arr1 == null ? arr2.clone() : arr1.clone();
		
		String[] tar = new String[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, tar, 0, arr1.length);
		System.arraycopy(arr2, 0, tar, arr1.length, arr2.length);
		return tar;
	}
	@Test
	public void testMerge() {
		String[] a1 = new String[]{"1", "2"};
		String[] a2 = new String[]{"3", "4", "5"};
		String[] a3 = merge(a1, a2);
		Assert.assertEquals("1,2,3,4,5", StringUtils.join(a3, ","));
	}
	
//	public static String readStringFromInputStream(InputStream is, String charset) throws IOException {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
//		StringBuffer sb = new StringBuffer();
//		String line = null;
//		while ((line = reader.readLine()) != null) sb.append(line).append('\n');
//		sb.setLength(sb.length() - 1);
//		reader.close();
//		is.close();
//		return sb.toString();
//	}
	
	/**
	 * @param is
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readStringFromInputStream(InputStream is, String charset) throws IOException {
		StringBuffer sb = new StringBuffer();
		byte[] bs = new byte[128];
		int len = -1;
		while ((len = is.read(bs)) >= 0) {
			sb.append(new String(bs, 0, len, charset));
		}
		is.close();
		return sb.toString();
	}
	
	public static void writeStringToOutputStream(String content, OutputStream os, String charset) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(os, Charset.forName(charset));
		writer.write(content);
		writer.flush();
		writer.close();
	}
	
	/**
	 * TODO this method is not correct!!!
	 */
//	@Test
	public void testReadStringFromInputStream() {
		String content = "encoding\n编码";
		try {
//			InputStream ansi = new FileInputStream("E:/test-files/ansi.txt");
//			String ansiStr = readStringFromInputStream(ansi, "ansii");
//			System.out.println(ansiStr);

			InputStream utf8 = new FileInputStream("E:/test-files/utf8.txt");
			String utf8Str = readStringFromInputStream(utf8, "utf-8");
			InputStream uni = new FileInputStream("E:/test-files/unicode.txt");
			String uniStr = readStringFromInputStream(uni, "unicode");
			
//			System.out.println("[" + utf8Str + "]");
//			System.out.println("[" + content + "]");
//			printBytes("normal ", content.trim());
//			printBytes("utf-8  ", utf8Str.trim());
			printBytes("unicode", uniStr.trim());
			printBytes("unicode", new String(content.getBytes(), "unicode"));
			
			Assert.assertEquals(new String(content.getBytes(), "unicode"), uniStr);
			
//			Assert.assertEquals(content, ansiStr);
//			Assert.assertEquals(content, utf8Str);
//			Assert.assertEquals(content, uniStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printBytes(String charset, String str) {
		byte[] bs = str.getBytes();
		System.out.print(charset + "\t");
		for (int i = 0; i < bs.length; i++) {
			System.out.print("[" + bs[i] + "]");
		}
		System.out.println();
	}
}
