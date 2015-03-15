package com.hysd.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.Client;
import com.hysd.domain.page.Page;
import com.hysd.service.ClientService;

@Controller
public class ClientAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ClientAction.class);
	private Page<Client> page = new Page<Client>();
	private Map<String, Object> param;
	private String message;
	private Client client;
	private Long id;
	@Resource
	private ClientService clientService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Client> getPage() {
		return page;
	}

	public void setPage(Page<Client> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String save() {
		log.debug("START: ClientAction-save()");
		clientService.save(client);
		message = "ok";
		log.debug("END  : ClientAction-save()");
		return "save";
	}

	public String edit() {
		log.debug("START: ClientAction-edit()");
		clientService.save(client);
		message = "ok";
		log.debug("END  : ClientAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: ClientAction-load()");
		client = clientService.findById(id);
		log.debug("END  : ClientAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: ClientAction-list()");
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		if (StringUtils.isEmpty((String) param.get("superId"))) {
			param.put("superId", 0);
		}
		// 获取页面的参数
		page = clientService.list(page, param);
		log.debug("END  : ClientAction-list()");
		return "list";
	}

}
