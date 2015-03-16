package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Category;
import com.hysd.domain.page.Page;
import com.hysd.service.CategoryService;

@Transactional
// 启用事务机制
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private BaseDAO<Category> dao;

	@Override
	public void save(Category entity) {
		if (entity.getSuperId() == null) {
			entity.setSuperId(0l);
		}
		dao.save(entity);
	}

	@Override
	public void update(Category entity) {
		if (entity.getSuperId() == null) {
			entity.setSuperId(0l);
		}
		dao.update(entity);

	}

	@Override
	public Category findById(Long id) {
		return dao.get(Category.class, id);
	}

	@Override
	public void delete(Category Category) {
		dao.delete(Category);

	}

	/**
	 * 分页查询
	 */
	public Page<Category> list(Page<Category> page, Map<String, Object> params) {
		if (page == null) {
			page = new Page<Category>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");

			if (params.get("categoryName") != null) {
				whereHql.append(" categoryName like '%?%'");
				paramlist.add(params.get("categoryName"));
			}
			if (params.get("superId") != null) {
				whereHql.append(" superId = '?'");
				paramlist.add(params.get("superId"));
			}
		}

		// 获取总数据
		String counthql = " select count(id) from Category " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Category " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

	@Override
	public boolean hasCode(String code, Long id) {
		String hql = "select count(id) from Category where code=?";
		List<Object> list = new ArrayList<Object>();
		list.add(code);
		if (id != null && id != 0) {
			hql = hql + " and id != ?";
			list.add(id);
		}
		long count = dao.count(hql, list);
		if (count == 0)
			return false;
		return true;
	}

	@Override
	public List<Category> findByParentCode(String code) {
		return dao.find("from Category a where superId=(select id from Category b where b.categoryCode=?)",
				new Object[] { code });
	}

	public List<Category> findByCode(String code) {
		return dao.find("from Category a where categoryCode=?", new Object[] { code });
	}

}
