package egovframework.edutrack.modules.course.oflnsbjtch.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.oflnsbjtch.service.OflnSbjTchService;
import egovframework.edutrack.modules.course.oflnsbjtch.service.OflnSbjTchVO;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmVO;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.impl.OflnSbjTchTmMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("oflnSbjTchService")
public class OflnSbjTchServiceImpl
	extends EgovAbstractServiceImpl implements OflnSbjTchService {

	/** Mapper */
	@Resource(name="oflnSbjTchMapper")
	private OflnSbjTchMapper 	oflnSbjTchMapper;
	
	@Resource(name="oflnSbjTchTmMapper")
	private OflnSbjTchTmMapper 	oflnSbjTchTmMapper;
	
	/**
	 * 오프라인 과목 강사의 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchVO> list(OflnSbjTchVO VO) throws Exception {
		ProcessResultListVO<OflnSbjTchVO> resultList = new ProcessResultListVO<OflnSbjTchVO>();
		try {
			List<OflnSbjTchVO> returnList = oflnSbjTchMapper.list(VO);
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
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchVO> listPageing(OflnSbjTchVO VO, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OflnSbjTchVO> resultList = new ProcessResultListVO<OflnSbjTchVO>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = oflnSbjTchMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OflnSbjTchVO> returnList = oflnSbjTchMapper.listPageing(VO);
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
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchVO> listPageing(OflnSbjTchVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultListVO<OflnSbjTchVO> listPageing(OflnSbjTchVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 오프라인 과목 강사의 단일 레코드를 조회하여 반환한다.
	 * @param OflnSbjTchVO.rateSn
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchVO> view(OflnSbjTchVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchVO> resultVO = new ProcessResultVO<OflnSbjTchVO>();
		try {
			OflnSbjTchVO returnVO = oflnSbjTchMapper.select(VO);
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
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchVO> add(OflnSbjTchVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchVO> resultVO = new ProcessResultVO<OflnSbjTchVO>();
		try {
			oflnSbjTchMapper.insert(VO);
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
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchVO> edit(OflnSbjTchVO VO) throws Exception {
		ProcessResultVO<OflnSbjTchVO> resultVO = new ProcessResultVO<OflnSbjTchVO>();
		try {
			oflnSbjTchMapper.update(VO);
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
	 * 강사의 강의 시간표 전체 삭제함.
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	@Override
	public ProcessResultVO<OflnSbjTchVO> remove(OflnSbjTchVO VO) throws Exception {
		
		
		ProcessResultVO<OflnSbjTchVO> resultVO = new ProcessResultVO<OflnSbjTchVO>();
		try {
			//-- 강사의 강의시간 삭제
			OflnSbjTchTmVO osttVO = new OflnSbjTchTmVO();
			osttVO.setCrsCreCd(VO.getCrsCreCd());
			osttVO.setSbjCd(VO.getSbjCd());
			osttVO.setUserNo(VO.getUserNo());
			osttVO.setDeclsNo(VO.getDeclsNo());
			oflnSbjTchTmMapper.deleteAll(osttVO);
			oflnSbjTchMapper.delete(VO);
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
