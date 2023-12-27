package egovframework.edutrack.modules.log.adminconn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogService;
import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logAdminConnLogService")
public class LogAdminConnLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogAdminConnLogService {

	/** Mapper */
    @Resource(name="logAdminConnLogMapper")
    private LogAdminConnLogMapper 		logAdminConnLogMapper;
    
    
    
    /**
 	 *  관리자 접속 로그 전체 목록을 조회한다.
 	 * @param LogAdminConnLogVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogAdminConnLogVO> list(LogAdminConnLogVO vo) throws Exception {
 		ProcessResultListVO<LogAdminConnLogVO> resultList = new ProcessResultListVO<LogAdminConnLogVO>(); 
 		try {
 			List<LogAdminConnLogVO> logList =  logAdminConnLogMapper.list(vo);
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
 	 * @param LogAdminConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogAdminConnLogVO> listPageing(LogAdminConnLogVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<LogAdminConnLogVO> resultList = new ProcessResultListVO<LogAdminConnLogVO>(); 
 		try {
 			/** start of paging */
 			PaginationInfo paginationInfo = new PaginationInfo();
 			paginationInfo.setCurrentPageNo(pageIndex);
 			paginationInfo.setRecordCountPerPage(listScale);
 			paginationInfo.setPageSize(pageScale);
 			
 			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
 			vo.setLastIndex(paginationInfo.getLastRecordIndex());
 			
 			// 전체 목록 수
 			int totalCount = logAdminConnLogMapper.count(vo);
 			paginationInfo.setTotalRecordCount(totalCount);
 			
 			List<LogAdminConnLogVO> logList =  logAdminConnLogMapper.listPageing(vo);
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
 	 * @param LogAdminConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogAdminConnLogVO> listPageing(LogAdminConnLogVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
     /**
 	 * 관리자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogAdminConnLogVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<LogAdminConnLogVO> listPageing(LogAdminConnLogVO vo, 
 			int pageIndex) throws Exception {
 		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}	
 	
 	/**
 	 * 관리자 접속 로그 상세 정보를 조회한다.
 	 * @param LogAdminConnLogVO
 	 * @return LogAdminConnLogVO
 	 * @throws Exception
 	 */
 	@Override
	public LogAdminConnLogVO view(LogAdminConnLogVO vo) throws Exception {
 		return logAdminConnLogMapper.select(vo);
 	}
 	
 	/**
 	 * 관리자 접속 로그 최종 접속 키 정보를 조회한다.
 	 * @param LogAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int viewLast(LogAdminConnLogVO vo) throws Exception {
 		return logAdminConnLogMapper.selectLast(vo);
 	} 	
 	
 	/**
 	 * 관리자 접속 로그 정보를 등록한다.
 	 * @param LogAdminConnLogVO
 	 * @return String
 	 * @throws Exception
 	 */
 	@Override
	public int add(LogAdminConnLogVO vo) throws Exception {
 		//-- 키가 없으면 생성 하여 등록
 		if(ValidationUtils.isZeroNull(vo.getConnLogSn()))
 			vo.setConnLogSn(logAdminConnLogMapper.selectKey());
 		return logAdminConnLogMapper.insert(vo);
 	}	
 	
 	/**
 	 * 관리자 접속 로그 정보를 수정한다.
 	 * @param LogAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int edit(LogAdminConnLogVO vo) throws Exception {
 		return logAdminConnLogMapper.update(vo);
 	}
}
