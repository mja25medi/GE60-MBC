package egovframework.edutrack.modules.log.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.log.message.service.LogMsgMngService;
import egovframework.edutrack.modules.log.message.service.LogMsgMngVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>로그 - 메시지 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logMsgMngService")
public class LogMsgMngServiceImpl 
	extends EgovAbstractServiceImpl implements LogMsgMngService {
	protected static final Log log = LogFactory.getLog(LogMsgMngServiceImpl.class);

	/** Mapper */
    @Resource(name="logMsgMngMapper")
    private LogMsgMngMapper 		logMsgMngMapper;
    
	@Resource(name="sysFileService")
	private SysFileService		sysFileService;

	
	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<LogMsgMngVO> listMessageMainPageing(LogMsgMngVO VO, int curPage, int listScale) throws Exception {
		return this.listMessageMainPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 개설 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<LogMsgMngVO> listMessageMainPageing(LogMsgMngVO vo, int pageIndex, int listScale, int pageScale)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());

		ProcessResultListVO<LogMsgMngVO> resultList = new ProcessResultListVO<LogMsgMngVO>();
		try {
			// 전체 목록 수
			int totalCount = logMsgMngMapper.listMessageMainPageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogMsgMngVO> returnList = logMsgMngMapper.listMessageMainPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			log.error("Exception occurred");
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultVO<LogMsgMngVO> view(LogMsgMngVO VO) throws Exception {
		ProcessResultVO<LogMsgMngVO> resultVO = new ProcessResultVO<LogMsgMngVO>();
		try {
			
			LogMsgMngVO view = logMsgMngMapper.select(VO);
			
			resultVO.setReturnVO(view);
			resultVO.setResultSuccess();
		} catch (Exception e){
			log.error("Exception occurred");
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	@Override
	public ProcessResultListVO<LogMsgMngVO> listMsgTrans(LogMsgMngVO vo) {
		ProcessResultListVO<LogMsgMngVO> resultList = new ProcessResultListVO<>();
		resultList.setReturnList(logMsgMngMapper.listMsgTrans(vo));
		return resultList;
	}
	
}
