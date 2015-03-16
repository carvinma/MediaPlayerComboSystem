package com.hysd.service;

import java.util.List;
import java.util.Map;

import com.hysd.domain.Category;
import com.hysd.domain.page.Page;

public interface CategoryService {

	public void save(Category entity);

	public void update(Category entity);

	public Category findById(Long id);

	public void delete(Category entity);

	Page<Category> list(Page<Category> page, Map<String, String> map);

	/** 判断是否已存在code */
	public boolean hasCode(String code, Long id);

	public List<Category> findByParentCode(String code);

	public List<Category> findByCode(String code);
}
