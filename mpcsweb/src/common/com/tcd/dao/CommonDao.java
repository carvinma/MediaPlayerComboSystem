package com.tcd.dao;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.tcd.common.orm.hibernate.HibernateDao;
import com.tcd.entity.BaseEntity;

/**
 * 通用DAO
 * @author 郑卫东
 *
 */
@Component
public class CommonDao extends HibernateDao<BaseEntity, Long>{

	/**
	 * 通过主键获得某属性值
	 * @param entityClass
	 * @param keyName
	 * @param keyValue
	 * @param arrName
	 * @return
	 */
	public Object getAtrValueByKey(Class<BaseEntity> entityClass,String keyName,Object keyValue,String arrName){
		
		Object entity=getByKey(entityClass,keyName,keyValue);
		
		if(entity==null){
			 return "";
		}
		
		try {
			return PropertyUtils.getProperty(entity, arrName);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 通过主键获得对象
	 * @param entityClass
	 * @param keyName
	 * @param keyValue
	 * @return
	 */
	public Object getByKey(Class entityClass,String keyName,Object keyValue){
		Criteria criteria = getSession().createCriteria(entityClass);
		Criterion criterion = Restrictions.eq(keyName, keyValue);
		criteria.add(criterion);
		List list=criteria.list();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
