package net.blacklee.common.net.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.blacklee.common.string.MyStringUtils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.lang.StringUtils;

/**
 * @author lihr
 * Oct 20, 2010 10:59:19 AM
 */
public class HttpResponseUtils {
	private static String defaultCharset = Charset.defaultCharset().displayName();
	private static final Pattern pattern = Pattern.compile("<meta.*content=['\" ].*\\Wcharset=(.*?)[\"'; ].*>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	public static String getResponseHtml(HttpMethodBase method) throws IOException {
		String charset = null;
		Header header = method.getResponseHeader("Content-Type");
		String[] pairs = header.getValue().toLowerCase().split(";");
		for (String pair : pairs) {
			if (pair.indexOf("charset") > -1) {
				charset = pair.substring(pair.indexOf('=') + 1);
				break;
			}
		}
		byte[] bytes = null;
		if (StringUtils.isBlank(charset)) {
			bytes = method.getResponseBody();
			String guessHtml = new String(bytes, defaultCharset);
			Matcher m = pattern.matcher(guessHtml);
			if (m.find()) {
				charset = m.group(1);
			}
			if (StringUtils.isBlank(charset)) charset = defaultCharset;
			if (StringUtils.equalsIgnoreCase(charset, defaultCharset)) {
				return guessHtml;
			}
		}
		return MyStringUtils.readStringFromInputStream(method.getResponseBodyAsStream(), charset);
	}
}
