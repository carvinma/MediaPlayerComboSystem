package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Eqinfo;
import com.hysd.domain.page.Page;
import com.hysd.service.EqinfoService;

@Transactional
// 启用事务机制
@Service("eqinfoService")
public class EqinfoServiceImpl implements EqinfoService {
	@Resource
	private BaseDAO<Eqinfo> dao;

	@Override
	public void save(Eqinfo entity) {
		dao.save(entity);
	}

	@Override
	public void update(Eqinfo entity) {
		dao.update(entity);

	}

	@Override
	public Eqinfo findById(Long id) {
		return dao.get(Eqinfo.class, id);
	}

	@Override
	public void delete(Eqinfo Eqinfo) {
		dao.delete(Eqinfo);

	}

	/**分页查询*/
	public Page<Eqinfo> list(Page<Eqinfo> page, Map<String, Object> params) {
		if (page == null) {
			page = new Page<Eqinfo>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");
			// TODO 增加查询条件
		}

		// 获取总数据
		String counthql = " select count(id) from Eqinfo " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Eqinfo " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

}
