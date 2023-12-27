package egovframework.edutrack.modules.opencourse.subject.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OpenCrsSbjService {

	/**
	 * 공개강좌 연결과목 목록 조회
	 *
	 * @return ProcessResultListDTO
	 */
	public abstract ProcessResultListVO<OpenCrsSbjVO> list(OpenCrsSbjVO dto) throws Exception;

	/**
	 * 공개강좌 연결과목 정보 조회
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract ProcessResultVO<OpenCrsSbjVO> view(OpenCrsSbjVO dto) throws Exception;

	/**
	 * 공개강좌 연결과목 정보 등록
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract ProcessResultVO<OpenCrsSbjVO> add(OpenCrsSbjVO dto) throws Exception;

	/**
	 * 공개강좌 연결과목 정보 수정
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract ProcessResultVO<OpenCrsSbjVO> edit(OpenCrsSbjVO dto) throws Exception;

	/**
	 * 공개강좌 연결과목 정보 삭제
	 *
	 * @return
	 */
	public abstract ProcessResultVO<?> remove(OpenCrsSbjVO dto) throws Exception;

	/**
	 * 공개강좌 연결과목 정렬 순서 변경
	 * @param iCodeDTO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(OpenCrsSbjVO dto) throws Exception;

}