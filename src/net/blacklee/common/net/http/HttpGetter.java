package net.blacklee.common.net.http;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * provide a simple interface to get html content
 * @author lihr
 * @created Oct 20, 2010 10:55:36 AM
 */
@SuiteClasses(value = {})
public class HttpGetter {
	private static final List<Integer> noBodyStatus = Arrays.asList(301, 302);
	public static String getHtml(String url) {
		GetMethod getter = new GetMethod(url);
		getter.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2");
		getter.setFollowRedirects(true);
		int status = -1;
		try {
			status = new HttpClient().executeMethod(getter);
//			if (status == HttpStatus.SC_OK) {
			if (!noBodyStatus.contains(status)) {
				return HttpResponseUtils.getResponseHtml(getter);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getter.releaseConnection();
		}
		return null;
	}
	
	@Test
	public void testGetHtml() {
		String url = "http://www.google.com/";
		System.out.println(url);
		Assert.assertNotNull(getHtml(url)); // a normal 200 return
		url = "http://blacklee.net/?page_id=2";
		System.out.println(url);
		Assert.assertNotNull(getHtml(url)); // 301 redirect to url below
		url = "http://blog.blacklee.net/?page_id=2";
		System.out.println(url);
		Assert.assertNotNull(getHtml(url)); // this will cause 302 redirect
		url = "http://www.google.com/abcdefg";
		System.out.println(url);
		Assert.assertNotNull(getHtml(url)); // 404, but still have content
		url = "http://blog.blacklee.net/uploads/500-sample.php";
		System.out.println(url);
		Assert.assertNotNull(getHtml(url)); // 500 internal error.
	}
}
