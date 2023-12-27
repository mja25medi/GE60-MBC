package egovframework.edutrack.modules.lecture.participate.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ParticipateService {

	/**
	 * 학습참여현황 목록 조회
	 */
	public abstract ProcessResultListVO<ParticipateVO> listPageing(
			ParticipateVO dto) throws Exception;

	/**
	 * 점수저장
	 */
	public abstract ProcessResultVO<ParticipateVO> addScore(ParticipateVO dto) throws Exception;

	/**
	 * 점수저장(복수)
	 */
	public abstract ProcessResultVO<ParticipateVO> addScoreAll(
			ParticipateVO dto, String strStdNo, String strJoinScore) throws Exception;

}