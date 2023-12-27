package egovframework.edutrack.modules.olc.sharerel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgVO;
import egovframework.edutrack.modules.olc.olccart.service.impl.OlcCartrgMapper;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelService;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("olcShareRelService")
public class OlcShareRelServiceImpl
	extends EgovAbstractServiceImpl implements OlcShareRelService{

	/** Mapper */
	@Resource(name="olcShareRelMapper")
	private OlcShareRelMapper 	olcShareRelMapper;
	
	@Resource(name="olcCartrgMapper")
	private OlcCartrgMapper 		olcCartrgMapper;
	

	/**
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listByCartrg(OlcShareRelVO VO) throws Exception {
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		try {
			List<OlcShareRelVO> returnList = olcShareRelMapper.listByCartrg(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(OlcShareRelVO VO, int curPage, int listScale, int pageScale) throws Exception {
		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCartrg(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(OlcShareRelVO VO, int curPage, int listScale) throws Exception {
		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCartrg(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(OlcShareRelVO VO, int curPage) throws Exception {
	
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCartrg(VO);
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
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listByCtgr(OlcShareRelVO VO) throws Exception {
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		try {
			List<OlcShareRelVO> returnList = olcShareRelMapper.listByCtgr(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(OlcShareRelVO VO, int curPage, int listScale, int pageScale) throws Exception {
		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.listPageingByCtgrCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCtgr(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(OlcShareRelVO VO, int curPage, int listScale) throws Exception {
		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.listPageingByCtgrCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCtgr(VO);
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
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(OlcShareRelVO VO, int curPage) throws Exception {
		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.listPageingByCtgrCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCtgr(VO);
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
	 * 공유 분류 관계 정보를 조회한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcShareRelVO> select(OlcShareRelVO VO) throws Exception {
		ProcessResultVO<OlcShareRelVO> resultVO = new ProcessResultVO<OlcShareRelVO>();
		try {
			OlcShareRelVO returnVO = olcShareRelMapper.select(VO);
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
	 * 공유 분류 관계 정보를 등록하고 결과를 반환한다.
	 * @param OlcShareRelVO
	 * @reurn ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcShareRelVO> insert(OlcShareRelVO VO) throws Exception  {
		
		ProcessResultVO<OlcShareRelVO> resultVO = new ProcessResultVO<OlcShareRelVO>();
		
		try {
			OlcCartrgVO ocVO = new OlcCartrgVO();
			ocVO.setOrgCd(VO.getOrgCd());
			ocVO.setUserNo(VO.getUserNo());
			ocVO.setCartrgCd(VO.getCartrgCd());
			ocVO = olcCartrgMapper.select(ocVO);

			//-- 분류 등록이기 때문에 cartrg의 공유 여부를 무조건 "Y"로 셋팅힌다.
			ocVO.setOpenYn("Y");
			olcCartrgMapper.update(ocVO);
			
			olcShareRelMapper.insert(VO);
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
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcShareRelVO> delete(OlcShareRelVO VO) throws Exception {
		// 분류 관계 삭제
		ProcessResultVO<OlcShareRelVO> resultVO = new ProcessResultVO<OlcShareRelVO>();

		try {
			olcShareRelMapper.delete(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
			
			// 카트리지에 속하는 분류 리스트를 검색함.
			List<OlcShareRelVO> shareRelList = this.listByCartrg(VO).getReturnList();

			// 카트리지 정보를 검색함.
			OlcCartrgVO ocVO = new OlcCartrgVO();
			ocVO.setOrgCd(VO.getOrgCd());
			ocVO.setUserNo(VO.getUserNo());
			ocVO.setCartrgCd(VO.getCartrgCd());
			ocVO = olcCartrgMapper.select(ocVO);

			if(shareRelList.size() > 0) {
				ocVO.setOpenYn("Y");
			} else {
				ocVO.setOpenYn("N");
			}
			olcCartrgMapper.update(ocVO);
			
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcShareRelVO> deleteCtgr(OlcShareRelVO VO) throws Exception {
		ProcessResultVO<OlcShareRelVO> resultVO = new ProcessResultVO<OlcShareRelVO>();
		try {
			olcShareRelMapper.deleteCtgr(VO);
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
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OlcShareRelVO> deleteCartrg(OlcShareRelVO VO) throws Exception {
		ProcessResultVO<OlcShareRelVO> resultVO = new ProcessResultVO<OlcShareRelVO>();
		try {
			olcShareRelMapper.deleteCartrg(VO);
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
	 * OLC 지식공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listByCtgrKnow(OlcShareRelVO VO) throws Exception {
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO> ();
		try {
			List<OlcShareRelVO> returnList = olcShareRelMapper.listByCtgrKnow(VO);
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
	 * OLC 지식공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<OlcShareRelVO> listPageingByCtgrKnow(OlcShareRelVO VO) throws Exception {

		
		ProcessResultListVO<OlcShareRelVO> resultList = new ProcessResultListVO<OlcShareRelVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(VO.getCurPage());
			paginationInfo.setRecordCountPerPage(VO.getListScale());
			paginationInfo.setPageSize(VO.getPageScale());
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcShareRelMapper.listPageingByCtgrCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);		
			
			List<OlcShareRelVO> returnList = olcShareRelMapper.listPageingByCtgrKnow(VO);
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
