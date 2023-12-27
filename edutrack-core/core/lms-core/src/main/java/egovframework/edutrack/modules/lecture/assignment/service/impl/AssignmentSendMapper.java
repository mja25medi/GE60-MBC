package egovframework.edutrack.modules.lecture.assignment.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentSendMapper")
public interface AssignmentSendMapper{

	/**
	 * 과제 제출자 목록 전체
	 */
	@SuppressWarnings("unchecked")
	public List<AssignmentSendVO> list(AssignmentVO vo );

	/**
	 * 과제 제출자 목록 (페이징)
	 */
	@SuppressWarnings("unchecked")
	public List<AssignmentSendVO> listPageing(AssignmentVO vo) throws Exception;

	/**
	 * 과제 제출자 목록수 반환
	 */
	@SuppressWarnings("unchecked")
	public int count(AssignmentVO vo) throws Exception;

	
	/**
	 * 학습자 과제 제출 정보 조회
	 */
	public AssignmentSendVO select(AssignmentSendVO vo ) throws Exception;
	
	/**
	 * 과제 제출 등록
	 */
	public int insert(AssignmentSendVO vo) throws Exception;
	
	/**
	 * 과제 제출 등록 (상세)
	 */
	public int insertDetail(AssignmentSendVO vo) throws Exception;

	/**
	 * 과제 제출 정보 수정
	 */
	public int update(AssignmentSendVO vo) throws Exception;

	/**
	 * 과제 제출 정보 삭제(all)
	 */
	public int deleteAll(AssignmentVO vo)  throws Exception;

	/**
	 * 과제 제출 정보 삭제
	 */
	public int delete(AssignmentSendVO vo) ;
	
	public int deleteDtl(AssignmentSendVO vo) ;

	public int send(AssignmentSendVO vo);
	
	public int sendDetail(AssignmentSendVO vo);
	
	public int rate(AssignmentSendVO vo);

	/**
	 * 코딩 과제 form 조회
	 */
	public AssignmentSendVO selectTemp(AssignmentSendVO iAssignmentSendVO);
	/**
	 * 코딩 과제 채점 결과 조회
	 */
	public AssignmentSendVO selectTestResult(AssignmentSendVO vo);
}
