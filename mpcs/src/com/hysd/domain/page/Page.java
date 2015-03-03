package com.hysd.domain.page;

import java.util.List;

public class Page<T> {
	private Long totalRow;
	private Integer pageSize;
	private Integer currentPage;
	private List<?> dataList;
	private static final Integer DEFAULT_PAGE_SIZE = new Integer(17);
	private static final Integer DEFALUT_PAGE = new Integer(1);
	private static final Long TOTAL_ROW = new Long(0);

	/**
	 * 取得页面显示条数
	 */
	public Integer getPageSize() {
		if (pageSize == null) {
			return DEFAULT_PAGE_SIZE;
		}

		return pageSize;
	}

	/**
	 * 设置页面显示条数
	 */
	public void setPageSize(Integer pSize) {
		if (pSize == null || pSize.intValue() <= 0) {
			return;
		}

		this.pageSize = pSize;
	}

	/**
	 * 取得当前页
	 */
	public Integer getCurrentPage() {
		if (currentPage == null) {
			return DEFALUT_PAGE;
		}

		return currentPage;
	}

	/**
	 * 设置当前页
	 */
	public void setCurrentPage(Integer cPage) {
		if ((cPage == null) || (cPage.intValue() <= 0)) {
			this.currentPage = DEFALUT_PAGE;
		} else {
			this.currentPage = cPage;
		}
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Long getTotalRow() {
		if (totalRow == null) {
			return TOTAL_ROW;
		}
		return totalRow;
	}

	/**
	 * 设置总记录条数, 设置后可自动算出当前页数
	 */
	public void setTotalRow(Long count) {
		if (count == null) {
			count = new Long(0);
		}

		this.totalRow = count;
		int current = this.getCurrentPage().intValue();
		int lastPage = this.getPageTotal();

		if (current > lastPage) {
			this.setCurrentPage(lastPage);
		}
	}

	/**
	 * 取得总页数
	 */
	public int getPageTotal() {
		int pgSize = this.getPageSize().intValue();
		int total = this.getTotalRow().intValue();
		int result = total / pgSize;

		if ((total % pgSize) != 0) {
			result++;
		}

		return result;
	}

	/**
	 * 取得当前页开始记录位置
	 */
	public int getStartRow() {
		int cPage = this.getCurrentPage().intValue();

		if (cPage == 1) {
			return 1;
		}
		cPage--;
		int pgSize = this.getPageSize().intValue();

		return (pgSize * cPage) + 1;
	}

}
