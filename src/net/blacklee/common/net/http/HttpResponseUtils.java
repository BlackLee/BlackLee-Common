package net.blacklee.common.net.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.blacklee.common.string.MyStringUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

/**
 * Help you avoid Charset problem when using apache-commons-httpclient to get html.
 * But if you can provide the charset, it'll help me to complete this work more efficiently.
 * @author LiHuiRong
 * @created Oct 20, 2010 10:59:19 AM
 */
public class HttpResponseUtils {
	private static Logger log = Logger.getLogger(HttpResponseUtils.class);
	
	private static final String defaultCharset = Charset.defaultCharset().displayName();
	private static final Pattern pattern = Pattern.compile("<meta.*content=['\" ].*\\Wcharset=(.*?)[\"'; ].*>", 
			Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	
	/**
	 * like a browser, user doesn't need to specify a charset.
	 * @param httpResponse http response
	 * @return html content
	 * @throws IOException
	 */
	public static String getResponseHtml(HttpResponse httpResponse) throws IOException {
		String charset = null;
		// can't use method.getResponseCharSet(), because it'll return ISO-8859-1 if nothing was found in HTTP Header.
		Header header = httpResponse.getFirstHeader("Content-Type");
		String[] pairs = header.getValue().toLowerCase().split(";");
		for (String pair : pairs) {
			if (pair.indexOf("charset") > -1) {
				charset = pair.substring(pair.indexOf('=') + 1);
				if (log.isDebugEnabled()) log.debug("get charset from HTTP Header: " + charset);
				break;
			}
		}
		if (StringUtils.isBlank(charset)) {
			String guessHtml = MyStringUtils.readStringFromInputStream(httpResponse.getEntity().getContent(), defaultCharset);
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
		return MyStringUtils.readStringFromInputStream(httpResponse.getEntity().getContent(), charset);
	}
	
	/**
	 * Get html content 
	 * @param httpResponse http response
	 * @param charset charset of server's response
	 * @return html content
	 * @throws IOException
	 */
	public static String getResponseHtml(HttpResponse httpResponse, String charset) throws IOException {
		return MyStringUtils.readStringFromInputStream(httpResponse.getEntity().getContent(), charset);
	}
}
