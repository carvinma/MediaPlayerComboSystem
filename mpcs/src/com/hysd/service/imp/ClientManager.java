package com.hysd.service.imp;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hysd.domain.Client;
import com.hysd.domain.ClientPO;
import com.hysd.domain.Eqinfo;
import com.hysd.domain.EqinfoPO;
import com.hysd.domain.Eqmonitorinfo;
import com.hysd.domain.EqmonitorinfoPO;
import com.hysd.domain.ParamPO;
import com.hysd.domain.ResultPO;
import com.hysd.service.ClientService;
import com.hysd.service.EqinfoService;
import com.hysd.service.EqmonitorinfoService;
import com.hysd.util.DateUtils;

@Service("clientManager")
@Transactional
public class ClientManager {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ClientService clientService;
	@Resource
	private EqinfoService eqinfoService;
	@Resource
	private EqmonitorinfoService eqmonitorinfoService;

	public void actions(ParamPO paramPO, ResultPO resultPO) throws Exception {
		this.action(paramPO.getAct(), paramPO, resultPO);
	}

	private void action(String act, ParamPO paramPO, ResultPO resultPO) throws Exception {
		if (StringUtils.isEmpty(paramPO.getSn())) {
			resultPO.fail("没有上传SN号");
			return;
		}
		if ("uploadInfo".equalsIgnoreCase(act)) {
			// 任务列表
			saveClientInfo(paramPO, resultPO);
			return;
		}
	}

	private void saveClientInfo(ParamPO paramPO, ResultPO resultPO) throws IllegalAccessException,
			InvocationTargetException {
		// 保存Client的信息
		ClientPO clientPO = paramPO.getClientPO();
		Client client = new Client();
		BeanUtils.copyProperties(clientPO, client);
		client.setActiveTime(DateUtils.parseDate(clientPO.getActiveTime(), "yyyy-MM-dd HH:mm:ss"));
		client.setUpdateTime(DateUtils.parseDate(clientPO.getActiveTime(), "yyyy-MM-dd HH:mm:ss"));
		clientService.save(client);

		// 保存tbeqinfo
		EqinfoPO eqinfoPO = paramPO.getEqinfoPO();
		Eqinfo eqinfo = new Eqinfo();
		BeanUtils.copyProperties(eqinfoPO, eqinfo);
		eqinfoService.save(eqinfo);

		// 保存tbeqmonitorinfo
		EqmonitorinfoPO eqmonitorinfoPO = paramPO.getEqmonitorinfoPO();
		Eqmonitorinfo eqmonitorinfo = new Eqmonitorinfo();
		BeanUtils.copyProperties(eqmonitorinfoPO, eqmonitorinfo);
		eqmonitorinfoService.save(eqmonitorinfo);
		// 返回OK
		resultPO.ok();
	}
}
