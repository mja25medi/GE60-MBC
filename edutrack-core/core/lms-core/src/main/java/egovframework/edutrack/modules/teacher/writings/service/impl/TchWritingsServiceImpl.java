package egovframework.edutrack.modules.teacher.writings.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsService;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("tchWritingsService")
public class TchWritingsServiceImpl
	extends EgovAbstractServiceImpl implements TchWritingsService {

	/** Mapper */
	@Resource(name="tchWritingsMapper")
	private TchWritingsMapper 		tchWritingsMapper;

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<TchWritingsVO> list(TchWritingsVO teacherWritingsVO) throws Exception {
		ProcessResultListVO<TchWritingsVO> resultList = new ProcessResultListVO<TchWritingsVO>(); 
		try {
			List<TchWritingsVO> returnList =  tchWritingsMapper.list(teacherWritingsVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<TchWritingsVO> listPageing(TchWritingsVO teacherWritingsVO,int curPage, int listScale, int pageScale) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		teacherWritingsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		teacherWritingsVO.setLastIndex(paginationInfo.getLastRecordIndex());

		
		ProcessResultListVO<TchWritingsVO> resultList = new ProcessResultListVO<TchWritingsVO>(); 
		try {
			// 전체 목록 수
			int totalCount = tchWritingsMapper.count(teacherWritingsVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<TchWritingsVO> returnList =  tchWritingsMapper.listPageing(teacherWritingsVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;

	}

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<TchWritingsVO> listPageing(TchWritingsVO teacherWritingsVO,
			int curPage, int listScale) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		teacherWritingsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		teacherWritingsVO.setLastIndex(paginationInfo.getLastRecordIndex());

		
		ProcessResultListVO<TchWritingsVO> resultList = new ProcessResultListVO<TchWritingsVO>(); 
		try {
			// 전체 목록 수
			int totalCount = tchWritingsMapper.count(teacherWritingsVO);
			paginationInfo.setTotalRecordCount(totalCount);

			List<TchWritingsVO> returnList =  tchWritingsMapper.listPageing(teacherWritingsVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<TchWritingsVO> listPageing(TchWritingsVO teacherWritingsVO, int curPage) throws Exception {
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		teacherWritingsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		teacherWritingsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<TchWritingsVO> resultList = new ProcessResultListVO<TchWritingsVO>(); 
		try {
			// 전체 목록 수
			int totalCount = tchWritingsMapper.count(teacherWritingsVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<TchWritingsVO> returnList =  tchWritingsMapper.listPageing(teacherWritingsVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		

	}

	/**
	 * 강사의 강의 저서 단일 항목을 반환한다.
	 * @return  ProcessResultVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<TchWritingsVO> view(TchWritingsVO teacherWritingsVO) throws Exception {
		ProcessResultVO<TchWritingsVO> resultVO = new ProcessResultVO<TchWritingsVO>(); 
		try {
			TchWritingsVO returnVO =  tchWritingsMapper.select(teacherWritingsVO);
			resultVO.setResult(1);
			resultVO.setReturnVO(returnVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 강사의 강의 저서 정보를 등록하고 결과를 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<TchWritingsVO> add(TchWritingsVO teacherWritingsVO) throws Exception{
		teacherWritingsVO.setLecWritingsSn(tchWritingsMapper.selectKey());
		tchWritingsMapper.insert(teacherWritingsVO);
		return ProcessResultVO.success();
	}

	/**
	 * 강사의 강의 저서 정보를 수정하고 결과를 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<TchWritingsVO> edit(TchWritingsVO teacherWritingsVO) throws Exception{
		tchWritingsMapper.update(teacherWritingsVO);
		return ProcessResultVO.success();
	}

	/**
	 * 강사의 강의 저서 정보를 삭제하고 결과를 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<TchWritingsVO> remove(TchWritingsVO teacherWritingsVO) throws Exception{
		tchWritingsMapper.delete(teacherWritingsVO);
		return ProcessResultVO.success();
	}
}
