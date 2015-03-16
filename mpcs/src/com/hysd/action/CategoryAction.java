package com.hysd.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.Category;
import com.hysd.domain.page.Page;
import com.hysd.service.CategoryService;
import com.hysd.util.Struts2Utils;

@Controller
public class CategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CategoryAction.class);
	private Page<Category> page = new Page<Category>();
	private Map<String, Object> param;
	private String message;
	private Category category;
	private Long id;
	@Resource
	private CategoryService categoryService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Category> getPage() {
		return page;
	}

	public void setPage(Page<Category> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String checkCode() {
		HttpServletRequest request = getServletRequest();
		String code = request.getParameter("categoryCode");
		Long id = Long.valueOf(request.getParameter("id"));

		if (categoryService.hasCode(code, id)) {// 存在code返回false
			Struts2Utils.renderText("false");
		} else {
			Struts2Utils.renderText("true");
		}
		return null;
	}

	public String save() {
		log.debug("START: CategoryAction-save()");
		categoryService.save(category);
		message = "ok";
		log.debug("END  : CategoryAction-save()");
		return "tolist";
	}

	public String edit() {
		log.debug("START: CategoryAction-edit()");
		categoryService.save(category);
		message = "ok";
		log.debug("END  : CategoryAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: CategoryAction-load()");
		category = categoryService.findById(id);
		log.debug("END  : CategoryAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: CategoryAction-list()");
		if(StringUtils.isEmpty((String) param.get("superId"))){
			param.put("superId", 0);
		}
		// 获取页面的参数
		page = categoryService.list(page, param);
		log.debug("END  : CategoryAction-list()");
		return "list";
	}

}
