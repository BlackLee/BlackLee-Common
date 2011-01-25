package net.blacklee.common.net.http;

import net.blacklee.common.net.UrlUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author LiHuiRong
 * @created 2011-1-11 09:29:04
 */
public class SimpleHttpUrl {
	public SimpleHttpUrl(String url) {
		if (!UrlUtils.validateHttpUrl(url)) {
			throw new IllegalArgumentException("Not a valid http url: " + url);
		}
		int idx = url.indexOf('/');
		protocol = url.substring(0, idx - 1);
		idx = url.indexOf('/', url.indexOf(':') + 3);
		host = url.substring(url.indexOf(':') + 3, idx);
		idx = url.indexOf('?');
		if (idx == -1) {
			path = url.substring(url.indexOf('/', url.indexOf(':') + 3));
			queryString = "";
		} else {
			path = url.substring(url.indexOf('/', url.indexOf(':') + 3), idx);
			queryString = url.substring(url.indexOf('?') + 1);
		}
	}
	public void removeParamFromQueryString(String paramName) {
		if (StringUtils.isBlank(queryString)) return;
		String[] ss = queryString.split("&");
		for (int i = 0; i < ss.length; i++) {
	        if (ss[i].startsWith(paramName + "=")) ss[i] = null;
        }
		queryString = StringUtils.join(ss, "&").replace("&&", "&").replaceFirst("^&", "").replaceFirst("&$", "");
		if (StringUtils.isBlank(queryString)) queryString = null;
	}
	public String toURL() {
		StringBuffer sb = new StringBuffer(protocol).append("://").append(host).append(path);
		if (StringUtils.isNotBlank(queryString)) {
			sb.append('?').append(queryString);
		}
		return sb.toString();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	private String protocol;
	private String host;
	private String path;
	private String queryString;
	public String getProtocol() {
    	return protocol;
    }
	public void setProtocol(String protocol) {
    	this.protocol = protocol;
    }
	public String getHost() {
    	return host;
    }
	public void setHost(String host) {
    	this.host = host;
    }
	public String getPath() {
    	return path;
    }
	public void setPath(String path) {
    	this.path = path;
    }
	public String getQueryString() {
    	return queryString;
    }
	public void setQueryString(String queryString) {
    	this.queryString = queryString;
    }
}
