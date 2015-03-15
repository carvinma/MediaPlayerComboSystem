package com.hysd.service.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
import com.hysd.domain.Eqmedia;
import com.hysd.domain.Eqmonitorinfo;
import com.hysd.domain.EqmonitorinfoPO;
import com.hysd.domain.Media;
import com.hysd.domain.MediaPO;
import com.hysd.domain.ParamPO;
import com.hysd.domain.ResultPO;
import com.hysd.service.ClientService;
import com.hysd.service.EqinfoService;
import com.hysd.service.EqmediaService;
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
	@Resource
	private EqmediaService eqmediaService;

	public void actions(ParamPO paramPO, ResultPO resultPO) throws Exception {
		this.action(paramPO.getAct(), paramPO, resultPO);
	}

	private void action(String act, ParamPO paramPO, ResultPO resultPO) throws Exception {
		if (StringUtils.isEmpty(paramPO.getSn())) {
			resultPO.fail("没有上传SN号");
			return;
		}
		if ("init".equalsIgnoreCase(act)) {
			// 上传
			saveClientInfo(paramPO, resultPO);
			return;
		}

		if ("getmedia".equalsIgnoreCase(act)) {
			// 获取当前设备的多媒体信息
			getMedias(paramPO, resultPO);
		}
		if ("uploadInfo".equalsIgnoreCase(act)) {
			// 获取当前设备的多媒体信息
			uploadInfo(paramPO, resultPO);
		}
	}

	/** 上传当前状态 */
	private void uploadInfo(ParamPO paramPO, ResultPO resultPO) {

	}

	/**
	 * 获取视频信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void getMedias(ParamPO paramPO, ResultPO resultPO) throws IllegalAccessException, InvocationTargetException {
		// 获取sn
		String sn = paramPO.getSn();
		// 获取多媒体视频
		List<Eqmedia> mlist = eqmediaService.findMedias(sn);
		List<MediaPO> polist = new ArrayList<MediaPO>();
		for (Eqmedia eqmedia : mlist) {
			MediaPO po = new MediaPO();
			Media media = eqmedia.getMedia();
			BeanUtils.copyProperties(media, po);
			po.setUpdateTime(DateUtils.dateFormat(media.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			polist.add(po);
		}
		resultPO.setMlist(polist);
		resultPO.ok();
	}

	/** 初次开机上传信息 */
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
