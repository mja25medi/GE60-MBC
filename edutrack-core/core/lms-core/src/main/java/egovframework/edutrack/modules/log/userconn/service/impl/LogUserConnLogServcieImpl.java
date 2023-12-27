package egovframework.edutrack.modules.log.userconn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogService;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 사용자 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logUserConnLogService")
public class LogUserConnLogServcieImpl 
	extends EgovAbstractServiceImpl implements LogUserConnLogService{

	/** Mapper */
    @Resource(name="logUserConnLogMapper")
    private LogUserConnLogMapper 		logUserConnLogMapper;
    
    /**
 	 *  사용자 접속 로그 전체 목록을 조회한다.
 	 * @param LogUserConnLogVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<LogUserConnLogVO> list(LogUserConnLogVO vo) throws Exception {
 		ProcessResultListVO<LogUserConnLogVO> resultList = new ProcessResultListVO<LogUserConnLogVO>(); 
 		try {
 			List<LogUserConnLogVO> logList =  logUserConnLogMapper.list(vo);
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
 	 * 사용자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogUserConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<LogUserConnLogVO> listPageing(LogUserConnLogVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<LogUserConnLogVO> resultList = new ProcessResultListVO<LogUserConnLogVO>(); 
 		try {
 			/** start of paging */
 			PaginationInfo paginationInfo = new PaginationInfo();
 			paginationInfo.setCurrentPageNo(pageIndex);
 			paginationInfo.setRecordCountPerPage(listScale);
 			paginationInfo.setPageSize(pageScale);
 			
 			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
 			vo.setLastIndex(paginationInfo.getLastRecordIndex());
 			
 			// 전체 목록 수
 			int totalCount = logUserConnLogMapper.count(vo);
 			paginationInfo.setTotalRecordCount(totalCount);
 			
 			List<LogUserConnLogVO> logList =  logUserConnLogMapper.listPageing(vo);
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
 	 * 사용자 접속 로그 페이징 목록을 조회한다.
 	 * @param LogUserConnLogVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<LogUserConnLogVO> listPageing(LogUserConnLogVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
	
	  /**
	 	 * 사용자 접속 로그 페이징 목록을 조회한다.
	 	 * @param LogUserConnLogVO
	 	 * @param pageIndex
	 	 * @return ProcessResultListVO
	 	 * @throws Exception
	 	 */
	@Override
	public ProcessResultListVO<LogUserConnLogVO> listPageing(LogUserConnLogVO vo) throws Exception {
			return this.listPageing(vo, vo.getCurPage(), vo.getListScale(),vo.getPageScale());
	}	
 	
 	/**
 	 * 사용자 접속 로그 정보를 등록한다.
 	 * @param LogUserConnLogVO
 	 * @return String
 	 * @throws Exception
 	 */
	@Override
	public int add(HttpServletRequest request) throws Exception {
		
		LogUserConnLogVO luclvo = new LogUserConnLogVO();
		luclvo.setUserNo(UserBroker.getUserNo(request));

		String deviceType = UserBroker.getUserDevice(request);
		String osType = UserBroker.getUserOS(request);
		String browserType = UserBroker.getUserBrowser(request);
		String appType = UserBroker.getAppType(request);
		String mobileType = UserBroker.getMobileType(request);
		String agent = request.getHeader("User-Agent");

		luclvo.setDeviceType(deviceType);
		luclvo.setOsType(osType);
		luclvo.setBrowserType(browserType);
		luclvo.setAppType(appType);
		luclvo.setMobileType(mobileType);

		String connIp = request.getRemoteAddr();
		luclvo.setConnIp(connIp);
		
 		return logUserConnLogMapper.insert(luclvo);
 	}	
 	
 	/**
 	 * 사용자 접속 로그 정보를 수정한다.
 	 * @param LogUserConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int edit(LogUserConnLogVO vo) throws Exception {
 		return logUserConnLogMapper.update(vo);
 	}
}
