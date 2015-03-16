package com.hysd.service;

import java.util.Map;

import com.hysd.domain.Media;
import com.hysd.domain.page.Page;

public interface MediaService {

	public void save(Media entity);

	public void update(Media entity);

	public Media findById(Long id);

	public void delete(Media entity);

	Page<Media> list(Page<Media> page, Map<String, String> map);

}
