package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentQbankCategoryMapper")
public interface AssignmentQbankCategoryMapper {

	/**
	 * 과제 문제은행 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<AssignmentQbankCategoryVO> list(String sbjCd)  ;


	/**
	 * 과제 문제은행 분류 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankCategoryVO select(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  ;

	/**
	 * 과제 문제은행 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  ;

	/**
	 * 과제 문제은행 분류 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankCategoryVO selectCd()  ;


	/**
	 * 과제 문제은행 분류 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  ;

	/**
	 * 과제 문제은행 분류 코드 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  ;
}
