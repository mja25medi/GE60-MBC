package egovframework.edutrack.modules.course.assignmentbank.service;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



public interface AssignmentQbankService {

	/**
	 * 과제 문제은행 문제 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract	ProcessResultListVO<AssignmentQbankQuestionVO> listQuestion(String sbjCd,
			String ctgrCd) throws Exception;

	/**
	 * 과제 문제은행 문제 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankQuestionVO> viewQuestion(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception;

	/**
	 * 과제 문제은행 문제 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankQuestionVO> addQuestion(
			AssignmentQbankQuestionVO iAssignmentQbankQuestionVO,
			List<Integer> txAttachFiles, List<Integer> txAttachImages) throws Exception;

	/**
	 * 과제 문제은행 문제 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankQuestionVO> editQuestion(
			AssignmentQbankQuestionVO iAssignmentQbankQuestionVO,
			List<Integer> txAttachFiles, List<Integer> txAttachImages) throws Exception;

	/**
	 * 과제 문제은행 문제 정보 삭제
	 *
	 * @return  ProcessResultVO 
	 */
	public abstract	ProcessResultVO<AssignmentQbankQuestionVO> deleteQuestion(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception;

	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	public abstract ProcessResultVO<AssignmentQbankQuestionVO> getArticle(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception;
	
	
	/**
	 * 과제 문제은행 첨부파일 목ㄹ
	 *
	 * @return  ProcessResultVO
	 */

	public abstract	ProcessResultVO<AssignmentQbankQuestionVO> listAttachFiles(
			AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception;


}