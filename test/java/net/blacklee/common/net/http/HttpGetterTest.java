package net.blacklee.common.net.http;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class HttpGetterTest {
	
	@Test
	public void testGetHtml() {
		String url = "http://www.google.com/";
		System.out.println(url);
		String html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); // a normal 200 return
		
		url = "http://blacklee.net/?page_id=2";
		System.out.println(url);
		html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); // 301 redirect to url below
		
		url = "http://blog.blacklee.net/?page_id=2";
		System.out.println(url);
		html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); // this will cause 302 redirect
		
		url = "http://www.google.com/abcdefg";
		System.out.println(url);
		html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); // 404, but still have content
		
		url = "http://blog.blacklee.net/uploads/php/500-sample.php";
		System.out.println(url);
		html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); // 500 internal error.
		

		url = "http://www.google.com.hk/search?hl=zh-CN&safe=strict&client=firefox-a&hs=sK3&q=%E8%89%BA%E6%9C%AF%E4%BA%BA%E7%94%9F+%E8%A3%85%E9%80%BC";
		System.out.println(url);
		html = HttpGetter.getHtml(url);
//		System.out.println(html);
		Assert.assertNotNull(html); 
	}
}
