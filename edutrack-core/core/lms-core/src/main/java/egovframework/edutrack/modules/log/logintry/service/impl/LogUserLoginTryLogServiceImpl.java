package egovframework.edutrack.modules.log.logintry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiUsrLogin;
import egovframework.edutrack.comm.annotation.HrdApiUsrLogin.Type;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogService;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 로그인 시도 로그 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logUserLoginTryLogService")
public class LogUserLoginTryLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogUserLoginTryLogService {

    @Resource(name="logUserLoginTryLogMapper")
    private LogUserLoginTryLogMapper 		logUserLoginTryLogMapper;
    
	/**
	 *  로그인 시도 로그 전체 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
 	@Override
	public ProcessResultListVO<LogUserLoginTryLogVO> list(LogUserLoginTryLogVO vo) throws Exception {
  		ProcessResultListVO<LogUserLoginTryLogVO> resultList = new ProcessResultListVO<LogUserLoginTryLogVO>(); 
  		List<LogUserLoginTryLogVO> logList =  logUserLoginTryLogMapper.list(vo);
  		resultList.setResult(1);
  		resultList.setReturnList(logList);
  		return resultList;
  	}
  	
	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
 	@Override
	public ProcessResultListVO<LogUserLoginTryLogVO> listPageing(LogUserLoginTryLogVO vo, 
  			int pageIndex, int listScale, int pageScale) throws Exception {
  		ProcessResultListVO<LogUserLoginTryLogVO> resultList = new ProcessResultListVO<LogUserLoginTryLogVO>(); 
  		
 		/** start of paging */
 		PaginationInfo paginationInfo = new PaginationInfo();
 		paginationInfo.setCurrentPageNo(pageIndex);
 		paginationInfo.setRecordCountPerPage(listScale);
 		paginationInfo.setPageSize(pageScale);
 		
 		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
 		vo.setLastIndex(paginationInfo.getLastRecordIndex());
 		
 		// 전체 목록 수
 		int totalCount = logUserLoginTryLogMapper.count(vo);
 		paginationInfo.setTotalRecordCount(totalCount);
 		
 		List<LogUserLoginTryLogVO> logList =  logUserLoginTryLogMapper.listPageing(vo);
 		resultList.setResult(1);
 		resultList.setReturnList(logList);
 		resultList.setPageInfo(paginationInfo);
  			
  		return resultList;
  	}
  	
	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
 	@Override
	public ProcessResultListVO<LogUserLoginTryLogVO> listPageing(LogUserLoginTryLogVO vo, 
  			int pageIndex, int listScale) throws Exception {
  		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
  	}
  	
	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
 	@Override
	public ProcessResultListVO<LogUserLoginTryLogVO> listPageing(LogUserLoginTryLogVO vo, 
  			int pageIndex) throws Exception {
  		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
  	}
 	
	/**
	 * 로그인 시도 로그 정보를 등록한다.
	 * @param LogUserLoginTryLogVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	@HrdApiUsrLogin(Type.CREATE)
	public void add(LogUserLoginTryLogVO vo) throws Exception {
		if(ValidationUtils.isZeroNull(vo.getLoginTrySn())) {
			vo.setLoginTrySn(logUserLoginTryLogMapper.selectKey());
		}
 		logUserLoginTryLogMapper.insert(vo);
 	}	 	
	
	/**
	 * api용 회원로그인정보 조회
	 * @param LogUserLoginTryLogVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> selectUserLoginApi(LogUserLoginTryLogVO vo) throws Exception {
		return logUserLoginTryLogMapper.selectUserLoginApi(vo);
	}
}
