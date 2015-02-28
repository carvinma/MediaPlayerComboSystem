package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tcd.dao.CommonDao;
import com.tcd.entity.BaseEntity;

/**
 * 公共service类
 * @author 郑卫东
 *
 */
@Component
@Transactional
public class CommonManager {
	private CommonDao commonDao;

	@Autowired
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public Object getAtrValueByKey(Class entityClass,String keyName,Object keyValue,String arrName){
		return commonDao.getAtrValueByKey(entityClass, keyName, keyValue, arrName);
	}
	
	
	public Object getByKey(Class<BaseEntity> entityClass,String keyName,Object keyValue){
		return commonDao.getByKey(entityClass, keyName, keyValue);
	}
}
