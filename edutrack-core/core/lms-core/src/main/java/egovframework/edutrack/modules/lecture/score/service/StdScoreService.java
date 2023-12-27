package egovframework.edutrack.modules.lecture.score.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface StdScoreService {

	/**
	 * 과정 학습자의 점수를 목록으로 가져온다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<StdScoreVO> list(StdScoreVO vo) throws Exception;

	/**
	 * 학습자의 점수를 단일레코드로 가져온다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StdScoreVO> view(StdScoreVO vo) throws Exception;
	
	/**
	 * 과정 학습자의 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public abstract	ProcessResultListVO<EgovMap> listCreStd(StdScoreVO vo) throws Exception;
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public abstract	ProcessResultListVO<EgovMap> listEndCreStd(StdScoreVO vo) throws Exception;
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다 - 페이징
	 */
	public abstract ProcessResultListVO<EgovMap> listEndCreStdPageing(StdScoreVO VO) throws Exception;

}