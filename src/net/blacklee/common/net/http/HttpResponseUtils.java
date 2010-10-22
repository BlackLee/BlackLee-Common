package net.blacklee.common.net.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import net.blacklee.common.string.MyStringUtils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Help you avoid Charset problem when using apache-commons-httpclient to get html.
 * @author LiHuiRong
 * @created Oct 20, 2010 10:59:19 AM
 */
@SuiteClasses(value = {})
public class HttpResponseUtils {
	private static Logger log = Logger.getLogger(HttpResponseUtils.class);
	
	private static final String defaultCharset = Charset.defaultCharset().displayName();
	private static final Pattern pattern = Pattern.compile("<meta.*content=['\" ].*\\Wcharset=(.*?)[\"'; ].*>", 
			Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	
	/**
	 * like a browser, user doesn't need to specify a charset.
	 * @param method method after execution
	 * @return html content
	 * @throws IOException
	 */
	public static String getResponseHtml(HttpMethodBase method) throws IOException {
		String charset = null;
		// can't use method.getResponseCharSet(), because it'll return ISO-8859-1 if nothing was found in HTTP Header.
		Header header = method.getResponseHeader("Content-Type");
		String[] pairs = header.getValue().toLowerCase().split(";");
		for (String pair : pairs) {
			if (pair.indexOf("charset") > -1) {
				charset = pair.substring(pair.indexOf('=') + 1);
				if (log.isDebugEnabled()) log.debug("get charset from HTTP Header: " + charset);
				break;
			}
		}
		byte[] bytes = null;
		if (StringUtils.isBlank(charset)) {
			bytes = method.getResponseBody();
			String guessHtml = new String(bytes, defaultCharset);
			Matcher m = pattern.matcher(guessHtml);
			if (m.find()) {
				charset = m.group(1);
				log.info("get charset from meta tag: " + charset);
			}
			if (StringUtils.isBlank(charset)){
				charset = defaultCharset;
				log.info("I can't find charset, so use default: " + charset);
			}
			if (StringUtils.equalsIgnoreCase(charset, defaultCharset)) {
				return guessHtml;
			}
		}
		return MyStringUtils.readStringFromInputStream(method.getResponseBodyAsStream(), charset);
	}
	
	@Test
	public void testGetResponseHtml() {
		String url = "http://www.google.com.hk/";
		String word = "廣告服務";
//		System.out.println("For Mainland China user, you reach [" + url + "] from [www.google.cn], 
//		your cookies tell the server to response Simplified Chinese");
		System.out.println("[" + url + "] should contains [" + word + "]");
		String html = HttpGetter.getHtml(url);
		Assert.assertTrue(html.contains(word));

		url = "http://www.google.cn/";
		word = "请收藏我们的网址";
		System.out.println("[" + url + "] should contains [" + word + "]");
		html = HttpGetter.getHtml(url);
		Assert.assertTrue(html.contains(word));

		url = "http://www.google.co.jp/";
		word = "広告掲載";
		System.out.println("[" + url + "] should contains [" + word + "]");
		html = HttpGetter.getHtml(url);
		Assert.assertTrue(html.contains(word));

		url = "http://www.google.co.kr/";
		word = "광고 프로그램";
		System.out.println("[" + url + "] should contains [" + word + "]");
		html = HttpGetter.getHtml(url);
		Assert.assertTrue(html.contains(word));
	}
}
