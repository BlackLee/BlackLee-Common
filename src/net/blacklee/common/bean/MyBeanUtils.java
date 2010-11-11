package net.blacklee.common.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * @author LiHuiRong
 * @created 2010-11-02 18:07:41
 */
public class MyBeanUtils {
	private static final Logger log = Logger.getLogger(MyBeanUtils.class);
	/**
	 * Set values array to object, the param properties can has less elements then values, buf if has more, I'll throw an IllegalArgumentException
	 * @param bean your pojo object
	 * @param properties property names of your pojo object
	 * @param values the values to be setted
	 */
	public static void setProps(Object bean, String[] properties, Object[] values) {
		if (values.length < properties.length) throw new IllegalArgumentException("Your param properties has more elements then values.");
		String prop = null;
		Object value = null;
		try {
	        for (int i = 0; i < properties.length; i++) {
				prop = properties[i];
				value = values[i];
		        BeanUtils.setProperty(bean, prop, value);
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        	String msg = "set property wrong: " + bean.getClass() + "." + prop + " = " + value;
        	log.error(msg, e);
			throw new RuntimeException(msg);
        }
	}
}
