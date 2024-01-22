package egovframework.edutrack.modules.lecture.assignment.service.impl;

import org.springframework.stereotype.Repository;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentSubMapper")
public interface AssignmentSubMapper{

	/**
	 * 과제 후보 목록 조회
	 */
	
	public List<AssignmentSubVO> list(AssignmentVO vo)  ;


	/**
	 * 과제 후보 정보 조회
	 * @param iSubAssignmentVO
	 * @return
	 */
	public AssignmentSubVO select(AssignmentSubVO vo)  ;
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 과제 후보 정보 등록
	 * @param iSubAssignmentVO
	 * @return
	 */
	public int insert(AssignmentSubVO vo)  ;

	/**
	 * 과제 후보 정보 수정
	 * @param iSubAssignmentVO
	 * @return
	 */
	public int update(AssignmentSubVO vo)  ;

	/**
	 * 과제 후보 정보 삭제(all)
	 * @param iAssignmentVO
	 * @return
	 */
	public int deleteAll(AssignmentVO vo)  ;
	/**
	 * 과제 후보 정보 삭제
	 * @param iSubAssignmentVO
	 * @return
	 */
	public int delete(AssignmentSubVO vo)  ;

	/**
	 * 과제 후보 목록 조회(제출 여부 포함)
	 */
	public List<AssignmentSubVO> listCodeSub(AssignmentVO avo) ;

	/**
	 * 코딩과제 문제별 점수 저장
	 */
	public int saveAsmtSubScore(AssignmentSendVO vo) ;



}
