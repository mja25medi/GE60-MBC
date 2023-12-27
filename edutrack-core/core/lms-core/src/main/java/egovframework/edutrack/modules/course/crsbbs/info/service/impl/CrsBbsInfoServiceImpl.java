package egovframework.edutrack.modules.course.crsbbs.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoService;
import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("crsBbsInfoService")
public class CrsBbsInfoServiceImpl 
	extends EgovAbstractServiceImpl implements CrsBbsInfoService {
	
	/** Mapper */
	@Resource(name="crsBbsInfoMapper")
	private CrsBbsInfoMapper		crsBbsInfoMapper;

	/**
	 * 과정 게시판 정보의 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsInfoVO> list(CrsBbsInfoVO VO)  throws Exception {
		ProcessResultListVO<CrsBbsInfoVO> resultList = new ProcessResultListVO<CrsBbsInfoVO>();
		try {
			List<CrsBbsInfoVO> returnList =  crsBbsInfoMapper.list(VO);
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
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsInfoVO> listPageing(CrsBbsInfoVO VO, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<CrsBbsInfoVO> resultList = new ProcessResultListVO<CrsBbsInfoVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());	
		
		// 전체 목록 수
		int totalCount = crsBbsInfoMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsBbsInfoMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		return resultList;
	}
	
	/**
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsInfoVO> listPageing(CrsBbsInfoVO VO, int curPage, int listScale) throws Exception {

		ProcessResultListVO<CrsBbsInfoVO> resultList = new ProcessResultListVO<CrsBbsInfoVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());			
		
		// 전체 목록 수
		int totalCount = crsBbsInfoMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsBbsInfoMapper.listPageing(VO));
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsInfoVO> listPageing(CrsBbsInfoVO VO, int curPage) throws Exception {

		ProcessResultListVO<CrsBbsInfoVO> resultList = new ProcessResultListVO<CrsBbsInfoVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());			
		
		// 전체 목록 수
		int totalCount = crsBbsInfoMapper.count(VO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(crsBbsInfoMapper.listPageing(VO)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 과정 게시판의 정보를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<CrsBbsInfoVO> select(CrsBbsInfoVO VO) throws Exception {
		ProcessResultVO<CrsBbsInfoVO> resultVO = new ProcessResultVO<CrsBbsInfoVO>();
		try {
			CrsBbsInfoVO returnVO = crsBbsInfoMapper.select(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 과정 게시판의 정보를 등록하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<CrsBbsInfoVO> insert(CrsBbsInfoVO VO) throws Exception {
		ProcessResultVO<CrsBbsInfoVO> resultVO = new ProcessResultVO<CrsBbsInfoVO>();
		try {
			crsBbsInfoMapper.insert(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 과정 게시판의 정보를 수정하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<CrsBbsInfoVO> update(CrsBbsInfoVO VO) throws Exception {
		ProcessResultVO<CrsBbsInfoVO> resultVO = new ProcessResultVO<CrsBbsInfoVO>();
		try {
			crsBbsInfoMapper.update(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 과정 게시판의 정보를 삭제하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<CrsBbsInfoVO> remove(CrsBbsInfoVO VO) throws Exception {
		ProcessResultVO<CrsBbsInfoVO> resultVO = new ProcessResultVO<CrsBbsInfoVO>();
		try {
			crsBbsInfoMapper.delete(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
		
	}
}
