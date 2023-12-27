package egovframework.edutrack.modules.log.usercert.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogService;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 */

@Service("logUserCertLogService")
public class LogUserCertLogServcieImpl 
	extends EgovAbstractServiceImpl implements LogUserCertLogService{
	
	/** Mapper */
    @Resource(name="logUserCertLogMapper")
    private LogUserCertLogMapper 		logUserCertLogMapper;


	@Override
	public ProcessResultListVO<LogUserCertLogVO> listPageing(LogUserCertLogVO vo) throws Exception {
		ProcessResultListVO<LogUserCertLogVO> resultList = new ProcessResultListVO<LogUserCertLogVO>(); 
 		try {
 			/** start of paging */
 			PaginationInfo paginationInfo = new PaginationInfo();
 			paginationInfo.setCurrentPageNo(vo.getCurPage());
 			paginationInfo.setRecordCountPerPage(vo.getListScale());
 			paginationInfo.setPageSize(vo.getPageScale());
 			
 			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
 			vo.setLastIndex(paginationInfo.getLastRecordIndex());
 			
 			// 전체 목록 수
 			int totalCount = logUserCertLogMapper.count(vo);
 			paginationInfo.setTotalRecordCount(totalCount);
 			
 			List<LogUserCertLogVO> logList =  logUserCertLogMapper.listPageing(vo);
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
 	 * 사용자 본인인증 로그 정보를 등록한다.
 	 * @param LogUserConnLogVO
 	 * @return String
 	 * @throws Exception
 	 */
	
	@Override
	public void add(LogUserCertLogVO vo) throws Exception {
		logUserCertLogMapper.insert(vo);
 	}	 	
  
}
