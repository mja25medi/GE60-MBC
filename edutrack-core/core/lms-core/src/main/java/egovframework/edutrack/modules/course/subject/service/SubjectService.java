package egovframework.edutrack.modules.course.subject.service;

import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj;
import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj.SyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface SubjectService {

	/**
	 * 과목 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<SubjectCategoryVO> listCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;

	
	/**
	 * 과목 분류 목록 조회 (하위 과목 분류 있는 목록만)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<SubjectCategoryVO> listCategorySort(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 하위 과목 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<SubjectCategoryVO> listCategorySub(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 과목 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<SubjectCategoryVO> viewCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 과목 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<SubjectCategoryVO> addCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 과목 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<SubjectCategoryVO> editCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 과목 분류 삭제
	 * @param crsCtgrCd 삭제 대상 코드값
	 * @return
	 */
	public ProcessResultVO<?> deleteCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;
	
	/**
	 * 과목 분류 정렬 순서 변경
	 * @param iSubjectCategoryVO
	 * @return
	 */
	public ProcessResultVO<?> sortCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;	
	
	/**
	 * 오프라인 과목 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception;
	
	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OfflineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(
			OfflineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(
			OfflineSubjectVO VO, int curPage, int listScale) throws Exception;
	
	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(
			OfflineSubjectVO VO, int curPage) throws Exception;

	/**
	 * 오프라인 과목 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<OfflineSubjectVO> viewOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 오프라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<OfflineSubjectVO> addOffline(
			OfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 오프라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<OfflineSubjectVO> editOffline(
			OfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 오프라인 과목 삭제
	 * 
	 * @return ProcessResultVO
	 */
	public ProcessResultVO<?> deleteOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 온라인 과목 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(
			OnlineSubjectVO VO, int curPage, int listScale, int pageScale, boolean filein) throws Exception;
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(
			OnlineSubjectVO VO, int curPage, int listScale) throws Exception;
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(
			OnlineSubjectVO VO, int curPage) throws Exception;
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(
			OnlineSubjectVO VO, int curPage, int listScale, boolean filein) throws Exception;
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(
			OnlineSubjectVO VO, int curPage, boolean filein) throws Exception;
	
	/**
	 * 온라인 과목 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<OnlineSubjectVO> viewOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 온라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.CREATE)
	public ProcessResultVO<OnlineSubjectVO> addOnline(
			OnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 온라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.UPDATE)
	public ProcessResultVO<OnlineSubjectVO> editOnline(
			OnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 온라인 과목 삭제
	 * 
	 * @return ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.DELETE)
	public ProcessResultVO<?> deleteOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception;
	
	/**
	 * 온라인 과목 목록 조회 (개설 과정 서치용)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearch(OnlineSubjectVO iOnlineSubjectVO) throws Exception;
	
	/**
	 * 오프라인 과목 목록 조회 (개설 과정 서치용)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearch(OfflineSubjectVO iOfflineSubjectVO) throws Exception;
	
	/**
	 * 온라인 과목 목록 조회 (공개 과정 용)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchOpen(OnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public ProcessResultVO<OfflineSubjectVO> transactionRollbackMethod(
			OfflineSubjectVO iOfflineSubjectVO) throws Exception;

	public ProcessResultListVO<SubjectCategoryVO> listOnOffCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception;


	/**
	 * 과목 분류 목록 조회 - 수강신청
	 * @param iSubjectCategoryVO
	 * @return
	 */
	ProcessResultListVO<SubjectCategoryVO> listCategoryForEnroll(SubjectCategoryVO iSubjectCategoryVO);
	
	/**
	 * 강의실 등록
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.CREATE)
	public ProcessResultVO<LecRoomVO> addLecRoom(LecRoomVO lecRoomVO) throws Exception;	
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param LecRoomVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(
			LecRoomVO VO, int curPage, int listScale, int pageScale, boolean filein) throws Exception;
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 */
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(
			LecRoomVO VO, int curPage, int listScale) throws Exception;
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 */
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(
			LecRoomVO VO, int curPage) throws Exception;
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 */
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(
			LecRoomVO VO, int curPage, int listScale, boolean filein) throws Exception;
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 */
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(
			LecRoomVO VO, int curPage, boolean filein) throws Exception;
	
	/**
	 * 강의실 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<LecRoomVO> viewLecRoom(LecRoomVO lecRoomVO) throws Exception;
	
	/**
	 * 강의실 수정
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.UPDATE)
	public ProcessResultVO<LecRoomVO> editLecRoom(LecRoomVO lecRoomVO) throws Exception;
	
	/**
	 * 강의실 삭제
	 * 
	 * @return ProcessResultVO
	 */
	@HrdApiCrsOnlnSbj(SyncType.DELETE)
	public ProcessResultVO<?> deleteLecRoom(LecRoomVO lecRoomVO) throws Exception;

	 
}