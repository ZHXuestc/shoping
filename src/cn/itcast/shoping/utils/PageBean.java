package cn.itcast.shoping.utils;

import java.util.List;

public class PageBean<T> {
	private Integer currentPage;
	private Integer limit;//ÿҳ��ʾ��¼��
	private Integer totalCount;//�ܼ�¼��
	private Integer totalPage;//��ҳ��
	private List<T> list;//��ʾ�������ҳ���ϵ�����
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
