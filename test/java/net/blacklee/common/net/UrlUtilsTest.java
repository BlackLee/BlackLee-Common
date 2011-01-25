package net.blacklee.common.net;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class UrlUtilsTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetHttpRealLink() {
		String expected = "http://blog.blacklee.net/tech/page/2";
		String currentAddr = "http://blog.blacklee.net/tech/page/1";
		assertEquals(expected, UrlUtils.getHttpRealLink(currentAddr, "../2"));
		assertEquals("http://blog.blacklee.net/misc", UrlUtils.getHttpRealLink(currentAddr, "../../../misc"));
		assertEquals(expected, UrlUtils.getHttpRealLink(currentAddr, "2"));
		assertEquals(expected, UrlUtils.getHttpRealLink(currentAddr, "/tech/page/2"));
		assertEquals(expected, UrlUtils.getHttpRealLink(currentAddr, "http://blog.blacklee.net/tech/page/2"));
		assertEquals(expected, UrlUtils.getHttpRealLink("exception", "aa"));
		assertEquals(expected, UrlUtils.getHttpRealLink(currentAddr, "javascript:void(0)"));
	}
	
	@Test
	public void testGetHostStr() {
		String url = "http://mail.google.com/";
		String host = "mail.google.com";
		Assert.assertEquals(host, UrlUtils.getHostFromUrl(url));
		url = "https://mail.google.com/";
		Assert.assertEquals(host, UrlUtils.getHostFromUrl(url));
		url = "https://mail.google.com/mail";
		Assert.assertEquals(host, UrlUtils.getHostFromUrl(url));
		url = "http://mail.google.com";
		Assert.assertEquals(host, UrlUtils.getHostFromUrl(url));
	}
	
	@Test
	public void testGetParam() {
		String qs = "wd=%CD%F8%D5%BE%C1%F7%C1%BF%CD%B3%BC%C6&rsp=0&oq=%C1%F7%C1%BF%CD%B3%BC%C6&f=1";
		System.out.println(UrlUtils.getParamFromQueryString(qs, "wd"));
	}

}
