package com.zqdy.core.common.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


public class CommonDto{

	private Map values = null;

	public CommonDto() {
		super();
		values = new HashMap();
	}

	public CommonDto(Object obj) {
		init(obj);
	}

	/**
	 * 初始�hu�?

	 * 
	 * @param obj
	 */
	public Map getDTO(){
		return this.values;
	}
	public void init(Object obj) {
		try {
			values = PropertyUtils.describe(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 
	 * @param obj
	 */
	public void populate(Object obj) {
		try {
			BeanUtils.populate(obj, values);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Object getProperty(Object obj) {
		return values.get(obj);
	}

	@SuppressWarnings("unchecked")
	public void setProperty(Object key, Object value) {
		values.put(key, value);
	}

	
}
