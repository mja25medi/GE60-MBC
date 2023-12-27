package egovframework.edutrack.modules.lecture.project.member.service.impl;

import java.util.List;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("prjTeamMapper")
public interface PrjTeamMapper {
	

	/**
	 * 팀관리 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	@SuppressWarnings("unchecked")
	public List<PrjTeamVO> list(PrjTeamVO vo) throws Exception;

	/**
	 * 팀관리 정보 등록
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int insert(PrjTeamVO vo) throws Exception;

	/**
	 * 이전 프로젝트 팀 복사 
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int insertTeam(PrjTeamVO vo) throws Exception;

	/**
	 * 팀관리 정보 수정
	 * @param vo
	 * @return ProcessResultVO
	 */
	public int update(PrjTeamVO vo) throws Exception;

	/**
	 * 팀관리 정보 조회
	 * @param vo
	 * @return ProcessResultVO
	 */
	public PrjTeamVO select(PrjTeamVO vo) throws Exception;
	
	/**
	 * 팀관리 정보 삭제
	 * @param teamArray
	 * @return ProcessResultListVO
	 */
	public int delete(List<PrjTeamVO> teamArray) throws Exception;

	/**
	 * 팀 전체 삭제
	 * @param teamArray
	 * @return ProcessResultListVO
	 */
	public int deleteAll(PrjTeamVO vo) throws Exception;
}