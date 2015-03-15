package com.hysd.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.Eqmonitorinfo;
import com.hysd.domain.page.Page;
import com.hysd.service.EqmonitorinfoService;

@Controller
public class EqmonitorinfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(EqmonitorinfoAction.class);
	private Page<Eqmonitorinfo> page = new Page<Eqmonitorinfo>();
	private Map<String, Object> param;
	private String message;
	private Eqmonitorinfo eqmonitorinfo;
	private Long id;
	@Resource
	private EqmonitorinfoService eqmonitorinfoService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Eqmonitorinfo getEqmonitorinfo() {
		return eqmonitorinfo;
	}

	public void setEqmonitorinfo(Eqmonitorinfo eqmonitorinfo) {
		this.eqmonitorinfo = eqmonitorinfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Eqmonitorinfo> getPage() {
		return page;
	}

	public void setPage(Page<Eqmonitorinfo> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String save() {
		log.debug("START: EqmonitorinfoAction-save()");
		eqmonitorinfoService.save(eqmonitorinfo);
		message = "ok";
		log.debug("END  : EqmonitorinfoAction-save()");
		return "save";
	}

	public String edit() {
		log.debug("START: EqmonitorinfoAction-edit()");
		eqmonitorinfoService.save(eqmonitorinfo);
		message = "ok";
		log.debug("END  : EqmonitorinfoAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: EqmonitorinfoAction-load()");
		eqmonitorinfo = eqmonitorinfoService.findById(id);
		log.debug("END  : EqmonitorinfoAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: EqmonitorinfoAction-list()");
		if(param==null){
			param=new HashMap<String, Object>();
		}
		// 获取页面的参数
		page = eqmonitorinfoService.list(page, param);
		log.debug("END  : EqmonitorinfoAction-list()");
		return "list";
	}

}
