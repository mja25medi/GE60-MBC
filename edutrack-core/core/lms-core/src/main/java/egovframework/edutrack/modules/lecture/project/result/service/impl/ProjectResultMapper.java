package egovframework.edutrack.modules.lecture.project.result.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("projectResultMapper")
public interface ProjectResultMapper {

	/**
	 * 평가 관리 목록 조회
	 */
	
	public List<ProjectResultVO> list(ProjectResultVO vo) ;

	/**
	 * 팀 점수저장
	 */
	public int updateScoreAll(ProjectResultVO vo) ;
	
	/**
	 * 팀 점수저장시 회원 점수 수정
	 */
	public int updateMbrScoreAll(ProjectResultVO vo) ;

	/**
	 *  팀원 보기
	 */
	public ProjectResultVO select(ProjectResultVO vo) ;

	/**
	 * 팀원 목록
	 */
	public List<ProjectResultVO> teamList (ProjectResultVO vo) ;
	
	/**
	 * 팀 회원 점수저장
	 */
	public int editMbScoreAll(ProjectResultVO vo) ;
	
	/**
	 *  과제 정보
	 */
	public ProjectResultVO siInfoSelect(ProjectResultVO vo) ;
	
	/**
	 *  제출 정보
	 */
	public PrjAssignmentSendVO submitInfoSelect(PrjAssignmentSendVO vo) ;
	

}
