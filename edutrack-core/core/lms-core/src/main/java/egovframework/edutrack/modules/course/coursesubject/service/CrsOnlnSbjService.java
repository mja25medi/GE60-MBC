package egovframework.edutrack.modules.course.coursesubject.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;


public interface CrsOnlnSbjService {

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultListVO<CrsOnlnSbjVO> list(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultListVO<CrsOnlnSbjVO> listPageing(
			CrsOnlnSbjVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsOnlnSbjVO> listPageing(
			CrsOnlnSbjVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 과정 온라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsOnlnSbjVO> listPageing(
			CrsOnlnSbjVO VO, int curPage) throws Exception;

	/**
	 * 과정 온라인 과목의 단일 레코드를 조회하여 반환한다.
	 * @param CrsOnlnSbjVO.atclSn
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOnlnSbjVO> view(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 과정 온라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOnlnSbjVO> add(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 과정 온라인 과목을 수정하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOnlnSbjVO> edit(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 과정 온라인 과목의 순서를 변경하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 과정 온라인 과목을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> listSearch(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OnlineSubjectVO> listSearchPageing(CrsOnlnSbjVO VO, int curPage) throws Exception;

	/**
	 * 공개과정 과정 온라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultVO<CrsOnlnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOnlnSbjVO> openCourseAdd(CrsOnlnSbjVO VO) throws Exception;

	/**
	 * 공개과정 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OnlineSubjectVO> openCourseListSearch(CrsOnlnSbjVO VO) throws Exception;
}