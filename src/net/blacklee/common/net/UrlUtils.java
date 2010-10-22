package net.blacklee.common.net;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Some methods are only suit for HTTP protocol.
 * @author LiHuiRong
 * @created 2010-10-22 下午04:38:38
 */
@SuiteClasses(value = {})
public class UrlUtils {
	/**
	 * When crawled a page, parse the a tag, will get links, but these links have many styles, thie method will
	 * transform these links to absolute URL. Don't input javascript to test me, thx.
	 * @param currentAddr
	 * @param url href value in a tag.
	 * @return absolute HTTP URL
	 * @throws IllegalArgumentException if currentAddr isn't starts with [http://] or [https://]
	 * @throws IllegalArgumentException if url starts with [javascript:]
	 */
	public static String getHttpRealLink(String currentAddr, String url) {
		if (!validateHttpUrl(currentAddr)) {
			throw new IllegalArgumentException(
			        "currentAddr is wrong, it should be starts with 'http://' or 'https://', but actual is: ["
			                + currentAddr + "]");
		}
		if (StringUtils.startsWithIgnoreCase(url, "javascript:")) {
			throw new IllegalArgumentException("this method doesn't handle javascript sytle url: [" + url + "]");
		}
		if (StringUtils.isBlank(StringUtils.trim(url))) {
			url = currentAddr;
		} else if (StringUtils.startsWithIgnoreCase(url, "http://")
		        || StringUtils.startsWithIgnoreCase(url, "https://")) {
		} else if (url.startsWith("/")) {
			url = getProtocolFromUrl(currentAddr) + "://" + getHostFromUrl(currentAddr) + url;
		} else if (url.startsWith("..")) {
			while (url.startsWith("..")) {
				url = url.substring(3);
				if (currentAddr.endsWith("/"))
					currentAddr = currentAddr.replaceFirst("/$", "");
				currentAddr = currentAddr.substring(0, currentAddr.lastIndexOf('/') + 1);
			}
			url = currentAddr + url;
		} else { // current=http://twitter.com, url=someguy
			url = "../" + url;
			return getHttpRealLink(currentAddr, url);
		}
		return url;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetHttpRealLink() {
		String expected = "http://blog.blacklee.net/tech/page/2";
		String currentAddr = "http://blog.blacklee.net/tech/page/1";
		assertEquals(expected, getHttpRealLink(currentAddr, "../2"));
		assertEquals("http://blog.blacklee.net/misc", getHttpRealLink(currentAddr, "../../../misc"));
		assertEquals(expected, getHttpRealLink(currentAddr, "2"));
		assertEquals(expected, getHttpRealLink(currentAddr, "/tech/page/2"));
		assertEquals(expected, getHttpRealLink(currentAddr, "http://blog.blacklee.net/tech/page/2"));
		assertEquals(expected, getHttpRealLink("exception", "aa"));
		assertEquals(expected, getHttpRealLink(currentAddr, "javascript:void(0)"));
	}
	
	/**
	 * Validate a url is right or not
	 * @param url
	 * @return true if url starts with [http://] or [https://]
	 */
	public static boolean validateHttpUrl(String url) {
		if (StringUtils.isBlank(url))
			return false;
		if (StringUtils.startsWithIgnoreCase(url, "http://") || StringUtils.startsWithIgnoreCase(url, "https://"))
			return true;
		return false;
	}
	
	private static final Pattern getHostPtn = Pattern.compile(".*//(.*?)/");
	/**
	 * Get host value from a url, not only http address.
	 * @param url source url
	 * @return the host string
	 */
	public static String getHostFromUrl(String url) {
		if (StringUtils.isBlank(url))
			throw new IllegalArgumentException("url can't be empty.");
		String host = null;
		Matcher m = getHostPtn.matcher(url);
		if (m.find()) {
			host = m.group(1);
		} else {
			if (StringUtils.countMatches(url, "/") == 2) {
				host = url.substring(url.lastIndexOf('/') + 1);
			} else {
				throw new RuntimeException("can't get host from [" + url + "]");
			}
		}
		return host;
	}
	
	private static final Pattern getProtocolPtn = Pattern.compile("(.*?)://");
	/**
	 * Get protocol value from a url, not only http address.
	 * @param url source url
	 * @return protocol string
	 */
	public static String getProtocolFromUrl(String url) {
		if (StringUtils.isBlank(url))
			throw new IllegalArgumentException("url can't be empty.");
		String protocol = null;
		Matcher m = getProtocolPtn.matcher(url);
		if (m.find()) {
			protocol = m.group(1);
		} else { // if not match the regex, simple get text before [:]
			protocol = StringUtils.substring(url, 0, url.indexOf(':'));
		}
		return protocol;
	}
	
	@Test
	public void testGetHostStr() {
		String url = "http://mail.google.com/";
		String host = "mail.google.com";
		Assert.assertEquals(host, getHostFromUrl(url));
		url = "https://mail.google.com/";
		Assert.assertEquals(host, getHostFromUrl(url));
		url = "https://mail.google.com/mail";
		Assert.assertEquals(host, getHostFromUrl(url));
		url = "http://mail.google.com";
		Assert.assertEquals(host, getHostFromUrl(url));
	}
}
