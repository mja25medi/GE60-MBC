package egovframework.edutrack.modules.student.worklog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogService;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("stdEduRsltWorkLogService")
public class StdEduRsltWorkLogServiceImpl
	extends EgovAbstractServiceImpl implements StdEduRsltWorkLogService {

	/** Mapper */
	@Resource(name="stdEduRsltWorkLogMapper")
	private StdEduRsltWorkLogMapper 		stdEduRsltWorkLogMapper;

	/**
	 * 성적 처리 작업 로그 전체 목록
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */

	@Override
	public ProcessResultListVO<StdEduRsltWorkLogVO> list(StdEduRsltWorkLogVO vo) throws Exception {
		ProcessResultListVO<StdEduRsltWorkLogVO> resultList = new ProcessResultListVO<StdEduRsltWorkLogVO>();
		try {
			List<StdEduRsltWorkLogVO> returnList = stdEduRsltWorkLogMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		
		return resultList;
	}	

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(StdEduRsltWorkLogVO vo,
			int curPage, int listScale, int pageScale ) throws Exception {
		
		ProcessResultListVO<StdEduRsltWorkLogVO> resultList = new ProcessResultListVO<StdEduRsltWorkLogVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = stdEduRsltWorkLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			
			List<StdEduRsltWorkLogVO> returnList = stdEduRsltWorkLogMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
	
		return resultList;
	}

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(StdEduRsltWorkLogVO vo,
			int curPage, int listScale ) throws Exception {
		
		ProcessResultListVO<StdEduRsltWorkLogVO> resultList = new ProcessResultListVO<StdEduRsltWorkLogVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = stdEduRsltWorkLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<StdEduRsltWorkLogVO> returnList = stdEduRsltWorkLogMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);			
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}				
		return resultList;
	}

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(StdEduRsltWorkLogVO vo,
			int curPage) throws Exception {
		
		ProcessResultListVO<StdEduRsltWorkLogVO> resultList = new ProcessResultListVO<StdEduRsltWorkLogVO>();
		

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = stdEduRsltWorkLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<StdEduRsltWorkLogVO> returnList = stdEduRsltWorkLogMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}	
		return resultList;
	}

	/**
	 * 성적 처리 작업 로그 단일 항목 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<StdEduRsltWorkLogVO> view(StdEduRsltWorkLogVO vo) throws Exception {
		ProcessResultVO<StdEduRsltWorkLogVO> resultVO = new ProcessResultVO<StdEduRsltWorkLogVO>();
		try {
			StdEduRsltWorkLogVO returnVO = stdEduRsltWorkLogMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 성적 처리 작업 로그 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<StdEduRsltWorkLogVO> add(StdEduRsltWorkLogVO vo) throws Exception{
		ProcessResultVO<StdEduRsltWorkLogVO> resultVO = new ProcessResultVO<StdEduRsltWorkLogVO>();
		try {
			stdEduRsltWorkLogMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}
}
