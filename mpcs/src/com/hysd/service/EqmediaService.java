package com.hysd.service;

import java.util.List;
import java.util.Map;

import com.hysd.domain.Eqmedia;
import com.hysd.domain.page.Page;

public interface EqmediaService {

	public void save(Eqmedia entity);

	public void update(Eqmedia entity);

	public Eqmedia findById(Long id);

	public void delete(Eqmedia entity);

	Page<Eqmedia> list(Page<Eqmedia> page, Map<String, Object> map);

	public List<Eqmedia> findMedias(Long eqId);

}
