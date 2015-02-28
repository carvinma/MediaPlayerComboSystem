package com.tcd.common.orm;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hibernate，通过关系条件进行查询，别名信息
 * @author 郑卫东
 *
 */
public class AliasParam {
	protected static Logger logger = LoggerFactory.getLogger(PropertyFilter.class);
	
	/**
	 * 查询属性
	 */
	private String property ;
	/**
	 * 别名
	 */
	private String alias ;	
	/**
	 * 查询sql连接类型
	 */
	private int joinType;
	/**
	 * criterion对象
	 */
	private Criterion criterion;
	
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}



	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	public int getJoinType() {
		return joinType;
	}



	public void setJoinType(int joinType) {
		this.joinType = joinType;
	}



	public Criterion getCriterion() {
		return criterion;
	}



	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}



	
}
