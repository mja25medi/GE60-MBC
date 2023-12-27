package egovframework.edutrack.modules.lecture.project.assignment.service.impl;

import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("prjAssignmentSendMapper")
public interface PrjAssignmentSendMapper {
	

	/**
	 * 팀프로젝트 과제 등록
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int insert(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 조회
	 * @param vo
	 * @return ProcessResultVO
	 */
	public PrjAssignmentSendVO select(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 수정
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int update(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 삭제
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int delete(PrjAssignmentSendVO vo) throws Exception;


}