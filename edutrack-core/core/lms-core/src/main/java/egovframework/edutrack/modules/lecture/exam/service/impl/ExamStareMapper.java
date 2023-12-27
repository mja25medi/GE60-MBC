package egovframework.edutrack.modules.lecture.exam.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("examStareMapper")
public interface ExamStareMapper {

	/**
	 * 학습자의 목록을 조회하여 반환한다.
	 * 시험응시 정보 및 결과 포함
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamStareVO> list(ExamVO vo ) throws Exception;

	/**
	 * 학습자의 페이징 목록을 조회하여 반환한다.
	 * 시험응시 정보 및 결과 포함
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamStareVO> listPageing(ExamVO vo ) throws Exception;

	/**
	 * 학습자의 페이징 목록수 반환
	 * 시험응시 정보 및 결과 포함
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(ExamVO vo ) throws Exception;
	
	/**
	 * 시험응시의 단일항목을 조회하여 반환한다.
	 * @param vo
	 * @return
	 */
	public ExamStareVO select(ExamStareVO vo) throws Exception;
	
	/**
	 * 시험 모사 정보를 조회하여 반환한다.
	 * @param vo
	 * @return
	 */
	public ExamStareVO selectCopyRatio(ExamStareVO vo) throws Exception;

	/**
	 * 시험응시 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int insert(ExamStareVO vo) throws Exception;
	
	/**
	 * 시험응시 정보릐 단일 항목을 반환한다.
	 * @param vo
	 * @return
	 */
	public int update(ExamStareVO vo) ;

	/**
	 * 해당 시험의 모든 응시 정보를 삭제한다.
	 * @param iExamVO
	 * @return
	 */
	public int deleteAll(ExamVO iExamVO) throws Exception;

	/**
	 * 시험응시 정보 단일 항목을 삭제한다.
	 * @param vo
	 * @return
	 */
	public int delete(ExamStareVO vo) ;

	/**
	 * 시험응시 고유번호를 검색한다.
	 * @return
	 */
	public ExamStareVO selectStareExamSn() throws Exception;

	/**
	 * 시험문제 정보에 등록되어 있는 시험의 고유번호를 목록으로 반환한다.
	 * @param vo
	 * @return
	 * @throws DataAccessException
	 */
	public List<ExamStareVO> listExamNum(final ExamStareVO vo) throws DataAccessException;
	
	public List<ExamStareVO> listExamRandomTypeNum(final ExamStareVO vo) throws DataAccessException;

	/**
	 * 시험문제에 등록되어 있는 시험중 하나를 렌덤으로 가져온다.
	 * @param vo
	 * @return
	 */
	public ExamStareVO getStare(ExamStareVO vo)  throws Exception;

	/**
	 * 학습자의 시험응시를 저장한다.
	 * @param vo
	 * @return
	 */
	public int addPaper(ExamStareVO vo)  throws Exception;

	/**
	 * 시험 응시 정보 조회
	 */
	public ExamStareVO selectExamQstnNo(ExamStareVO vo)  throws Exception;

	public int addPaperSubmit(ExamStareVO vo)  throws Exception;

	/**
	 * 시험 평가 완료
	 */
	public int updateExamComplete(ExamStareVO vo)  throws Exception;

	/**
	 * 시험 평가 정보를 삭제한다.
	 * @param examStareVO
	 * @return
	 */
	public int deleteMulti(ExamStareVO vo)  throws Exception;
}
