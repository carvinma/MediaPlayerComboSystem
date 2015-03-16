package com.hysd.service;

import java.util.Map;

import com.hysd.domain.Eqmonitorinfo;
import com.hysd.domain.page.Page;

public interface EqmonitorinfoService {
	public void save(Eqmonitorinfo entity);

	public void update(Eqmonitorinfo entity);

	public Eqmonitorinfo findById(Long id);

	public void delete(Eqmonitorinfo entity);

	Page<Eqmonitorinfo> list(Page<Eqmonitorinfo> page, Map<String, String> map);
	 
}
