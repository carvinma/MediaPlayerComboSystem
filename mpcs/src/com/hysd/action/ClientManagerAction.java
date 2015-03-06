package com.hysd.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.hysd.domain.ParamPO;
import com.hysd.domain.ResultPO;
import com.hysd.service.imp.ClientManager;
import com.opensymphony.xwork2.ActionSupport;

/** 接受客户端发来的请求 */
public class ClientManagerAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(ClientManagerAction.class);
	private static final long serialVersionUID = 1L;
	private String data;
	@Resource
	private ClientManager clientManager;
	private ResultPO resultPO;
	private ParamPO paramPO;

	public String execute() throws Exception {
		logger.debug("ClientManager-receiveContent=" + data);

		ObjectMapper mapper = new ObjectMapper();
		paramPO = mapper.readValue(data, ParamPO.class);
		resultPO = new ResultPO();
		// TODO 处理信息
		clientManager.actions(paramPO, resultPO);

		return "json";
	}

	public void setData(String data) {
		this.data = data;
	}

	public ResultPO getResultPO() {
		return resultPO;
	}

}
