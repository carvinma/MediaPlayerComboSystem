package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Client;
import com.hysd.domain.page.Page;
import com.hysd.service.ClientService;

@Transactional
// 启用事务机制
@Service("clientService")
public class ClientServiceImpl implements ClientService {
	@Resource
	private BaseDAO<Client> dao;

	@Override
	public void save(Client entity) {
		dao.save(entity);
	}

	@Override
	public void update(Client entity) {
		dao.update(entity);

	}

	@Override
	public Client findById(Long id) {
		return dao.get(Client.class, id);
	}

	@Override
	public void delete(Client Client) {
		dao.delete(Client);

	}

	/**
	 * 分页查询
	 */
	public Page<Client> list(Page<Client> page, Map<String, Object> params) {
		if (page == null) {
			page = new Page<Client>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");

			// TODO 增加查询条件
		}

		// 获取总数据
		String counthql = " select count(id) from Client " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = "from Client " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

}
