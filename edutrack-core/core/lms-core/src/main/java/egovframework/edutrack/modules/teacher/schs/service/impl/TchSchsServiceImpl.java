package egovframework.edutrack.modules.teacher.schs.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("tchSchsService")
public class TchSchsServiceImpl
	extends EgovAbstractServiceImpl implements TchSchsService {

	/** Mapper */
	@Resource(name="tchSchsMapper")
	private TchSchsMapper 		tchSchsMapper;

	/**
	 * 강사의 학력 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchSchsVO> list(TchSchsVO tchSchsVO) throws Exception {
		ProcessResultListVO<TchSchsVO> resultList = new ProcessResultListVO<TchSchsVO>(); 
		try {
			List<TchSchsVO> returnList = tchSchsMapper.list(tchSchsVO);
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
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchSchsVO> listPageing(TchSchsVO tchSchsVO, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<TchSchsVO> resultList = new ProcessResultListVO<TchSchsVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		tchSchsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tchSchsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = tchSchsMapper.count(tchSchsVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<TchSchsVO> returnList = tchSchsMapper.listPageing(tchSchsVO);
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
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchSchsVO> listPageing(TchSchsVO tchSchsVO, int curPage, int listScale) throws Exception {

		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		tchSchsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tchSchsVO.setLastIndex(paginationInfo.getLastRecordIndex());		

		
		ProcessResultListVO<TchSchsVO> resultList = new ProcessResultListVO<TchSchsVO>(); 
		try {
			// 전체 목록 수
			int totalCount = tchSchsMapper.count(tchSchsVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<TchSchsVO> returnList = tchSchsMapper.listPageing(tchSchsVO);
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
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchSchsVO> listPageing(TchSchsVO tchSchsVO, int curPage) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		tchSchsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tchSchsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<TchSchsVO> resultList = new ProcessResultListVO<TchSchsVO>(); 
		try {
			// 전체 목록 수
			int totalCount = tchSchsMapper.count(tchSchsVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<TchSchsVO> returnList = tchSchsMapper.listPageing(tchSchsVO);
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
	 * 강사의 학력 단일 항목을 조회하여 반환한다.
	 * @param userNo
	 * @param schsSn
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchSchsVO> view(TchSchsVO tchSchsVO) throws Exception {
		
		ProcessResultVO<TchSchsVO> resultVO = new ProcessResultVO<TchSchsVO>(); 
		try {
			TchSchsVO returnVO =  tchSchsMapper.select(tchSchsVO);
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
	 * 강사의 학력 정보를 등록하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchSchsVO> add(TchSchsVO tchSchsVO) throws Exception{
		String entrgradDt = StringUtil.ReplaceAll(StringUtil.ReplaceAll(tchSchsVO.getEntrgradDt(), ".", ""),"-","");
		tchSchsVO.setEntrgradDt(entrgradDt);
		tchSchsVO.setSchsSn(tchSchsMapper.selectKey());

		tchSchsMapper.insert(tchSchsVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사의 학력 정보를 수정하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchSchsVO> edit(TchSchsVO tchSchsVO) throws Exception{
		String entrgradDt = StringUtil.ReplaceAll(StringUtil.ReplaceAll(tchSchsVO.getEntrgradDt(), ".", ""),"-","");
		tchSchsVO.setEntrgradDt(entrgradDt);

		tchSchsMapper.update(tchSchsVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사의 학력 정보를 삭제하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchSchsVO> remove(TchSchsVO tchSchsVO) throws Exception{
		tchSchsMapper.delete(tchSchsVO);
		return ProcessResultVO.success();
	}
}
