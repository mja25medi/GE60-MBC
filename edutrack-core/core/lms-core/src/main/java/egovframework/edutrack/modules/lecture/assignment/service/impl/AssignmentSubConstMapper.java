package egovframework.edutrack.modules.lecture.assignment.service.impl;

import java.util.List;

import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubConstVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentSubConstMapper")
public interface AssignmentSubConstMapper{

	/**
	 * 제한 사항 목록 조회
	 */
	
	public List<AssignmentSubConstVO> list(AssignmentSubConstVO vo)  ;

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 제한 사항 등록
	 * @param AssignmentSubConstVO
	 * @return
	 */
	public int insert(AssignmentSubConstVO vo)  ;


	/**
	 * 제한 사항 삭제(all)
	 * @param AssignmentSubConstVO
	 * @return
	 */
	public int deleteAll(AssignmentSubConstVO vo)  ;
}
