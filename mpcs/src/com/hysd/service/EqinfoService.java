package com.hysd.service;

import java.util.Map;

import com.hysd.domain.Eqinfo;
import com.hysd.domain.page.Page;

public interface EqinfoService {
	public void save(Eqinfo entity);

	public void update(Eqinfo entity);

	public Eqinfo findById(Long id);

	public void delete(Eqinfo entity);

	Page<Eqinfo> list(Page<Eqinfo> page, Map<String, Object> map);

	public Eqinfo findBySn(String sn);
	 
}
