package com.tcd.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tcd.applog.service.AppLogManager;
import com.tcd.common.orm.CriteriaParam;
import com.tcd.common.orm.Page;
import com.tcd.common.orm.PropertyFilter;
import com.tcd.common.orm.hibernate.HibernateDao;
import com.tcd.entity.BaseEntity;

/**
 * Manage 基类 封装基本方法
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <E>
 *            实体对象
 * 
 * @author zhengwd
 */

// 默认将类中的所有函数纳入事务管理.
@Transactional
@Component
public class SimpleManage<T extends HibernateDao, E> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected AppLogManager appLogManager;

	@Autowired
	public void setAppLogManager(AppLogManager appLogManager) {
		this.appLogManager = appLogManager;
	}

	protected T dao;

	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public E get(Serializable id) {
		return (E) dao.get(id);
	}

	/**
	 * 保存新增或修改的对象.
	 */
	@SuppressWarnings("unchecked")
	public void save(E entity) {
		logger.debug("save");
		dao.save(entity);
	}

	/**
	 * 获取全部对象.
	 */
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return dao.getAll();
	}

	/**
	 * 分页获取全部对象.
	 */
	@SuppressWarnings("unchecked")
	public Page<E> getAll(final Page<E> page) {
		return dao.getAll(page);
	}

	/**
	 * 获取全部对象, 支持按属性行序.
	 */
	@SuppressWarnings("unchecked")
	public List<E> getAll(String orderByProperty, boolean isAsc) {
		return dao.getAll(orderByProperty, isAsc);
	}

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	@SuppressWarnings("unchecked")
	public List<E> findBy(String propertyName, Object value) {
		return dao.findBy(propertyName, value);
	}

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	@SuppressWarnings("unchecked")
	public E findUniqueBy(String propertyName, final Object value) {
		return (E) dao.findUniqueBy(propertyName, value);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	@SuppressWarnings("unchecked")
	public Page<E> findPage(final Page<E> page, final List<PropertyFilter> filters) {
		return dao.findPage(page, filters);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	@SuppressWarnings("unchecked")
	public Page<E> findPage(final Page<E> page, final List<PropertyFilter> filters,
			final Criterion... criterions) {
		return dao.findPage(page, filters, criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	@SuppressWarnings("unchecked")
	public Page<E> findPage(final Page<E> page, final CriteriaParam criteriaParam,
			final List<PropertyFilter> filters) {
		return dao.findPage(page, criteriaParam, filters);
	}

	/**
	 * 按id删除对象.
	 */
	@SuppressWarnings("unchecked")
	public void delete(Serializable id) {
		dao.delete(id);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	@SuppressWarnings("unchecked")
	public List<E> find(final List<PropertyFilter> filters) {
		return dao.find(filters);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<E> find(final List<PropertyFilter> filters, final Criterion... criterions) {
		return dao.find(filters, criterions);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 * 
	 * @param page
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		return dao.findPage(page, criterions);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	@SuppressWarnings("unchecked")
	public List<E> find(final CriteriaParam criteriaParam, final List<PropertyFilter> filters) {
		return dao.find(criteriaParam, filters);
	}

	/**
	 * 保存操作日志
	 * 
	 * @param opName
	 * @param opNameSub
	 * @return logkey
	 */
	public Long applog(String opName, String opNameSub) {
		return appLogManager.applog(opName, opNameSub);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.(同一属性多值查询，值为字符串)
	 */
	@SuppressWarnings("unchecked")
	public List<E> find(final CriteriaParam criteriaParam, final List<PropertyFilter> filters,
			List<String> names, Map<String, String> map) {
		return dao.find(criteriaParam, filters, names, map);
	}
	
}
