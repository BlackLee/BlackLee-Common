package net.blacklee.common.net.http;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class HttpResponseUtilsTest {
	
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
