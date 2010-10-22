package net.blacklee.common.net;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author LiHuiRong
 * @created Sep 27, 2010 3:19:40 PM
 */
@SuiteClasses(value = {})
public class UrlUtils {
	/**
	 * @param currentAddr
	 * @param url
	 * @return
	 * @throws IllegalArgumentException if currentAddr isn't starts with [http://] or [https://]
	 * @throws IllegalArgumentException if url starts with [javascript:]
	 */
	public static String getHttpRealLink(String currentAddr, String url) {
		if (!validateHttpUrl(currentAddr)) {
			throw new IllegalArgumentException("currentAddr is wrong, it should be starts with 'http://' or 'https://', but actual is: [" + currentAddr + "]");
		}
		if (StringUtils.startsWithIgnoreCase(url, "javascript:")) {
			throw new IllegalArgumentException("this method doesn't handle javascript sytle url: [" + url + "]");
		}
		if (StringUtils.isBlank(StringUtils.trim(url))) {
			url = currentAddr;
		} else if (StringUtils.startsWithIgnoreCase(url, "http://") || StringUtils.startsWithIgnoreCase(url, "https://")) {
		} else if (url.startsWith("/")) {
			url = getProtocalFromUrl(currentAddr) + "://" + getHostFromUrl(currentAddr) + url;
		} else if (url.startsWith("..")) {
			while (url.startsWith("..")) {
				url = url.substring(3);
				currentAddr = currentAddr.substring(0, currentAddr.lastIndexOf('/') + 1);
			}
			url = currentAddr + url;
		} else { // current=http://twitter.com, url=someguy
			url = "../" + url;
			return getHttpRealLink(currentAddr, url);
		}
		return url;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetHttpRealLink() {
		String expected = "http://blog.blacklee.net/tech/page/2";
		String currentAddr = "http://blog.blacklee.net/tech/page/1";
		assertEquals(expected, getHttpRealLink(currentAddr, "../2"));
		assertEquals(expected, getHttpRealLink(currentAddr, "2"));
		assertEquals(expected, getHttpRealLink(currentAddr, "/tech/page/2"));
		assertEquals(expected, getHttpRealLink(currentAddr, "http://blog.blacklee.net/tech/page/2"));
		assertEquals(expected, getHttpRealLink("exception", "aa"));
		assertEquals(expected, getHttpRealLink(currentAddr, "javascript:void(0)"));
	}
	
	/**
	 * @param url
	 * @return true if url starts with [http://] or [https://]
	 */
	public static boolean validateHttpUrl(String url) {
		if (StringUtils.isBlank(url)) return false;
		if (StringUtils.startsWithIgnoreCase(url, "http://") || StringUtils.startsWithIgnoreCase(url, "https://")) return true;
		return false;
	}

	private static final Pattern getHostPtn = Pattern.compile(".*//(.*?)/");
	public static String getHostFromUrl(String url) {
		if (StringUtils.isBlank(url)) 
			throw new IllegalArgumentException("url can't be empty.");
		String host = null;
		Matcher m = getHostPtn.matcher(url);
		if (m.find()){
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
	
	private static final Pattern getProtocalPtn = Pattern.compile("(.*?)://");
	public static String getProtocalFromUrl(String url) {
		if (StringUtils.isBlank(url)) 
			throw new IllegalArgumentException("url can't be empty.");
		String protocal = null;
		Matcher m = getProtocalPtn.matcher(url);
		if (m.find()) {
			protocal = m.group(1);
		} else { // if not match the regex, simple get text before [:]
			protocal = StringUtils.substring(url, 0, url.indexOf(':'));
		}
		return protocal;
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
