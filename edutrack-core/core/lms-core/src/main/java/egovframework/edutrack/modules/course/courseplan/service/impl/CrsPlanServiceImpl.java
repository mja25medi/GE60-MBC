package egovframework.edutrack.modules.course.courseplan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanService;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 년간 과정 계획의 입.출력 처리를 하는 서비스 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 연간 과정 계획
 * </pre>
 *
 * @author MediopiaTech
 */
@Service("crsPlanService")
public class CrsPlanServiceImpl
extends EgovAbstractServiceImpl implements CrsPlanService {

	/** Mapper */
	@Resource(name="crsPlanMapper")
	private CrsPlanMapper 		crsPlanMapper;


	/**
	 * 년간 과정 계획의 단일 레코드를 조회하여 반환한다.
	 * @param CrsPlanVO.atclSn
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	@Override
	public ProcessResultVO<CrsPlanVO> view(CrsPlanVO VO) throws Exception {
		ProcessResultVO<CrsPlanVO> resultVO = new ProcessResultVO<CrsPlanVO>();
		try {
			resultVO.setReturnVO(crsPlanMapper.select(VO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 년간 과정 계획를 등록하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	@Override
	public ProcessResultVO<CrsPlanVO> add(CrsPlanVO VO) throws Exception  {
		ProcessResultVO<CrsPlanVO> resultVO = new ProcessResultVO<CrsPlanVO>();
		try {
			crsPlanMapper.insert(VO);
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
	 * 년간 과정 계획를 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	@Override
	public ProcessResultVO<CrsPlanVO> edit(CrsPlanVO VO) throws Exception  {
		ProcessResultVO<CrsPlanVO> resultVO = new ProcessResultVO<CrsPlanVO>();
		try {
			crsPlanMapper.update(VO);
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
	 * 년간 과정 계획를 등록 또는 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	@Override
	public ProcessResultVO<CrsPlanVO> marge(CrsPlanVO VO) throws Exception  {
		ProcessResultVO<CrsPlanVO> resultVO = new ProcessResultVO<CrsPlanVO>();
		try {
			crsPlanMapper.marge(VO);
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
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> list(CourseVO VO) throws Exception  {
		VO.setCrsCtgrArr(StringUtil.split(VO.getCrsCtgrCd(), "/"));
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = crsPlanMapper.list(VO);
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
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int pageIndex, int listScale, int pageScale) throws Exception  {
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		VO.setCrsCtgrArr(StringUtil.split(VO.getCrsCtgrCd(), "/"));
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			// 전체 목록 수
			int totalCount = crsPlanMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CourseVO> returnList = crsPlanMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int curPage, int listScale) throws Exception  {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int curPage) throws Exception  {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 년간 과정 계획를 등록 또는 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	@Override
	public ProcessResultVO<CrsPlanVO> margeAll(List<CrsPlanVO> addCrsPlanList, CrsPlanVO crsPlanVO) throws Exception  {
		ProcessResultVO<CrsPlanVO> VO = new ProcessResultVO<CrsPlanVO>();
		for(CrsPlanVO listVO : addCrsPlanList){
			if(StringUtil.isNull(listVO.getCrsCd())){
				break;
			}
			listVO.setRegNo(crsPlanVO.getRegNo());
			listVO.setModNo(crsPlanVO.getModNo());
			crsPlanMapper.marge(listVO);
		}
		return VO;
	}
}
