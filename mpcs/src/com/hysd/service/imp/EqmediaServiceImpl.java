package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Eqmedia;
import com.hysd.domain.page.Page;
import com.hysd.service.EqmediaService;

@Transactional
// 启用事务机制
@Service("eqmediaService")
public class EqmediaServiceImpl implements EqmediaService {
	@Resource
	private BaseDAO<Eqmedia> dao;

	@Override
	public void save(Eqmedia entity) {
		entity.setUpdateTime(new Date());
		dao.save(entity);
	}

	@Override
	public void update(Eqmedia entity) {
		dao.update(entity);

	}

	@Override
	public Eqmedia findById(Long id) {
		return dao.get(Eqmedia.class, id);
	}

	@Override
	public void delete(Eqmedia entity) {
		dao.delete(entity);

	}

	/**
	 * 分页查询
	 */
	public Page<Eqmedia> list(Page<Eqmedia> page, Map<String, Object> params) {
		if (page == null) {
			page = new Page<Eqmedia>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");

			if (params.get("mediaName") != null) {
				whereHql.append(" mediaName like '%?%'");
				paramlist.add(params.get("mediaName"));
			}

		}

		// 获取总数据
		String counthql = " select count(id) from Eqmedia " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Eqmedia " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

	@Override
	public List<Eqmedia> findMedias(String sn) {
		return dao.find("from Eqmedia where sn=?", new Object[] { sn });
	}

}
