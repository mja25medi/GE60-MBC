package egovframework.edutrack.modules.course.assignmentbank.service;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



public interface AssignmentQbankCategoryService {

	/**
	 * 과제 문제은행 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract	ProcessResultListVO<AssignmentQbankCategoryVO> listCategory(String sbjCd) throws Exception;

	/**
	 * 과제 문제은행 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankCategoryVO> viewCategory(String sbjCd,
			String ctgrCd) throws Exception;

	/**
	 * 과제 문제은행 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankCategoryVO> addCategory(
			AssignmentQbankCategoryVO iAssignmentQbankCategoryVO) throws Exception;

	/**
	 * 과제 문제은행 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankCategoryVO> editCategory(
			AssignmentQbankCategoryVO iAssignmentQbankCategoryVO) throws Exception;

	/**
	 * 과제 문제은행 분류 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	public abstract	ProcessResultVO<AssignmentQbankCategoryVO> deleteCategory(String sbjCd,
			String ctgrCd) throws Exception;


}