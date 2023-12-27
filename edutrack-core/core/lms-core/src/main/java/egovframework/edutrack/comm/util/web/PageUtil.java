package egovframework.edutrack.comm.util.web;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PageUtil {
	public static PaginationInfo getDefaultPaginationInfo(DefaultVO vo) {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		return paginationInfo;
	}
}
