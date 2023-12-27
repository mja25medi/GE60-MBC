package egovframework.edutrack.modules.course.exambank.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("examQbankQstnMapper")
public interface ExamQbankQstnMapper {

	/**
	 * 시험 문제은행 문제 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public int selectKey()  throws DataAccessException ;
	/**
	 * 시험 문제은행 문제 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ExamQbankQstnVO> list(ExamQbankQstnVO qstnVO)   throws DataAccessException ;


	/**
	 * 시험 문제은행 문제 정보 보회
	 *
	 * @return ProcessResultVO
	 */
	public ExamQbankQstnVO select(ExamQbankQstnVO VO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 문제 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(ExamQbankQstnVO VO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 문제 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(ExamQbankQstnVO VO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 문제 코드 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(ExamQbankQstnVO VO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 분류의 모든 문제 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(ExamQbankQstnVO VO)  throws DataAccessException ;
}
