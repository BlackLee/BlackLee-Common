package net.blacklee.common.wrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * Convert a String to an Object
 * TODO: write testcase
 * @author LiHuiRong
 * @created 2010-11-02 10:57:32
 */
public class StringPatternWrapper {
	private static final Logger log = Logger.getLogger(StringPatternWrapper.class);
	
	private Pattern pattern;
	private String[] properties;
	private int[] indexes;
	
	/**
	 * @param ptn regex pattern
	 * @param props bean's properties that to be setted
	 * @param idxes properties indexes mapped to pattern group
	 * @throws IllegalArgumentException if properties' length don't equals indexes' length
	 */
	public StringPatternWrapper(String ptn, String props, String idxes) {
		pattern = Pattern.compile(ptn);
		properties = props.split(",");
		String[] ss = idxes.split(",");
		int len = ss.length;
		indexes = new int[len];
		if (properties.length != indexes.length) {
			throw new IllegalArgumentException("Properties' length not equals GroupIndexes' length! [" + props + "], ["
			        + idxes + "]");
		}
		for (int i = 0; i < len; i++) {
			indexes[i] = Integer.parseInt(ss[i].trim());
		}
	}
	
	/**
	 * set bean properties with values fetch from string source
	 * @param bean object to be set value
	 * @param src source string
	 * @throws IllegalArgumentException if pattern not match source string
	 * @throws RuntimeException if set bean value error.
	 */
	public void wrap(Object bean, String src) {
		Matcher m = pattern.matcher(src);
		if (m.find()) {
			String cls = bean.getClass().getSimpleName();
			String prop = null, value = null;
			try {
				for (int idx = 0; idx < properties.length; idx++) {
					prop = properties[idx];
					value = m.group(indexes[idx]);
					if (log.isDebugEnabled()) {
						log.debug("set: " + cls + "." + prop + " = " + value);
					}
					BeanUtils.setProperty(bean, prop, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("set property wrong: " + cls + "." + prop + " = " + value);
			}
		} else {
			log.error("Pattern:[" + pattern.pattern() + "] not match [" + src + "]");
			throw new IllegalArgumentException("Pattern:[" + pattern.pattern() + "] not match [" + src + "]");
		}
	}
}
