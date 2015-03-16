package com.hysd.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hysd.dao.BaseDAO;
import com.hysd.domain.Media;
import com.hysd.domain.page.Page;
import com.hysd.service.MediaService;
import com.hysd.util.Struts2Utils;

@Transactional
// 启用事务机制
@Service("mediaService")
public class MediaServiceImpl implements MediaService {

	@Resource
	private BaseDAO<Media> dao;

	@Override
	public void save(Media entity) {
		// 获取当前项目的路径
		// TODO
		String basePath = Struts2Utils.getRequest().getRequestURL() + "/movie/";
		String filePath = entity.getUrl();
		File file = new File(basePath + filePath);
		String fileName = file.getName();
		// 设置文件大小
		entity.setMediaSize(convertFileSize(file.length()));
		// 设置文件格式
		entity.setMediaType(fileName.substring(fileName.lastIndexOf(".") + 1));
		// 设置更新时间
		entity.setUpdateTime(new Date());
		dao.save(entity);
	}

	/** 文件大小 */
	private String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
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
	public Page<Media> list(Page<Media> page, Map<String, String> params) {
		if (page == null) {
			page = new Page<Media>();
		}

		// 构建hql查询条件
		StringBuffer whereHql = new StringBuffer();
		List<Object> paramlist = new ArrayList<Object>();
		if (params.size() > 0) {
			whereHql.append(" where ");

			if (params.get("mediaName") != null) {
				whereHql.append(" mediaName like ?");
				paramlist.add(params.get("%" + "mediaName" + "%"));
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
