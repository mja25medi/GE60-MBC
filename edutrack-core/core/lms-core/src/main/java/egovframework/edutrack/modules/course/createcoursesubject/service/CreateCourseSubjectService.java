package egovframework.edutrack.modules.course.createcoursesubject.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;


public interface CreateCourseSubjectService {

	/**
	 * 개설 과정 오프라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<CreateOfflineSubjectVO> listOfflineSubject(
			CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 개설 과정 오프라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOfflineSubjectVO> viewOfflineSubject(
			CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 개설 과정 오프라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOfflineSubjectVO> addOfflineSubject(
			CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 개설 과정 오프라인 과목 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOfflineSubjectVO> editOfflineSubject(
			CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 개설 과정 오프라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOfflineSubjectVO> deleteOfflineSubject(
			CreateOfflineSubjectVO iOfflineSubjectVO) throws Exception;

	/**
	 * 개설 과정 오프라인 과목 순서 변경
	 * @return
	 */
	public abstract ProcessResultVO<?> sortOfflineSubject(
			CreateOfflineSubjectVO iCreateOfflineSubjectVO) throws Exception;


	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearch(
			CreateOfflineSubjectVO VO) throws Exception;

	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(
			CreateOfflineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(
			CreateOfflineSubjectVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 오프라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearchPageing(
			CreateOfflineSubjectVO VO, int curPage) throws Exception;



	/**
	 * 개설 과정 온라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<CreateOnlineSubjectVO> listOnlineSubject(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 개설 과정 온라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOnlineSubjectVO> viewOnlineSubject(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 개설 과정 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOnlineSubjectVO> addOnlineSubject(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 개설 과정 온라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOnlineSubjectVO> editOnlineSubject(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 개설 과정 온라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOnlineSubjectVO> deleteOnlineSubject(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;

	/**
	 * 개설 과정 온라인 과목 순서 변경
	 * @return
	 */
	public abstract ProcessResultVO<?> sortOnlineSubject(
			CreateOnlineSubjectVO iCreateOnlineSubjectVO) throws Exception;


	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearch(CreateOnlineSubjectVO VO) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(
			CreateOnlineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(
			CreateOnlineSubjectVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchPageing(
			CreateOnlineSubjectVO VO, int curPage) throws Exception;

	/**
	 * 개설 과정 과목 목록 조회 (on + off)
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<CreateCourseSubjectVO> listSubject(
			CreateCourseSubjectVO iVO) throws Exception;

	/**
	 * 2015.11.06 김현욱 추가
	 * 개설 과정 온라인 과목 정보 조회(TB_CRS_CRS_ONLN_SBJ)
	 *
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<CreateOnlineSubjectVO> viewOnlineSubjectMaster(
			CreateOnlineSubjectVO iOnlineSubjectVO) throws Exception;
}