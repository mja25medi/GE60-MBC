package egovframework.edutrack.modules.log.orgstatus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusService;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 기관 상태 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logOrgStatusService")
public class LogOrgStatusServiceImpl 
	extends EgovAbstractServiceImpl implements LogOrgStatusService{

	/** Mapper */
    @Resource(name="logOrgStatusMapper")
    private LogOrgStatusMapper 		logOrgStatusMapper;
    
	/**
	 * 기관 상태 로그 전체 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogOrgStatusVO> list(LogOrgStatusVO vo) throws Exception {
  		ProcessResultListVO<LogOrgStatusVO> resultList = new ProcessResultListVO<LogOrgStatusVO>(); 
  		try {
  			List<LogOrgStatusVO> logList =  logOrgStatusMapper.list(vo);
  			resultList.setResult(1);
  			resultList.setReturnList(logList);
  		} catch (Exception e){
  			e.printStackTrace();
  			resultList.setResult(-1);
  			resultList.setMessage(e.getMessage());
  		}
  		return resultList;
  	}
  	
	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogOrgStatusVO> listPageing(LogOrgStatusVO vo, 
  			int pageIndex, int listScale, int pageScale) throws Exception {
  		ProcessResultListVO<LogOrgStatusVO> resultList = new ProcessResultListVO<LogOrgStatusVO>(); 
  		try {
  			/** start of paging */
  			PaginationInfo paginationInfo = new PaginationInfo();
  			paginationInfo.setCurrentPageNo(pageIndex);
  			paginationInfo.setRecordCountPerPage(listScale);
  			paginationInfo.setPageSize(pageScale);
  			
  			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
  			vo.setLastIndex(paginationInfo.getLastRecordIndex());
  			
  			// 전체 목록 수
  			int totalCount = logOrgStatusMapper.count(vo);
  			paginationInfo.setTotalRecordCount(totalCount);
  			
  			List<LogOrgStatusVO> logList =  logOrgStatusMapper.listPageing(vo);
  			resultList.setResult(1);
  			resultList.setReturnList(logList);
  			resultList.setPageInfo(paginationInfo);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  			resultList.setResult(-1);
  			resultList.setMessage(e.getMessage());
  		}
  		return resultList;
  	}
  	
	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogOrgStatusVO> listPageing(LogOrgStatusVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogOrgStatusVO> listPageing(LogOrgStatusVO vo, 
 			int pageIndex) throws Exception {
 		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}
	
	/**
	 * 시스템 총 현황 검색
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	@Override
	public LogOrgStatusVO viewTotalStatus(LogOrgStatusVO vo) throws Exception {
		return logOrgStatusMapper.selectTotalStatus(vo);
	}
	
	/**
	 * 사이트별 메인페이지 현황
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	@Override
	public LogOrgStatusVO viewOrgStatus(LogOrgStatusVO vo) throws Exception {
		return logOrgStatusMapper.selectOrgStatus(vo);
	}
	
	/**
	 * 사이트별 카운트 현황
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	@Override
	public LogOrgStatusVO count(LogOrgStatusVO vo) throws Exception {
		return logOrgStatusMapper.count1(vo);
	}
}
