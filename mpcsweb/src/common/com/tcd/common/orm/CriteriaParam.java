package com.tcd.common.orm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.ResultTransformer;

/**
 * 
 * hibernate，通过关系条件进行查询
 * @author zhengwd
 *
 */
public class CriteriaParam  {
	
	/**
	 * 别名列表
	 */
	private List<AliasParam> aliases;
	
	/**
	 * fetchModes
	 */
	private List<FetchModeParam> fetchModes;
	
	/**
	 * 属性
	 */
	private Projection projection;
	
	/**
	 * 结果
	 */
	private ResultTransformer resultTransformer;
	
	
	
	
	public CriteriaParam(){
		aliases=new ArrayList<AliasParam>();
		fetchModes=new ArrayList<FetchModeParam>();
	}
	
//	/**
//	 * 设置别名
//	 * @param alias
//	 * @return
//	 */
//	public CriteriaParam setAlias(AliasParam alias){
//		aliases.add(alias);
//		return this;
//	}
	
	/**
	 * 设置属性的别名
	 * @param property 属性名称
	 * @param alias    别名
	 * @param joinType join关联类型(INNER_JOIN,LEFT_JOIN,FULL_JOIN)
	 * @param Criterion 表达式语句
	 * @return
	 */
	public CriteriaParam setAlias(String property,String alias,int joinType,Criterion criterion){
		AliasParam aliasParam=new AliasParam();
		aliasParam.setProperty(property);
		aliasParam.setAlias(alias);
		aliasParam.setJoinType(joinType);
		aliasParam.setCriterion(criterion);
		aliases.add(aliasParam);
		return this;
	}
	
	/**
	 * 设置属性的别名
	 * @param property 属性名称
	 * @param alias    别名
	 * @param Criterion 表达式语句
	 * @return
	 */
	public CriteriaParam setAlias(String property,String alias,Criterion criterion){
		AliasParam aliasParam=new AliasParam();
		aliasParam.setProperty(property);
		aliasParam.setAlias(alias);
		aliasParam.setCriterion(criterion);
		aliases.add(aliasParam);
		return this;
	}
	
	/**
	 * 设置属性的别名
	 * @param property 属性名称
	 * @param alias    别名
	 * @param joinType join关联类型(INNER_JOIN,LEFT_JOIN,FULL_JOIN)
	 * @return
	 */
	public CriteriaParam setAlias(String property,String alias,int joinType){
		return this.setAlias(property,alias,joinType,null);
	}
	
	/**
	 * 设置属性的别名
	 * @param property 属性名称
	 * @param alias    别名
	 * @return
	 */
	public CriteriaParam setAlias(String property,String alias){
		return this.setAlias(property,alias,-1,null);
	}
	
	/**
	 * 设置抓取策略
	 * @param name
	 * @param fetchMode
	 * @return
	 */
	public CriteriaParam setFetchMode(String name,FetchMode fetchMode){
		FetchModeParam fetchModeParam=new FetchModeParam();
		fetchModeParam.setName(name);
		fetchModeParam.setFetchMode(fetchMode);
		fetchModes.add(fetchModeParam);
		return this;
	}
	
	
	
	
	public List<AliasParam> getAliases() {
		return aliases;
	}

	public List<FetchModeParam> getFetchModes() {
		return fetchModes;
	}

	/**
	 * 设置 Projection
	 * @param projection
	 */
	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public Projection getProjection() {
		return projection;
	}


	public void setResultTransformer(ResultTransformer resultTransformer) {
		this.resultTransformer = resultTransformer;
	}


	public ResultTransformer getResultTransformer() {
		return resultTransformer;
	}


	public static final FetchMode DEFAULT = FetchMode.DEFAULT;
	public static final FetchMode JOIN = FetchMode.JOIN;
	public static final FetchMode SELECT = FetchMode.SELECT;
	@SuppressWarnings("deprecation")
	public static final FetchMode LAZY = FetchMode.LAZY;
	@SuppressWarnings("deprecation")
	public static final FetchMode EAGER = FetchMode.EAGER;
	
	final public static int LEFT_JOIN=Criteria.LEFT_JOIN;
	final public static int FULL_JOIN=Criteria.FULL_JOIN;
	final public static int INNER_JOIN=Criteria.INNER_JOIN;
	
	
	final public static ResultTransformer ALIAS_TO_ENTITY_MAP=Criteria.ALIAS_TO_ENTITY_MAP;
	final public static ResultTransformer DISTINCT_ROOT_ENTITY=Criteria.DISTINCT_ROOT_ENTITY;
	final public static ResultTransformer PROJECTION=Criteria.PROJECTION;
	final public static ResultTransformer ROOT_ENTITY=Criteria.ROOT_ENTITY;

}

