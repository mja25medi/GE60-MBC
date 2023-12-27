package egovframework.edutrack.modules.lecture.project.assignment.service.impl;

import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("prjAssignmentMapper")
public interface PrjAssignmentMapper {
	

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 팀프로젝트 과제 등록
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int insert(PrjAssignmentVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public PrjAssignmentVO select(PrjAssignmentVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 수정
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int update(PrjAssignmentVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 삭제
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int delete(PrjAssignmentVO vo) throws Exception;

	public int deletePrjAll(PrjAssignmentVO vo) throws Exception;

}