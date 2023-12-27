package egovframework.edutrack.modules.log.usercnsl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.usercnsl.service.LogUserCnslLogService;
import egovframework.edutrack.modules.log.usercnsl.service.LogUserCnslLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 */

@Service("logUserCnslLogService")
public class LogUserCnslLogServcieImpl 
	extends EgovAbstractServiceImpl implements LogUserCnslLogService{
	
	/** Mapper */
    @Resource(name="logUserCnslLogMapper")
    private LogUserCnslLogMapper 		logUserCnslLogMapper;

	/**
	 * [CRM] 유저 상담 내역 조회
	 * @param LogUserCnslLogVO
	 * @return ProcessResultListVO<LogUserCnslLogVO>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<LogUserCnslLogVO> listPageing(LogUserCnslLogVO vo) throws Exception {
		ProcessResultListVO<LogUserCnslLogVO> resultList = new ProcessResultListVO<>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		int totalCnt = logUserCnslLogMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCnt);
		
		List<LogUserCnslLogVO> cnslList = logUserCnslLogMapper.listPageing(vo);
		cnslList.forEach(this::setAnsrListByType);
		resultList.setReturnList(cnslList);
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	private void setAnsrListByType(LogUserCnslLogVO vo) {
		String cnslType = vo.getCnslCtgr();
		if("OBJT".equals(cnslType)) vo.setAnsrList(logUserCnslLogMapper.listObjtAnsr(vo));
		else vo.setAnsrList(logUserCnslLogMapper.selectQnaAnsr(vo));
	}
}
