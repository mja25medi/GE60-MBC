package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentQbankCategoryMapper")
public interface AssignmentQbankCategoryMapper {

	/**
	 * 과제 문제은행 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<AssignmentQbankCategoryVO> list(String sbjCd)  throws Exception;


	/**
	 * 과제 문제은행 분류 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankCategoryVO select(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  throws Exception;

	/**
	 * 과제 문제은행 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  throws Exception;

	/**
	 * 과제 문제은행 분류 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankCategoryVO selectCd()  throws Exception;


	/**
	 * 과제 문제은행 분류 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  throws Exception;

	/**
	 * 과제 문제은행 분류 코드 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO)  throws Exception;
}
