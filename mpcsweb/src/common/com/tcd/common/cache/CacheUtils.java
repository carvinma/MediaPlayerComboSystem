package com.tcd.common.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcd.common.utils.spring.SpringContextHolder;
import com.tcd.datainfo.entity.DataInfo;
import com.tcd.datainfo.service.DataInfoManager;

/**
 * 数据缓存类
 * 
 * @author 郑卫东
 * 
 */
public class CacheUtils {

	private static long time = 0;

	private static Map<String, DataInfo> cacheMap = new HashMap<String, DataInfo>();
	private static DataInfoManager datainfoManager;
	public static Map<String, String> loginnames = new HashMap<String, String>();

	/**
	 * 初始化缓存
	 */
	public static void init() {
		long time1 = new Date().getTime();
		if ((time1 - time) < 60000) {
			// if((time1-time) < 1200){
			return;
		}
		logger.debug("..........init start...........");
		cacheMap.clear();
		datainfoManager = SpringContextHolder.getBean(DataInfoManager.class);
		List<DataInfo> list = datainfoManager.getAllByEnable();
		if (list == null) {
			return;
		}
		for (DataInfo b : list) {
			cacheMap.put(b.getCode(), b);
		}

		time = time1;
		logger.debug(".........init end............");
	}

	protected static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

	/**
	 * 通过代码得到值
	 * 
	 * @param code
	 * @return
	 */
	public static String getValueByCode(String code) {
		init();
		DataInfo data = cacheMap.get(code);
		if (data == null) {
			return null;
		} else {
			return data.getValue();
		}

	}

	/**
	 * 得到父节点的编码
	 * 
	 * @return
	 */
	public static String getParentCodeByCode(String code) {
		init();
		DataInfo data = cacheMap.get(code);
		return getParentDate(data).getCode();
	}

	private static DataInfo getParentDate(DataInfo data) {
		if (data.getParent() == null) {
			data.setParent(datainfoManager.get(data.getParentId()));
			cacheMap.put(data.getCode(), data);
		}
		return data.getParent();
	}

	private static List<DataInfo> getChildsDate(DataInfo data) {
		if (data.getChilds() == null) {
			data.setChilds(datainfoManager.getChildsByParentId(data.getId()));
			cacheMap.put(data.getCode(), data);
		}
		return data.getChilds();
	}

	/**
	 * 通过代码得到名称
	 * 
	 * @param code
	 * @return
	 */
	public static String getNameByCode(String code) {
		init();
		DataInfo data = cacheMap.get(code);
		if (data == null) {
			return null;
		} else {
			return data.getName();
		}
	}

	/**
	 * 通过父节点ID得到所有子节点
	 * 
	 * @param parentCode
	 * @return
	 */
	public static List<DataInfo> listByParentCode(String parentCode) {
		return getChildsDate(cacheMap.get(parentCode));
	}

	/**
	 * listByParentCode 是否加载子元素
	 * 
	 * @param parentCode
	 * @param loadChild
	 * @return
	 */
	public static List<DataInfo> listByParentCode(String parentCode, boolean loadChild) {
		init();
		DataInfo data = cacheMap.get(parentCode);
		if (data == null) {
			return new ArrayList<DataInfo>();
		}
		/*
		 * List<DataInfo> childs = getChildsDate(data); List<DataInfo> childse =
		 * new ArrayList<DataInfo>(); DataInfo b = null; if (childs != null &&
		 * childs.size() > 0) { for (DataInfo child : childs) { if
		 * ("Y".equals(child.getEnable())) { if (!loadChild) { b = new
		 * DataInfo(); b.setCode(child.getCode()); b.setName(child.getName());
		 * b.setValue(child.getValue()); b.setAllName(child.getAllName());
		 * b.setIndex(child.getIndex()); b.setLevel(child.getLevel());
		 * childse.add(b); } else { childse.add(child); }
		 * 
		 * }
		 * 
		 * } }
		 */
		return getChildsDate(data);
	}

	/**
	 * 通过父代码和值得到名称
	 * 
	 * @param parentCode
	 * @param value
	 * @return
	 */
	public static String getNameByParentCodeOrValue(String parentCode, String value) {
		init();
		List<DataInfo> list = getChildsDate(cacheMap.get(parentCode));
		if (list == null || list.size() == 0) {
			return null;
		}

		for (DataInfo b : list) {
			if (b.getValue().equals(value)) {
				return b.getName();
			}
		}
		return null;
	}

	/**
	 * 得到应用类型
	 * 
	 * @return
	 */
	public static String getAppType() {
		return CacheUtils.getValueByCode("SYS_APP_YUN");
	}

	public static void addUser(String loginname, String ip) {
		loginnames.put(loginname, ip);
	}

	public static void removeUser(String loginname) {
		loginnames.remove(loginname);
	}
}
