package egovframework.edutrack.modules.lecture.score.service.impl;

import java.util.List;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("stdScoreMapper")
public interface StdScoreMapper {

	/**
	 * 과정의 학습자별 성적의 목록를 구한다.
	 * 각 모듈별(시험, 과제...) 100점 단위
	 * @param vo
	 * @return
	 */
	
	public abstract List<StdScoreVO> list(StdScoreVO vo) ;

	/**
	 * 학습자의 단위 성적을 구한다.
	 * @param iExamVO
	 * @return
	 */
	public abstract StdScoreVO select(StdScoreVO vo) ;
	
	/**
	 * 과정 학습자의 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 */
	
	public abstract List<EgovMap> listCreStd(StdScoreVO vo) ;
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 */
	
	public abstract List<EgovMap> listEndCreStd(StdScoreVO vo) ;
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다 - 카운트
	 * @param OrgReshVO
	 * @return
	 */
	
	public int myListCount(StdScoreVO vo)  ;
	
	/**
	 * 과정 학습자의 종료 과정,시험,과제 성적 정보를 가져온다
	 * @param vo
	 * @return
	 */
	
	public abstract List<EgovMap> listEndCreStdPageing(StdScoreVO vo) ;

}