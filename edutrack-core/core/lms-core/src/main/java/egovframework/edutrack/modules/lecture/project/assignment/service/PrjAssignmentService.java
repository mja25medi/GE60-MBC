package egovframework.edutrack.modules.lecture.project.assignment.service;

import egovframework.edutrack.comm.service.ProcessResultVO;


public interface PrjAssignmentService {

	/**
	 * 팀프로젝트 과제 등록
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 */
	public ProcessResultVO<PrjAssignmentVO> add(PrjAssignmentVO vo) throws Exception;


	/**
	 * 팀프로젝트 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 */
	public ProcessResultVO<PrjAssignmentVO> view(PrjAssignmentVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 수정
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 */
	public ProcessResultVO<PrjAssignmentVO> edit(PrjAssignmentVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 삭제
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 */
	public ProcessResultVO<PrjAssignmentVO> remove(PrjAssignmentVO vo) throws Exception;
	
	
	/**
	 * 팀프로젝트 과제 제출 등록(학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 */
	public ProcessResultVO<PrjAssignmentSendVO> addSend(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 제출 정보 조회 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 */
	public ProcessResultVO<PrjAssignmentSendVO> viewSend(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 제출 수정(학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 */
	public ProcessResultVO<PrjAssignmentSendVO> editSend(PrjAssignmentSendVO vo) throws Exception;

	/**
	 * 팀프로젝트 과제 제출 삭제(학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 */
	public ProcessResultVO<PrjAssignmentSendVO> removeSend(PrjAssignmentSendVO vo) throws Exception;

}





