package egovframework.edutrack.modules.lecture.participate.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.participate.service.ParticipateVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("participateMapper")
public interface ParticipateMapper {

	/**
	 * 학습참여현황 목록 조회
	 */
	@SuppressWarnings("unchecked")
	public List<ParticipateVO> listPageing(ParticipateVO vo) throws Exception;
	
	/**
	 * 학습참여현황 목록수 반환
	 */
	@SuppressWarnings("unchecked")
	public int count(ParticipateVO vo) throws Exception;
	

	/**
	 * 점수저장
	 */
	public int insertScore(ParticipateVO vo) throws Exception;

	/**
	 * 점수저장
	 */
	public int updateScore(ParticipateVO vo) throws Exception;
}
