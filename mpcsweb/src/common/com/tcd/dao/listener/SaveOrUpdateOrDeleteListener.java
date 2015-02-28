package com.tcd.dao.listener;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.type.Type;

import com.tcd.applog.annotation.AppLog;
import com.tcd.applog.entity.ApplogEntity;
import com.tcd.applog.entity.ApplogProp;
import com.tcd.applog.format.DefaultFormat;
import com.tcd.common.cache.CacheUtils;
import com.tcd.entity.SimpleEntity;

/**
 * hibernate 监听器
 * @author 郑卫东
 *
 */
public class SaveOrUpdateOrDeleteListener implements PostInsertEventListener,
		PostUpdateEventListener, PostDeleteEventListener {
	private static final long serialVersionUID = 1L;

	static Logger log = LogManager
			.getLogger(SaveOrUpdateOrDeleteListener.class);

	/**
	 * 监听删除
	 */
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		log.debug("...................");
		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		if (entityAnno == null || entry.getLogKey() == null) {
			log.debug(entityClass + " no applog,entityAnno='" + entityAnno
					+ "',logKey='" + entry.getLogKey() + "'");
			return;
		}

		Session session = event.getSession().getSessionFactory().openSession();

		Transaction ts = session.beginTransaction();
		ts.begin();
		try {
			deleteLog(event, session);
			ts.commit();
		} catch (Exception e) {
			log.debug("日志提交错误", e);
			ts.rollback();
		}
		try{
			session.close();
		}catch(Exception e){
			
		}

	}

	/**
	 * 新增日志
	 * 
	 * @param event
	 * @param session
	 */
	public void deleteLog(PostDeleteEvent event, Session session) {
		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		ApplogEntity applogEntity = saveLogEntity(session, entry, entityAnno,
				CacheUtils.getValueByCode("SYS_APPLOG_TYPE_DELETE"));
		entry.setLogKey(null);

		String idName = event.getPersister().getIdentifierPropertyName();
		Object idValue = getPropertyValue(entry, idName);

		AppLog keyAnno = getAnnotation(entry, idName, AppLog.class);
		if (keyAnno != null) {
			saveLogProp(session, applogEntity.getId(), idName, event
					.getPersister().getIdentifierType(), keyAnno, 
					idValue, null,CacheUtils.getValueByCode("SYS_BOOLEAN_YES"));
		}

		String property;
		for (int i = 0; i < event.getDeletedState().length; i++) {
			property = event.getPersister().getPropertyNames()[i];

			AppLog propAnno = getAnnotation(entry, property, AppLog.class);
			if (propAnno != null) {
				saveLogProp(session, applogEntity.getId(), property, event
						.getPersister().getPropertyTypes()[i], propAnno, event.getDeletedState()[i],
						null, CacheUtils
								.getValueByCode("SYS_BOOLEAN_NO"));
			}
		}
	}

	/**
	 * 更新监听
	 */
	@Override
	public void onPostUpdate(PostUpdateEvent event) {

		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		if (entityAnno == null || entry.getLogKey() == null) {
			log.debug(entityClass + " no applog,entityAnno='" + entityAnno
					+ "',logKey='" + entry.getLogKey() + "'");
			return;
		}

		Session session = event.getSession().getSessionFactory().openSession();

		Transaction ts = session.beginTransaction();
		ts.begin();
		try {
			editLog(event, session);
			ts.commit();
		} catch (Exception e) {
			log.debug("日志提交错误", e);
			ts.rollback();
		}
		try{
			session.close();
		}catch(Exception e){
			
		}
		
		

	}

	/**
	 * 修改日志
	 * 
	 * @param event
	 * @param session
	 */
	public void editLog(PostUpdateEvent event, Session session) {
		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		ApplogEntity applogEntity = saveLogEntity(session, entry, entityAnno,
				CacheUtils.getValueByCode("SYS_APPLOG_TYPE_UPDATE"));
		entry.setLogKey(null);

		String idName = event.getPersister().getIdentifierPropertyName();
		Object idValue = getPropertyValue(entry, idName);

		AppLog keyAnno = getAnnotation(entry, idName, AppLog.class);
		if (keyAnno != null) {
			saveLogProp(session, applogEntity.getId(), idName, event
					.getPersister().getIdentifierType(), keyAnno, idValue,
					idValue, CacheUtils.getValueByCode("SYS_BOOLEAN_YES"));
		}

		String property;
		for (int i = 0; i < event.getState().length; i++) {
			property = event.getPersister().getPropertyNames()[i];

			AppLog propAnno = getAnnotation(entry, property, AppLog.class);
			if (propAnno != null) {		
				saveLogProp(session, applogEntity.getId(), property, event
						.getPersister().getPropertyTypes()[i], propAnno,						
						event.getOldState()==null?null:event.getOldState()[i], event.getState()[i], CacheUtils
						.getValueByCode("SYS_BOOLEAN_NO"));
			}
		}
	}

	/**
	 * 新增监听
	 */
	@Override
	public void onPostInsert(PostInsertEvent event) {
		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		if (entityAnno == null || entry.getLogKey() == null) {
			log.debug(entityClass + " no applog,entityAnno='" + entityAnno
					+ "',logKey='" + entry.getLogKey() + "'");
			return;
		}

		log.debug(entityClass + " has applog,entityAnno='" + entityAnno
				+ "',logKey='" + entry.getLogKey() + "'");
		// log.debug("entityClass.toString()=" + entityClass.toString());

		
		Session session = event.getSession().getSessionFactory().openSession();

		Transaction ts = session.beginTransaction();
		ts.begin();
		try {
			insertLog(event, session);
			ts.commit();
		} catch (Exception e) {
			log.debug("日志提交错误", e);
			ts.rollback();
		}
		try{
			session.close();
		}catch(Exception e){
			
		}
	}

	/**
	 * 新增日志
	 * 
	 * @param event
	 * @param session
	 */
	public void insertLog(PostInsertEvent event, Session session) {
		Class<?> entityClass = event.getEntity().getClass();
		AppLog entityAnno = entityClass.getAnnotation(AppLog.class);
		SimpleEntity entry = (SimpleEntity) event.getEntity();

		ApplogEntity applogEntity = saveLogEntity(session, entry, entityAnno,
				CacheUtils.getValueByCode("SYS_APPLOG_TYPE_INSERT"));
		entry.setLogKey(null);

		String idName = event.getPersister().getIdentifierPropertyName();
		Object idValue = getPropertyValue(entry, idName);

		AppLog keyAnno = getAnnotation(entry, idName, AppLog.class);
		if (keyAnno != null) {
			saveLogProp(session, applogEntity.getId(), idName, event
					.getPersister().getIdentifierType(), keyAnno, null,
					idValue, CacheUtils.getValueByCode("SYS_BOOLEAN_YES"));
		}

		String property;
		for (int i = 0; i < event.getState().length; i++) {
			property = event.getPersister().getPropertyNames()[i];

			AppLog propAnno = getAnnotation(entry, property, AppLog.class);
			if (propAnno != null) {
				saveLogProp(session, applogEntity.getId(), property, event
						.getPersister().getPropertyTypes()[i], propAnno, null,
						event.getState()[i], CacheUtils
								.getValueByCode("SYS_BOOLEAN_NO"));
			}
		}
	}

	/**
	 * 得到指定bean的属性值
	 * @param bean
	 * @param propertyName
	 * @return
	 */
	public static String getPropertyValue(Object bean, String propertyName) {
		try {
			Object obj=PropertyUtils.getProperty(bean, propertyName);
			if(obj==null){
				return null;
			}else{
				return obj.toString();
			}
		} catch (Exception e) {
			log.debug(e);
			return null;
		}
	}

	public static <X extends Annotation> X getAnnotation(Object entry,
			String property, Class<X> x) {
		PropertyDescriptor p = null;
		try {
			p = PropertyUtils.getPropertyDescriptor(entry, property);
		} catch (Exception e) {
			log.debug(e);
			return null;
		}
		return p.getReadMethod().getAnnotation(x);
	}

	/**
	 * 得到值
	 * @param property
	 * @param propertyType
	 * @param propAnno
	 * @param value
	 * @param session
	 * @return
	 */
	public String getValue(String property, Type propertyType, AppLog propAnno,
			Object value,Session session) {
		String parentCode = propAnno.dataParentCode();
		Class<DefaultFormat> formatClass=propAnno.format();		
		try {
			DefaultFormat faultFormat=formatClass.newInstance();
			value=faultFormat.format(value);
		} catch (Exception e) {
			log.error("", e);
		}
		
		if(value==null){
			return  null;
		}else if (parentCode != null && parentCode.length() > 0) {
			String sValue = value.toString();
			return CacheUtils.getNameByParentCodeOrValue(parentCode, sValue);
		} else if(propAnno.showpropertie().length()>0){
			
			if(!propAnno.collectionClass().equals(Object.class)){
				Collection<SimpleEntity> c=(Collection<SimpleEntity>)value;
				String cvalue="";
				for(SimpleEntity cEntity:c){
					Object centity=session.get(propAnno.tableclass(), cEntity.getId());
					cvalue=cvalue+","+getPropertyValue(centity,propAnno.showpropertie());
				}
				return cvalue;
			}
			//TODO value类型和空值
			Object entity=session.get(propAnno.tableclass(), Long.parseLong(value.toString()));
			return getPropertyValue(entity,propAnno.showpropertie());
		}else {
			return value.toString();
		}
	}

	/**
	 * 保存日志实体信息
	 * @param session
	 * @param entry
	 * @param entityAnno
	 * @param opType
	 * @return
	 */
	public ApplogEntity saveLogEntity(Session session, SimpleEntity entry,
			AppLog entityAnno, String opType) {
		ApplogEntity applogEntity = new ApplogEntity();
		applogEntity.setAppLogId(entry.getLogKey());
		applogEntity.setEntityClass(entry.getClass().toString());
		applogEntity.setEntityName(entityAnno.name());
		applogEntity.setOpType(opType);
		applogEntity.setEntityIndex(entry.nextIntex());
		session.save(applogEntity);
		session.flush();
		return applogEntity;
	}

	/**
	 * 保存日志属性信息
	 * @param session
	 * @param entityId
	 * @param property
	 * @param propertyType
	 * @param propAnno
	 * @param oldValue
	 * @param value
	 * @param isKey
	 */
	public void saveLogProp(Session session, Long entityId, String property,
			Type propertyType, AppLog propAnno, Object oldValue, Object value,
			String isKey) {
		
		ApplogProp applogProp = new ApplogProp();
		applogProp.setAppLogEntityId(entityId);
		applogProp.setPropLable(propAnno.name());
		applogProp.setPropName(property);
		
		if (oldValue != null) {
			applogProp.setPropOldValue(getValue(property, propertyType,
					propAnno, oldValue,session));
		}
		if (value != null) {
			applogProp.setPropValue(getValue(property, propertyType, propAnno,
					value,session));
		}
		applogProp.setIsKey(isKey);
		if (applogProp.getPropOldValue() == null
				&& applogProp.getPropValue() == null) {
			applogProp.setIsUpdate(CacheUtils.getValueByCode("SYS_BOOLEAN_NO"));
		} else if (applogProp.getPropOldValue() == null
				&& applogProp.getPropValue() != null) {
			applogProp
					.setIsUpdate(CacheUtils.getValueByCode("SYS_BOOLEAN_YES"));
		} else if (applogProp.getPropOldValue().equals(
				applogProp.getPropValue())) {
			applogProp.setIsUpdate(CacheUtils.getValueByCode("SYS_BOOLEAN_NO"));
		} else {
			applogProp
					.setIsUpdate(CacheUtils.getValueByCode("SYS_BOOLEAN_YES"));
		}
			
		session.save(applogProp);
		session.flush();
	}
	


}
