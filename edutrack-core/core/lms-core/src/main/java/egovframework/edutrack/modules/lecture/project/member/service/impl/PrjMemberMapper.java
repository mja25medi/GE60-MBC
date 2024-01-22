package egovframework.edutrack.modules.lecture.project.member.service.impl;


import java.util.List;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("prjMemberMapper")
public interface PrjMemberMapper {
	

	/**
	 * 팀원 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<PrjMemberVO> list(PrjMemberVO vo) ;

	
	/**
	 * 팀원 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public List<PrjMemberVO> stdList(PrjMemberVO vo) ;

	/**
	 * 총 학습자 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public List<PrjMemberVO> learnerList(PrjMemberVO vo) ;

	/**
	 * 총학습자 수
	 * @param vo
	 * @return ProcessResultVO
	 */
	public PrjMemberVO selectLearnerCnt(PrjMemberVO vo) ;

	/**
	 * 팀원 등록
	 * @param vo
	 * @return 
	 */
	public int insert(PrjMemberVO vo) ;

	/**
	 * 이전 프로젝트 팀원 복사
	 * @param vo
	 * @return 
	 */
	public int insertMember(PrjMemberVO vo) ;

	/**
	 * 팀장 선정 
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int update(PrjMemberVO vo) ;

	/**
	 * 팀원 삭제
	 * @param vo
	 * @return 
	 */
	public int updatePrjMbr(List<PrjMemberVO> teamMbrArray) ;


	/**
	 * 팀원 삭제
	 * @param vo
	 * @return 
	 */
	public int delete(List<PrjMemberVO> teamMbrArray) ;
	
	/**
	 * 팀원 전체 삭제
	 * @param vo
	 * @return 
	 */
	public int deletePrjAll(PrjMemberVO vo) ;

	public int deletePrjTeamAll(PrjMemberVO vo) ;


}