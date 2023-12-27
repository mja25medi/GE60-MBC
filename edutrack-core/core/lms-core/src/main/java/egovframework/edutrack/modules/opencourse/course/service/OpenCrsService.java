package egovframework.edutrack.modules.opencourse.course.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OpenCrsService {

	/**
	 * 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> list(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage) throws Exception;

	/**
	 * 공개강좌 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsVO> view(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsVO> add(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsVO> edit(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정보 삭제
	 *
	 * @return
	 */
	public abstract ProcessResultVO<?> remove(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정렬 순서 변경
	 * @param iCodeVO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(OpenCrsVO vo) throws Exception;

	/**
	 * 홈페이지 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listCourse(OpenCrsVO vo) throws Exception;

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage) throws Exception;

	/**
	 * 과목 사용중인 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsVO> listSubInfo(OpenCrsVO vo) throws Exception;
}