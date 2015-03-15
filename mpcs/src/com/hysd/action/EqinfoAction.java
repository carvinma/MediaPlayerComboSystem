package com.hysd.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.Eqinfo;
import com.hysd.domain.page.Page;
import com.hysd.service.EqinfoService;

@Controller
public class EqinfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(EqinfoAction.class);
	private Page<Eqinfo> page = new Page<Eqinfo>();
	private Map<String, Object> param;
	private String message;
	private Eqinfo eqinfo;
	private Long id;
	@Resource
	private EqinfoService eqinfoService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Eqinfo getEqinfo() {
		return eqinfo;
	}

	public void setEqinfo(Eqinfo eqinfo) {
		this.eqinfo = eqinfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Eqinfo> getPage() {
		return page;
	}

	public void setPage(Page<Eqinfo> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String save() {
		log.debug("START: EqinfoAction-save()");
		eqinfoService.save(eqinfo);
		message = "ok";
		log.debug("END  : EqinfoAction-save()");
		return "save";
	}

	public String edit() {
		log.debug("START: EqinfoAction-edit()");
		eqinfoService.save(eqinfo);
		message = "ok";
		log.debug("END  : EqinfoAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: EqinfoAction-load()");
		eqinfo = eqinfoService.findById(id);
		log.debug("END  : EqinfoAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: EqinfoAction-list()");
		if(param==null){
			param=new HashMap<String, Object>();
		}
		// 获取页面的参数
		page = eqinfoService.list(page, param);
		log.debug("END  : EqinfoAction-list()");
		return "list";
	}

}
