package com.tcd.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.tcd.common.cache.CacheUtils;
import com.tcd.common.orm.PropertyFilter;
import com.tcd.dao.util.HibernateUtils;


/**
 * 通过主键转名称TAG
 * @author 郑卫东
 *
 */
public class Key2NameTag extends TagSupport {
	
	private static final long serialVersionUID = 7578521538525420781L;
	private String type;
	private String keyName;
	private Object keyValue;
	private String name;
	private String code;
	private String keyType;

	public int doEndTag() throws JspException {
		
		JspWriter out = this.pageContext.getOut();
		try {
			if("datainfo".equals(type)){
				if(code!=null){
					out.println(CacheUtils.getValueByCode(code));
				}			
			}else {
				
				if(keyType!=null && keyValue!=null){			
					if((PropertyFilter.PropertyType.L+"").equals(keyType)){
						keyValue=Long.valueOf(keyValue.toString());
					}else if((PropertyFilter.PropertyType.I+"").equals(keyType)){
						keyValue=Integer.valueOf(keyValue.toString());
					}
				}
				Object value=HibernateUtils.getAtrValueByKey(type,keyName, keyValue, name);
				if(value==null){
					value="";
				}
				out.print(value);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		}
		return EVAL_PAGE;

	}

	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;		
	}
	
	

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Object keyValue) {
		this.keyValue = keyValue;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	
}