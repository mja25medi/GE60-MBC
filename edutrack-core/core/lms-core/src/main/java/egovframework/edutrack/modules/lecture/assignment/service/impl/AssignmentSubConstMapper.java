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
	@SuppressWarnings("unchecked")
	public List<AssignmentSubConstVO> list(AssignmentSubConstVO vo)  throws Exception;

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;

	/**
	 * 제한 사항 등록
	 * @param AssignmentSubConstVO
	 * @return
	 */
	public int insert(AssignmentSubConstVO vo)  throws Exception;


	/**
	 * 제한 사항 삭제(all)
	 * @param AssignmentSubConstVO
	 * @return
	 */
	public int deleteAll(AssignmentSubConstVO vo)  throws Exception;
}
