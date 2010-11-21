package net.blacklee.common.net.http;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * provide a simple interface to get html content
 * @author lihr
 * @created Oct 20, 2010 10:55:36 AM
 */
public class HttpGetter {
	private static final List<Integer> noBodyStatus = Arrays.asList(301, 302);
	/**
	 * get target url html content
	 * @param url target url
	 * @return html content
	 */
	public static String getHtml(String url) {
		HttpGet getter = new HttpGet(url);
		getter.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2");
		try {
			HttpResponse resp = new DefaultHttpClient().execute(getter);
			int status = resp.getStatusLine().getStatusCode();
//			if (status == HttpStatus.SC_OK) {
			if (!noBodyStatus.contains(status)) {
				return HttpResponseUtils.getResponseHtml(resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	/**
	 * If you tell me the charset, I'll work better.
	 * @param url target url
	 * @param charset charset of server's response
	 * @return html content
	 */
	public static String getHtml(String url, String charset) {
		HttpGet getter = new HttpGet(url);
		getter.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2");
		try {
			HttpResponse httpResp = new DefaultHttpClient().execute(getter);
			if (!noBodyStatus.contains(httpResp.getStatusLine().getStatusCode())) {
				return HttpResponseUtils.getResponseHtml(httpResp, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
}
