package net.blacklee.common.string;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;

/**
 * Encode string to adapter different sites when you construct URL. 
 * If error occurs, each method returns the original input.
 * @author LiHuiRong
 * @created Sep 26, 2010 5:26:36 PM
 */
public class UrlStringCodecUtils {
	private static final Logger log = Logger.getLogger(UrlStringCodecUtils.class);
	
	/**
	 * encode string with specify charset
	 * @param src string to be encoded
	 * @param charset target charset
	 * @return encoded string
	 */
	public static String encode(String src, String charset) {
		try {
			return new URLCodec(charset).encode(src);
		} catch (EncoderException e) {
			log.error("encode [" + src + "] to " + charset + " error... " + e.getMessage());
			return src;
		}
	}
	
	private static StringEncoder utf8Encoder = new URLCodec();
	/**
	 * encode string to utf-8 form
	 * @param src string to be encoded
	 * @return encoded string
	 */
	public static String toUtf8(String src) {
		try {
			return utf8Encoder.encode(src);
		} catch (EncoderException e) {
			log.error("encode [" + src + "] to utf-8 error... " + e.getMessage());
			return src;
		}
	}
	
	private static StringEncoder gbkEncoder = new URLCodec("gbk");
	/**
	 * encode string to gbk form
	 * @param src string to be encoded
	 * @return encoded string
	 */
	public static String toGbk(String src) {
		try {
			return gbkEncoder.encode(src);
		} catch (EncoderException e) {
			log.error("encode [" + src + "] to gbk error... " + e.getMessage());
			return src;
		}
	}
	
	private static StringEncoder gb2312Encoder = new URLCodec("gb2312");
	/**
	 * encode string to gb2312 form
	 * @param src string to be encoded
	 * @return encoded string
	 */
	public static String toGb2312(String src) {
		try {
			return gb2312Encoder.encode(src);
		} catch (EncoderException e) {
			log.error("encode [" + src + "] to gb2312 error... " + e.getMessage());
			return src;
		}
	}
}
