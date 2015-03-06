package com.hysd.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.Media;
import com.hysd.domain.page.Page;
import com.hysd.service.MediaService;

@Controller
public class MediaAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MediaAction.class);
	private Page<Media> page = new Page<Media>();
	private Map<String, Object> param;
	private String message;
	private Media media;
	private Long id;
	@Resource
	private MediaService mediaService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Media> getPage() {
		return page;
	}

	public void setPage(Page<Media> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String save() {
		log.debug("START: MediaAction-save()");
		mediaService.save(media);
		message = "ok";
		log.debug("END  : MediaAction-save()");
		return "save";
	}

	public String edit() {
		log.debug("START: MediaAction-edit()");
		mediaService.save(media);
		message = "ok";
		log.debug("END  : MediaAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: MediaAction-load()");
		media = mediaService.findById(id);
		log.debug("END  : MediaAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: MediaAction-list()");
		// 获取页面的参数
		page = mediaService.list(page, param);
		log.debug("END  : MediaAction-list()");
		return "list";
	}

}
