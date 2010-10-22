package net.blacklee.common.net.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * provide a simple interface to get html content
 * @author lihr
 * Oct 20, 2010 10:55:36 AM
 */
public class HttpGetter {
	public static String getHtml(String url) {
		GetMethod getter = new GetMethod(url);
		getter.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2");
		getter.setFollowRedirects(true);
		int status = -1;
		try {
			status = new HttpClient().executeMethod(getter);
			if (status == HttpStatus.SC_OK) {
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
}
