package egovframework.edutrack.modules.opencourse.ctgr.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OpenCrsCtgrService {

	/**
	 * 공개강좌 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<OpenCrsCtgrVO> list(OpenCrsCtgrVO vo) throws Exception;

	/**
	 * 공개강좌 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsCtgrVO> view(OpenCrsCtgrVO vo) throws Exception;

	/**
	 * 공개강좌 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsCtgrVO> add(OpenCrsCtgrVO vo) throws Exception;

	/**
	 * 공개강좌 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OpenCrsCtgrVO> edit(OpenCrsCtgrVO vo) throws Exception;

	/**
	 * 공개강좌 분류 정보 삭제
	 *
	 * @return
	 */
	public abstract ProcessResultVO<?> remove(OpenCrsCtgrVO vo) throws Exception;

	/**
	 * 공개강좌 분류 정렬 순서 변경
	 * @param iCodeVO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(OpenCrsCtgrVO vo) throws Exception;

}