package com.tcd.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.tcd.common.utils.spring.SpringContextHolder;
import com.tcd.common.utils.web.struts2.Struts2Utils;
import com.tcd.equip.entity.EquipTree;
import com.tcd.equip.service.EquipTreeManager;
import com.tcd.user.entity.Functions;
import com.tcd.user.entity.User;
import com.tcd.user.service.FunctionsManager;

/**
 * 菜单
 * 
 */
public class MenuUtils {
	private static JSONArray etrees;
	private static JSONArray etreeall;

	private static Map<String, List<Functions>> menus = new HashMap<String, List<Functions>>();
	private static User user;
	private static String roleType;

	public static void init() {
		user = (User) Struts2Utils.getSessionAttribute("SYS_SESSION_USER");
		roleType = (String) Struts2Utils.getSessionAttribute("SYS_SESSION_ROLETYPE");
		// 顶部菜单栏
		FunctionsManager functionsManager = SpringContextHolder.getBean(FunctionsManager.class);

		List<Functions> funlist = functionsManager.findTopMenu();
		Struts2Utils.getSession().setAttribute("SYS_SESSION_TOPMUNU", funlist);
		if (user == null) {// ADMIN菜单
			// 左侧菜单栏
			List<Functions> list1 = functionsManager.findBy("parentId", 100l);
			menus.put("100", list1);
			funlist.get(0).setFunctionUrl(list1.get(0).getFunctionUrl());

			List<Functions> list2 = functionsManager.findBy("parentId", 200l);
			menus.put("200", list2);
			funlist.get(1).setFunctionUrl(list2.get(0).getFunctionUrl());
			return;
		}

		for (Functions functions : funlist) {
			List<Functions> list = functionsManager.findListByUserId(user.getId(), functions.getId());
			if (list != null && list.size() > 0) {
				menus.put(functions.getId().toString(), list);
				functions.setFunctionUrl(list.get(0).getFunctionUrl());
			}
		}

		// 设备树
		EquipTreeManager equipTreeManager = SpringContextHolder.getBean(EquipTreeManager.class);
		List<EquipTree> tlist = equipTreeManager.findBy("companyId", user.getCompanyId());
		etreeall = JSONArray.fromObject(tlist);
		etrees = etreeall;
		if (("BANZHANG".equals(roleType) || "XIANCHANG".equals(roleType)) && user.getTeamId() != null) {
			etrees = JSONArray.fromObject(equipTreeManager.findByTeam(user.getTeamId()));
		}

	}

	public static JSONArray getEtrees() {
		if (etrees == null) {
			init();
		}
		return etrees;
	}

	public static JSONArray getEtreeall() {
		if (etreeall == null) {
			init();
		}
		return etreeall;
	}

	public static Map<String, List<Functions>> getMenus() {
		if (menus.isEmpty()) {
			init();
		}
		return menus;
	}

}
