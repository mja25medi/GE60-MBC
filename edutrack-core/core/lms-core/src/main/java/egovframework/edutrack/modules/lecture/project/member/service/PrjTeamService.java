package egovframework.edutrack.modules.lecture.project.member.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface PrjTeamService {

	/**
	 * 팀관리 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 23.
	 * @param vo
	 * @return ProcessResultListVO<PrjTeamVO>
	 */
	public ProcessResultListVO<PrjTeamVO> list(PrjTeamVO vo) throws Exception;

	/**
	 * 팀관리 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> view(PrjTeamVO vo) throws Exception;
	
	/**
	 * 팀관리 정보 등록
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> add(PrjTeamVO vo) throws Exception;

	/**
	 * 팀 자동생성
	 * @author mhShin 
	 * @date 2013. 11. 04.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> addTeamCreate(PrjTeamVO vo) throws Exception;

	/**
	 * 이전 프로젝트 복사
	 * @author mhShin 
	 * @date 2013. 11. 06.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> addProjectTeamCopy(PrjTeamVO vo) throws Exception;

	/**
	 * 팀관리 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> edit(PrjTeamVO vo) throws Exception;


	/**
	 * 팀관리 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<PrjTeamVO>
	 */
	public ProcessResultVO<PrjTeamVO> remove(PrjTeamVO vo) throws Exception;
	

}