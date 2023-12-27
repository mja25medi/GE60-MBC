package egovframework.edutrack.modules.student.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.student.student.service.StudentResultService;
import egovframework.edutrack.modules.student.student.service.StudentResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("studentResultService")
public class StudentResultServiceImpl 
	extends EgovAbstractServiceImpl implements StudentResultService {

	/** Mapper */
	@Resource(name="studentResultMapper")
	private StudentResultMapper 		studentResultMapper;
	
	/**
	 * 수강생 결과 목록
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StudentResultVO> list(StudentResultVO vo) throws Exception {
		ProcessResultListVO<StudentResultVO> resultList = new ProcessResultListVO<StudentResultVO>();
		try {
			List<StudentResultVO> returnList = studentResultMapper.list(vo);
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
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StudentResultVO> listPageing(StudentResultVO vo, int curPage, int listScale, int pageScale ) throws Exception {

		ProcessResultListVO<StudentResultVO> resultList = new ProcessResultListVO<StudentResultVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = studentResultMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<StudentResultVO> returnList = studentResultMapper.listPageing(vo);
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
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StudentResultVO> listPageing(StudentResultVO vo, int curPage, int listScale ) throws Exception {

		ProcessResultListVO<StudentResultVO> resultList = new ProcessResultListVO<StudentResultVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = studentResultMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<StudentResultVO> returnList = studentResultMapper.listPageing(vo);
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
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<StudentResultVO> listPageing(StudentResultVO vo, int curPage ) throws Exception {

		ProcessResultListVO<StudentResultVO> resultList = new ProcessResultListVO<StudentResultVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = studentResultMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<StudentResultVO> returnList = studentResultMapper.listPageing(vo);
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
}
