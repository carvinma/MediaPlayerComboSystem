package com.hysd.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.User;
import com.hysd.domain.page.Page;
import com.hysd.service.UserService;

@Transactional
// 启用事务机制
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private BaseDAO<User> dao;

	@Override
	public void save(User user) {
		dao.save(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);

	}

	@Override
	public User findById(Long id) {
		return dao.get(User.class, id);
	}

	@Override
	public void delete(User user) {
		dao.delete(user);

	}

	public User findByLoginName(String loginName) {
		return dao.get("from User where loginName=?", new Object[] { loginName });
	}

	/**
	 * 分页查询
	 */
	public Page<User> list(Page<User> page, Map<String, String> params) {
		if (page == null) {
			page = new Page<User>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");

			if (params.get("userName") != null) {
				whereHql.append(" userName like '%?%'");
				paramlist.add(params.get("userName"));
			}

		}

		// 获取总数据
		String counthql = " select count(id) from User " + whereHql.toString();
		long count = dao.count(counthql, paramlist);
		page.setTotalRow(count);
		// 获取当前页的数据
		whereHql.append(" order by id desc");
		String listhql = " from User " + whereHql.toString();
		page.setDataList(dao.find(listhql, paramlist));
		return page;
	}

	@Override
	public boolean hasCode(String userName, Long id) {
		String hql = "select count(id) from User where loginName=?";
		List<Object> list = new ArrayList<Object>();
		list.add(userName);
		if (id != null && id != 0) {
			hql = hql + " and id != ?";
			list.add(id);
		}
		long count = dao.count(hql, list);
		if (count == 0)
			return false;
		return true;
	}

}
