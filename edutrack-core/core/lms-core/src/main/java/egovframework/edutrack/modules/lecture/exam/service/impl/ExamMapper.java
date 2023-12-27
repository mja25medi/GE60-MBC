package egovframework.edutrack.modules.lecture.exam.service.impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;



@Mapper("examMapper")
public interface ExamMapper {

	/**
	 * 시험의 목록을 반환한다.
	 * 등록여부에 따라 시험 목록 반환
	 * @param vo.regYn (등록여부)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamVO> list(ExamVO vo ) throws Exception;

	/**
	 * 시험 정보 단일항목 반환.
	 * @param vo
	 * @return
	 */
	public ExamVO selectExam(ExamVO vo )  throws Exception;

	/**
	 * 학습자의 목록을 반환한다.
	 * 시험응시 정보 포함(응시횟수, 취득점수)
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamVO> listExamStd(ExamVO vo ) throws Exception;

	/**
	 * 시험의 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertExam(ExamVO vo)  throws Exception;

	/**
	 * 시험의 정보를 수정하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int updateExam(ExamVO vo)  throws Exception;

	/**
	 * 시험 정보를 삭제하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int deleteExam(ExamVO vo )  throws Exception;
	
	/**
	 * 시험 고유 번호를 검색하여 반환한다.
	 * @return
	 */
	public ExamVO selectExamSn()  throws Exception;
	
	/**
	 * 시험 관련 정보 현황
	 * @param vo
	 * @return
	 */
	public EgovMap selectExamStatus(ExamVO vo) throws Exception;
	
	List<EgovMap> selectExamEvalType(String crsCreCd);
	
	public List<ExamVO> listByStdNo(ExamVO vo);
}