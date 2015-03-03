package com.hysd.action;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.hysd.domain.ParamPO;
import com.opensymphony.xwork2.ActionSupport;

public class ClientManagerAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(ClientManagerAction.class);
	private static final long serialVersionUID = 1L;
	private String message;
	private String data;

	public String execute() throws Exception {
		logger.debug("ClientManager-receiveContent=" + data);
		message = "fail";
		ObjectMapper mapper = new ObjectMapper();
		ParamPO paramPO = mapper.readValue(data, ParamPO.class);
		// TODO 处理信息

		message = "ok";
		return "json";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(String data) {
		this.data = data;
	}

}
