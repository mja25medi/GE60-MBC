package egovframework.edutrack.modules.log.prnlog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 개인 정보 출력 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logPrnLogService")
public class LogPrnLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogPrnLogService {

	/** Mapper */
    @Resource(name="logPrnLogMapper")
    private LogPrnLogMapper 		logPrnLogMapper;
    
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#list(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO)
	 */
	@Override
	public ProcessResultListVO<LogPrnLogVO> list(LogPrnLogVO vo) throws Exception {
		ProcessResultListVO<LogPrnLogVO> resultList = new ProcessResultListVO<LogPrnLogVO>(); 
		try {
			List<LogPrnLogVO> bbsInfoList =  logPrnLogMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsInfoList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#listPageing(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO, int, int, int)
	 */
	@Override
	public ProcessResultListVO<LogPrnLogVO> listPageing(LogPrnLogVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<LogPrnLogVO> resultList = new ProcessResultListVO<LogPrnLogVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logPrnLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogPrnLogVO> bbsInfoList =  logPrnLogMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#listPageing(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO, int, int)
	 */
	@Override
	public ProcessResultListVO<LogPrnLogVO> listPageing(LogPrnLogVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#listPageing(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO, int)
	 */
	@Override
	public ProcessResultListVO<LogPrnLogVO> listPageing(LogPrnLogVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#view(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO)
	 */
	@Override
	public LogPrnLogVO view(LogPrnLogVO vo) throws Exception {
		return logPrnLogMapper.select(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.log.prnlog.service.impl.LogPrnLogService#add(egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO)
	 */
	@Override
	public int add(LogPrnLogVO vo) throws Exception {
		return logPrnLogMapper.insert(vo);
	}	    
}
