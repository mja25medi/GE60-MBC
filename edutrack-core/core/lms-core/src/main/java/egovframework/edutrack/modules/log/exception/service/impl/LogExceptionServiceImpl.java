package egovframework.edutrack.modules.log.exception.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.edutrack.modules.log.exception.service.LogExceptionVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 오류 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logExceptionService")
public class LogExceptionServiceImpl 
	extends EgovAbstractServiceImpl implements LogExceptionService{

	protected final Log log = LogFactory.getLog(getClass());
	
	/** Mapper */
    @Resource(name="logExceptionMapper")
    private LogExceptionMapper 		logExceptionMapper;
    
    /**
  	 * 오류 로그 전체 목록을 조회한다.
  	 * @param LogExcepVO
  	 * @return ProcessResultListVO
  	 * @throws Exception
  	 */
 	@Override
	public ProcessResultListVO<LogExceptionVO> list(LogExceptionVO vo) throws Exception {
  		ProcessResultListVO<LogExceptionVO> resultList = new ProcessResultListVO<LogExceptionVO>(); 
  		try {
  			List<LogExceptionVO> logList =  logExceptionMapper.list(vo);
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
  	 * 오류 로그 페이징 목록을 조회한다.
  	 * @param LogExcepVO
  	 * @param pageIndex
  	 * @param listScale
  	 * @param pageScale
  	 * @return ProcessResultListVO
  	 * @throws Exception
  	 */
 	@Override
	public ProcessResultListVO<LogExceptionVO> listPageing(LogExceptionVO vo, 
  			int pageIndex, int listScale, int pageScale) throws Exception {
  		ProcessResultListVO<LogExceptionVO> resultList = new ProcessResultListVO<LogExceptionVO>(); 
  		try {
  			/** start of paging */
  			PaginationInfo paginationInfo = new PaginationInfo();
  			paginationInfo.setCurrentPageNo(pageIndex);
  			paginationInfo.setRecordCountPerPage(listScale);
  			paginationInfo.setPageSize(pageScale);
  			
  			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
  			vo.setLastIndex(paginationInfo.getLastRecordIndex());
  			
  			// 전체 목록 수
  			int totalCount = logExceptionMapper.count(vo);
  			paginationInfo.setTotalRecordCount(totalCount);
  			
  			List<LogExceptionVO> logList =  logExceptionMapper.listPageing(vo);
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
 	 * 오류 로그 페이징 목록을 조회한다.
 	 * @param LogExcepVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<LogExceptionVO> listPageing(LogExceptionVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
    /**
 	 * 오류 로그 페이징 목록을 조회한다.
 	 * @param LogExcepVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<LogExceptionVO> listPageing(LogExceptionVO vo, 
 			int pageIndex) throws Exception {
 		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}  	
 	
 	/**
 	 * 오류 로그 정보를 등록한다.
 	 * @param LogExcepVO
 	 * @return String
 	 * @throws Exception
 	 */
	@Override
	public void add(String exceptionDiv, String methodNm, String exceptionNm, Throwable throwable) throws Exception {
		
		// 구분자가 없으면 그냥 리턴. 저장할 필요도 없음.
    	if(StringUtil.isNull(exceptionDiv)) return;

    	StringWriter writer = new StringWriter();

    	try {
			throwable.printStackTrace(new PrintWriter(writer));
			writer.close();
		} catch (IOException ex) {
			log.error("예외정보를 추출하던 중 오류가 발생했습니다.");
		} finally {
			if(writer != null) writer.close();
		}

    	if("use".equals(Constants.EXCEPTION_DBLOG_USE)) {
			LogExceptionVO exception = new LogExceptionVO();
			exception.setExceptionDiv(exceptionDiv);
			exception.setOriginDt(DateTimeUtil.getCurrentString("YYYYMMDD"));
			exception.setMethodNm(methodNm);
			exception.setExceptionNm(exceptionNm);
			exception.setStackTrace(writer.toString());
	    	exception.setOriginCnt(1);
	    	try {
	    		logExceptionMapper.insert(exception);
	    	} catch (Exception e) {
	    		logExceptionMapper.update(exception);
	    	}
    	}		
 	}
}
