package egovframework.edutrack.modules.lecture.assignment.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("assignmentMapper")
public interface AssignmentMapper{

	/**
	 * 과제 리스트 조회
	 */
	
	public List<AssignmentVO> list(AssignmentVO vo)  ;

	/**
	 * 과제 정보 조회
	 */
	public AssignmentVO select(AssignmentVO vo) ;
	/**
	 * 과제 번호 조회
	 */
	public AssignmentVO selectKey() ;

	/**
	 * 과제 정보 등록
	 */
	public int insert(AssignmentVO vo) ;

	/**
	 * 과제 정보 수정
	 */
	public int update(AssignmentVO vo) ;

	/**
	 * 과제 삭제
	 */
	public int delete(AssignmentVO vo) ;

	/**
	 * 과제 목록 조회 학습자 전송 정보 포함
	 */
	
	public List<AssignmentVO> listStd(AssignmentVO vo) ;
	
	/**
	 * 과제 관련 정보 현황
	 * @param vo
	 * @return
	 */
	public EgovMap selectAsmtStatus(AssignmentVO vo) ;
	
	public List<AssignmentVO> listByStdNo(AssignmentVO vo);

	/**
	 * 코딩 과제 목록 조회 학습자 전송 정보 포함
	 */
	
	public List<AssignmentVO> listCodeStd(AssignmentVO vo) ;
	
}