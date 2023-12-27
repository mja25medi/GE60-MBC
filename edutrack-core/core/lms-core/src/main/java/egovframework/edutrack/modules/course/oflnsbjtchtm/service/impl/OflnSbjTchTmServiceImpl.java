package egovframework.edutrack.modules.course.oflnsbjtchtm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmService;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("oflnSbjTchTmService")
public class OflnSbjTchTmServiceImpl 
	extends EgovAbstractServiceImpl implements OflnSbjTchTmService{

	/** Mapper */
	@Resource(name="oflnSbjTchTmMapper")
	private OflnSbjTchTmMapper 	oflnSbjTchTmMapper;
	
	/**
	 * 오프라인 과목 강사의 목록을 조회하여 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return ProcessResultListVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchTmVO> list(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultListVO<OflnSbjTchTmVO> resultList = new ProcessResultListVO<OflnSbjTchTmVO>();
		try {
			List<OflnSbjTchTmVO> returnList = oflnSbjTchTmMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchTmVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchTmVO> listPageing(OflnSbjTchTmVO VO, int curPage, int listScale, int pageScale) throws Exception {
		
		ProcessResultListVO<OflnSbjTchTmVO> resultList = new ProcessResultListVO<OflnSbjTchTmVO>();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = oflnSbjTchTmMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OflnSbjTchTmVO> returnList = oflnSbjTchTmMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchTmVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @return ProcessResultListVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchTmVO> listPageing(OflnSbjTchTmVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchTmVO
	 * @param curPage 현재 페이지
	 * @return ProcessResultListVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchTmVO> listPageing(OflnSbjTchTmVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 오프라인 과목 강사의 단일 레코드를 조회하여 반환한다.
	 * @param OflnSbjTchTmVO.rateSn
	 * @return ProcessResultVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchTmVO> view(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchTmVO> resultVO = new ProcessResultVO<OflnSbjTchTmVO>();
		try {
			OflnSbjTchTmVO returnVO = oflnSbjTchTmMapper.select(VO);
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
	 * 오프라인 과목 강사를 등록하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return ProcessResultVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchTmVO> add(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchTmVO> resultVO = new ProcessResultVO<OflnSbjTchTmVO>();
		try {
			oflnSbjTchTmMapper.insert(VO);
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
	 * 오프라인 과목 강사를 수정하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return ProcessResultVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchTmVO> edit(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchTmVO> resultVO = new ProcessResultVO<OflnSbjTchTmVO>();
		try {
			oflnSbjTchTmMapper.update(VO);
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
	 * 오프라인 과목 강사를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return ProcessResultVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchTmVO> remove(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchTmVO> resultVO = new ProcessResultVO<OflnSbjTchTmVO>();
		try {
			oflnSbjTchTmMapper.delete(VO);
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
	 * 오프라인 과목 강사를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return ProcessResultVO<OflnSbjTchTmVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchTmVO> removeAll(OflnSbjTchTmVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchTmVO> resultVO = new ProcessResultVO<OflnSbjTchTmVO>();
		try {
			oflnSbjTchTmMapper.deleteAll(VO);
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
