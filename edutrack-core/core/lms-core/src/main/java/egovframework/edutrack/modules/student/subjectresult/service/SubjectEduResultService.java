package egovframework.edutrack.modules.student.subjectresult.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface SubjectEduResultService {

	/**
	 * 수강 결과 목록 조회(전체)
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<SubjectEduResultVO> list(
			SubjectEduResultVO dto) throws Exception;

	/**
	 * 수강 결과 목록 조회(페이징)
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<SubjectEduResultVO> list(
			SubjectEduResultVO dto, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 수강 결과 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<SubjectEduResultVO> view(
			SubjectEduResultVO dto) throws Exception;

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<SubjectEduResultVO> regist(
			SubjectEduResultVO dto) throws Exception;

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<SubjectEduResultVO> regist(
			List<SubjectEduResultVO> itemList) throws Exception;

}