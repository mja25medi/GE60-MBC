package egovframework.edutrack.modules.course.coursesubject.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;


public interface CrsOflnSbjService {

	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultListVO<CrsOflnSbjVO>
	 */
	public abstract ProcessResultListVO<CrsOflnSbjVO> list(CrsOflnSbjVO VO) throws Exception;

	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOflnSbjVO>
	 */
	public abstract ProcessResultListVO<CrsOflnSbjVO> listPageing(
			CrsOflnSbjVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsOflnSbjVO> listPageing(
			CrsOflnSbjVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 과정 오프라인 과목의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOflnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsOflnSbjVO> listPageing(
			CrsOflnSbjVO VO, int curPage) throws Exception;

	/**
	 * 과정 오프라인 과목의 단일 레코드를 조회하여 반환한다.
	 * @param CrsOflnSbjVO.atclSn
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOflnSbjVO> view(CrsOflnSbjVO VO) throws Exception;

	/**
	 * 과정 오프라인 과목을 등록하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOflnSbjVO> add(CrsOflnSbjVO VO) throws Exception;

	/**
	 * 과정 오프라인 과목을 수정하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return ProcessResultVO<CrsOflnSbjVO>
	 */
	public abstract ProcessResultVO<CrsOflnSbjVO> edit(CrsOflnSbjVO VO) throws Exception;

	/**
	 * 과정 오프라인 과목의 순서를 변경하고 결과를 반환한다.
	 * @param CrsOflnSbjVO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(CrsOflnSbjVO VO) throws Exception;

	/**
	 * 과정 오프라인 과목을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CrsOflnSbjVO VO) throws Exception;
	
	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * @param CrsOnlnSbjVO
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OfflineSubjectVO> listSearch(CrsOflnSbjVO VO) throws Exception;
	
	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsOnlnSbjVO>
	 */
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int curPage, int listScale) throws Exception;
	
	/**
	 * 온라인 과목의 목록을 검색하여 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsOnlnSbjVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<OfflineSubjectVO> listSearchPageing(CrsOflnSbjVO VO, int curPage) throws Exception;

}