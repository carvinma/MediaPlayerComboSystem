package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Eqmonitorinfo;
import com.hysd.domain.page.Page;
import com.hysd.service.EqmonitorinfoService;

@Transactional
// 启用事务机制
@Service("eqmonitorinfoService")
public class EqmonitorinfoServiceImpl implements EqmonitorinfoService {
	@Resource
	private BaseDAO<Eqmonitorinfo> dao;

	@Override
	public void save(Eqmonitorinfo entity) {
		dao.save(entity);
	}

	@Override
	public void update(Eqmonitorinfo entity) {
		dao.update(entity);

	}

	@Override
	public Eqmonitorinfo findById(Long id) {
		return dao.get(Eqmonitorinfo.class, id);
	}

	@Override
	public void delete(Eqmonitorinfo Eqmonitorinfo) {
		dao.delete(Eqmonitorinfo);

	}

	/**分页查询*/
	public Page<Eqmonitorinfo> list(Page<Eqmonitorinfo> page, Map<String, String> params) {
		if (page == null) {
			page = new Page<Eqmonitorinfo>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");
			// TODO 增加查询条件
		}

		// 获取总数据
		String counthql = " select count(id) from Eqmonitorinfo " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Eqmonitorinfo " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

}
