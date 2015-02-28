package com.tcd.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tcd.common.cache.Constant;
import com.tcd.user.entity.Functions;
import com.tcd.user.entity.User;

/**
 * Struts2中典型CRUD Action的抽象基类.
 * 
 * 主要定义了对Preparable,ModelDriven接口的使用,以及CRUD函数和返回值的命名.
 * 
 * @param <T>
 *            CRUDAction所管理的对象类型.
 * 
 * @author zhengwd
 */
public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	private static final long serialVersionUID = -1653204626115064950L;

	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名. */
	public static final String RELOAD = "reload";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	@SkipValidation
	public String execute() throws Exception {
		return list();
	}

	// -- CRUD Action函数 --//
	/**
	 * Action函数,显示Entity列表界面. 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action函数,显示新增或修改Entity界面. 建议return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action函数,新增或修改Entity. 建议return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity. 建议return RELOAD.
	 */
	public abstract String delete() throws Exception;

	// -- Preparable函数 --//
	/**
	 * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 定义在input()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * 定义在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * 定义在delete()前执行二次绑定.
	 */
	public void prepareDelete() throws Exception {
		prepareModel();
	}

	/**
	 * 得到当前登录用户ID
	 * 
	 * @return
	 */
	public Long getLoginUserId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return (Long) request.getSession().getAttribute("SYS_SESSION_USERID");

	}

	/**
	 * 得到当前登录用户
	 * 
	 * @return
	 */
	public User getLoginUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("SYS_SESSION_USER");
		return user;

	}

	/**
	 * 等到当前登录用户企业ID
	 * 
	 * @return
	 */
	public Long getCompanyId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return (Long) request.getSession().getAttribute("SYS_SESSION_COMID");
	}

	/** 判断是否有权限 */
	public Long getAuditPermissions() {
		Long purview = Constant.NO_APPROVAL;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Functions> list = (List<Functions>) request.getSession().getAttribute("userpurview");
		if (list == null)
			return purview;

		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).getId() == Constant.LEADER_APPROVAL) {
				purview = Constant.LEADER_APPROVAL;
				break;
			}
			if (list.get(i).getId() == Constant.ROOM_APPROVAL) {
				purview = Constant.ROOM_APPROVAL;
				break;
			}
			if (list.get(i).getId() == Constant.LEADERSHIP_APPROVAL) {
				purview = Constant.LEADERSHIP_APPROVAL;
				break;
			}
		}
		return purview;
	}

	/** 判断是否有权限 */
	@SuppressWarnings("unchecked")
	public boolean getPermissions(Long p) {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Functions> list = (List<Functions>) request.getSession().getAttribute("userpurview");
		if (list == null)
			return false;

		for (int i = list.size() - 1; i >= 0; i--) {
			// System.out.println(list.get(i).getId());
			if (list.get(i).getId().equals(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 等同于prepare()的内部函数,供prepardMethodName()函数调用.
	 */
	protected abstract void prepareModel() throws Exception;

	/**
	 * 对象转换
	 * 
	 * @param target
	 * @param o
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void convert(Object target, Object o) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtils.copyProperties(target, o);
	}

}
