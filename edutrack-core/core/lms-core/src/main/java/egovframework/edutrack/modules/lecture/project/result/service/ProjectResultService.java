package egovframework.edutrack.modules.lecture.project.result.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;

public interface ProjectResultService {

	/**
	 * 평가 관리 목록 조회
	 */
	public ProcessResultListVO<ProjectResultVO> list(ProjectResultVO vo) throws Exception;
	
	/**
	 * 팀 점수저장
	 */
	public ProcessResultVO<ProjectResultVO> editScoreAll(ProjectResultVO vo, String strTeamScore, String strPrjtTeamSn) throws Exception;
	
	/**
	 *  팀원 보기
	 */
	public ProcessResultVO<ProjectResultVO> view(ProjectResultVO vo) throws Exception;

	
	/**
	 * 팀원 목록
	 */
	public ProcessResultListVO<ProjectResultVO> teamList (ProjectResultVO vo) throws Exception;
	
	/**
	 * 팀 회원 점수저장
	 */
	public ProcessResultVO<ProjectResultVO> editMbScoreAll(ProjectResultVO vo, String strMbrScore, String strPrjtMbrSn) throws Exception;
	
	/**
	 *  과제 정보
	 */
	public ProcessResultVO<ProjectResultVO> siInfoView(ProjectResultVO vo) throws Exception;
	
	/**
	 *  제출 정보
	 */
	public ProcessResultVO<PrjAssignmentSendVO> submitInfoView(PrjAssignmentSendVO vo) throws Exception;
	
	
}
