package com.hysd.domain.page;

import java.util.List;

public class Page<T> {
	private Long totalRow;
	private Integer pageSize;
	private Integer pageNo;
	private List<?> dataList;
	private Integer totalPages;
	private Integer prePage;
	private Integer nextPage;
 
	private static final Integer DEFAULT_PAGE_SIZE = new Integer(17);
	private static final Integer DEFALUT_PAGE = new Integer(1);
	private static final Long TOTAL_ROW = new Long(0);

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	/** 取得页面显示条数 */
	public Integer getPageSize() {
		if (pageSize == null) {
			return DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	/** 设置页面显示条数 */
	public void setPageSize(Integer pSize) {
		if (pSize == null || pSize.intValue() <= 0) {
			return;
		}
		this.pageSize = pSize;
	}

	/** 取得当前页 */
	public Integer getPageNo() {
		if (pageNo == null) {
			return DEFALUT_PAGE;
		}
		return pageNo;
	}

	/** 设置当前页 */
	public void setPageNo(Integer cPage) {
		if ((cPage == null) || (cPage.intValue() <= 0)) {
			this.pageNo = DEFALUT_PAGE;
		} else {
			this.pageNo = cPage;
		}
	}

	private void setPrePage() {
		if (pageNo == 1) {
			this.prePage = 1;
		}
		this.prePage = pageNo - 1;
	}

	private void setNextPage() {
		if (pageNo == totalPages) {
			this.nextPage = pageNo;
		}
		this.nextPage = pageNo + 1;
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
		this.pageNo = getPageNo().intValue();

		setPageTotal();

		setPrePage();

		setNextPage();

	}

	/** 取得总页数 */
	private void setPageTotal() {
		int pgSize = this.getPageSize().intValue();
		int total = this.getTotalRow().intValue();
		int result = total / pgSize;

		if ((total % pgSize) != 0) {
			result++;
		}
		this.totalPages = result;
	}

	/** 取得当前页开始记录位置 */
	public int getStartRow() {
		int cPage = this.getPageNo().intValue();

		if (cPage == 1) {
			return 1;
		}
		cPage--;
		int pgSize = this.getPageSize().intValue();

		return (pgSize * cPage) + 1;
	}

}
