package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("assignmentQbankQuestionMapper")
public interface AssignmentQbankQuestionMapper {



	/**
	 * 과제 문제은행 문제 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<AssignmentQbankQuestionVO> list(AssignmentQbankQuestionVO questionVO)  ;


	/**
	 * 과제 문제은행 문제 정보 보회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankQuestionVO select(AssignmentQbankQuestionVO assignmentQbankQuestionVO)  ;

	/**
	 * 과제 문제은행 문제 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(AssignmentQbankQuestionVO entity)  ;

	/**
	 * 과제 문제은행 문제 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public AssignmentQbankQuestionVO selectSn()   ;


	/**
	 * 과제 문제은행 문제 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(AssignmentQbankQuestionVO iAssignmentQbankQuestionVO)  ;

	/**
	 * 과제 문제은행 문제 코드 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(AssignmentQbankQuestionVO assignmentQbankQuestionVO)  ;
}
