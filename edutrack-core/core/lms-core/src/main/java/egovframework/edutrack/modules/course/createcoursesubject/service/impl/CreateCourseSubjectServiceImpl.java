package egovframework.edutrack.modules.course.createcoursesubject.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.contents.service.impl.ContentsMapper;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOfflineSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("createCourseSubjectService")
public class CreateCourseSubjectServiceImpl extends EgovAbstractServiceImpl implements  CreateCourseSubjectService {

	/** Mapper */
	@Resource(name="createCourseOfflineSubjectMapper")
	private CreateCourseOfflineSubjectMapper createCourseOfflineSubjectMapper;

	@Resource(name="createCourseOnlineSubjectMapper")
	private CreateCourseOnlineSubjectMapper createCourseOnlineSubjectMapper;

	@Resource(name="createCourseSubjectMapper")
	private CreateCourseSubjectMapper createCourseSubjectMapper;

	@Resource(name="contentsMapper")
	private ContentsMapper contentsMapper;

	/**
	 * 개설 과정 오프라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@Override
	public	ProcessResultListVO<CreateOfflineSubjectVO> listOfflineSubject(CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultListVO<CreateOfflineSubjectVO> resultList = new ProcessResultListVO<CreateOfflineSubjectVO>();
		try {
			List<CreateOfflineSubjectVO> returnList = createCourseOfflineSubjectMapper.list(iOfflineSubjectVO);
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
	 * 개설 과정 오프라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOfflineSubjectVO> viewOfflineSubject(CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception {
		
		ProcessResultVO<CreateOfflineSubjectVO> resultVO = new ProcessResultVO<CreateOfflineSubjectVO>();
		try {
			CreateOfflineSubjectVO returnVO = createCourseOfflineSubjectMapper.select(iOfflineSubjectVO);
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
	 * 개설 과정 오프라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOfflineSubjectVO> addOfflineSubject(CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultVO<CreateOfflineSubjectVO> resultVO = new ProcessResultVO<CreateOfflineSubjectVO>();
		try {
			createCourseOfflineSubjectMapper.insert(iOfflineSubjectVO);
			resultVO.setReturnVO(iOfflineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 개설 과정 오프라인 과목 수정
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOfflineSubjectVO> editOfflineSubject(CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception {
		String eduMthdCd  = iOfflineSubjectVO.getEduMthdCd();
		String modNo = iOfflineSubjectVO.getModNo();
		
		ProcessResultVO<CreateOfflineSubjectVO> resultVO = new ProcessResultVO<CreateOfflineSubjectVO>();
		try {
			iOfflineSubjectVO = createCourseOfflineSubjectMapper.select(iOfflineSubjectVO);
			iOfflineSubjectVO.setEduMthdCd(eduMthdCd);
			iOfflineSubjectVO.setModNo(modNo);
			createCourseOfflineSubjectMapper.update(iOfflineSubjectVO);
			resultVO.setReturnVO(iOfflineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		return resultVO;
	}

	/**
	 * 개설 과정 오프라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOfflineSubjectVO> deleteOfflineSubject(CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception {

		//오프라인 과목 삭제
		createCourseOfflineSubjectMapper.delete(iOfflineSubjectVO);
		return ProcessResultVO.success();
	}

	/**
	 * 개설 과정 오프라인 과목 순서 변경
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortOfflineSubject(CreateOfflineSubjectVO iCreateOfflineSubjectVO)  throws Exception {

		String[] subjectList = StringUtil.split(iCreateOfflineSubjectVO.getSbjCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<CreateOfflineSubjectVO> subjectArray = createCourseOfflineSubjectMapper.list(iCreateOfflineSubjectVO);

		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (CreateOfflineSubjectVO subjectVO : subjectArray) {
			for (int order = 0; order < subjectList.length; order++) {
				if(subjectVO.getSbjCd().equals(subjectList[order])) {
					subjectVO.setSbjOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		createCourseOfflineSubjectMapper.updateBatch(subjectArray);
		return ProcessResultVO.success();
	}


	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearch(CreateOfflineSubjectVO VO)  throws Exception {
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		try {
			List<OfflineSubjectVO> returnList = createCourseOfflineSubjectMapper.listSearch(VO);
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
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(CreateOfflineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = createCourseOfflineSubjectMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OfflineSubjectVO> returnList = createCourseOfflineSubjectMapper.listSearchPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(CreateOfflineSubjectVO VO, int curPage, int listScale) throws Exception {
		return this.listOfflineSearchPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(CreateOfflineSubjectVO VO, int curPage) throws Exception {
		return this.listOfflineSearchPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}






	/**
	 * 개설 과정 온라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@Override
	public  ProcessResultListVO<CreateOnlineSubjectVO> listOnlineSubject(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultListVO<CreateOnlineSubjectVO> resultList = new ProcessResultListVO<CreateOnlineSubjectVO>();
		try {
			List<CreateOnlineSubjectVO> returnList = createCourseOnlineSubjectMapper.list(iOnlineSubjectVO);
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
	 * 개설 과정 온라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CreateOnlineSubjectVO> viewOnlineSubject(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {
		
		ProcessResultVO<CreateOnlineSubjectVO> resultVO = new ProcessResultVO<CreateOnlineSubjectVO>();
		try {
			CreateOnlineSubjectVO returnVO = createCourseOnlineSubjectMapper.select(iOnlineSubjectVO);
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
	 * 개설 과정 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOnlineSubjectVO> addOnlineSubject(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultVO<CreateOnlineSubjectVO> resultVO = new ProcessResultVO<CreateOnlineSubjectVO>();
		
		try {
			createCourseOnlineSubjectMapper.insert(iOnlineSubjectVO);
			
			ContentsVO cntVO = new ContentsVO();
			cntVO.setSbjCd(iOnlineSubjectVO.getSbjCd());
			cntVO.setCrsCreCd(iOnlineSubjectVO.getCrsCreCd());
			cntVO.setRegNo(iOnlineSubjectVO.getRegNo());
			cntVO.setModNo(iOnlineSubjectVO.getModNo());
			contentsMapper.insertCopy(cntVO);
			
			resultVO.setReturnVO(iOnlineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	/**
	 * 개설 과정 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public	ProcessResultVO<CreateOnlineSubjectVO> editOnlineSubject(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {
		String studyMthd = iOnlineSubjectVO.getStudyMthd();
		String modNo = iOnlineSubjectVO.getModNo();
		
		ProcessResultVO<CreateOnlineSubjectVO> resultVO = new ProcessResultVO<CreateOnlineSubjectVO>();
		try {
			iOnlineSubjectVO = createCourseOnlineSubjectMapper.select(iOnlineSubjectVO);
			iOnlineSubjectVO.setStudyMthd(studyMthd);
			iOnlineSubjectVO.setModNo(modNo);
			createCourseOnlineSubjectMapper.update(iOnlineSubjectVO);
			resultVO.setReturnVO(iOnlineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		
		return resultVO;
	}

	/**
	 * 회차 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	@Override
	public 	ProcessResultVO<CreateOnlineSubjectVO> deleteOnlineSubject(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {

		//온라인 과목 삭제
		createCourseOnlineSubjectMapper.delete(iOnlineSubjectVO);
		
		
		return ProcessResultVO.success();
	}

	/**
	 * 개설 과정 온라인 과목 순서 변경
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortOnlineSubject(CreateOnlineSubjectVO iCreateOnlineSubjectVO) throws Exception {

		String[] subjectList = StringUtil.split(iCreateOnlineSubjectVO.getSbjCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<CreateOnlineSubjectVO> subjectArray = createCourseOnlineSubjectMapper.list(iCreateOnlineSubjectVO);

		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (CreateOnlineSubjectVO subjectVO : subjectArray) {
			for (int order = 0; order < subjectList.length; order++) {
				if(subjectVO.getSbjCd().equals(subjectList[order])) {
					subjectVO.setSbjOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		createCourseOnlineSubjectMapper.updateBatch(subjectArray);
		return ProcessResultVO.success();
	}

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearch(CreateOnlineSubjectVO VO) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = createCourseOnlineSubjectMapper.listSearch(VO);
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
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(CreateOnlineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		try {
			// 전체 목록 수
			int totalCount = createCourseOnlineSubjectMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OnlineSubjectVO> returnList = createCourseOnlineSubjectMapper.listSearchPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
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
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(CreateOnlineSubjectVO VO, int curPage, int listScale) throws Exception {
		return this.listOnlineSearchPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
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
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(CreateOnlineSubjectVO VO, int curPage) throws Exception {
		return this.listOnlineSearchPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 개설 과정 과목 목록 조회 (on + off)
	 *
	 * @return ProcessReslutListVO
	 */
	@Override
	public	ProcessResultListVO<CreateCourseSubjectVO> listSubject(CreateCourseSubjectVO iVO) throws Exception {
		ProcessResultListVO<CreateCourseSubjectVO> resultList = new ProcessResultListVO<CreateCourseSubjectVO>();
		try {
			List<CreateCourseSubjectVO> returnList = createCourseSubjectMapper.list(iVO);
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
	 * 2015.11.06 김현욱 추가
	 * 개설 과정 온라인 과목 정보 조회(TB_CRS_CRS_ONLN_SBJ)
	 *
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CreateOnlineSubjectVO> viewOnlineSubjectMaster(CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception {
		
		ProcessResultVO<CreateOnlineSubjectVO> resultVO = new ProcessResultVO<CreateOnlineSubjectVO>();
		try {
			CreateOnlineSubjectVO returnVO = createCourseOnlineSubjectMapper.selectMaster(iOnlineSubjectVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
}
