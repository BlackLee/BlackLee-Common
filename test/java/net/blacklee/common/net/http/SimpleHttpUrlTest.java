package net.blacklee.common.net.http;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author LiHuiRong
 * @created 2011-1-11 09:35:37
 */
public class SimpleHttpUrlTest {
	@Test
	public void testCons() {
		String url = "http://blog.blacklee.net/hello";
		SimpleHttpUrl shu = new SimpleHttpUrl(url);
		assertEquals("http", shu.getProtocol());
		assertEquals("blog.blacklee.net", shu.getHost());
		assertEquals("/hello", shu.getPath());
		assertNull(shu.getQueryString());
		assertEquals(url, shu.toURL());
		
		url = "https://blog.blacklee.net/samplepath?p=332";
		shu = new SimpleHttpUrl(url);
		assertEquals("https", shu.getProtocol());
		assertEquals("blog.blacklee.net", shu.getHost());
		assertEquals("/samplepath", shu.getPath());
		assertEquals("p=332", shu.getQueryString());
		assertEquals(url, shu.toURL());
		
		url = "http://www.google.com.hk/search?sourceid=chrome&ie=UTF-8&q=hello+world";
		shu = new SimpleHttpUrl(url);
		assertEquals("sourceid=chrome&ie=UTF-8&q=hello+world", shu.getQueryString());
		assertEquals(url, shu.toURL());
	}
}
