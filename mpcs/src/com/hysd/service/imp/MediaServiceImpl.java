package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Media;
import com.hysd.domain.page.Page;
import com.hysd.service.MediaService;

@Transactional
// 启用事务机制
@Service("mediaService")
public class MediaServiceImpl implements MediaService {
	@Resource
	private BaseDAO<Media> dao;

	@Override
	public void save(Media entity) {
		dao.save(entity);
	}

	@Override
	public void update(Media entity) {
		dao.update(entity);

	}

	@Override
	public Media findById(Long id) {
		return dao.get(Media.class, id);
	}

	@Override
	public void delete(Media entity) {
		dao.delete(entity);

	}

	/**
	 * 分页查询
	 */
	public Page<Media> list(Page<Media> page, Map<String, Object> params) {
		if (page == null) {
			page = new Page<Media>();
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
		String counthql = " select count(id) from Media " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Media " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

}
