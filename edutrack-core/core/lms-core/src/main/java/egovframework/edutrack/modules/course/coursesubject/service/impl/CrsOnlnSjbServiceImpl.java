package egovframework.edutrack.modules.course.coursesubject.service.impl;

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
import egovframework.edutrack.modules.board.bbs.service.impl.BrdBbsInfoMapper;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjService;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 과정 온라인 과목의 입.출력 처리를 하는 서비스 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 온라인 과목
 * </pre>
 *
 * @author MediopiaTech
 */
@Service("crsOnlnSbjService")
public class CrsOnlnSjbServiceImpl
	extends EgovAbstractServiceImpl implements CrsOnlnSbjService {

	/** Mapper */
	@Resource(name="crsOnlnSbjMapper")
	private CrsOnlnSbjMapper 		crsOnlnSbjMapper;

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<CrsOnlnSbjVO> list(CrsOnlnSbjVO VO) throws Exception {
		
		ProcessResultListVO<CrsOnlnSbjVO> resultList = new ProcessResultListVO<CrsOnlnSbjVO>();
		try {
			List<CrsOnlnSbjVO> returnList = crsOnlnSbjMapper.list(VO);
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
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<CrsOnlnSbjVO> listPageing(CrsOnlnSbjVO VO, int pageIndex, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<CrsOnlnSbjVO> resultList = new ProcessResultListVO<CrsOnlnSbjVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());		

			// 전체 목록 수
			int totalCount = crsOnlnSbjMapper.listPageingCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CrsOnlnSbjVO> returnList = crsOnlnSbjMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		

		
		
		return resultList;
	}

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsOnlnSbjVO> listPageing(CrsOnlnSbjVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CrsOnlnSbjVO> listPageing(CrsOnlnSbjVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 온라인 과목의 단일 레코드를 조회하여 반환한다.
	 * @param CrsOnlnSbjVO.atclSn
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOnlnSbjVO> view(CrsOnlnSbjVO VO) throws Exception {
		ProcessResultVO<CrsOnlnSbjVO> resultVO = new ProcessResultVO<CrsOnlnSbjVO>();
		try {
			CrsOnlnSbjVO returnVO = crsOnlnSbjMapper.select(VO);
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
	 * 과정 온라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOnlnSbjVO> add(CrsOnlnSbjVO VO) throws Exception {
		
		ProcessResultVO<CrsOnlnSbjVO> resultVO = new ProcessResultVO<CrsOnlnSbjVO>();
		try {
			crsOnlnSbjMapper.insert(VO);
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
	 * 과정 온라인 과목을 수정하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOnlnSbjVO> edit(CrsOnlnSbjVO VO) throws Exception {
		String studyMthd = VO.getStudyMthd();
		String modNo = VO.getModNo();
		
		ProcessResultVO<CrsOnlnSbjVO> resultVO = new ProcessResultVO<CrsOnlnSbjVO>();
		try {
			//-- 기존의 Online Sbj 정보를 가져온다.
			VO = crsOnlnSbjMapper.select(VO);
			VO.setStudyMthd(studyMthd); //-- 변경된 studyMthd로 셋팅
			VO.setModNo(modNo);
			
			crsOnlnSbjMapper.update(VO);
			
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
	 * 과정 온라인 과목의 순서를 변경하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(CrsOnlnSbjVO VO) throws Exception {

		String[] itemList = StringUtil.split(VO.getSbjCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<CrsOnlnSbjVO> itemArray = crsOnlnSbjMapper.list(VO);

		for (CrsOnlnSbjVO ctVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(ctVO.getSbjCd().equals(itemList[order])) {
					ctVO.setSbjOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		crsOnlnSbjMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 과정 온라인 과목을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CrsOnlnSbjVO VO) throws Exception {
		crsOnlnSbjMapper.delete(VO); // 분류 삭제
		return ProcessResultVO.success();
	}


	/**
	 * 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listSearch(CrsOnlnSbjVO VO) throws Exception {
		
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = crsOnlnSbjMapper.listSearch(VO);
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
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO, int pageIndex, int listScale, int pageScale) throws Exception {

		
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			VO.setLastIndex(paginationInfo.getLastRecordIndex());		
			
			// 전체 목록 수
			int totalCount = crsOnlnSbjMapper.listSearchPageingCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			List<OnlineSubjectVO> returnList = crsOnlnSbjMapper.listSearchPageing(VO);

			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		

		
		return resultList;
	}

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO, int curPage, int listScale) throws Exception {
		return this.listSearchPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO, int curPage) throws Exception {
		return this.listSearchPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 공개과정 온라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultVO<CrsOnlnSbjVO> openCourseAdd(CrsOnlnSbjVO VO) throws Exception {
		ProcessResultVO<CrsOnlnSbjVO> resultVO = new ProcessResultVO<CrsOnlnSbjVO>();
		try {
			crsOnlnSbjMapper.openCourseInsert(VO);
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
	 * 공개과정 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> openCourseListSearch(CrsOnlnSbjVO VO) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = crsOnlnSbjMapper.openCourseListSearch(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
}
