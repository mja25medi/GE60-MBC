package egovframework.edutrack.comm.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class ProcessResultListVO<T> extends AbstractResult {

	private List<T> returnList;
	
	private PaginationInfo pageInfo;
	private T returnVO;

	public ProcessResultListVO() {
		super();
	}

	public T getReturnVO() {
		return returnVO;
	}
	public void setReturnVO(T returnVO) {
		this.returnVO = returnVO;
	}
	public ProcessResultListVO(List<T> returnList) {
		super();
		this.returnList = returnList;
	}
	public ProcessResultListVO(List<T> returnList, int returnResult) {
		super(returnResult);
		this.returnList = returnList;
	}
	public List<T> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<T> returnList) {
		this.returnList = returnList;
	}

	public PaginationInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}