package com.hysd.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeanUtilsEx extends BeanUtils {
	static {
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		ConvertUtils.register(new DateConvert(), java.sql.Date.class);
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void populate(Object bean, Map properties) {
		try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

class NumberConvert implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class arg0, Object arg1) {
		if (arg1 == null)
			return null;
		if (arg1 instanceof Integer)
			return (Integer) arg1;
		if (arg1 instanceof Long)
			return (Long) arg1;
		return null;

	}

}