package egovframework.edutrack.modules.student.subjectresult.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.subjectresult.service.SubjectEduResultService;
import egovframework.edutrack.modules.student.subjectresult.service.SubjectEduResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("subjectEduResultService")
public class SubjectEduResultServiceImpl
	extends EgovAbstractServiceImpl implements SubjectEduResultService{
	

	/** Mapper */
	@Resource(name="subjectEduResultMapper")
	private SubjectEduResultMapper 		subjectEduResultMapper;
	
	
	/**
	 * 수강 결과 목록 조회(전체)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<SubjectEduResultVO> list(SubjectEduResultVO dto) throws Exception {
		ProcessResultListVO<SubjectEduResultVO> resultList = new ProcessResultListVO<SubjectEduResultVO>();
		try {
			List<SubjectEduResultVO> returnList = subjectEduResultMapper.list(dto);
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
	 * 수강 결과 목록 조회(페이징)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<SubjectEduResultVO> list(SubjectEduResultVO dto,
			int curPage, int listScale, int pageScale ) throws Exception {
		
		ProcessResultListVO<SubjectEduResultVO> resultList = new ProcessResultListVO<SubjectEduResultVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			dto.setFirstIndex(paginationInfo.getFirstRecordIndex());
			dto.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = subjectEduResultMapper.count(dto);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<SubjectEduResultVO> returnList = subjectEduResultMapper.listPageing(dto, curPage, listScale, pageScale);
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
	 * 수강 결과 정보 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<SubjectEduResultVO> view(SubjectEduResultVO dto) throws Exception {
		ProcessResultVO<SubjectEduResultVO> resultVO = new ProcessResultVO<SubjectEduResultVO>();
		try {
			SubjectEduResultVO returnVO = subjectEduResultMapper.select(dto);
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
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<SubjectEduResultVO> regist(SubjectEduResultVO dto) throws Exception{
		subjectEduResultMapper.merge(dto);
		subjectEduResultMapper.autoInserteduRslt(dto);
		return ProcessResultVO.success();
	}
	
	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<SubjectEduResultVO> regist(List<SubjectEduResultVO> itemList) throws Exception{
		subjectEduResultMapper.mergeBatch(itemList);
		subjectEduResultMapper.autoInserteduRsltBatch(itemList);
		return ProcessResultVO.success();
	}
	

}
