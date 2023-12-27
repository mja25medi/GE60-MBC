package egovframework.edutrack.modules.log.orgadminconn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogService;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logOrgAdminConnLogService")
public class LogOrgAdminConnLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogOrgAdminConnLogService {

	/** Mapper */
    @Resource(name="logOrgAdminConnLogMapper")
    private LogOrgAdminConnLogMapper 		logOrgAdminConnLogMapper;
    
    /**
 	 *  관리자 접속 로그 전체 목록을 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogOrgAdminConnLogVO> list(LogOrgAdminConnLogVO vo) throws Exception {
 		ProcessResultListVO<LogOrgAdminConnLogVO> resultList = new ProcessResultListVO<LogOrgAdminConnLogVO>(); 
 		try {
 			List<LogOrgAdminConnLogVO> logList =  logOrgAdminConnLogMapper.list(vo);
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
 	 * 관리자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(LogOrgAdminConnLogVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<LogOrgAdminConnLogVO> resultList = new ProcessResultListVO<LogOrgAdminConnLogVO>(); 
 		try {
 			/** start of paging */
 			PaginationInfo paginationInfo = new PaginationInfo();
 			paginationInfo.setCurrentPageNo(pageIndex);
 			paginationInfo.setRecordCountPerPage(listScale);
 			paginationInfo.setPageSize(pageScale);
 			
 			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
 			vo.setLastIndex(paginationInfo.getLastRecordIndex());
 			
 			// 전체 목록 수
 			int totalCount = logOrgAdminConnLogMapper.count(vo);
 			paginationInfo.setTotalRecordCount(totalCount);
 			
 			List<LogOrgAdminConnLogVO> logList =  logOrgAdminConnLogMapper.listPageing(vo);
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
 	 * 관리자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(LogOrgAdminConnLogVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
     /**
 	 * 관리자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(LogOrgAdminConnLogVO vo, 
 			int pageIndex) throws Exception {
 		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}	
 	
 	/**
 	 * 관리자 접속 로그 상세 정보를 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return LogOrgAdminConnLogVO
 	 * @throws Exception
 	 */
 	@Override
	public LogOrgAdminConnLogVO view(LogOrgAdminConnLogVO vo) throws Exception {
 		return logOrgAdminConnLogMapper.select(vo);
 	}
 	
 	/**
 	 * 관리자 접속 로그 최종 접속 키 정보를 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int viewLast(LogOrgAdminConnLogVO vo) throws Exception {
 		return logOrgAdminConnLogMapper.selectLast(vo);
 	} 	
 	
 	/**
 	 * 관리자 접속 로그 정보를 등록한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return String
 	 * @throws Exception
 	 */
 	@Override
	public void add(LogOrgAdminConnLogVO vo) throws Exception {
 		//-- 키가 없으면 생성 하여 등록
// 		if(ValidationUtils.isZeroNull(vo.getConnLogSn()))
// 			vo.setConnLogSn(logOrgAdminConnLogMapper.selectKey());
 		logOrgAdminConnLogMapper.insert(vo);
 	}	
 	
 	/**
 	 * 관리자 접속 로그 정보를 수정한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public void edit(LogOrgAdminConnLogVO vo) throws Exception {
 		logOrgAdminConnLogMapper.update(vo);
 	}
}
