package net.blacklee.common.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author LiHuiRong
 * Sep 27, 2010 3:19:40 PM
 */
@SuiteClasses(value = {})
public class UrlUtils {
	/**
	 * TODO handle these url: href="../../index.html"
	 * @param host
	 * @param url
	 * @return
	 */
	public static final String getRealLink(String host, String url) {
		if (url.startsWith("/")) url = host + url;
		return url;
	}
	
	/**
	 * @param url
	 * @return
	 */
	public static final boolean validateHttpUrl(String url) {
		if (StringUtils.isBlank(url)) return false;
		if (StringUtils.startsWithIgnoreCase(url, "http://")) return true;
		return false;
	}

	private static final Pattern getHostPtn = Pattern.compile(".*//(.*?)/");
	public static String getHostFromSearchUrl(String url) {
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
	
	@Test
	public void testGetHostStr() {
		String url = "http://mail.google.com/";
		String host = "mail.google.com";
		Assert.assertEquals(host, getHostFromSearchUrl(url));
		url = "https://mail.google.com/";
		Assert.assertEquals(host, getHostFromSearchUrl(url));
		url = "https://mail.google.com/mail";
		Assert.assertEquals(host, getHostFromSearchUrl(url));
		url = "http://mail.google.com";
		Assert.assertEquals(host, getHostFromSearchUrl(url));
	}
}
